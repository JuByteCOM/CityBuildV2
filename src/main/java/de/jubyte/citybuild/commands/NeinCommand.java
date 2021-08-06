package de.jubyte.citybuild.commands;

import com.jubyte.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class NeinCommand extends AbstractCommand {

    public NeinCommand() {
        super(ConfigData.CONFIG_COMMAND_NEIN_NAME, null, "In a vote, decide no.", ConfigData.CONFIG_COMMAND_NEIN_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            if (StartkickCommand.isStartkick) {
                if(!CityBuildV2.getPLUGIN().getVOTING_NO().contains(commandSender.getName())) {
                    if(!CityBuildV2.getPLUGIN().getVOTING_YES().contains(commandSender.getName())) {
                        CityBuildV2.getPLUGIN().getVOTING_NO().add(commandSender.getName());
                        commandSender.sendMessage(MessagesData.NO_COMMAND_MESSAGE_SUCCESFUL_VOTED);
                    } else {
                        commandSender.sendMessage(MessagesData.NO_COMMAND_MESSAGE_VOTED_FOR_YES);
                    }
                } else {
                    commandSender.sendMessage(MessagesData.NO_COMMAND_MESSAGE_ALREADY_VOTED);
                }
            } else {
                commandSender.sendMessage(MessagesData.NO_COMMAND_MESSAGE_NO_STARTKICK);
            }
        } else {
            commandSender.sendMessage(MessagesData.NO_COMMAND_MESSAGE_USAGE);
        }
        return false;
    }
}