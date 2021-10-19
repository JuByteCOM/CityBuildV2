package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.locations.Locations;
import com.jubyte.citybuild.storage.LocationSQL;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand extends AbstractCommand {

  public SetWarpCommand() {
    super(
        ConfigData.CONFIG_COMMAND_NETHER_NAME,
        null,
        "Teleport to Nehter world.",
        ConfigData.CONFIG_COMMAND_NETHER_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    } else {
      Player player = (Player) commandSender;
      if (player.hasPermission(MessagesData.SPAWN_COMMAND_PERMISSION_ADMIN)) {
        if (strings.length == 1) {
          double LocX = player.getLocation().getX();
          double LocY = player.getLocation().getY();
          double LocZ = player.getLocation().getZ();
          float Yaw = player.getLocation().getYaw();
          float Pitch = player.getLocation().getPitch();
          String World = player.getLocation().getWorld().getName();
          String warpName = strings[0];
          LocationSQL.createLocation(warpName, LocX, LocY, LocZ, Yaw, Pitch, World);
          Locations.LOCATIONS.clear();
          Bukkit.getServer()
              .getScheduler()
              .runTaskLater(
                  CityBuildV2.getPLUGIN(),
                  () -> Locations.setLocations(LocationSQL.loadLocations()),
                  10);
          player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_SPAWN_SET);
          if (Locations.exitsLocation(warpName)) {
            LocationSQL.deleteLoc(warpName);
            Locations.LOCATIONS.remove(warpName);
            player.sendMessage(MessagesData.SPAWN_COMMAND_MESSAGE_SPAWN_REMOVE);
          }
        } else {
          player.sendMessage(MessagesData.NOPERMS);
        }
      }
    }
    return false;
  }
}
