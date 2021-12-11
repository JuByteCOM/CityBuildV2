package com.jubyte.citybuild.storage;

import com.jubyte.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.query.result.QueryResult;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class LocationSQL {

  public static boolean existsLocation(String name) {
    return !CityBuildV2.getPlugin()
        .getStorage()
        .getLocationCollection()
        .find()
        .where("Name", name)
        .execute()
        .isEmpty();
  }

  public static void createLocation(
      String name, double locX, double locY, double locZ, float yaw, float pitch, String world) {
    if (!existsLocation(name)) {
      CityBuildV2.getPlugin()
          .getStorage()
          .getLocationCollection()
          .insert()
          .set("Name", name)
          .set("LocX", locX)
          .set("LocY", locY)
          .set("LocZ", locZ)
          .set("Yaw", yaw)
          .set("Pitch", pitch)
          .set("World", world)
          .executeAsync();
    } else {
      CityBuildV2.getPlugin()
          .getStorage()
          .getLocationCollection()
          .update()
          .set("LocX", locX)
          .set("LocY", locY)
          .set("LocZ", locZ)
          .set("Yaw", yaw)
          .set("Pitch", pitch)
          .set("World", world)
          .where("Name", name)
          .executeAsync();
    }
  }

  public static void deleteLoc(String name) {
    if (existsLocation(name)) {
      CityBuildV2.getPlugin()
          .getStorage()
          .getLocationCollection()
          .delete()
          .where("Name", name)
          .executeAsync();
    }
  }

  public static Map<String, Location> loadLocations() {
    Map<String, Location> locations = new HashMap<>();
    QueryResult result =
        CityBuildV2.getPlugin().getStorage().getLocationCollection().find().execute();
    for (QueryResultEntry entry : result) {
      Location location =
          new Location(
              Bukkit.getWorld(entry.getString("World")),
              entry.getDouble("LocX"),
              entry.getDouble("LocY"),
              entry.getDouble("LocZ"),
              entry.getFloat("Yaw"),
              entry.getFloat("Pitch"));
      locations.put(entry.getString("Name"), location);
    }
    return locations;
  }
}
