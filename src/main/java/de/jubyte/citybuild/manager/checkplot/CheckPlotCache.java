package de.jubyte.citybuild.manager.checkplot;

import de.jubyte.citybuild.CityBuildV2;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import net.pretronic.libraries.caching.ArrayCache;
import net.pretronic.libraries.caching.Cache;
import net.pretronic.libraries.caching.CacheQuery;
import org.bukkit.Bukkit;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CheckPlotCache {

    private final Cache<CheckPlotPlayer> checkplotCache;

    public CheckPlotCache() {
        checkplotCache = new ArrayCache<CheckPlotPlayer>()
                .setExpireAfterAccess(15, TimeUnit.MINUTES)
                .setMaxSize(500)
                .registerQuery("byUUID", new CacheQuery<CheckPlotPlayer>() {
                    @Override
                    public CheckPlotPlayer load(Object[] identifiers) {
                        UUID uuid = (UUID) identifiers[0];
                        DatabaseCollection collection = CityBuildV2.getPLUGIN().getStorage().getPlayerInformation();
                        QueryResultEntry entry = collection.find().where("UUID", uuid).execute().firstOrNull();

                        if (entry == null) {
                            insertCheckplotPlayer(uuid);
                            return new CheckPlotPlayer(uuid, Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName(), System.currentTimeMillis(),System.currentTimeMillis(), 0);
                        }
                        return new CheckPlotPlayer(uuid, entry.getString("Name"), entry.getLong("FirstJoin"), entry.getLong("LastJoin"), entry.getLong("Playtime"));
                    }

                    @Override
                    public boolean check(CheckPlotPlayer checkPlotPlayer, Object[] objects) {
                        return checkPlotPlayer.getUuid().equals(objects[0]);
                    }
                });
    }

    private void insertCheckplotPlayer(UUID uuid) {
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().insert()
                .set("UUID", uuid)
                .set("Name", Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName())
                .set("FirstJoin", System.currentTimeMillis())
                .set("LastJoin", System.currentTimeMillis())
                .set("Playtime", 0)
                .executeAsync();
    }

    public CheckPlotPlayer getPlayerByUUID(UUID uuid) {
        return checkplotCache.get("byUUID", uuid);
    }
}