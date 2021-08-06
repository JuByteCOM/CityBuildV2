package de.jubyte.citybuild.commands;

import com.jubyte.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.CityBuildV2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author Justin_SGD
 * @since 06.08.2021
 */

public class ReloadConfigCommand extends AbstractCommand {

    public ReloadConfigCommand() {
        super("reloadconfig", null, "Let other players decide your luck!");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            CityBuildV2.getPLUGIN().loadConfig();
            CityBuildV2.getPLUGIN().loadMySQLConfig();
            CityBuildV2.getPLUGIN().loadMessagesConfig();

            CityBuildV2.getPLUGIN().saveConfig();
            CityBuildV2.getPLUGIN().saveMySQLConfig();
            CityBuildV2.getPLUGIN().saveMessagesConfig();

            CityBuildV2.getPLUGIN().reloadConfig();
            CityBuildV2.getPLUGIN().reloadMySQLConfig();
            CityBuildV2.getPLUGIN().reloadMessagesConfig();

            commandSender.sendMessage("§aHat funktioniert!");
        } else {
            commandSender.sendMessage("§cBitte nutze: §e/reloadconfig");
        }
        return false;
    }
}