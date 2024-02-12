package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Justin_SGD
 * @since 19.04.2023
 */

public class VanishCommand extends AbstractCommand {

    private final CityBuildV2 cityBuildV2;

    public VanishCommand(CityBuildV2 cityBuildV2) {
        super(
                ConfigData.CONFIG_COMMAND_VANISH_NAME,
                null,
                "Go into vanish mode.",
                ConfigData.CONFIG_COMMAND_VANISH_ALIASES);
        this.cityBuildV2 = cityBuildV2;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.hasPermission(this.cityBuildV2.getMessageHandler()
                .getString("Commands.Vanish.Permissions.Use"))) {
            commandSender.sendMessage(this.cityBuildV2.getMessageHandler().getPrefixString("NoPerms"));
            return false;
        }
        if (strings.length == 0) {
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(this.cityBuildV2.getMessageHandler().getPrefixString("IsntPlayer"));
                return false;
            }
            Player player = (Player) commandSender;
            
        }
        return false;
    }
}