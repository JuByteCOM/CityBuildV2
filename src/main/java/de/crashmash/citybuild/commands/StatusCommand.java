package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.storage.StatusSQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatusCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(ConfigData.CONFIG_COMMAND_STATUS) {
                if (player.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE)) {
                    if (strings.length >= 1) {
                        if (StatusSQL.playerExists(player.getUniqueId())) {
                            if (strings[0].equalsIgnoreCase("off") || strings[0].equalsIgnoreCase("deaktiviert")) {
                                if (StatusSQL.hasStatus(player.getUniqueId())) {
                                    StatusSQL.setStatus(player.getUniqueId(), null);
                                } else {
                                    player.sendMessage(MessagesData.STATUS_COMMAND_MESSAGE_HASNOTSTATUS);
                                }
                            } else {
                                String message = "";
                                for (int i = 0; i < strings.length; i++) {
                                    message = message + strings[i] + " ";
                                }
                                StatusSQL.setStatus(player.getUniqueId(), message);
                                player.sendMessage(MessagesData.STATUS_COMMAND_MESSAGE_SETSTATUS.replace("[status]", message));
                            }
                        } else {
                            player.sendMessage(MessagesData.MYSQL_ERROR);
                        }
                    } else {
                        player.sendMessage(MessagesData.STATUS_COMMAND_MESSAGE_USAGE);
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
