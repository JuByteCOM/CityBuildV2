package de.crashmash.citybuild.storage;

import de.crashmash.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.query.result.QueryResult;

import java.util.UUID;

public class MutepSQL {

    public static boolean playerExists(UUID uuid) {
        return !CityBuildV2.getPlugin().getStorage().getMutePCollection().find().where("UUID", uuid).execute().isEmpty();
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExists(uuid)) {
            CityBuildV2.getPlugin().getStorage().getMutePCollection().insert()
                    .set("UUID", uuid)
                    .set("Reason", (Object) null)
                    .set("Duration", 0)
                    .set("Cooldown", 0)
                    .set("Creator", (Object) null)
                    .executeAsync();
        }
    }

    public static String getReason(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getMutePCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            return queryResult.first().getString("Reason");
        }
        return null;
    }

    public static long getDuration(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getMutePCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            return queryResult.first().getLong("Duration");
        }
        return 0;
    }

    public static long getCooldown(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getMutePCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            return queryResult.first().getLong("Cooldown");
        }
        return 0;
    }

    public static UUID getCreator(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getMutePCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            return queryResult.first().getUniqueId("Creator");
        }
        return null;
    }

    public static void setMuteP(UUID uuid, String reason, long duration, UUID creator) {
        CityBuildV2.getPlugin().getStorage().getMutePCollection().update()
                .set("Reason", reason)
                .set("Duration", duration)
                .set("Creator", creator)
                .where("UUID", uuid).executeAsync();
    }

    public static void setCooldown(UUID uuid, Long cooldown) {
        CityBuildV2.getPlugin().getStorage().getMutePCollection().update()
                .set("Cooldown", cooldown)
                .where("UUID", uuid).executeAsync();
    }

}
