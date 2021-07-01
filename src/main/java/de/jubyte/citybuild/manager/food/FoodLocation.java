package de.jubyte.citybuild.manager.food;

import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.storage.FoodSQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.Collection;
import java.util.Map;

public class FoodLocation {

    public static Map<Integer, Location> FOOD_LOCATIONS;

    public static Location getLocation(int id) {
        return FOOD_LOCATIONS.get(id);
    }

    public static Collection<Integer> getLocationNames() {
        return FOOD_LOCATIONS.keySet();
    }

    public static void setLocations(Map<Integer, Location> locations) {
        FOOD_LOCATIONS = locations;
    }

    public static boolean exitsLocation(int id) {
        return getLocation(id) != null;
    }

    public static World getWorldById(int id){
        return getLocation(id).getWorld();
    }

    public static boolean removeFoodFromDeletedWorld(World world, int id){

        if (getWorldById(id) == world){

            for (Entity entity : world.getNearbyEntities(getLocation(id), 1, 1, 1)) {
                if (entity.getType() == EntityType.PIG && entity.getCustomName().equalsIgnoreCase(MessagesData.FOOD_COMMAND_MESSAGE_NAME)) {
                    entity.remove();
                }
            }

            FOOD_LOCATIONS.remove(id);
            FoodSQL.deleteFood(id);
        }
    return true;
    }

}
