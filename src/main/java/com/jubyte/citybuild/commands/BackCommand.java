package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.listener.player.PlayerDeathListener;
import com.jubyte.developerapi.commands.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author KeinByte
 * @since 03.03.2022
 */

public class BackCommand extends AbstractCommand {

    public BackCommand(){
        super(ConfigData.CONFIG_COMMAND_BACk_NAME, null, "Teleport to the last death location", ConfigData.CONFIG_COMMAND_BACk_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        }else{
            Player player = (Player) commandSender;
            if (player.hasPermission(MessagesData.BACK_COMMAND_PERMISSION_USE)){
                if (strings.length == 0){
                    if (PlayerDeathListener.BACK_LOCATION.containsKey(player)){
                        player.teleport(PlayerDeathListener.BACK_LOCATION.get(player));
                        player.sendMessage(MessagesData.BACK_COMMAND_TELEPORT_SUCCESSFUL);
                    }else{
                        player.sendMessage(MessagesData.BACK_COMMAND_NO_BACK_POINT);
                    }
                }else{
                    player.sendMessage(MessagesData.BACK_COMMAND_USAGE);
                }
            }else{
                player.sendMessage(MessagesData.NOPERMS);
            }
        }

        return false;
    }
}
