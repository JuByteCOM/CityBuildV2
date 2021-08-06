package de.jubyte.citybuild.listener;

import de.jubyte.citybuild.data.MessagesData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * @author Justin_SGD
 * @since 04.08.2021
 */

public class InventoryClickListener implements Listener {

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getType() == InventoryType.PLAYER && event.getInventory().getHolder() != player)  {
            Player targetPlayer = (Player) event.getInventory().getHolder();
            if(targetPlayer != null && targetPlayer.isOnline()) {
                if (player.hasPermission(MessagesData.INVSEE_COMMAND_PERMISSION_ACCESS) && !targetPlayer.hasPermission(MessagesData.INVSEE_COMMAND_PERMISSION_BYPASS)) {
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }

}