package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlowChatCommand implements CommandExecutor {

    public static boolean SLOWCHAT_STATUS = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        command.setAliases(MessagesData.SLOWCHAT_COMMAND_MESSAGE_ALIASES);
        System.out.println(MessagesData.SLOWCHAT_COMMAND_MESSAGE_ALIASES.size());
        System.out.println(MessagesData.SLOWCHAT_COMMAND_MESSAGE_ALIASES);
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(ConfigData.CONFIG_COMMAND_SLOWCHAT) {
                if(player.hasPermission(MessagesData.SLOWCHAT_COMMAND_PERMISSION_USE)) {
                    if(strings.length == 0) {
                        if(SLOWCHAT_STATUS) {
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(MessagesData.SLOWCHAT_COMMAND_MESSAGE_DEACTIVATED.replace("[player]", player.getDisplayName()));
                                SLOWCHAT_STATUS = false;
                            }
                        } else {
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(MessagesData.SLOWCHAT_COMMAND_MESSAGE_ACTIVATED.replace("[player]", player.getDisplayName()));
                                SLOWCHAT_STATUS = true;
                            }
                        }
                    }
                } else {
                    player.sendMessage(MessagesData.NOPERMS);
                }
            } else {
                player.sendMessage(MessagesData.DEACTIVATED);
            }
        }
        return false;
    }
}
