package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.manager.mutep.MuteP;
import de.crashmash.citybuild.storage.MutepSQL;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UnmutePCommand extends AbstractCommand {

    public UnmutePCommand() {
        super(ConfigData.CONFIG_COMMAND_UNMUTEP_NAME, null,  "Unmute muted Players.", ConfigData.CONFIG_COMMAND_UNMUTEP_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission(MessagesData.UNMUTEP_COMMAND_PERMISSION_USE)) {
            if(strings.length == 1) {
                Player targetPlayer = Bukkit.getPlayer(strings[0]);
                if(targetPlayer != null) {
                    if(targetPlayer.isOnline()) {
                        if (MuteP.playerIsMutedP(targetPlayer)) {
                            MuteP.playerUnmutedP(targetPlayer);
                            commandSender.sendMessage(MessagesData.UNMUTEP_COMMAND_MESSAGE_UNMUTED.replace("[targetPlayer]", targetPlayer.getDisplayName()));
                            targetPlayer.sendMessage(MessagesData.UNMUTEP_COMMAND_MESSAGE_UNMUTED_TARGETPLAYER.replace("[targetPlayer]", targetPlayer.getDisplayName()));
                        } else {
                            commandSender.sendMessage(MessagesData.UNMUTEP_COMMAND_MESSAGE_ISNT_MUTED.replace("[targetPlayer]", targetPlayer.getDisplayName()));
                        }
                    }
                } else {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
                    if(MutepSQL.playerExists(offlinePlayer.getUniqueId())) {
                        if (MutepSQL.getDuration(offlinePlayer.getUniqueId()) + MessagesData.MUTEP_COMMAND_SETTINGS_DURATION * 1000L > System.currentTimeMillis()) {
                            MutepSQL.setMuteP(offlinePlayer.getUniqueId(), null, 0, null);
                            commandSender.sendMessage(MessagesData.UNMUTEP_COMMAND_MESSAGE_UNMUTED.replace("[targetPlayer]", offlinePlayer.getName()));
                        } else {
                            commandSender.sendMessage(MessagesData.UNMUTEP_COMMAND_MESSAGE_ISNT_MUTED.replace("[targetPlayer]", offlinePlayer.getName()));
                        }
                    } else
                        commandSender.sendMessage(MessagesData.UNMUTEP_COMMAND_MESSAGE_PLAYER_NOT_FOUND.replace("[targetPlayer]", strings[0]));
                }
            } else {
                commandSender.sendMessage(MessagesData.UNMUTEP_COMMAND_MESSAGE_USAGE);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            final List<String> players = new ArrayList<>();

            return players;
        }
        return null;
    }
}