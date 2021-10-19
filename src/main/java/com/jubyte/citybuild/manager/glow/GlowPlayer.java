package com.jubyte.citybuild.manager.glow;

import com.jubyte.citybuild.CityBuildV2;

import java.util.UUID;

public class GlowPlayer {

  private UUID uuid;
  private boolean state;

  public GlowPlayer(UUID uuid, boolean state) {
    this.uuid = uuid;
    this.state = state;
  }

  public UUID getUuid() {
    return uuid;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
    CityBuildV2.getPLUGIN()
        .getStorage()
        .getGlowCollection()
        .update()
        .set("State", state)
        .where("UUID", uuid)
        .executeAsync();
  }
}
