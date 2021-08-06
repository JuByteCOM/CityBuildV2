package de.jubyte.citybuild.commands;


import com.jubyte.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SudoCommand extends AbstractCommand {

    public SudoCommand() {
        super(ConfigData.CONFIG_COMMAND_SUDO_NAME, null, "Perform for other commands!", ConfigData.CONFIG_COMMAND_SUDO_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

       if (!(commandSender instanceof Player)){
           commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
       }else{

           Player player = (Player) commandSender;

           if (player.hasPermission(MessagesData.SUDO_COMMAND_PERMISSION_USE)){
               if (strings.length < 2){
                   player.sendMessage(MessagesData.SUDO_COMMAND_MESSAGE_USAGE);
               }else{

                   Player target = Bukkit.getPlayer(strings[0]);

                   if (target != null){

                       if (target.hasPermission(MessagesData.SUDO_COMMAND_PERMISSION_BYPASS)){
                           player.sendMessage(MessagesData.SUDO_COMMAND_MESSAGE_HAS_BYPASS);
                           return true;
                       }

                       String[] sudoCommand = Arrays.copyOfRange(strings, 1, strings.length);
                       String newCommand = String.join(" ", sudoCommand);

                       target.performCommand(newCommand);

                       player.sendMessage(MessagesData.SUDO_COMMAND_MESSAGE_HAS_PERFORMED.replace("[command]", newCommand).replace("[targetPlayer]", target.getName()));

                   }else{
                       player.sendMessage(MessagesData.SUDO_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE);
                   }

               }
           }else{
               player.sendMessage(MessagesData.NOPERMS);
           }

       }

        return false;
    }
}
