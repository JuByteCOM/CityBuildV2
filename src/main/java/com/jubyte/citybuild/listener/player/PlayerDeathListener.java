package com.jubyte.citybuild.listener.player;

import com.google.common.collect.Maps;
import com.jubyte.citybuild.CityBuildV2;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

/**
 * @author KeinByte
 * @since 03.03.2022
 */

public class PlayerDeathListener implements Listener {

    public static final HashMap<Player, Location> BACK_LOCATION = Maps.newHashMap();

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent event){
        if (CityBuildV2.getPlugin().getMessagesConfig().getBoolean("Commands.Back.SaveBackLocationByDeath.Enabled")){
            BACK_LOCATION.put(event.getEntity(), event.getEntity().getLocation());
        }
    }

}
