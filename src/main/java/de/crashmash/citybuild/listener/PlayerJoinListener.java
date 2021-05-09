package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.glow.GlowPlayer;
import de.crashmash.citybuild.manager.locations.Locations;
import de.crashmash.citybuild.manager.status.StatusPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //Todo: Cache
        CityBuildV2.getPLUGIN().getCooldownCache().getPlayerByUUID(player.getUniqueId());
        CityBuildV2.getPLUGIN().getMutePCache().getPlayerByUUID(player.getUniqueId());

        //Todo: Status
        if(player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE)) {
            StatusPlayer statusPlayer = CityBuildV2.getPLUGIN().getStatusCache().getPlayerByUUID(player.getUniqueId());
            if(statusPlayer.hasStatus()) {
                player.chat(statusPlayer.getStatus().replaceAll("&", "§"));
            }
        }

        //Todo: Glow
        if(player.hasPermission(MessagesData.GLOW_COMMAND_PERMISSION_USE)) {
            GlowPlayer glowPlayer = CityBuildV2.getPLUGIN().getGlowCache().getPlayerByUUID(player.getUniqueId());
            if(glowPlayer.isState()) {
                player.setGlowing(true);
            }
        }

        //Todo: SpawnTP
        if(MessagesData.SPAWN_COMMAND_SETTING_SPAWN_ON_JOIN) {
            if (Locations.exitsLocation("Spawn")) {
                player.teleport(Locations.getLocation("Spawn"));
            }
        }
    }
}
