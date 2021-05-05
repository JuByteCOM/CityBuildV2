package de.crashmash.citybuild.storage.playerdata.implementation;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.storage.playerdata.AbstractPlayerDataHandler;

import java.util.UUID;

public class DefaultPlayerDataHandlerImplementation extends AbstractPlayerDataHandler {

    @Override
    public long getFirstJoin(UUID playerUUID) {
        return CityBuildV2.getPlugin().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("FirstJoin");
    }

    @Override
    public long getLastJoin(UUID playerUUID) {
        return CityBuildV2.getPlugin().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("LastJoin");
    }

    @Override
    public long getPlaytime(UUID playerUUID) {
        return CityBuildV2.getPlugin().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("Playtime");
    }

    @Override
    public void setFirstJoin(UUID playerUUID) {
        CityBuildV2.getPlugin().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("FirstJoin", System.currentTimeMillis());
    }

    @Override
    public void setLastJoin(UUID playerUUID) {
        CityBuildV2.getPlugin().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("LastJoin", System.currentTimeMillis());
    }

    @Override
    public void setPlaytime(UUID playerUUID, long toAdd) {
        CityBuildV2.getPlugin().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("Playtime", getPlaytime(playerUUID)+toAdd);
    }
}
