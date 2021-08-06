package de.jubyte.citybuild.commands;


import com.jubyte.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.commands.subcommand.farmworld.FarmworldRemoveCommand;
import de.jubyte.citybuild.commands.subcommand.farmworld.FarmworldSetCommand;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.manager.locations.Locations;
import de.jubyte.citybuild.utils.SubCommand;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FarmworldCommand extends AbstractCommand {

    private final Map<String, SubCommand> subCommandMap = new LinkedHashMap<>();

    public FarmworldCommand() {
        super(ConfigData.CONFIG_COMMAND_FARMWORLD_NAME, null,
                "Teleport to Farm world.", ConfigData.CONFIG_COMMAND_FARMWORLD_ALIASES);
        this.subCommandMap.put("set", new FarmworldSetCommand());
        this.subCommandMap.put("remove", new FarmworldRemoveCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(strings.length == 0) {
                if (Locations.exitsLocation("Farm")) {
                    Location location = Locations.getLocation("Farm");
                    player.teleport(location);
                    player.sendMessage(MessagesData.FARMWORLD_COMMAND_MESSAGE_TELEPORTED);
                } else {
                    player.sendMessage(MessagesData.FARMWORLD_COMMAND_MESSAGE_NOT_FOUND);
                }
            } else if(strings.length == 1) {
                SubCommand subCommand = this.subCommandMap.get(strings[0].toLowerCase());
                if (subCommand != null) {
                    subCommand.execute(commandSender, strings);
                }
            } else {
                player.sendMessage(MessagesData.FARMWORLD_COMMAND_MESSAGE_USAGE);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] strings) {
        if(strings.length == 1 && sender.hasPermission(MessagesData.FARMWORLD_COMMAND_PERMISSION_ADMIN)) {
            return new ArrayList<>(subCommandMap.keySet());
        }
        return null;
    }
}
