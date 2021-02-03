package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NeinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(ConfigData.CONFIG_COMMAND_NEIN) {
                if(strings.length == 0) {
                    if (StartkickCommand.isStartkick) {
                        if(!CityBuildV2.getPlugin().getVOTING_NO().contains(player.getName())) {
                            if(!CityBuildV2.getPlugin().getVOTING_YES().contains(player.getName())) {
                                CityBuildV2.getPlugin().getVOTING_NO().add(player.getName());
                                player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_SUCCESFUL_VOTED);
                            } else {
                                player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_VOTED_FOR_YES);
                            }
                        } else {
                            player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_ALREADY_VOTED);
                        }
                    } else {
                        player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_NO_STARTKICK);
                    }
                } else {
                    player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_USAGE);
                }
            } else {
                player.sendMessage(MessagesData.DEACTIVATED);
            }
        }
        return false;
    }
}