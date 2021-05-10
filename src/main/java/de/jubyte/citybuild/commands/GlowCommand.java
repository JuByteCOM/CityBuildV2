package de.jubyte.citybuild.commands;

import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.manager.glow.GlowPlayer;
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
                    GlowPlayer glowPlayer = CityBuildV2.getPLUGIN().getGlowCache().getPlayerByUUID(player.getUniqueId());
                    if (glowPlayer.isState()) {
                        glowPlayer.setState(false);
                        player.setGlowing(false);
                        player.sendMessage(MessagesData.GLOW_COMMAND_TOGGLE_OFF);
                    } else {
                        glowPlayer.setState(true);
                        player.setGlowing(true);
                        player.sendMessage(MessagesData.GLOW_COMMAND_TOGGLE_ON);
                    }
                } else {
                    player.sendMessage(MessagesData.GLOW_COMMAND_MESSAGE_USAGE);
                }
            } else {
                player.sendMessage(MessagesData.NOPERMS);
            }
        }

        return false;
    }
}
