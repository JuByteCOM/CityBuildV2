package de.jubyte.citybuild.commands;

import com.jubyte.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CityBuildCommand extends AbstractCommand {

    public CityBuildCommand() {
        super(ConfigData.CONFIG_COMMAND_CITYBUILDV2_NAME, null, "Outputs plugin information.", ConfigData.CONFIG_COMMAND_CITYBUILDV2_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("§7===============================");
        commandSender.sendMessage("§bVersion: §f" + CityBuildV2.getPLUGIN().getDescription().getVersion());
        commandSender.sendMessage("§bAuthors: §f" + CityBuildV2.getPLUGIN().getDescription().getAuthors());
        commandSender.sendMessage("§7===============================");
        return false;
    }
}
