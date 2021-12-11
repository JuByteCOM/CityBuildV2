package com.jubyte.citybuild.manager.cooldown;

import com.jubyte.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import net.pretronic.libraries.caching.ArrayCache;
import net.pretronic.libraries.caching.Cache;
import net.pretronic.libraries.caching.CacheQuery;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CooldownCache {

  private final Cache<CooldownPlayer> cooldownCache;

  public CooldownCache() {
    cooldownCache =
        new ArrayCache<CooldownPlayer>()
            .setExpireAfterAccess(15, TimeUnit.MINUTES)
            .setMaxSize(500)
            .registerQuery(
                "byUUID",
                new CacheQuery<>() {
                  @Override
                  public CooldownPlayer load(Object[] identifiers) {
                    UUID uuid = (UUID) identifiers[0];
                    DatabaseCollection collection =
                        CityBuildV2.getPlugin().getStorage().getcooldownCollection();
                    QueryResultEntry entry =
                        collection.find().where("UUID", uuid).execute().firstOrNull();

                    if (entry == null) {
                      insertCooldownPlayer(uuid);
                      return new CooldownPlayer(uuid, 0, 0, 0, 0);
                    }
                    return new CooldownPlayer(
                        uuid,
                        entry.getLong("Head"),
                        entry.getLong("BreakBlock"),
                        entry.getLong("GiftRank"),
                        entry.getLong("ClearChat"));
                  }

                  @Override
                  public boolean check(CooldownPlayer cooldownPlayer, Object[] objects) {
                    return cooldownPlayer.getUuid().equals(objects[0]);
                  }
                });
  }

  private void insertCooldownPlayer(UUID uuid) {
    CityBuildV2.getPlugin()
        .getStorage()
        .getcooldownCollection()
        .insert()
        .set("UUID", uuid)
        .set("Head", 0)
        .set("BreakBlock", 0)
        .set("GiftRank", 0)
        .set("ClearChat", 0)
        .execute();
  }

  public CooldownPlayer getPlayerByUUID(UUID uuid) {
    return cooldownCache.get("byUUID", uuid);
  }
}
