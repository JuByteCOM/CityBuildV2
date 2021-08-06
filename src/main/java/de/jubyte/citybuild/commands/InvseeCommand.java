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
 * @since 04.08.2021
 */

public class InvseeCommand extends AbstractCommand {

    public InvseeCommand() {
        super(ConfigData.CONFIG_COMMAND_INVSEE_NAME, null, "See inventories off other players.", ConfigData.CONFIG_COMMAND_INVSEE_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            if(strings.length == 1) {
                Player player = (Player) commandSender;
                if(player.hasPermission(MessagesData.INVSEE_COMMAND_PERMISSION_USE)) {
                    Player targetPlayer = Bukkit.getPlayer(strings[0]);
                    if(targetPlayer != null) {
                        player.openInventory(targetPlayer.getInventory());
                    } else {
                        player.sendMessage(MessagesData.INVSEE_COMMAND_MESSAGE_WARP_PLAYERISOFFLINE);
                    }
                } else {
                    player.sendMessage(MessagesData.NOPERMS);
                }
            } else {
                commandSender.sendMessage(MessagesData.INVSEE_COMMAND_MESSAGE_USAGE);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> playerList = new ArrayList<>();
        if(strings.length == 1 && commandSender.hasPermission(MessagesData.INVSEE_COMMAND_PERMISSION_USE)) {
            for(Player players : Bukkit.getOnlinePlayers()) {
                playerList.add(players.getName());
            }
        }
        return playerList;
    }
}