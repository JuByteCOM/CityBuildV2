package de.crashmash.citybuild.manager.mutep;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.storage.MutepSQL;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MuteP {

    public static void createMutePPlayer(Player player) {
        CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().put(player, new MutepPlayer(player.getUniqueId(), MutepSQL.getReason(player.getUniqueId()),
                MutepSQL.getDuration(player.getUniqueId()), MutepSQL.getCooldown(player.getUniqueId()), MutepSQL.getCreator(player.getUniqueId())));
    }

    public static boolean canUseMuteP(Player player) {
        long time;
        if (player.hasPermission(MessagesData.MUTEP_COMMAND_PERMISSION_BYPASS_TIME)) {
            return true;
        }
        time = CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(player).getCooldown() + MessagesData.MUTEP_COMMAND_SETTINGS_COOLDOWN * 1000L;
        return System.currentTimeMillis() >= time;
    }

    public static long getCooldownTime(Player player) {
        return (CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(player).getCooldown() + MessagesData.MUTEP_COMMAND_SETTINGS_COOLDOWN * 1000L);
    }

    public static void setCooldownTime(Player player) {
        CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(player).setCooldown(System.currentTimeMillis());
        MutepSQL.setCooldown(player.getUniqueId(), System.currentTimeMillis());
    }

    public static boolean playerIsMutedP(Player player) {
        return CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(player).getDuration() + MessagesData.MUTEP_COMMAND_SETTINGS_DURATION *1000L > System.currentTimeMillis();
    }

    public static String getReason(Player player) {
        return CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(player).getReason();
    }

    public static UUID getCreator(Player player) {
        return CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(player).getCreator();
    }

    public static void playerMutedP(Player targetPlayer, String reason, Player creator) {
        CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(targetPlayer).setReason(reason);
        CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(targetPlayer).setDuration(System.currentTimeMillis());
        MutepSQL.setMuteP(targetPlayer.getUniqueId(), reason, System.currentTimeMillis(), creator.getUniqueId());
    }

    public static void playerUnmutedP(Player targetPlayer) {
        CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(targetPlayer).setReason(null);
        CityBuildV2.getPlugin().getMUTEPPLAYER_MAP().get(targetPlayer).setDuration(0);
        MutepSQL.setMuteP(targetPlayer.getUniqueId(), null, 0, null);
    }
}