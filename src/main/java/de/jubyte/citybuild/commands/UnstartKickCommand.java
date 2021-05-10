package de.jubyte.citybuild.commands;

import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.manager.startkick.StartKickPlayer;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class UnstartKickCommand extends AbstractCommand {

    public UnstartKickCommand() {
        super(ConfigData.CONFIG_COMMAND_UNSTARTKICK_NAME, null, "Unkick other players who have been kicked out by the community.", ConfigData.CONFIG_COMMAND_UNSTARTKICK_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission(MessagesData.UNSTARTKICK_COMMAND_PERMISSION_USE)) {
            if (strings.length == 1) {
                OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(strings[0]);
                if (null != targetPlayer) {
                    StartKickPlayer startKickPlayer = CityBuildV2.getPLUGIN().getStartKickCache().getPlayerByUUID(targetPlayer.getUniqueId());
                    if (startKickPlayer.isStartKicked()) {
                        startKickPlayer.setReason(null);
                        startKickPlayer.setDuration(0);
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
        return false;
    }
}
