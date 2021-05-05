package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JaCommand extends AbstractCommand {

    public JaCommand() {
        super(ConfigData.CONFIG_COMMAND_JA_NAME, null, "In a vote, decide yes.", ConfigData.CONFIG_COMMAND_JA_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            if (StartkickCommand.isStartkick) {
                if(!CityBuildV2.getPlugin().getVOTING_YES().contains(commandSender.getName())) {
                    if(!CityBuildV2.getPlugin().getVOTING_NO().contains(commandSender.getName())) {
                        CityBuildV2.getPlugin().getVOTING_YES().add(commandSender.getName());
                        commandSender.sendMessage(MessagesData.YES_COMMAND_MESSAGE_SUCCESFUL_VOTED);
                    } else {
                        commandSender.sendMessage(MessagesData.YES_COMMAND_MESSAGE_VOTED_FOR_NO);
                    }
                } else {
                    commandSender.sendMessage(MessagesData.YES_COMMAND_MESSAGE_ALREADY_VOTED);
                }
            } else {
                commandSender.sendMessage(MessagesData.YES_COMMAND_MESSAGE_NO_STARTKICK);
            }
        } else {
            commandSender.sendMessage(MessagesData.YES_COMMAND_MESSAGE_USAGE);
        }
        return false;
    }
}
