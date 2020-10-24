package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.storage.StatusSQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //ToDo: MySQL Einträge
        if(player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE))
            StatusSQL.createPlayer(player.getUniqueId());

        //Todo: Status
        if(player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE)) {
            if(StatusSQL.playerExists(player.getUniqueId())) {
                if(StatusSQL.hasStatus(player.getUniqueId())) {
                    player.chat(Objects.requireNonNull(StatusSQL.getStatus(player.getUniqueId())));
                }
            } else {
                StatusSQL.createPlayer(player.getUniqueId());
            }
        }
    }
}
