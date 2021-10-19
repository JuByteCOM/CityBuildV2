package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyCommand extends AbstractCommand {

  public FlyCommand() {
    super(
        ConfigData.CONFIG_COMMAND_FLY_NAME,
        null,
        "Set Flymode for users",
        ConfigData.CONFIG_COMMAND_FLY_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {

    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    } else {

      Player player = (Player) commandSender;

      if (player.hasPermission(MessagesData.FLY_COMMAND_PERMISSION_USE_SELF)) {
        if (strings.length == 0) {
          if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(MessagesData.FLY_COMMAND_MESSAGE_DEACTIVATED_SELF);
          } else {
            player.setAllowFlight(true);
            player.sendMessage(MessagesData.FLY_COMMAND_MESSAGE_ACTIVATED_SELF);
          }
        } else if (strings.length == 1) {
          if (player.hasPermission(MessagesData.FLY_COMMAND_PERMISSION_USE_OTHER)) {

            Player target = Bukkit.getPlayer(strings[0]);

            if (target != null) {
              if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                player.sendMessage(MessagesData.FLY_COMMAND_MESSAGE_DEACTIVATED_SELF);
                player.sendMessage(
                    MessagesData.FLY_COMMAND_MESSAGE_DEACTIVATED_OTHER.replace(
                        "[targetPlayer]", strings[0]));
              } else {
                target.setAllowFlight(true);
                player.sendMessage(MessagesData.FLY_COMMAND_MESSAGE_ACTIVATED_SELF);
                player.sendMessage(
                    MessagesData.FLY_COMMAND_MESSAGE_ACTIVATED_OTHER.replace(
                        "[targetPlayer]", strings[0]));
              }
            } else {
              player.sendMessage(
                  MessagesData.FLY_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE.replace(
                      "[targetPlayer]", strings[0]));
            }

          } else {
            player.sendMessage(MessagesData.NOPERMS);
          }
        } else {
          player.sendMessage(MessagesData.FLY_COMMAND_MESSAGE_USAGE);
        }
      } else {
        player.sendMessage(MessagesData.NOPERMS);
      }
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
