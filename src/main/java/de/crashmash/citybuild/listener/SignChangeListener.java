package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    @EventHandler
    public void handleSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_COLOR)) {
            String[] lines = event.getLines();
            for (int i = 0; i <= 3; i++) {
                event.setLine(i, ChatColor.translateAlternateColorCodes('&', lines[i]));
            }
        }
    }

}
