package com.jubyte.citybuild.listener;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.commands.SlowChatCommand;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.mutep.MutepPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class AsynPlayerChatListener implements Listener {

  public static final List<Player> SLOWCHAT_PLAYER = new ArrayList<>();

  @EventHandler
  public void handleAsyncPlayerChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    // event.setMessage(ColoredChat.format(event.getMessage(), player));

    if (SlowChatCommand.SLOWCHAT_STATUS) {
      if (SLOWCHAT_PLAYER.contains(player)) {
        player.sendMessage(MessagesData.SLOWCHAT_COMMAND_MESSAGE_CHATTET_TO_FAST);
        event.setCancelled(true);
      }
    }
    if (SlowChatCommand.SLOWCHAT_STATUS) {
      if (!player.hasPermission(MessagesData.SLOWCHAT_COMMAND_PERMISSION_BYPASS)) {
        System.out.println("Keine Rechte");
        if (!SLOWCHAT_PLAYER.contains(player)) {
          SLOWCHAT_PLAYER.add(player);
          Bukkit.getScheduler()
              .runTaskLater(
                  CityBuildV2.getPLUGIN(),
                  () -> SLOWCHAT_PLAYER.remove(player),
                  MessagesData.SLOWCHAT_COMMAND_SETTINGS_CHAT_COOLDOWN * 20);
        }
      }
    }
    MutepPlayer mutepPlayer =
        CityBuildV2.getPLUGIN().getMutePCache().getPlayerByUUID(player.getUniqueId());
    if (mutepPlayer.playerIsMutep()) {
      Player creator = Bukkit.getPlayer(mutepPlayer.getCreator());
      if (creator != null) {
        player.sendMessage(
            MessagesData.MUTEP_COMMAND_MESSAGE_MUTE_SCREEN
                .replace("[player]", creator.getName())
                .replace("[reason]", mutepPlayer.getReason()));
      } else {
        String offlineCreator = Bukkit.getOfflinePlayer(mutepPlayer.getCreator()).getName();
        assert offlineCreator != null;
        player.sendMessage(
            MessagesData.MUTEP_COMMAND_MESSAGE_MUTE_SCREEN
                .replace("[player]", offlineCreator)
                .replace("[reason]", mutepPlayer.getReason()));
      }
      event.setCancelled(true);
    }
  }
}
