package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.startkick.StartKickPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.text.SimpleDateFormat;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void handlePlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        //Todo: Cache
        StartKickPlayer startKickPlayer = CityBuildV2.getPLUGIN().getStartKickCache().getPlayerByUUID(player.getUniqueId());
        //Todo: Startkick Check
        if(startKickPlayer.isStartKicked()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
            long time = startKickPlayer.getDuration();

            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, MessagesData.STARTKICK_COMMAND_MESSAGE_PLAYER_BANSCREEN.replace("[date]", simpleDateFormat.format(time))
                    .replace("[time]", simpleTimeFormat.format(time)));
        }
    }
}
