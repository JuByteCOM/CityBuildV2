package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeleportCommand extends AbstractCommand {

  public TeleportCommand() {
    super(
        ConfigData.CONFIG_COMMAND_TELEPORT_NAME,
        null,
        "Teleport to other players or coordinates.",
        ConfigData.CONFIG_COMMAND_TELEPORT_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    } else {
      Player player = (Player) commandSender;
      if (player.hasPermission(MessagesData.TELEPORT_COMMAND_PERMISSION_USE)) {
        if (strings.length == 1) {
          Player target = Bukkit.getPlayer(strings[0]);
          if (target != null) {
            player.teleport(target.getLocation());
            player.sendMessage(
                MessagesData.TELEPORT_COMMAND_MESSAGE_TPTOPLAYER.replace(
                    "[targetPlayer]", target.getName()));
          } else {
            player.sendMessage(
                MessagesData.TELEPORT_COMMAND_MESSAGE_PLAYERNOTFOUND.replace(
                    "[targetPlayer]", strings[0]));
          }
        } else if (strings.length == 3) {
          double x = Double.parseDouble(strings[0]);
          double y = Double.parseDouble(strings[1]);
          double z = Double.parseDouble(strings[2]);
          if ((x < 3.0E7D)
              || (y < 3.0E7D)
              || (z < 3.0E7D)
              || (x > -3.0E7D)
              || (y > -3.0E7D)
              || (z > -3.0E7D)) {
            Location location = new Location(player.getWorld(), x, y, z);
            player.teleport(location);
            player.sendMessage(
                MessagesData.TELEPORT_COMMAND_MESSAGE_TPTOLOCATION.replace(
                    "[location]", location.toString()));
          } else {
            player.sendMessage(MessagesData.TELEPORT_COMMAND_MESSAGE_FALSELOCATION);
          }
        }
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
