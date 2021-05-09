package de.crashmash.citybuild.manager.glow;

import de.crashmash.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import net.pretronic.libraries.caching.ArrayCache;
import net.pretronic.libraries.caching.Cache;
import net.pretronic.libraries.caching.CacheQuery;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GlowCache {

    private final Cache<GlowPlayer> glowPlayerCache;

    public GlowCache() {
        glowPlayerCache = new ArrayCache<GlowPlayer>()
                .setExpireAfterAccess(30, TimeUnit.MINUTES)
                .setMaxSize(500)
                .registerQuery("byUUID", new CacheQuery<GlowPlayer>() {
                    @Override
                    public GlowPlayer load(Object[] identifiers) {
                        UUID uuid = (UUID) identifiers[0];
                        DatabaseCollection collection = CityBuildV2.getPLUGIN().getStorage().getGlowCollection();
                        QueryResultEntry entry = collection.find().where("UUID", uuid).execute().firstOrNull();

                        if (entry == null) {
                            insertGlowPlayer(uuid);
                            return new GlowPlayer(uuid, false);
                        }
                        return new GlowPlayer(uuid, entry.getBoolean("State"));
                    }

                    @Override
                    public boolean check(GlowPlayer glowPlayer, Object[] objects) {
                        return glowPlayer.getUuid().equals(objects[0]);
                    }
                });
    }

    private void insertGlowPlayer(UUID uuid) {
        CityBuildV2.getPLUGIN().getStorage().getGlowCollection().insert()
                .set("UUID", uuid)
                .set("State", false)
                .execute();
    }

    public GlowPlayer getPlayerByUUID(UUID uuid) {
        return glowPlayerCache.get("byUUID", uuid);
    }
}