package de.jubyte.citybuild.commands;

import com.jubyte.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin_SGD
 * @since 09.08.2021
 */

public class GodCommand extends AbstractCommand {

    public static List<Player> playerGodList = new ArrayList<>();

    public GodCommand() {
        super(ConfigData.CONFIG_COMMAND_GOD_NAME, null,
                "Make yourself and other players invincible.", ConfigData.CONFIG_COMMAND_GOD_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
                return false;
            }
            Player player = (Player) commandSender;
            if(!player.hasPermission(MessagesData.GOD_COMMAND_PERMISSION_USE)) {
                commandSender.sendMessage(MessagesData.NOPERMS);
                return false;
            }
            if(playerGodList.contains(player)) {
                player.setInvulnerable(false);
                playerGodList.remove(player);
                player.sendMessage(MessagesData.GOD_COMMAND_MESSAGE_DEACTIVATED_SELF);
            } else {
                player.setInvulnerable(true);
                playerGodList.add(player);
                player.sendMessage(MessagesData.GOD_COMMAND_MESSAGE_ACTIVATED_SELF);
            }
        } else if(strings.length == 1) {
            if(!commandSender.hasPermission(MessagesData.GOD_COMMAND_PERMISSION_OTHER)) {
                commandSender.sendMessage(MessagesData.NOPERMS);
                return false;
            }
            Player targetPlayer = Bukkit.getPlayer(strings[0]);
            if(targetPlayer != null) {
                if(playerGodList.contains(targetPlayer)) {
                    targetPlayer.setInvulnerable(false);
                    playerGodList.remove(targetPlayer);
                    commandSender.sendMessage(MessagesData.GOD_COMMAND_MESSAGE_DEACTIVATED_OTHER);
                } else {
                    targetPlayer.setInvulnerable(true);
                    playerGodList.add(targetPlayer);
                    commandSender.sendMessage(MessagesData.GOD_COMMAND_MESSAGE_ACTIVATED_OTHER);
                }
            } else {
                commandSender.sendMessage(MessagesData.GOD_COMMAND_MESSAGE_PLAYERISOFFLINE);
            }
        } else {
            commandSender.sendMessage(MessagesData.GOD_COMMAND_MESSAGE_USAGE);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> playerList = new ArrayList<>();
        if(strings.length == 1 && commandSender.hasPermission(MessagesData.FARMWORLD_COMMAND_PERMISSION_ADMIN)) {
            for(Player players : Bukkit.getOnlinePlayers()) {
                playerList.add(players.getName());
            }
            return playerList;
        }
        return null;
    }
}