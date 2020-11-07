package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(ConfigData.CONFIG_COMMAND_TELEPORT) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
            } else {
                Player player = (Player) commandSender;
                if (player.hasPermission(MessagesData.TELEPORT_COMMAND_PERMISSION_USE)) {
                    if (strings.length == 1) {
                        Player target = Bukkit.getPlayer(strings[0]);
                        if (target != null) {
                            player.teleport(target.getLocation());
                            player.sendMessage(MessagesData.TELEPORT_COMMAND_MESSAGE_TPTOPLAYER.replace("[targetPlayer]", target.getName()));
                        } else {
                            player.sendMessage(MessagesData.TELEPORT_COMMAND_MESSAGE_PLAYERNOTFOUND.replace("[targetPlayer]", strings[0]));
                        }
                    } else if (strings.length == 3) {
                        double x = Double.parseDouble(strings[0]);
                        double y = Double.parseDouble(strings[1]);
                        double z = Double.parseDouble(strings[2]);
                        if ((x < 3.0E7D) || (y < 3.0E7D) || (z < 3.0E7D) || (x > -3.0E7D) || (y > -3.0E7D) || (z > -3.0E7D)) {
                            Location location = new Location(player.getWorld(), x, y, z);
                            player.teleport(location);
                            player.sendMessage(MessagesData.TELEPORT_COMMAND_MESSAGE_TPTOLOCATION.replace("[location]", location.toString()));
                        } else {
                            player.sendMessage(MessagesData.TELEPORT_COMMAND_MESSAGE_FALSELOCATION);
                        }
                    }
                }
            }
        } else {
            commandSender.sendMessage(MessagesData.DEACTIVATED);
        }
        return false;
    }
}
