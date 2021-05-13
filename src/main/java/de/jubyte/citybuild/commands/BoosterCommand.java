package de.jubyte.citybuild.commands;

import de.jubyte.citybuild.data.MessagesData;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoosterCommand extends AbstractCommand {

    public BoosterCommand(String command) {
        super(command);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
        }
        return false;
    }
}