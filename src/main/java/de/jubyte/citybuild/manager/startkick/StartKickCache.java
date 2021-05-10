package de.jubyte.citybuild.manager.startkick;

import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.MessagesData;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import net.pretronic.libraries.caching.ArrayCache;
import net.pretronic.libraries.caching.Cache;
import net.pretronic.libraries.caching.CacheQuery;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StartKickCache {

    private final Cache<StartKickPlayer> startKickPlayerCache;

    public StartKickCache() {
        startKickPlayerCache = new ArrayCache<StartKickPlayer>()
                .setExpireAfterAccess((MessagesData.STARTKICK_COMMAND_SETTING_DURATION + (60*5)), TimeUnit.SECONDS)
                .setMaxSize(500)
                .registerQuery("byUUID", new CacheQuery<StartKickPlayer>() {
                    @Override
                    public StartKickPlayer load(Object[] identifiers) {
                        UUID uuid = (UUID) identifiers[0];
                        DatabaseCollection collection = CityBuildV2.getPLUGIN().getStorage().getStartKickCollection();
                        QueryResultEntry entry = collection.find().where("UUID", uuid).execute().firstOrNull();

                        if(entry == null) {
                            insertStartKickPlayer(uuid);
                            return new StartKickPlayer(uuid, null, 0, 0);
                        }
                        return new StartKickPlayer(uuid, entry.getString("Reason"), entry.getLong("Duration"), entry.getLong("Cooldown"));
                    }
                    @Override
                    public boolean check(StartKickPlayer startKickPlayer, Object[] objects) {
                        return startKickPlayer.getUuid().equals(objects[0]);
                    }
                });
    }

    private void insertStartKickPlayer(UUID uuid) {
        CityBuildV2.getPLUGIN().getStorage().getStartKickCollection().insert()
                .set("UUID", uuid)
                .set("Reason", (Object) null)
                .set("Duration", 0L)
                .set("Cooldown", 0L)
                .execute();
    }

    public StartKickPlayer getPlayerByUUID(UUID uuid) {
        return startKickPlayerCache.get("byUUID", uuid);
    }

}
