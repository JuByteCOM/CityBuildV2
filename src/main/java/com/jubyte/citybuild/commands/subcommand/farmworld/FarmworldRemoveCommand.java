package com.jubyte.citybuild.commands.subcommand.farmworld;

import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.locations.Locations;
import com.jubyte.citybuild.storage.LocationSQL;
import com.jubyte.citybuild.utils.SubCommand;
import org.bukkit.command.CommandSender;

/**
 * @author Justin_SGD
 * @since 29.07.2021
 */
public class FarmworldRemoveCommand implements SubCommand {

  @Override
  public String getName() {
    return "remove";
  }

  @Override
  public void execute(CommandSender commandSender, String[] strings) {
    if (strings.length == 1 && strings[0].equalsIgnoreCase(getName())) {
      if (commandSender.hasPermission(MessagesData.FARMWORLD_COMMAND_PERMISSION_ADMIN)) {
        if (Locations.exitsLocation("Farm")) {
          LocationSQL.deleteLoc("Farm");
          Locations.LOCATIONS.remove("Farm");
          commandSender.sendMessage(MessagesData.FARMWORLD_COMMAND_MESSAGE_WARP_REMOVE);
        }
      } else {
        commandSender.sendMessage(MessagesData.NOPERMS);
      }
    }
  }
}
