package com.jubyte.citybuild.manager.mutep;

import com.jubyte.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import net.pretronic.libraries.caching.ArrayCache;
import net.pretronic.libraries.caching.Cache;
import net.pretronic.libraries.caching.CacheQuery;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MutePCache {

  private final Cache<MutepPlayer> mutepPlayerCache;

  public MutePCache() {
    mutepPlayerCache =
        new ArrayCache<MutepPlayer>()
            .setExpireAfterAccess(30, TimeUnit.MINUTES)
            .setMaxSize(500)
            .registerQuery(
                "byUUID",
                new CacheQuery<MutepPlayer>() {
                  @Override
                  public MutepPlayer load(Object[] identifiers) {
                    UUID uuid = (UUID) identifiers[0];
                    DatabaseCollection collection =
                        CityBuildV2.getPLUGIN().getStorage().getMutePCollection();
                    QueryResultEntry entry =
                        collection.find().where("UUID", uuid).execute().firstOrNull();

                    if (entry == null) {
                      insertMutepPlayer(uuid);
                      return new MutepPlayer(uuid, null, 0, 0, null);
                    }
                    return new MutepPlayer(
                        uuid,
                        entry.getString("Reason"),
                        entry.getLong("Duration"),
                        entry.getLong("Cooldown"),
                        entry.getUniqueId("Creator"));
                  }

                  @Override
                  public boolean check(MutepPlayer mutepPlayer, Object[] objects) {
                    return mutepPlayer.getUuid().equals(objects[0]);
                  }
                });
  }

  private void insertMutepPlayer(UUID uuid) {
    CityBuildV2.getPLUGIN()
        .getStorage()
        .getMutePCollection()
        .insert()
        .set("UUID", uuid)
        .set("Reason", (Object) null)
        .set("Duration", 0)
        .set("Cooldown", 0)
        .set("Creator", (Object) null)
        .execute();
  }

  public MutepPlayer getPlayerByUUID(UUID uuid) {
    return mutepPlayerCache.get("byUUID", uuid);
  }
}
