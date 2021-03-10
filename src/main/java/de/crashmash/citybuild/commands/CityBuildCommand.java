package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CityBuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("§f===============================");
        commandSender.sendMessage("§bVersion: §f" + CityBuildV2.getPlugin().getDescription().getVersion());
        commandSender.sendMessage("§bAuthors: §f" + CityBuildV2.getPlugin().getDescription().getAuthors());
        commandSender.sendMessage("§f===============================");
        return false;
    }
}
