package de.crashmash.citybuild.storage;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.manager.booster.BoosterEntry;
import de.crashmash.citybuild.manager.booster.BoosterType;
import net.pretronic.databasequery.api.query.result.QueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BoosterSQL {

    public static List<BoosterEntry> getBoosters(UUID uuid) {
        QueryResult queryResult = CityBuildV2.getPLUGIN().getStorage().getBoosterCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            List<BoosterEntry> entries = new ArrayList<>();
            int boosterId = queryResult.first().getInt("BoosterID");
            int amount = queryResult.first().getInt("Amount");
            entries.add(new BoosterEntry(BoosterType.byId(boosterId), amount));
            return entries;
        }
        return null;
    }

    public static BoosterEntry getBoosterEntry(UUID uuid, BoosterType boosterType) {
        QueryResult queryResult = CityBuildV2.getPLUGIN().getStorage().getBoosterCollection()
                .find()
                .where("UUID", uuid)
                .limit(1)
                .execute();
        if(!queryResult.isEmpty()) {
            int amount = queryResult.first().getInt("Amount");
            return new BoosterEntry(boosterType, amount);
        }
        return null;
    }

    public static boolean hasBooster(UUID uuid, BoosterType boosterType) {
        return getBoosterEntry(uuid, boosterType) != null;
    }

    public static void setBooster(UUID uuid, BoosterType boosterType, int amount) {
        CityBuildV2.getPLUGIN().getStorage().getBoosterCollection().update()
                .set("Amount", amount)
                .where("UUID", uuid)
                .where("BoosterID", boosterType).executeAsync();
    }

    public static void createPlayer(UUID uuid) {
        for(BoosterType boosterType : BoosterType.values()) {
            if(!hasBooster(uuid, boosterType)) {
                CityBuildV2.getPLUGIN().getStorage().getBoosterCollection().insert()
                        .set("UUID", uuid)
                        .set("BoosterID", boosterType.getId())
                        .set("Amount", 0).executeAsync();
            }
        }
    }

}
