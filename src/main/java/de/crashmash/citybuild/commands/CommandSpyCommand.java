package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class CommandSpyCommand implements CommandExecutor {

    private final Map<Player, Player> commandSpy_Map = CityBuildV2.getPlugin().getCOMMANDSPY_MAP();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        }
        Player player = (Player) commandSender;
        if(player.hasPermission(MessagesData.COMMANDSPY_COMMAND_PERMISSION_USE)) {
            if(strings.length == 0) {
                if(commandSpy_Map.containsKey(player)) {
                    player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_DISABLED);
                    commandSpy_Map.remove(player);
                } else {
                    player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_ENABLED_ALL);
                    commandSpy_Map.put(player, null);
                }
            } else if(strings.length == 1) {
                if(commandSpy_Map.containsKey(player)) {
                    player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_DISABLED);
                    commandSpy_Map.remove(player);
                } else {
                    Player targetPlayer = Bukkit.getPlayer(strings[0]);
                    if(targetPlayer != null) {
                        player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_ENABLED_PLAYER
                                .replace("[targetPlayer]", targetPlayer.getDisplayName()));
                        commandSpy_Map.put(player, targetPlayer);
                    } else {
                        player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE.replace("[targetPlayer]", strings[0]));
                    }
                }
            } else {
                player.sendMessage(MessagesData.COMMANDSPY_COMMAND_MESSAGE_USAGE);
            }
        } else {
            player.sendMessage(MessagesData.NOPERMS);
        }
        return false;
    }
}