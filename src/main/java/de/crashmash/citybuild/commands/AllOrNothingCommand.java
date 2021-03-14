package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class AllOrNothingCommand implements CommandExecutor {

    private int counter = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_COUNTER;
    private int startCountdown;

    private int ingameResult = 0;
    private int realResult = 0;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(player.hasPermission(MessagesData.ALLORNOTHING_COMMAND_PERMISSION_USE)) {
                if(strings.length == 0) {
                    int minIngame = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MIN_INGAMEMONEY;
                    int maxIngame = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MAX_INGAMEMONEY;
                    int minReal = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MIN_REALMONEY;
                    int maxReal = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MAX_REALMONEY;
                    ingameResult = ThreadLocalRandom.current().nextInt(minIngame, maxIngame + 1);
                    realResult = ThreadLocalRandom.current().nextInt(minReal, maxReal + 1);
                    startCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(CityBuildV2.getPlugin(), () -> {
                        for(int i : MessagesData.ALLORNOTHING_COMMAND_SETTINGS_COUNTERTIMES) {
                            if (i != 0 && i != 1) {
                                if (counter == i) {
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        all.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_COUNTER.replace("[counter]", Integer.toString(i)));
                                    }
                                }
                            }
                        }
                        if (counter == 1) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_LAST_SECOND_COUNTER);
                            }
                        }
                        if (counter == 0) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_RESULT.replace("[ingameMoney]", String.valueOf(ingameResult))
                                        .replace("[realMoney]", String.valueOf(realResult)));
                            }
                            Bukkit.getScheduler().cancelTask(startCountdown);
                            ingameResult = 0;
                            realResult = 0;
                        }
                        counter--;
                    }, 0, 20);
                } else {
                    player.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_USAGE);
                }
            }
        }
        return false;
    }
}
