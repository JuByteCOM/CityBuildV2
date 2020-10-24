package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(ConfigData.CONFIG_COMMAND_CHATCLEAR) {
                if (player.hasPermission(MessagesData.CLEARCHAT_COMMAND_PERMISSION_USE)){
                    if (strings.length == 0){
                        for (int i = 0; i < 200; i++) {
                            Bukkit.broadcastMessage(" ");
                        }
                        Bukkit.broadcastMessage(MessagesData.CLEARCHAT_COMMAND_MESSAGE_CLEARED.replace("[playername]", player.getName()).replace("[displayname]", player.getDisplayName()));
                    }else{
                        player.sendMessage(MessagesData.CLEARCHAT_COMMAND_MESSAGE_USAGE);
                    }
                }else{
                    player.sendMessage(MessagesData.NOPERMS);
                }
            } else {
                player.sendMessage(MessagesData.DEACTIVATED);
            }
        }
        return false;
    }
}

