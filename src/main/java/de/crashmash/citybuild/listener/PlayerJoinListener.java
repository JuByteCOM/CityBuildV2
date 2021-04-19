package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.cooldown.Cooldown;
import de.crashmash.citybuild.manager.mutep.MuteP;
import de.crashmash.citybuild.manager.startkick.StartKick;
import de.crashmash.citybuild.storage.*;
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
        StatusSQL.createPlayer(player.getUniqueId());
        StartkickSQL.createPlayer(player.getUniqueId());
        CooldownSQL.createPlayer(player.getUniqueId());
        MutepSQL.createPlayer(player.getUniqueId());
        //Todo: Map Einträge
        if(StartkickSQL.playerExists(player.getUniqueId())) {
            if(!CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().containsKey(player)) {
                StartKick.createStartKickPlayer(player);
            }
        }
        if(CooldownSQL.playerExists(player.getUniqueId())) {
            if(!CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().containsKey(player)) {
                Cooldown.createCooldownPlayer(player);
            }
        }
        if(MutepSQL.playerExists(player.getUniqueId())) {
            if(!(CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().containsKey(player))) {
                MuteP.createMutePPlayer(player);
            }
        }

        //Todo: Status
        if(player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE)) {
            if(StatusSQL.playerExists(player.getUniqueId())) {
                if(StatusSQL.hasStatus(player.getUniqueId())) {
                    player.chat(Objects.requireNonNull(StatusSQL.getStatus(player.getUniqueId())).replaceAll("&", "§"));
                }
            } else {
                StatusSQL.createPlayer(player.getUniqueId());
            }
        }

        //Todo: Glow
        if(player.hasPermission(MessagesData.GLOW_COMMAND_PERMISSION_USE)) {
            if(GlowSQL.playerExists(player.getUniqueId())) {
                if(GlowSQL.getGlowEffect(player.getUniqueId())) {
                    player.setGlowing(true);
                }
            } else {
                GlowSQL.createPlayer(player.getUniqueId());
            }
        }
    }
}
