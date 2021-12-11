package com.jubyte.citybuild.listener.player;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.checkplot.CheckPlotPlayer;
import com.jubyte.citybuild.manager.glow.GlowPlayer;
import com.jubyte.citybuild.manager.locations.Locations;
import com.jubyte.citybuild.manager.status.StatusPlayer;
import com.jubyte.developerapi.utils.config.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

  private static final MessageHandler messageHandler = CityBuildV2.getPlugin().getMessageHandler();

  @EventHandler
  public void handleJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();

    if (CityBuildV2.getPlugin().getMessagesConfig().getBoolean("Settings.PlayerJoin.Enabled")) {
      event.setJoinMessage(
          messageHandler
              .getPrefixString("Settings.PlayerJoin.Message")
              .replace("[player]", player.getName()));
    } else {
      event.setJoinMessage(null);
    }

    // Todo: Status
    if (player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE)) {
      StatusPlayer statusPlayer =
          CityBuildV2.getPlugin().getStatusCache().getPlayerByUUID(player.getUniqueId());
      if (statusPlayer.hasStatus()) {
        player.chat(statusPlayer.getStatus().replaceAll("&", "§"));
      }
    }

    // Todo: Glow
    if (player.hasPermission(MessagesData.GLOW_COMMAND_PERMISSION_USE)) {
      GlowPlayer glowPlayer =
          CityBuildV2.getPlugin().getGlowCache().getPlayerByUUID(player.getUniqueId());
      if (glowPlayer.isState()) {
        player.setGlowing(true);
      }
    }

    // Todo: SpawnTP
    if (MessagesData.SPAWN_COMMAND_SETTING_SPAWN_ON_JOIN
        && ConfigData.CONFIG_COMMAND_SPAWN_ACTIVE) {
      if (Locations.exitsLocation("Spawn")) {
        if (!player.hasPlayedBefore()) {
          player.teleport(Locations.getLocation("Spawn"));
          if (CityBuildV2.getPlugin()
              .getMessagesConfig()
              .getBoolean("Settings.PlayerFirstJoin.Enabled"))
            Bukkit.getOnlinePlayers()
                .forEach(
                    onlinePlayer ->
                        onlinePlayer.sendMessage(
                            messageHandler
                                .getPrefixString("Settings.PlayerFirstJoin.Message")
                                .replace("[player]", player.getName())));
        }
        player.teleport(Locations.getLocation("Spawn"));
      }
    }

    CheckPlotPlayer checkPlotPlayer =
        CityBuildV2.getPlugin().getCheckPlotCache().getPlayerByUUID(player.getUniqueId());
    checkPlotPlayer.setLastJoin();
  }
}
