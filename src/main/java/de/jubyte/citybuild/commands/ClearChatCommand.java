package de.jubyte.citybuild.commands;

import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand extends AbstractCommand {

    public ClearChatCommand() {
        super(ConfigData.CONFIG_COMMAND_CHATCLEAR_NAME, null, "Delete the chat in the event of spam, advertising, insults or other situations.", ConfigData.CONFIG_COMMAND_CHATCLEAR_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission(MessagesData.CLEARCHAT_COMMAND_PERMISSION_USE)){
            if (strings.length == 0){
                for (int i = 0; i < 200; i++) {
                    for(Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage(" ");
                    }
                }
                Bukkit.broadcastMessage(MessagesData.CLEARCHAT_COMMAND_MESSAGE_CLEARED.replace("[player]", commandSender.getName()));
            }else{
                commandSender.sendMessage(MessagesData.CLEARCHAT_COMMAND_MESSAGE_USAGE);
            }
        }else{
            commandSender.sendMessage(MessagesData.NOPERMS);
        }
        return false;
    }
}

