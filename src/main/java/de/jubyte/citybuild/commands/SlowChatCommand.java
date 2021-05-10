package de.jubyte.citybuild.commands;

import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlowChatCommand extends AbstractCommand {

    public static boolean SLOWCHAT_STATUS = false;

    public SlowChatCommand() {
        super(ConfigData.CONFIG_COMMAND_SLOWCHAT_NAME, null, "If there is spam or other situations, slow down the chat.", ConfigData.CONFIG_COMMAND_SLOWCHAT_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission(MessagesData.SLOWCHAT_COMMAND_PERMISSION_USE)) {
            if(strings.length == 0) {
                if(SLOWCHAT_STATUS) {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage(MessagesData.SLOWCHAT_COMMAND_MESSAGE_DEACTIVATED.replace("[player]", commandSender.getName()));
                        SLOWCHAT_STATUS = false;
                    }
                } else {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage(MessagesData.SLOWCHAT_COMMAND_MESSAGE_ACTIVATED.replace("[player]", commandSender.getName()));
                        SLOWCHAT_STATUS = true;
                    }
                }
            } else {
                commandSender.sendMessage(MessagesData.SLOWCHAT_COMMAND_MESSAGE_USAGE);
            }
        } else {
            commandSender.sendMessage(MessagesData.NOPERMS);
        }
        return false;
    }
}
