package com.jubyte.citybuild.manager.status;

import com.jubyte.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import net.pretronic.libraries.caching.ArrayCache;
import net.pretronic.libraries.caching.Cache;
import net.pretronic.libraries.caching.CacheQuery;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StatusCache {

  private final Cache<StatusPlayer> statusPlayerCache;

  public StatusCache() {
    statusPlayerCache =
        new ArrayCache<StatusPlayer>()
            .setExpireAfterAccess(30, TimeUnit.MINUTES)
            .setMaxSize(500)
            .registerQuery(
                "byUUID",
                new CacheQuery<StatusPlayer>() {
                  @Override
                  public StatusPlayer load(Object[] identifiers) {
                    UUID uuid = (UUID) identifiers[0];
                    DatabaseCollection collection =
                        CityBuildV2.getPlugin().getStorage().getStatusCollection();
                    QueryResultEntry entry =
                        collection.find().where("UUID", uuid).execute().firstOrNull();

                    if (entry == null) {
                      insertStatusPlayer(uuid);
                      return new StatusPlayer(uuid, null);
                    }
                    return new StatusPlayer(uuid, entry.getString("Status"));
                  }

                  @Override
                  public boolean check(StatusPlayer statusPlayer, Object[] objects) {
                    return statusPlayer.getUuid().equals(objects[0]);
                  }
                });
  }

  private void insertStatusPlayer(UUID uuid) {
    CityBuildV2.getPlugin()
        .getStorage()
        .getStatusCollection()
        .insert()
        .set("UUID", uuid)
        .set("Status", (Object) null)
        .execute();
  }

  public StatusPlayer getPlayerByUUID(UUID uuid) {
    return statusPlayerCache.get("byUUID", uuid);
  }
}
