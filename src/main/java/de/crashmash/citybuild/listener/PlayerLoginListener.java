package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.startkick.StartKick;
import de.crashmash.citybuild.storage.StartkickSQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.text.SimpleDateFormat;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void handlePlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        //Todo: Map Einträge
        if(StartkickSQL.playerExists(player.getUniqueId())) {
            if(!CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().containsKey(player)) {
                StartKick.createStartKickPlayer(player);
            }
        }
        //Todo: Startkick Check
        if(CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().containsKey(player)) {
            if(StartKick.playerStartKicked(player)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                long time = StartKick.getDuration(player) + MessagesData.STARTKICK_COMMAND_SETTING_DURATION*1000L;

                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, MessagesData.STARTKICK_COMMAND_MESSAGE_PLAYER_BANSCREEN.replace("[date]", simpleDateFormat.format(time))
                        .replace("[time]", simpleTimeFormat.format(time)));
            }
        }
    }
}
