package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class AllOrNothingCommand extends AbstractCommand {

  private int counter = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_COUNTER;
  private int startCountdown;

  private int ingameResult = 0;
  private int realResult = 0;

  private boolean isAllOrNothing = false;

  public AllOrNothingCommand() {
    super(
        ConfigData.CONFIG_COMMAND_ALLORNOTHING_NAME,
        null,
        "Let other players decide your luck!",
        ConfigData.CONFIG_COMMAND_ALLORNOTHING_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    } else {
      Player player = (Player) commandSender;
      if (player.hasPermission(MessagesData.ALLORNOTHING_COMMAND_PERMISSION_USE)) {
        if (strings.length == 0) {
          if (!isAllOrNothing) {
            isAllOrNothing = true;
            int minIngame = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MIN_INGAMEMONEY;
            int maxIngame = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MAX_INGAMEMONEY;
            int minReal = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MIN_REALMONEY;
            int maxReal = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MAX_REALMONEY;
            ingameResult = ThreadLocalRandom.current().nextInt(minIngame, maxIngame + 1);
            realResult = ThreadLocalRandom.current().nextInt(minReal, maxReal + 1);
            startCountdown =
                Bukkit.getScheduler()
                    .scheduleSyncRepeatingTask(
                        CityBuildV2.getPLUGIN(),
                        () -> {
                          for (int i : MessagesData.ALLORNOTHING_COMMAND_SETTINGS_COUNTERTIMES) {
                            if (i != 0 && i != 1) {
                              if (counter == i) {
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                  players.sendMessage(
                                      MessagesData.ALLORNOTHING_COMMAND_MESSAGE_COUNTER.replace(
                                          "[counter]", Integer.toString(i)));
                                }
                              }
                            }
                          }
                          if (counter == 1) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                              players.sendMessage(
                                  MessagesData.ALLORNOTHING_COMMAND_MESSAGE_LAST_SECOND_COUNTER);
                            }
                          }
                          if (counter == 0) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                              players.sendMessage(
                                  MessagesData.ALLORNOTHING_COMMAND_MESSAGE_RESULT
                                      .replace("[ingameMoney]", String.valueOf(ingameResult))
                                      .replace("[realMoney]", String.valueOf(realResult)));
                            }
                            Bukkit.getScheduler().cancelTask(startCountdown);
                            isAllOrNothing = false;
                            ingameResult = 0;
                            realResult = 0;
                            counter = MessagesData.ALLORNOTHING_COMMAND_SETTINGS_COUNTER + 1;
                          }
                          counter--;
                        },
                        0,
                        20);
          } else {
            player.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_ALLREADY_RUN_COMMAND);
          }
        } else if (strings.length == 1) {
          if (strings[0].equalsIgnoreCase("admin")) {
            player.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_SETTINGS_INFORMATION);
          }
        } else if (strings.length == 4) {
          int amount = Integer.parseInt(strings[3]);
          if (strings[0].equalsIgnoreCase("admin") && strings[1].equalsIgnoreCase("set")) {
            if (amount >= 0) {
              if (strings[2].equalsIgnoreCase("maxingamemoney")) {
                CityBuildV2.getPLUGIN()
                    .getMessagesConfig()
                    .set("Commands.AllOrNothing.Settings.MaxIngamemoney", amount);
                CityBuildV2.getPLUGIN().saveMessagesConfig();
                MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MAX_INGAMEMONEY = amount;
                player.sendMessage(
                    MessagesData.ALLORNOTHING_COMMAND_MESSAGE_SET_MAX_INGAMEMONEY.replace(
                        "[amount]", String.valueOf(amount)));
              } else if (strings[2].equalsIgnoreCase("maxrealmoney")) {
                CityBuildV2.getPLUGIN()
                    .getMessagesConfig()
                    .set("Commands.AllOrNothing.Settings.MaxRealmoney", amount);
                CityBuildV2.getPLUGIN().saveMessagesConfig();
                MessagesData.ALLORNOTHING_COMMAND_SETTINGS_MAX_INGAMEMONEY = amount;
                player.sendMessage(
                    MessagesData.ALLORNOTHING_COMMAND_MESSAGE_SET_MAX_REALMONEY.replace(
                        "[amount]", String.valueOf(amount)));
              } else {
                player.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_USAGE_ADMIN);
              }
            }
          } else {
            player.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_USAGE);
          }
        } else {
          player.sendMessage(MessagesData.ALLORNOTHING_COMMAND_MESSAGE_USAGE);
        }
      }
    }
    return false;
  }
}
