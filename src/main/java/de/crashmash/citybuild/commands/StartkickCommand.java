package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.startkick.StartKick;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

public class StartkickCommand implements CommandExecutor {

    private final CityBuildV2 plugin = CityBuildV2.getPlugin();
    public static boolean isStartkick = false;

    private int counter = MessagesData.STARTKICK_COMMAND_SETTING_COUNTER;
    private static int startCountdown;
    private String reasons = "";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
            return false;
        }
        Player player = (Player) commandSender;
        if(!ConfigData.CONFIG_COMMAND_STARTKICK) {
            player.sendMessage(MessagesData.DEACTIVATED);
            return false;
        }
        if(!player.hasPermission(MessagesData.STARTKICK_COMMAND_PERMISSION_USE)) {
            player.sendMessage(MessagesData.NOPERMS);
            return false;
        }
        if(!(strings.length >= 2)) {
            player.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_USAGE);
            return false;
        }
        if(isStartkick) {
            player.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_ALLREADY_POLL);
            return false;
        }
        Player targetPlayer = Bukkit.getPlayer(strings[0]);
        if(targetPlayer == null) {
            player.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_TARGET_PLAYER_OFFLINE.replace("[targetPlayer]", strings[0]));
            return false;
        }
        if(targetPlayer.equals(player)) {
            player.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_SELF_KICK);
            return false;
        }
        if(MessagesData.STARTKICK_COMMAND_SETTING_KICK_BYPASS) {
            if(targetPlayer.hasPermission(MessagesData.STARTKICK_COMMAND_PERMISSION_KICK_BYPASS)) {
                player.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_KICK_BYPASS.replace("[targetPlayer]", player.getDisplayName()));
                return false;
            }
        }
        if(!player.hasPermission(MessagesData.STARTKICK_COMMAND_PERMISSION_TIME_BYPASS)) {
            if(!StartKick.canStartKick(player)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                long time = StartKick.getCooldownTime(player) + MessagesData.STARTKICK_COMMAND_SETTING_COOLDOWN;
                player.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_COOLDOWN.replace("[date]", simpleDateFormat.format(time))
                        .replace("[time]", simpleTimeFormat.format(time)));
                return false;
            }
        }
        StartKick.setCooldownTime(player);
        isStartkick = true;
        StringBuilder reason = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
            reason.append(" ").append(strings[i]);
        }

        TextComponent voteYes = new TextComponent(TextComponent.fromLegacyText
                (MessagesData.STARTKICK_COMMAND_MESSAGE_VOTE_FOR_YES.replace("[targetPlayer]", targetPlayer.getDisplayName())));
        voteYes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ja"));
        TextComponent voteNo = new TextComponent(TextComponent.fromLegacyText
                (MessagesData.STARTKICK_COMMAND_MESSAGE_VOTE_FOR_NO.replace("[targetPlayer]", targetPlayer.getDisplayName())));
        voteNo.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nein"));
        for(Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_STARTED.replace("[player]", player.getDisplayName())
                    .replace("[reason]", reason.toString()).replace("[duration]", Integer.toString(MessagesData.STARTKICK_COMMAND_SETTING_DURATION/60))
                    .replace("[targetPlayer]", targetPlayer.getDisplayName()));
            all.spigot().sendMessage(voteYes);
            all.spigot().sendMessage(voteNo);
        }

        startCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for(int i : MessagesData.STARTKICK_COMMAND_SETTING_COUNTER_TIMES) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if(i != 0) {
                        if (counter == i) {
                            all.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_COUNTER.replace("[counter]", Integer.toString(i)));
                        }
                        if (counter == 0) {
                            startStartKick(targetPlayer);
                            Bukkit.getScheduler().cancelTask(startCountdown);
                        }
                    }
                }
            }
            counter--;
        }, 0, 20);
        return false;
    }

    public void startStartKick(Player targetPlayer) {
        StringBuilder yes_string = new StringBuilder();
        StringBuilder no_string = new StringBuilder();
        int yes_int = plugin.getVOTING_YES().size();
        int no_int = plugin.getVOTING_NO().size();

        boolean yes_boolean = true;
        boolean no_boolean = true;

        for(String ja : plugin.getVOTING_YES()) {
            if(!plugin.getVOTING_YES().isEmpty()) {
                if (!yes_boolean) {
                    yes_string.append("§7,§a ");
                }
                yes_string.append(ja);
                yes_boolean = false;
            }
        }
        for(String nein : plugin.getVOTING_NO()) {
            if(!plugin.getVOTING_NO().isEmpty()) {
                if (!no_boolean) {
                    no_string.append("§7,§c ");
                }
                no_string.append(nein);
                no_boolean = false;
            }
        }

        if(!plugin.getVOTING_YES().isEmpty()) {
            for(Player all : Bukkit.getOnlinePlayers())
                all.sendMessage("§a" + yes_string);
        }
        if(!plugin.getVOTING_NO().isEmpty()) {
            for(Player all : Bukkit.getOnlinePlayers())
                all.sendMessage("§c" + no_string);
        }
        if(yes_int > no_int) {
            for(Player all : Bukkit.getOnlinePlayers())
                all.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_PLAYER_KICKED.replace("[targetPlayer]", targetPlayer.getDisplayName())
                        .replace("[yesVotes]", Integer.toString(yes_int)).replace("[noVotes]", Integer.toString(no_int)));
            StartKick.playerStartKicked(targetPlayer, reasons, System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
            long time = StartKick.getDuration(targetPlayer) + MessagesData.STARTKICK_COMMAND_SETTING_DURATION* 1000L;
            targetPlayer.kickPlayer(MessagesData.STARTKICK_COMMAND_MESSAGE_PLAYER_BANSCREEN.replace("[date]", simpleDateFormat.format(time))
                .replace("[time]", simpleTimeFormat.format(time)));
        } else {
            for(Player all : Bukkit.getOnlinePlayers())
                all.sendMessage(MessagesData.STARTKICK_COMMAND_MESSAGE_PLAYER_NOT_KICKED.replace("[targetPlayer]", targetPlayer.getDisplayName())
                        .replace("[yesVotes]", Integer.toString(yes_int)).replace("[noVotes]", Integer.toString(no_int)));
        }
        reasons = "";
        plugin.getVOTING_NO().clear();
        plugin.getVOTING_YES().clear();
        isStartkick = false;
        counter = MessagesData.STARTKICK_COMMAND_SETTING_COUNTER + 1;
    }
}
