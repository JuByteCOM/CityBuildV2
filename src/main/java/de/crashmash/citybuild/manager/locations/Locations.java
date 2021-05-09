package de.crashmash.citybuild.manager.locations;

import org.bukkit.Location;

import java.util.Collection;
import java.util.Map;

public class Locations {

    public static Map<String, Location> LOCATIONS;

    public static Location getLocation(String name) {
        return LOCATIONS.get(name);
    }

    public static Collection<String> getLocationNames() {
        return LOCATIONS.keySet();
    }

    public static void setLocations(Map<String, Location> locations) {
        LOCATIONS = locations;
    }

    public static boolean exitsLocation(String name) {
        return getLocation(name) != null;
    }

}