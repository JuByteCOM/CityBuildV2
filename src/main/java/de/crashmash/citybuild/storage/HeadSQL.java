package de.crashmash.citybuild.storage;

import de.crashmash.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.query.result.QueryResult;

import java.util.UUID;

public class HeadSQL {

    public static boolean playerExists(UUID uuid) {
        return !CityBuildV2.getPlugin().getStorage().getHeadColloction().find().where("UUID", uuid).execute().isEmpty();
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExists(uuid)) {
            CityBuildV2.getPlugin().getStorage().getHeadColloction().insert()
                    .set("UUID", uuid)
                    .set("Cooldown", 0)
                    .executeAsync();
        }
    }

    public static long getHeadCooldown(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getHeadColloction()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            return queryResult.first().getLong("Cooldown");
        }
        return 0;
    }

    public static void setHeadCooldown(UUID uuid, long cooldown) {
        CityBuildV2.getPlugin().getStorage().getHeadColloction().update()
                .set("Cooldown", cooldown)
                .where("UUID", uuid).executeAsync();
    }

}
