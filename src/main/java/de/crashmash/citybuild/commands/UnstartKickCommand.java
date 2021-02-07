package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.storage.StartkickSQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnstartKickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(ConfigData.CONFIG_COMMAND_UNSTARTKICK) {
            if (commandSender.hasPermission(MessagesData.UNSTARTKICK_COMMAND_PERMISSION_USE)) {
                if (strings.length == 1) {
                    OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(strings[0]);
                    if (null != targetPlayer && StartkickSQL.playerExists(targetPlayer.getUniqueId())) {
                        if (StartkickSQL.getDuration(targetPlayer.getUniqueId()) + MessagesData.STARTKICK_COMMAND_SETTING_DURATION * 1000L > System.currentTimeMillis()) {
                            StartkickSQL.setStartKick(targetPlayer.getUniqueId(), null, null);
                            commandSender.sendMessage(MessagesData.UNSTARTKICK_COMMAND_MESSAGE_UNKICKED.replace("[targetPlayer]", targetPlayer.getName()));
                        } else {
                            commandSender.sendMessage(MessagesData.UNSTARTKICK_COMMAND_MESSAGE_NOT_KICKED.replace("[targetPlayer]", targetPlayer.getName()));
                        }
                    } else {
                        commandSender.sendMessage(MessagesData.UNSTARTKICK_COMMAND_MESSAGE_PLAYER_NOT_FOUND.replace("[targetPlayer]", strings[0]));
                    }
                } else {
                    commandSender.sendMessage(MessagesData.UNSTARTKICK_COMMAND_MESSAGE_USAGE);
                }
            } else {
                commandSender.sendMessage(MessagesData.NOPERMS);
            }
        } else {
            commandSender.sendMessage(MessagesData.DEACTIVATED);
        }
        return false;
    }
}
