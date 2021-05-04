package de.crashmash.citybuild.storage;

import de.crashmash.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.query.result.QueryResult;

import java.util.UUID;

public class CooldownSQL {

    public static boolean playerExists(UUID uuid) {
        return !CityBuildV2.getPlugin().getStorage().getcooldownCollection().find().where("UUID", uuid).execute().isEmpty();
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExists(uuid)) {
            CityBuildV2.getPlugin().getStorage().getcooldownCollection().insert()
                    .set("UUID", uuid)
                    .set("Head", 0)
                    .set("BreakBlock", 0)
                    .executeAsync();
        }
    }

    public static long getHeadCooldown(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getcooldownCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            return queryResult.first().getLong("Head");
        }
        return 0;
    }

    public static long getBreakBlockCooldown(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getcooldownCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            return queryResult.first().getLong("BreakBlock");
        }
        return 0;
    }

    public static void setHeadCooldown(UUID uuid, long head) {
        CityBuildV2.getPlugin().getStorage().getcooldownCollection().update()
                .set("Head", head)
                .where("UUID", uuid).executeAsync();
    }

    public static void setBreakBlockCooldown(UUID uuid, long breakBlock) {
        CityBuildV2.getPlugin().getStorage().getcooldownCollection().update()
                .set("BreakBlock", breakBlock)
                .where("UUID", uuid).executeAsync();
    }
}
