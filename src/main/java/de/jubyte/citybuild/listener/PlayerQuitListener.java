package de.jubyte.citybuild.listener;

import com.jubyte.developerapi.utils.MessageHandler;
import de.jubyte.citybuild.CityBuildV2;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayerQuitListener implements Listener {

    private final List<Player> slowchatPlayer = AsynPlayerChatListener.SLOWCHAT_PLAYER;
    private final static MessageHandler messageHandler = CityBuildV2.getPLUGIN().getMessageHandler();

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        slowchatPlayer.remove(player);

        if(CityBuildV2.getPLUGIN().getConfig().getBoolean("Settings.PlayerQuit.Enabled")) {
            event.setQuitMessage(messageHandler.getPrefixString("Settings.PlayerQuit.Message").replace("[player]", player.getName()));
        } else {
            event.setQuitMessage(null);
        }
    }

}
