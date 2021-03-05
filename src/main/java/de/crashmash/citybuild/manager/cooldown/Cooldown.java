package de.crashmash.citybuild.manager.cooldown;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.storage.CooldownSQL;
import org.bukkit.entity.Player;

public class Cooldown {

    public static void createCooldownPlayer(Player player) {
        CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().put(player, new CooldownPlayer(player.getUniqueId(),
                CooldownSQL.getHeadCooldown(player.getUniqueId()), CooldownSQL.getBreakBlockCooldown(player.getUniqueId()),
                CooldownSQL.getMutePCooldown(player.getUniqueId())));
    }

    public static boolean canUseHead(Player player) {
        long time;
        assert player != null;
        if (player.hasPermission(MessagesData.HEAD_COMMAND_PERMISSION_BYPASS)) {
            return true;
        }
        time = CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).getHead() + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN * 1000L;
        return System.currentTimeMillis() >= time;
    }

    public static long getHeadCooldown(Player player) {
        return (CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).getHead() + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN * 1000L);
    }

    public static void setHeadCooldown(Player player) {
        CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).setHead(System.currentTimeMillis());
        CooldownSQL.setHeadCooldown(player.getUniqueId(), System.currentTimeMillis());
    }

    public static boolean canUseBreakBlock(Player player) {
        long time;
        assert player != null;
        if (player.hasPermission(MessagesData.BREAKBLOCK_COMMAND_PERMISSION_BYPASS)) {
            return true;
        }
        time = CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).getBreakBlock() + MessagesData.BREAKBLOCK_COMMAND_SETTINGS_COOLDOWN * 1000L;
        return System.currentTimeMillis() >= time;
    }

    public static long getBreakBlockCooldown(Player player) {
        return (CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).getBreakBlock() + MessagesData.BREAKBLOCK_COMMAND_SETTINGS_COOLDOWN * 1000L);
    }

    public static void setBreakBlockCooldown(Player player) {
        CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).setBreakBlock(System.currentTimeMillis());
        CooldownSQL.setBreakBlockCooldown(player.getUniqueId(), System.currentTimeMillis());
    }

    public static boolean canUseMuteP(Player player) {
        long time;
        assert player != null;
        if (player.hasPermission(MessagesData.HEAD_COMMAND_PERMISSION_BYPASS)) {
            return true;
        }
        time = CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).getMuteP() + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN * 1000L;
        return System.currentTimeMillis() >= time;
    }

    public static long getMutePCooldown(Player player) {
        return (CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).getMuteP() + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN * 1000L);
    }

    public static void setMutePCooldown(Player player) {
        CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().get(player).setMuteP(System.currentTimeMillis());
        CooldownSQL.setMutePCooldown(player.getUniqueId(), System.currentTimeMillis());
    }
}
