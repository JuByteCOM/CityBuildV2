package de.crashmash.citybuild.manager.food;

import org.bukkit.Location;

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
}
