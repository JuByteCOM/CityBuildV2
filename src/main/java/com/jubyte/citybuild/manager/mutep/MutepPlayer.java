package com.jubyte.citybuild.manager.mutep;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.MessagesData;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MutepPlayer {

  private UUID uuid;
  private String reason;
  private long duration;
  private long cooldown;
  private UUID creator;

  public MutepPlayer(UUID uuid, String reason, long duration, long cooldown, UUID creator) {
    this.uuid = uuid;
    this.reason = reason;
    this.duration = duration;
    this.cooldown = cooldown;
    this.creator = creator;
  }

  public String getReason() {
    return reason;
  }

  public long getDuration() {
    return duration;
  }

  public long getCooldown() {
    return cooldown;
  }

  public void setReason(String reason) {
    this.reason = reason;
    CityBuildV2.getPlugin()
        .getStorage()
        .getMutePCollection()
        .update()
        .set("Reason", reason)
        .where("UUID", uuid)
        .executeAsync();
  }

  public void setDuration(long duration) {
    this.duration = duration;
    CityBuildV2.getPlugin()
        .getStorage()
        .getMutePCollection()
        .update()
        .set("Duration", duration)
        .where("UUID", uuid)
        .executeAsync();
  }

  public void setCooldown() {
    this.cooldown =
        System.currentTimeMillis() + MessagesData.MUTEP_COMMAND_SETTINGS_COOLDOWN * 1000L;
    CityBuildV2.getPlugin()
        .getStorage()
        .getMutePCollection()
        .update()
        .set("Cooldown", cooldown)
        .where("UUID", uuid)
        .executeAsync();
  }

  public UUID getCreator() {
    return creator;
  }

  public void setCreator(UUID creator) {
    this.creator = creator;
    CityBuildV2.getPlugin()
        .getStorage()
        .getMutePCollection()
        .update()
        .set("Creator", creator)
        .where("UUID", uuid)
        .executeAsync();
  }

  public UUID getUuid() {
    return uuid;
  }

  public boolean hasCooldown(Player player) {
    if (player.hasPermission(MessagesData.MUTEP_COMMAND_PERMISSION_BYPASS_TIME)) {
      return true;
    }
    return System.currentTimeMillis() >= getCooldown();
  }

  public boolean playerIsMutep() {
    return System.currentTimeMillis() < getDuration();
  }

  public void playerMutep(String reason, Player creator) {
    setReason(reason);
    setDuration(System.currentTimeMillis() + MessagesData.MUTEP_COMMAND_SETTINGS_DURATION * 1000L);
    setCreator(creator.getUniqueId());
  }

  public void playerUnmutep() {
    setReason(null);
    setDuration(0);
    setCreator(null);
  }
}
