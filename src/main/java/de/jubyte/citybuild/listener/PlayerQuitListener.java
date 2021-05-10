package de.jubyte.citybuild.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayerQuitListener implements Listener {

    private final List<Player> slowchatPlayer = AsynPlayerChatListener.SLOWCHAT_PLAYER;

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        slowchatPlayer.remove(player);
    }

}
