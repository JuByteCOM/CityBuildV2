package com.jubyte.citybuild.listener.player;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.developerapi.utils.config.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayerQuitListener implements Listener {

  private final List<Player> slowchatPlayer = AsyncPlayerChatListener.SLOW_CHAT_PLAYER;
  private static final MessageHandler messageHandler = CityBuildV2.getPlugin().getMessageHandler();

  @EventHandler
  public void handlePlayerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    slowchatPlayer.remove(player);

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
