package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        }
        if(ConfigData.CONFIG_COMMAND_JA) {
            Player player = (Player) commandSender;
            if(strings.length == 0) {
                if (CityBuildV2.getPlugin().getVOTING_NO().contains(player)) {
                    player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_ALREADYVOTED);
                } else if(CityBuildV2.getPlugin().getVOTING_YES().contains(player)) {
                    player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_VOTEDFORYES);
                } else {
                    CityBuildV2.getPlugin().getVOTING_NO().add(player);
                    player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_SUCCESSVOTED);
                }
            } else {
                player.sendMessage(MessagesData.NO_COMMAND_MESSAGE_USAGE);
            }
        } else {
            commandSender.sendMessage(MessagesData.DEACTIVATED);
        }
        return false;
    }
}