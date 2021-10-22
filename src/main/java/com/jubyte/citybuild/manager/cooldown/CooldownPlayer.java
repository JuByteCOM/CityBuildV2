package com.jubyte.citybuild.manager.cooldown;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.MessagesData;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CooldownPlayer {

  private UUID uuid;
  private long head;
  private long breakBlock;
  private long giftRank;
  private long clearChat;

  public void setHead() {
    this.head = System.currentTimeMillis() + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN * 1000L;
    CityBuildV2.getPLUGIN()
        .getStorage()
        .getcooldownCollection()
        .update()
        .set("Head", head)
        .where("UUID", uuid)
        .executeAsync();
  }

  public long getBreakBlock() {
    return breakBlock;
  }

  public void setBreakBlock() {
    this.breakBlock =
        System.currentTimeMillis() + MessagesData.BREAKBLOCK_COMMAND_SETTINGS_COOLDOWN * 1000L;
    CityBuildV2.getPLUGIN()
        .getStorage()
        .getcooldownCollection()
        .update()
        .set("BreakBlock", breakBlock)
        .where("UUID", uuid)
        .executeAsync();
  }

  public boolean hasHeadCooldown(Player player) {
    if (player.hasPermission(MessagesData.HEAD_COMMAND_PERMISSION_BYPASS)) {
      return true;
    }
    return System.currentTimeMillis() > getHead();
  }

  public boolean hasBreakblockCooldown(Player player) {
    if (player.hasPermission(MessagesData.BREAKBLOCK_COMMAND_PERMISSION_BYPASS)) {
      return true;
    }
    return System.currentTimeMillis() > getBreakBlock();
  }

  public void setGiftRank() {
    this.giftRank =
        System.currentTimeMillis() + MessagesData.GIFTRANK_COMMAND_SETTING_COOLDOWN * 1000L;
    CityBuildV2.getPLUGIN()
        .getStorage()
        .getcooldownCollection()
        .update()
        .set("GiftRank", giftRank)
        .where("UUID", uuid)
        .executeAsync();
  }

  public boolean hasGiftRankCooldown(Player player) {
    if (player.hasPermission(MessagesData.GIFTRANK_COMMAND_PERMISSION_BYPASS)) {
      return true;
    }
    return System.currentTimeMillis() > getGiftRank();
  }
}
