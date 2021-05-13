package de.jubyte.citybuild.manager.playerdata;

import java.util.*;

public class CachedPlayerData {

    private UUID uuid;
    private Map<CacheDataType, Object> cache;

    private static final List<CachedPlayerData> playerDataList = new ArrayList<>();

    public CachedPlayerData(UUID uuid) {
        this.uuid = uuid;
        this.cache = new HashMap<>();

        playerDataList.add(this);
    }

    public static Optional<CachedPlayerData> getCacheEntry(UUID uuid){
        return playerDataList.stream().filter(cachedPlayerData -> cachedPlayerData.getUuid().equals(uuid)).findFirst();
    }

    public void cache(CacheDataType type, Object value){
        this.cache.put(type, value);
    }

    public Object getCacheValue(CacheDataType type){
        return this.cache.getOrDefault(type, null);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<CacheDataType, Object> getCache() {
        return cache;
    }

    public static List<CachedPlayerData> getPlayerDataList() {
        return playerDataList;
    }

    public enum CacheDataType {

        LAST_JOIN,
        FIRST_JOIN,
        PLAY_TIME;

    }

}
