package de.crashmash.citybuild.commands;

import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.player.PlotPlayer;
import de.crashmash.citybuild.CityBuildV2;
import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class SchildCommand implements CommandExecutor {

    private final PlotAPI plotAPI = new PlotAPI();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player) commandSender;
            if(ConfigData.CONFIG_COMMAND_SCHILD) {
                if(Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") != null) {
                    if(player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_USE)) {
                        Block block = player.getTargetBlock(null, 5);
                        PlotPlayer plotPlayer = plotAPI.wrapPlayer(player.getUniqueId());
                        if (block.getState() instanceof Sign) {
                            if (plotPlayer.getCurrentPlot().getPosition() == player.getTargetBlock(null, 5) ||
                                    player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_NOTONPLOT)) {
                                if (plotPlayer.getCurrentPlot().isOwner(player.getUniqueId()) ||
                                        player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_NOPLOTOWNER)) {
                                    Sign sign = (Sign) block.getState();
                                    CityBuildV2.getSignEdit().editSign(player, sign);
                                } else {
                                    player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOPLOTOWNER);
                                }
                            } else {
                                player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOTONPLOT);
                            }
                        } else {
                            player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOTSIGN);
                        }
                    } else {
                        player.sendMessage(MessagesData.NOPERMS);
                    }
                } else {
                    player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOPLOTSQUARED);
                }
            } else {
                player.sendMessage(MessagesData.DEACTIVATED);
            }
        }
        return false;
    }
}
