package de.jubyte.citybuild.commands;

import de.crashmash.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudoCommand extends AbstractCommand {

    public SudoCommand() {
        super(ConfigData.CONFIG_COMMAND_SUDO_NAME, null, "Perform for other commands!", ConfigData.CONFIG_COMMAND_SUDO_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

           Player player = (Player) commandSender;

           if (commandSender.hasPermission(MessagesData.SUDO_COMMAND_PERMISSION_USE)){
               if (strings.length < 2){
                   commandSender.sendMessage(MessagesData.SUDO_COMMAND_MESSAGE_USAGE);
               }else{

                   Player target = Bukkit.getPlayer(strings[0]);

                   if (target != null){

                       if (target.hasPermission(MessagesData.SUDO_COMMAND_PERMISSION_BYPASS)){
                           commandSender.sendMessage(MessagesData.SUDO_COMMAND_MESSAGE_HAS_BYPASS);
                           return true;
                       }

                       String[] sudoCommand = Arrays.copyOfRange(strings, 1, strings.length);
                       String newCommand = String.join(" ", sudoCommand);

                       target.performCommand(newCommand);

                       commandSender.sendMessage(MessagesData.SUDO_COMMAND_MESSAGE_HAS_PERFORMED.replace("[command]", newCommand).replace("[targetPlayer]", target.getName()));

                   }else{
                       commandSender.sendMessage(MessagesData.SUDO_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE);
                   }

               }
           }else{
               commandSender.sendMessage(MessagesData.NOPERMS);
           }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        final List<String> players = new ArrayList<>();
        for(Player all : Bukkit.getOnlinePlayers()) {
            players.add(all.getName());
        }
        return players;
    }

}
