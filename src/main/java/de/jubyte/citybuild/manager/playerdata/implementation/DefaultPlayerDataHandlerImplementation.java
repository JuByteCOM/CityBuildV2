package de.jubyte.citybuild.manager.playerdata.implementation;

import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.manager.playerdata.AbstractPlayerDataHandler;

import java.util.UUID;

public class DefaultPlayerDataHandlerImplementation extends AbstractPlayerDataHandler {

    @Override
    public long getFirstJoin(UUID playerUUID) {
        if(CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID.toString()).execute().isEmpty()){
            createUser(playerUUID);
            return 0;
        }
        return CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("FirstJoin");
    }

    @Override
    public long getLastJoin(UUID playerUUID) {
        if(CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID.toString()).execute().isEmpty()){
            createUser(playerUUID);
            return 0;
        }
        return CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("LastJoin");
    }

    @Override
    public long getPlaytime(UUID playerUUID) {
        if(CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID.toString()).execute().isEmpty()){
            createUser(playerUUID);
            return 0;
        }
        return CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID).execute().first().getLong("Playtime");
    }

    @Override
    public void setFirstJoin(UUID playerUUID) {
        if(CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID.toString()).execute().isEmpty()){
            createUser(playerUUID);
        }
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("FirstJoin", System.currentTimeMillis());
    }

    @Override
    public void setLastJoin(UUID playerUUID) {
        if(CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID.toString()).execute().isEmpty()){
            createUser(playerUUID);
        }
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("LastJoin", System.currentTimeMillis());
    }

    @Override
    public void setPlaytime(UUID playerUUID, long toAdd) {
        if(CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().find().where("UUID", playerUUID.toString()).execute().isEmpty()){
            createUser(playerUUID);
        }
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update().where("UUID", playerUUID).set("Playtime", getPlaytime(playerUUID)+toAdd);
    }

    private void createUser(UUID playerUUID){
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().insert().set("UUID", playerUUID.toString()).set("Playtime", 0).set("LasJoin", 0).set("FirstJoin", 0).execute();
    }
}
