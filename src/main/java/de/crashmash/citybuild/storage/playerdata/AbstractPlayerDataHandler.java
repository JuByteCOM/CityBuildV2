package de.crashmash.citybuild.storage.playerdata;

import de.crashmash.citybuild.CityBuildV2;

import java.util.UUID;

public abstract class AbstractPlayerDataHandler {

    public boolean playerExists(String name) {
        return !CityBuildV2.getPlugin().getStorage().getPlayerInformation().find().where("Name", name).execute().isEmpty();
    }

    public UUID getUUID(String playerName) {
        return CityBuildV2.getPlugin().getStorage().getPlayerInformation().find().where("Name", playerName).execute().first().getUniqueId("UUID");
    }

    public String getName(UUID playerUUID) {
        return CityBuildV2.getPlugin().getStorage().getPlayerInformation().find().where("UUID", playerUUID.toString()).execute().first().getString("Name");
    }

    public abstract long getFirstJoin(UUID playerUUID);

    public abstract long getLastJoin(UUID playerUUID);

    public abstract long getPlaytime(UUID playerUUID);

    public abstract void setFirstJoin(UUID playerUUID);

    public abstract void setLastJoin(UUID playerUUID);

    public abstract void setPlaytime(UUID playerUUID, long toAdd);
}
