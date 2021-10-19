package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandSpyCommand extends AbstractCommand {

  private final Map<Player, Player> commandSpy_Map = CityBuildV2.getPLUGIN().getCOMMANDSPY_MAP();

  public CommandSpyCommand() {
    super(
        ConfigData.CONFIG_COMMAND_COMMANDSPY_NAME,
        null,
        "Spy on commands given by players.",
        ConfigData.CONFIG_COMMAND_COMMANDSPY_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    }
    Player player = (Player) commandSender;
    if (player.hasPermission(MessagesData.COMMANDSPY_COMMAND_PERMISSION_USE)) {
      if (strings.length == 0) {
        if (commandSpy_Map.containsKey(player)) {
          player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_DISABLED);
          commandSpy_Map.remove(player);
        } else {
          player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_ENABLED_ALL);
          commandSpy_Map.put(player, null);
        }
      } else if (strings.length == 1) {
        if (commandSpy_Map.containsKey(player)) {
          player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_DISABLED);
          commandSpy_Map.remove(player);
        } else {
          Player targetPlayer = Bukkit.getPlayer(strings[0]);
          if (targetPlayer != null) {
            player.sendMessage(
                MessagesData.COMMANDSPY_COMMAND_MESSAGE_ENABLED_PLAYER.replace(
                    "[targetPlayer]", targetPlayer.getDisplayName()));
            commandSpy_Map.put(player, targetPlayer);
          } else {
            player.sendMessage(
                MessagesData.COMMANDSPY_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE.replace(
                    "[targetPlayer]", strings[0]));
          }
        }
      } else {
        player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_USAGE);
      }
    } else {
      player.sendMessage(MessagesData.NOPERMS);
    }
    return false;
  }

  @Override
  public List<String> onTabComplete(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (strings.length == 1) {
      final List<String> players = new ArrayList<>();
      for (Player all : Bukkit.getOnlinePlayers()) {
        players.add(all.getName());
      }
      return players;
    }
    return null;
  }
}
