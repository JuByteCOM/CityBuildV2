package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {

    @EventHandler
    public void handlePlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission(MessagesData.COMMANDSPY_COMMAND_PERMISSION_USE)) {
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(players.hasPermission(MessagesData.COMMANDSPY_COMMAND_PERMISSION_BYPASS))
                    players.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_COMMAND.replace("[targetPlayer]", player.getName())
                    .replace("[command]", event.getMessage()));
            }
        }
    }

}
