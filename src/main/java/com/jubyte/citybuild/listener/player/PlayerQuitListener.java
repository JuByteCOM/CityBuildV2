package com.jubyte.citybuild.listener.player;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.commands.GodCommand;
import com.jubyte.citybuild.commands.VanishCommand;
import com.jubyte.developerapi.utils.config.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.Map;

public class PlayerQuitListener implements Listener {

  private final List<Player> slowchatPlayer = AsyncPlayerChatListener.SLOW_CHAT_PLAYER;
  private static final MessageHandler messageHandler = CityBuildV2.getPlugin().getMessageHandler();

  @EventHandler
  public void handlePlayerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    slowchatPlayer.remove(player);

    // Bugfix: these collections stored raw Player references and were never cleaned up on
    // logout, causing a memory leak (stale Player objects piling up for the lifetime of the
    // server) and stale god-mode/commandspy state if the same player rejoined.
    GodCommand.playerGodList.remove(player);
    Map<Player, Player> commandSpyMap = CityBuildV2.getPlugin().getCOMMANDSPY_MAP();
    commandSpyMap.remove(player);
    commandSpyMap.values().removeIf(target -> target != null && target.equals(player));
    VanishCommand.clearVanish(player.getUniqueId());

    if (CityBuildV2.getPlugin().getMessagesConfig().getBoolean("Settings.PlayerQuit.Enabled")) {
      event.setQuitMessage(
          messageHandler
              .getPrefixString("Settings.PlayerQuit.Message")
              .replace("[player]", player.getName()));
    } else {
      event.setQuitMessage(null);
    }
  }
}
