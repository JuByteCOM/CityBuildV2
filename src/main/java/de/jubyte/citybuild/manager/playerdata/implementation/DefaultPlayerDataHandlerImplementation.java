package de.jubyte.citybuild.manager.playerdata.implementation;

import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.manager.playerdata.AbstractPlayerDataHandler;

import java.util.UUID;

public class DefaultPlayerDataHandlerImplementation extends AbstractPlayerDataHandler {

    @Override
    public long getFirstJoin(UUID playerUUID) {
        return CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("FirstJoin");
    }

    @Override
    public long getLastJoin(UUID playerUUID) {
        return CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("LastJoin");
    }

    @Override
    public long getPlaytime(UUID playerUUID) {
        return CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("Playtime");
    }

    @Override
    public void setFirstJoin(UUID playerUUID) {
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("FirstJoin", System.currentTimeMillis());
    }

    @Override
    public void setLastJoin(UUID playerUUID) {
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("LastJoin", System.currentTimeMillis());
    }

    @Override
    public void setPlaytime(UUID playerUUID, long toAdd) {
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("Playtime", getPlaytime(playerUUID)+toAdd);
    }
}
