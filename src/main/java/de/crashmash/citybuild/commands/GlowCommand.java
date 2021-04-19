package de.crashmash.citybuild.commands;

import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.storage.GlowSQL;
import de.crashmash.developerapi.commands.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlowCommand extends AbstractCommand {

    public GlowCommand() {
        super(ConfigData.CONFIG_COMMAND_GLOW_NAME, null, "For Glowing of Player", ConfigData.CONFIG_COMMAND_GLOW_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        }else{
            Player player = (Player) commandSender;

            if (player.hasPermission(MessagesData.GLOW_COMMAND_PERMISSION_USE)){
                if (strings.length == 0){
                    if (GlowSQL.playerExists(player.getUniqueId())){
                        if (GlowSQL.getGlowEffect(player.getUniqueId())){
                            GlowSQL.setGlowEffect(player.getUniqueId(), false);
                            player.setGlowing(false);
                            player.sendMessage(MessagesData.GLOW_COMMAND_TOGGLE_OFF);
                        }else{
                            GlowSQL.setGlowEffect(player.getUniqueId(), true);
                            player.setGlowing(true);
                            player.sendMessage(MessagesData.GLOW_COMMAND_TOGGLE_ON);
                        }
                    }
                }
            } else {
                player.sendMessage(MessagesData.NOPERMS);
            }
        }

        return false;
    }
}
