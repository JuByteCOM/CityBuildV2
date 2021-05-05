package de.crashmash.citybuild.storage.playerdata.implementation;

import ch.dkrieger.bansystem.lib.BanSystem;
import ch.dkrieger.bansystem.lib.DKBansPlatform;
import ch.dkrieger.bansystem.lib.player.NetworkPlayer;
import ch.dkrieger.bansystem.lib.storage.DKBansStorage;
import de.crashmash.citybuild.storage.playerdata.AbstractPlayerDataHandler;

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
