package de.crashmash.citybuild.storage;

import de.crashmash.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.query.result.QueryResult;

import java.util.UUID;

public class StatusSQL {

    public static boolean playerExists(UUID uuid) {
        return !CityBuildV2.getPlugin().getStorage().getStatusCollection().find().where("UUID", uuid).execute().isEmpty();
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExists(uuid)) {
            CityBuildV2.getPlugin().getStorage().getStatusCollection().insert()
                    .set("UUID", uuid)
                    .set("Status", (Object) null)
                    .executeAsync();
        }
    }

    public static boolean hasStatus(UUID uuid) {
        return getStatus(uuid) != null;
    }

    public static String getStatus(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getStatusCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            return queryResult.first().getString("Status");
        }
        return null;
    }

    public static void setStatus(UUID uuid, String status) {
        CityBuildV2.getPlugin().getStorage().getStatusCollection().update()
                .set("Status", status)
                .where("UUID", uuid).executeAsync();
    }
}
