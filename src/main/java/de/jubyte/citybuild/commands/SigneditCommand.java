package de.jubyte.citybuild.commands;

import de.jubyte.citybuild.CityBuildV2;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.data.MessagesData;
import de.crashmash.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.utils.PlotUtilsV6;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SigneditCommand extends AbstractCommand {

    public SigneditCommand() {
        super(ConfigData.CONFIG_COMMAND_SCHILD_NAME, null, "Look at a sign to edit signs.", ConfigData.CONFIG_COMMAND_SCHILD_ALIASES);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
        } else {
            Player player = (Player)commandSender;
            if(Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") != null) {
                if(player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_USE)) {
                    Block block = player.getTargetBlock(null, 5);
                    if (block.getState() instanceof Sign) {
                        if (PlotUtilsV6.getPlot(player.getTargetBlock(null,5).getLocation()) != null ||
                                player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_NOTONPLOT)) {
                            if(PlotUtilsV6.isInPlot(player.getLocation()) ||
                                    player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_NOPLOTOWNER)) {
                                if (PlotUtilsV6.getPlot(player.getLocation()).isOwner(player.getUniqueId()) ||
                                        player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_NOPLOTOWNER)) {
                                    Sign sign = (Sign) block.getState();
                                    System.out.println();
                                    CityBuildV2.getSignEdit().editSign(player, sign);
                                } else {
                                    player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOPLOTOWNER);
                                }
                            } else {
                                player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_PLAYERNOTONPLOT);
                            }
                        } else {
                            player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_SIGNNOTONPLOT);
                        }
                    } else {
                        player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOTSIGN);
                    }
                } else {
                    player.sendMessage(MessagesData.NOPERMS);
                }
            } else {
                if(player.hasPermission("citybuild.schild.admin")) {
                    player.sendMessage("§cPlotSquaredV5 is missing.");
                } else {
                    player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOPLOTSQUARED);
                }
            }
        }
        return false;
    }
}