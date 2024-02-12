package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.mutep.MutepPlayer;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UnmutePCommand extends AbstractCommand {

    public UnmutePCommand() {
        super(
                ConfigData.CONFIG_COMMAND_UNMUTEP_NAME,
                null,
                "Unmute muted Players.",
                ConfigData.CONFIG_COMMAND_UNMUTEP_ALIASES);
    }

    @Override
    public boolean onCommand(
            CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission(MessagesData.UNMUTEP_COMMAND_PERMISSION_USE)) {
            if (strings.length == 1) {
                Player targetPlayer = Bukkit.getPlayer(strings[0]);
                if (targetPlayer != null) {
                    MutepPlayer mutepPlayer =
                            CityBuildV2.getPlugin().getMutePCache().getPlayerByUUID(targetPlayer.getUniqueId());
                    if (targetPlayer.isOnline()) {
                        if (mutepPlayer.playerIsMutep()) {
                            mutepPlayer.playerUnmutep();
                            commandSender.sendMessage(
                                    MessagesData.UNMUTEP_COMMAND_MESSAGE_UNMUTED.replace(
                                            "[targetPlayer]", targetPlayer.getDisplayName()));
                            targetPlayer.sendMessage(
                                    MessagesData.UNMUTEP_COMMAND_MESSAGE_UNMUTED_TARGETPLAYER.replace(
                                            "[targetPlayer]", targetPlayer.getDisplayName()));
                        } else {
                            commandSender.sendMessage(
                                    MessagesData.UNMUTEP_COMMAND_MESSAGE_ISNT_MUTED.replace(
                                            "[targetPlayer]", targetPlayer.getDisplayName()));
                        }
                    }
                } else {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
                    MutepPlayer mutepPlayer =
                            CityBuildV2.getPlugin().getMutePCache().getPlayerByUUID(offlinePlayer.getUniqueId());
                    if (offlinePlayer != null) {
                        if (mutepPlayer.playerIsMutep()) {
                            mutepPlayer.playerUnmutep();
                            commandSender.sendMessage(
                                    MessagesData.UNMUTEP_COMMAND_MESSAGE_UNMUTED.replace(
                                            "[targetPlayer]", offlinePlayer.getName()));
                        } else {
                            commandSender.sendMessage(
                                    MessagesData.UNMUTEP_COMMAND_MESSAGE_ISNT_MUTED.replace(
                                            "[targetPlayer]", offlinePlayer.getName()));
                        }
                    } else {
                        commandSender.sendMessage(
                                MessagesData.UNMUTEP_COMMAND_MESSAGE_PLAYER_NOT_FOUND.replace(
                                        "[targetPlayer]", strings[0]));
                    }
                }
            } else {
                commandSender.sendMessage(MessagesData.UNMUTEP_COMMAND_MESSAGE_USAGE);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(
            CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            final List<String> players = new ArrayList<>();
            for (Player all : Bukkit.getOnlinePlayers()) {
                players.add(all.getName());
            }
            return players;
        }
        return null;
    }
}
