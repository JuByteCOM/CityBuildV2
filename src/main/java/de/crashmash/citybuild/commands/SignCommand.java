package de.crashmash.citybuild.commands;

import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SignCommand extends AbstractCommand {

    public SignCommand() {
        super("command", "usage", "description", "aliases");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {



        return false;
    }
}
