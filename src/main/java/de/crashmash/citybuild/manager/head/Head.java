package de.crashmash.citybuild.manager.head;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.storage.HeadSQL;
import org.bukkit.entity.Player;

import java.util.Map;

public class Head {

    public static void createHeadPlayer(Player player) {
        CityBuildV2.getPlugin().getHEADPLAYER_MAP().put(player, HeadSQL.getHeadCooldown(player.getUniqueId()));
    }

    public static boolean canUseHead(Player player) {
        long time;
        assert player != null;
        if (player.hasPermission(MessagesData.HEAD_COMMAND_PERMISSION_BYPASS)) {
            return true;
        }
        time = CityBuildV2.getPlugin().getHEADPLAYER_MAP().get(player) + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN * 1000L;
        return System.currentTimeMillis() >= time;
    }

    public static long getHeadCooldown(Player player) {
        return (CityBuildV2.getPlugin().getHEADPLAYER_MAP().get(player) + MessagesData.HEAD_COMMAND_SETTINGS_COOLDOWN * 1000L);
    }

    public static void setCooldownTime(Player player) {
        for (Map.Entry<Player, Long> entry : CityBuildV2.getPlugin().getHEADPLAYER_MAP().entrySet()) {
            entry.setValue(System.currentTimeMillis());
        }
        HeadSQL.setHeadCooldown(player.getUniqueId(), System.currentTimeMillis());
    }
}
