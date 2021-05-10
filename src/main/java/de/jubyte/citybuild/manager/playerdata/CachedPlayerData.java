package de.jubyte.citybuild.manager.playerdata;

import java.util.*;

public class CachedPlayerData {

    private UUID uuid;
    private Map<String, Object> cache;

    private static final List<CachedPlayerData> playerDataList = new ArrayList<>();

    public CachedPlayerData(UUID uuid) {
        this.uuid = uuid;
        this.cache = new HashMap<>();
    }

    public static Optional<CachedPlayerData> getCacheEntry(UUID uuid){
        return playerDataList.stream().filter(cachedPlayerData -> cachedPlayerData.getUuid().equals(uuid)).findFirst();
    }

    public void cache(String key, Object value){
        this.cache.put(key, value);
    }

    public boolean isCached(String key){
        return this.cache.containsKey(key);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<String, Object> getCache() {
        return cache;
    }

    public static List<CachedPlayerData> getPlayerDataList() {
        return playerDataList;
    }
}
