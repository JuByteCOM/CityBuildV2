package de.crashmash.citybuild.listener;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.food.FoodLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void handleEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if(entity.getType() == EntityType.PIG && Objects.requireNonNull(entity.getCustomName()).equalsIgnoreCase(MessagesData.FOOD_COMMAND_MESSAGE_NAME)) {
            for (int iD : FoodLocation.getLocationNames()) {
                Location foodLocation = FoodLocation.getLocation(iD);
                Location entityLocation = entity.getLocation();
                if(foodLocation.equals(entityLocation)) {
                    event.setDroppedExp(MessagesData.FOOD_COMMAND_SETTINGS_EXP);
                    for (ItemStack drop : event.getDrops()) {
                        drop.setType(Material.PORKCHOP);
                        Objects.requireNonNull(event.getEntity().getLocation().getWorld()).dropItemNaturally(entity.getLocation(), drop);
                    }
                    Bukkit.getServer().getScheduler().runTaskLater(CityBuildV2.getPlugin(), () -> {
                        LivingEntity livingEntity = entity.getWorld().spawn(entity.getLocation(), Pig.class);
                        livingEntity.setCustomName(MessagesData.FOOD_COMMAND_MESSAGE_NAME);
                        livingEntity.setAI(false);
                        livingEntity.setHealth(MessagesData.FOOD_COMMAND_SETTINGS_HEALTH);
                    }, MessagesData.FOOD_COMMAND_SETTINGS_RESPAWNTIME);
                }
            }
        }
    }
}
