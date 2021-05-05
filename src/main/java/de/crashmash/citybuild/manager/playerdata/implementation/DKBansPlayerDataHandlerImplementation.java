package de.crashmash.citybuild.manager.playerdata.implementation;

import ch.dkrieger.bansystem.lib.BanSystem;
import de.crashmash.citybuild.manager.playerdata.AbstractPlayerDataHandler;

import java.util.UUID;

public class DKBansPlayerDataHandlerImplementation extends AbstractPlayerDataHandler {

    @Override
    public long getFirstJoin(UUID playerUUID) {
        return BanSystem.getInstance().getPlayerManager().getPlayer(playerUUID).getFirstLogin();
    }

    @Override
    public long getLastJoin(UUID playerUUID) {
        return BanSystem.getInstance().getPlayerManager().getPlayer(playerUUID).getLastLogin();
    }

    @Override
    public long getPlaytime(UUID playerUUID) {
        return BanSystem.getInstance().getPlayerManager().getPlayer(playerUUID).getOnlineTime();
    }


    /*
    UNUSED
     */
    @Override
    public void setFirstJoin(UUID playerUUID) { }

    @Override
    public void setLastJoin(UUID playerUUID) { }

    @Override
    public void setPlaytime(UUID playerUUID, long toAdd) { }
}
