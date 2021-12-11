package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CityBuildCommand extends AbstractCommand {

  public CityBuildCommand() {
    super(
        ConfigData.CONFIG_COMMAND_CITYBUILDV2_NAME,
        null,
        "Outputs plugin information.",
        ConfigData.CONFIG_COMMAND_CITYBUILDV2_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    commandSender.sendMessage("§7===============================");
    commandSender.sendMessage(
        "§bVersion: §f" + CityBuildV2.getPlugin().getDescription().getVersion());
    commandSender.sendMessage(
        "§bAuthors: §f" + CityBuildV2.getPlugin().getDescription().getAuthors());
    commandSender.sendMessage("§7===============================");
    return false;
  }
}
