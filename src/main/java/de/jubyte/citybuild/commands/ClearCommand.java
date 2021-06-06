package de.jubyte.citybuild.commands;

import de.crashmash.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KeinByte
 * @since 06.06.2021
 */

public class ClearCommand extends AbstractCommand {

    public ClearCommand() {
        super(ConfigData.CONFIG_COMMAND_CLEAR_NAME, null, "Cleared Player Inventory", ConfigData.CONFIG_COMMAND_CLEAR_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        }else{

            Player player = (Player) commandSender;

            if (strings.length == 0){
                if (player.hasPermission(MessagesData.CLEAR_COMMAND_PERMISSION_CLEAR_SELF)){

                    player.getInventory().clear();
                    player.sendMessage(MessagesData.CLEAR_COMMAND_MESSAGE_CLEARED_SELF);

                }else{
                    player.sendMessage(MessagesData.NOPERMS);
                }
            }else if (strings.length == 1){
                if (player.hasPermission(MessagesData.CLEAR_COMMAND_PERMISSION_CLEAR_OTHER)){

                    Player target = Bukkit.getPlayer(strings[0]);

                    if (target != null){

                        target.getInventory().clear();
                        target.sendMessage(MessagesData.CLEAR_COMMAND_MESSAGE_CLEARED_SELF);
                        player.sendMessage(MessagesData.CLEAR_COMMAND_MESSAGE_CLEARED_OTHER.replace("[targetPlayer]", strings[0]));

                    }else{
                        player.sendMessage(MessagesData.CLEAR_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE.replace("[targetPlayer]", strings[0]));
                    }

                }else{
                    player.sendMessage(MessagesData.NOPERMS);
                }
            }

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            final List<String> players = new ArrayList<>();
            for(Player all : Bukkit.getOnlinePlayers()) {
                players.add(all.getName());
            }
            return players;
        }
        return null;
    }

}
