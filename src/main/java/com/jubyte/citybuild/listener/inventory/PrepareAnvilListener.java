package com.jubyte.citybuild.listener.inventory;

import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.developerapi.utils.color.ColorAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Justin_SGD
 * @since 09.08.2021
 */
public class PrepareAnvilListener implements Listener {

  @EventHandler
  public void handlePrepareAnvil(PrepareAnvilEvent event) {
    ItemStack itemStack = event.getResult();
    Player player = (Player) event.getView().getPlayer();
    if (player.hasPermission(MessagesData.SETTINGS_PERMISSION_COLORED_ANVIL)) {
      if (itemStack != null && itemStack.getItemMeta() != null) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ColorAPI.process(itemStack.getItemMeta().getDisplayName()));
        itemStack.setItemMeta(itemMeta);
        event.setResult(itemStack);
      }
    }
  }
}
