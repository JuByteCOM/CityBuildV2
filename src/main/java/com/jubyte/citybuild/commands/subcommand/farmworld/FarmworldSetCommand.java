package com.jubyte.citybuild.commands.subcommand.farmworld;

import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.locations.Locations;
import com.jubyte.citybuild.storage.LocationSQL;
import com.jubyte.citybuild.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Justin_SGD
 * @since 29.07.2021
 */
public class FarmworldSetCommand implements SubCommand {

  @Override
  public String getName() {
    return "set";
  }

  @Override
  public void execute(CommandSender commandSender, String[] strings) {
    if (strings.length == 1 && strings[0].equalsIgnoreCase(getName())) {
      Player player = (Player) commandSender;
      if (player.hasPermission(MessagesData.FARMWORLD_COMMAND_PERMISSION_ADMIN)) {
        double LocX = player.getLocation().getX();
        double LocY = player.getLocation().getY();
        double LocZ = player.getLocation().getZ();
        float Yaw = player.getLocation().getYaw();
        float Pitch = player.getLocation().getPitch();
        String World = player.getLocation().getWorld().getName();
        LocationSQL.createLocation("Farm", LocX, LocY, LocZ, Yaw, Pitch, World);
        if (Locations.exitsLocation("Farm")) {
          Locations.LOCATIONS.remove("Farm");
        }
        Locations.LOCATIONS.put("Farm", player.getLocation());
        player.sendMessage(MessagesData.FARMWORLD_COMMAND_MESSAGE_WARP_SET);
      } else {
        player.sendMessage(MessagesData.NOPERMS);
      }
    }
  }
}
