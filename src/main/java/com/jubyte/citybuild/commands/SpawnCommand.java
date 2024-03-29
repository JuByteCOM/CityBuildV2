package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.locations.Locations;
import com.jubyte.citybuild.storage.LocationSQL;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends AbstractCommand {

  public SpawnCommand() {
    super(
        ConfigData.CONFIG_COMMAND_SPAWN_NAME,
        null,
        "Teleport to or set the Spawn.",
        ConfigData.CONFIG_COMMAND_SPAWN_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    } else {
      Player player = (Player) commandSender;
      if (strings.length == 0) {
        if (player.hasPermission(MessagesData.SPAWN_COMMAND_PERMISSION_USE)) {
          if (Locations.exitsLocation("Spawn")) {
            Location location = Locations.getLocation("Spawn");
            player.teleport(location);
            player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_TELEPORT_TO_SPAWN);
          } else {
            player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_SPAWN_NOT_FOUND);
          }
        } else {
          player.sendMessage(MessagesData.NOPERMS);
        }
      } else if (strings.length == 1 && strings[0].equalsIgnoreCase("admin")) {
        if (player.hasPermission(MessagesData.SPAWN_COMMAND_PERMISSION_ADMIN)) {
          player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_ADMINUSAGE);
        } else {
          player.sendMessage(MessagesData.NOPERMS);
        }
      } else if (strings.length == 2) {
        if (player.hasPermission(MessagesData.SPAWN_COMMAND_PERMISSION_ADMIN)) {
          if (strings[1].equalsIgnoreCase("set")) {
            double LocX = player.getLocation().getX();
            double LocY = player.getLocation().getY();
            double LocZ = player.getLocation().getZ();
            float Yaw = player.getLocation().getYaw();
            float Pitch = player.getLocation().getPitch();
            String World = player.getLocation().getWorld().getName();
            LocationSQL.createLocation("Spawn", LocX, LocY, LocZ, Yaw, Pitch, World);
            Locations.LOCATIONS.put("Spawn", player.getLocation());
            player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_SPAWN_SET);
          } else if (strings[1].equalsIgnoreCase("remove")) {
            if (Locations.exitsLocation("Spawn")) {
              LocationSQL.deleteLoc("Spawn");
              Locations.LOCATIONS.remove("Spawn");
              player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_SPAWN_REMOVE);
            }
          } else {
            player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_ADMINUSAGE);
          }
        } else {
          player.sendMessage(MessagesData.NOPERMS);
        }
      } else {
        player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_USAGE);
      }
    }
    return false;
  }
}
