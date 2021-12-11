package com.jubyte.citybuild.manager.checkplot;

import com.jubyte.citybuild.CityBuildV2;

import java.util.UUID;

public class CheckPlotPlayer {

  private UUID uuid;
  private long firstJoin;
  private long lastJoin;
  private long playTime;

  public CheckPlotPlayer(UUID uuid, long firstJoin, long lastJoin, long playTime) {
    this.uuid = uuid;
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

  public long getLastJoin() {
    return lastJoin;
  }

  public void setLastJoin() {
    this.lastJoin = System.currentTimeMillis();
    CityBuildV2.getPlugin()
        .getStorage()
        .getPlayerInformation()
        .update()
        .set("LastJoin", System.currentTimeMillis())
        .where("UUID", uuid)
        .executeAsync();
  }

  public long getPlayTime() {
    return playTime;
  }

  public void setPlayTime(long playTime) {
    this.playTime = playTime;
    CityBuildV2.getPlugin()
        .getStorage()
        .getPlayerInformation()
        .update()
        .set("LastJoin", firstJoin)
        .where("UUID", uuid)
        .executeAsync();
  }
}
