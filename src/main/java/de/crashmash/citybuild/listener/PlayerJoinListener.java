package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.head.Head;
import de.crashmash.citybuild.manager.startkick.StartKick;
import de.crashmash.citybuild.storage.HeadSQL;
import de.crashmash.citybuild.storage.StartkickSQL;
import de.crashmash.citybuild.storage.StatusSQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //ToDo: MySQL Einträge
        StatusSQL.createPlayer(player.getUniqueId());
        StartkickSQL.createPlayer(player.getUniqueId());
        HeadSQL.createPlayer(player.getUniqueId());
        //Todo: Map Einträge
        if(StartkickSQL.playerExists(player.getUniqueId())) {
            if(!CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().containsKey(player)) {
                StartKick.createStartKickPlayer(player);
            }
        }
        if(HeadSQL.playerExists(player.getUniqueId())) {
            if(!CityBuildV2.getPlugin().getHEADPLAYER_MAP().containsKey(player)) {
                Head.createHeadPlayer(player);
            }
        }

        //Todo: Status
        if(player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE)) {
            if(StatusSQL.playerExists(player.getUniqueId())) {
                if(StatusSQL.hasStatus(player.getUniqueId())) {
                    player.chat(StatusSQL.getStatus(player.getUniqueId()).replaceAll("&", "§"));
                }
            } else {
                StatusSQL.createPlayer(player.getUniqueId());
            }
        }
    }
}
