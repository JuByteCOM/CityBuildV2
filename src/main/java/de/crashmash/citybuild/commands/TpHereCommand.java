package de.crashmash.citybuild.commands;


import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TpHereCommand extends AbstractCommand {

    public TpHereCommand(){
        super(ConfigData.CONFIG_COMMAND_TPHERE_NAME,null,"Teleport a target to you", ConfigData.CONFIG_COMMAND_TPHERE_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

       if (!(commandSender instanceof Player)) {
           commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
       } else {

           Player player = (Player) commandSender;
           if (player.hasPermission(MessagesData.TPHERE_COMMAND_PERMISSION_USE)){
               if (strings.length == 1){
                   Player target = Bukkit.getPlayer(strings[0]);
                   if (target != null) {
                       target.teleport(player);
                       player.sendMessage(MessagesData.TPHERE_COMMAND_MESSAGE_TPTARGETTOYOU.replace("[targetPlayer]", target.getName()));
                   } else {
                       player.sendMessage(MessagesData.TPHERE_COMMAND_MESSAGE_PLAYERNOTFOUND.replace("[targetPlayer]", strings[0]));
                   }
               } else {
                   player.sendMessage(MessagesData.TPHERE_COMMAND_MESSAGE_TPTARGETTOYOU_USAGE);
               }
           } else {
               player.sendMessage(MessagesData.NOPERMS);
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
