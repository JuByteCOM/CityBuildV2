package de.crashmash.citybuild.storage;

import de.crashmash.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.query.result.QueryResult;

import java.util.UUID;

public class GlowSQL {

    public static boolean playerExists(UUID uuid) {
        return !CityBuildV2.getPlugin().getStorage().getGlowCollection().find().where("UUID", uuid).execute().isEmpty();
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExists(uuid)) {
            CityBuildV2.getPlugin().getStorage().getGlowCollection().insert()
                    .set("UUID", uuid)
                    .set("State", false)
                    .executeAsync();
        }
    }

    public static void setGlowEffect(UUID uuid, Boolean state){
        CityBuildV2.getPlugin().getStorage().getGlowCollection().update()
                .set("State", state)
                .where("UUID", uuid).executeAsync();
    }

    public static boolean getGlowEffect(UUID uuid){
        QueryResult queryResult = CityBuildV2.getPlugin().getStorage().getGlowCollection()
                .find()
                .where("UUID",uuid)
                .limit(1)
                .execute();
        if (!queryResult.isEmpty()){
            return queryResult.first().getBoolean("State");
        }
        return false;
    }

}
