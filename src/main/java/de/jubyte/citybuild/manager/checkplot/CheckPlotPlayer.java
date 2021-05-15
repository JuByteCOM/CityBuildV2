package de.jubyte.citybuild.manager.checkplot;

import de.jubyte.citybuild.CityBuildV2;

import java.util.UUID;

public class CheckPlotPlayer {

    private UUID uuid;
    private String name;
    private long firstJoin;
    private long lastJoin;
    private long playTime;

    public CheckPlotPlayer(UUID uuid, String name, long firstJoin, long lastJoin, long playTime) {
        this.uuid = uuid;
        this.name = name;
        this.firstJoin = firstJoin;
        this.lastJoin = lastJoin;
        this.playTime = playTime;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getFirstJoin() {
        return firstJoin;
    }

    public void setFirstJoin(long firstJoin) {
        this.firstJoin = firstJoin;
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update()
                .set("FirstJoin", firstJoin)
                .where("UUID", uuid).executeAsync();
    }

    public long getLastJoin() {
        return lastJoin;
    }

    public void setLastJoin(long lastJoin) {
        this.lastJoin = lastJoin;
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update()
                .set("LastJoin", firstJoin)
                .where("UUID", uuid).executeAsync();
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
        CityBuildV2.getPLUGIN().getStorage().getPlayerInformation().update()
                .set("LastJoin", firstJoin)
                .where("UUID", uuid).executeAsync();
    }
}
