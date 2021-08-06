package de.jubyte.citybuild.listener;

import com.jubyte.developerapi.utils.MessageHandler;
import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.manager.checkplot.CheckPlotPlayer;
import de.jubyte.citybuild.manager.glow.GlowPlayer;
import de.jubyte.citybuild.manager.locations.Locations;
import de.jubyte.citybuild.manager.status.StatusPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final static MessageHandler messageHandler = CityBuildV2.getPLUGIN().getMessageHandler();

    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //Todo: Status
        if (player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE)) {
            StatusPlayer statusPlayer = CityBuildV2.getPLUGIN().getStatusCache().getPlayerByUUID(player.getUniqueId());
            if (statusPlayer.hasStatus()) {
                player.chat(statusPlayer.getStatus().replaceAll("&", "§"));
            }
        }

        //Todo: Glow
        if (player.hasPermission(MessagesData.GLOW_COMMAND_PERMISSION_USE)) {
            GlowPlayer glowPlayer = CityBuildV2.getPLUGIN().getGlowCache().getPlayerByUUID(player.getUniqueId());
            if (glowPlayer.isState()) {
                player.setGlowing(true);
            }
        }

        //Todo: SpawnTP
        if (MessagesData.SPAWN_COMMAND_SETTING_SPAWN_ON_JOIN && ConfigData.CONFIG_COMMAND_SPAWN_ACTIVE) {
            if (Locations.exitsLocation("Spawn")) {
                player.teleport(Locations.getLocation("Spawn"));
            }
        }

        CheckPlotPlayer checkPlotPlayer = CityBuildV2.getPLUGIN().getCheckPlotCache().getPlayerByUUID(player.getUniqueId());
        checkPlotPlayer.setLastJoin();

        if(CityBuildV2.getPLUGIN().getConfig().getBoolean("Settings.PlayerJoin.Enabled")) {
            event.setJoinMessage(messageHandler.getPrefixString("Settings.PlayerJoin.Message").replace("[player]", player.getName()));
        } else {
            event.setJoinMessage(null);
        }
    }
}
