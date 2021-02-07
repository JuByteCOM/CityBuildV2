package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.startkick.StartKick;
import de.crashmash.citybuild.storage.StartkickSQL;
import de.crashmash.citybuild.storage.StatusSQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //ToDo: MySQL Einträge
        if (player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE))
            StatusSQL.createPlayer(player.getUniqueId());
        StartkickSQL.createPlayer(player.getUniqueId());
        //Todo: Map Einträge
        if(StartkickSQL.playerExists(player.getUniqueId())) {
            if(!CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().containsKey(player)) {
                StartKick.createStartKickPlayer(player);
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
        //Todo: Player Startkicked
        if(CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().containsKey(player)) {
            if(StartKick.playerStartKicked(player)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                long time = StartKick.getDuration(player) + MessagesData.STARTKICK_COMMAND_SETTING_DURATION*1000L;
                player.kickPlayer(MessagesData.STARTKICK_COMMAND_MESSAGE_PLAYER_BANSCREEN.replace("[date]", simpleDateFormat.format(time))
                        .replace("[time]", simpleTimeFormat.format(time)));
            }
        }
    }
}
