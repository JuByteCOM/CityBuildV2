package com.jubyte.citybuild.manager.startkick;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.MessagesData;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StartKickPlayer {

  private UUID uuid;
  private String reason;
  private long duration;
  private long cooldown;

  public StartKickPlayer(UUID uuid, String reason, long duration, long cooldown) {
    this.uuid = uuid;
    this.reason = reason;
    this.duration = duration;
    this.cooldown = cooldown;
  }

  public UUID getUuid() {
    return uuid;
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
        .getStartKickCollection()
        .update()
        .set("Reason", reason)
        .where("UUID", uuid)
        .executeAsync();
  }

  public void setDuration(long duration) {
    this.duration = duration;
    CityBuildV2.getPlugin()
        .getStorage()
        .getStartKickCollection()
        .update()
        .set("Duration", duration)
        .where("UUID", uuid)
        .executeAsync();
  }

  public void setCooldown() {
    this.cooldown =
        System.currentTimeMillis() + MessagesData.STARTKICK_COMMAND_SETTING_COOLDOWN * 1000L;
    CityBuildV2.getPlugin()
        .getStorage()
        .getStartKickCollection()
        .update()
        .set("Cooldown", cooldown)
        .where("UUID", uuid)
        .executeAsync();
  }

  public boolean hasCooldown(Player player) {
    if (player.hasPermission(MessagesData.STARTKICK_COMMAND_PERMISSION_TIME_BYPASS)) {
      return true;
    }
    return System.currentTimeMillis() < getCooldown();
  }

  public boolean isStartKicked() {
    return getDuration() > System.currentTimeMillis();
  }

  public void setStartKick(String reason) {
    setDuration(
        System.currentTimeMillis() + MessagesData.STARTKICK_COMMAND_SETTING_DURATION * 1000L);
    setReason(reason);
  }
}
