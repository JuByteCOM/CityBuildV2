package de.crashmash.citybuild.storage;

import de.crashmash.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.query.result.QueryResult;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class FoodSQL {

    public static boolean existsFood(int id) {
        return !CityBuildV2.getPlugin().getStorage().getFoodCollection().find().where("ID", id).execute().isEmpty();
    }

    public static void createFood(double locX, double locY, double locZ, float yaw, float pitch, String world) {
        CityBuildV2.getPlugin().getStorage().getFoodCollection().insert()
                .set("LocX", locX)
                .set("LocY", locY)
                .set("LocZ", locZ)
                .set("Yaw", yaw)
                .set("Pitch", pitch)
                .set("World", world)
                .executeAsync();
    }

    public static void deleteFood(int id) {
        if(existsFood(id)) {
            CityBuildV2.getPlugin().getStorage().getFoodCollection().delete()
                    .where("ID", id).executeAsync();
        }
    }

    public static Map<Integer, Location> loadFood() {
        Map<Integer, Location> locations = new HashMap<>();
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getFoodCollection().find().execute();
        for(QueryResultEntry entry : queryResult) {
            Location location = new Location(Bukkit.getWorld(entry.getString("World")),
                    entry.getDouble("LocX"),
                    entry.getDouble("LocY"),
                    entry.getDouble("LocZ"),
                    entry.getFloat("Yaw"),
                    entry.getFloat("Pitch"));
            locations.put(entry.getInt("ID"),location);
        }
        return locations;
    }
}
