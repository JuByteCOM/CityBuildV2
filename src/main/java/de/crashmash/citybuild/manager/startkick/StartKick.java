package de.crashmash.citybuild.manager.startkick;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.storage.StartkickSQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StartKick {

    public static void createStartKickPlayer(Player player) {
        CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().put(player, new StartKickPlayer(player.getUniqueId(), StartkickSQL.getReason(player.getUniqueId()),
                StartkickSQL.getDuration(player.getUniqueId()), StartkickSQL.getCooldown(player.getUniqueId())));
    }

    public static boolean canStartKick(Player player) {
        long time;
        if (player.hasPermission(MessagesData.STARTKICK_COMMAND_PERMISSION_TIME_BYPASS)) {
            return true;
        }
        time = CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().get(player).getCooldown() + MessagesData.STARTKICK_COMMAND_SETTING_COOLDOWN * 1000L;
        return System.currentTimeMillis() >= time;
    }

    public static long getCooldownTime(Player player) {
        return (CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().get(player).getCooldown() + MessagesData.STARTKICK_COMMAND_SETTING_COOLDOWN * 1000L);
    }

    public static void setCooldownTime(Player player) {
        CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().get(player).setCooldown(System.currentTimeMillis());
        StartkickSQL.setCooldown(player.getUniqueId(), System.currentTimeMillis());
    }

    public static boolean playerStartKicked(Player player) {
        return CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().get(player).getDuration() + MessagesData.STARTKICK_COMMAND_SETTING_DURATION *1000L > System.currentTimeMillis();
    }

    public static long getDuration(Player player) {
        return CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().get(player).getDuration();
    }

    public static void playerStartKicked(Player player, String reason, long duration) {
        CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().get(player).setReason(reason);
        CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().get(player).setDuration(duration);
        StartkickSQL.setStartKick(player.getUniqueId(), reason, duration, 0);
    }
}