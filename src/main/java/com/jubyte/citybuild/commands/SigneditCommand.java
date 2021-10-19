package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.utils.PlotUtilsV6;
import com.jubyte.developerapi.commands.AbstractCommand;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SigneditCommand extends AbstractCommand {

  public SigneditCommand() {
    super(
        ConfigData.CONFIG_COMMAND_SCHILD_NAME,
        null,
        "Look at a sign to edit signs.",
        ConfigData.CONFIG_COMMAND_SCHILD_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(MessagesData.ISNOT_PLAYER);
    } else {
      Player player = (Player) commandSender;
      if (Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") != null
          && Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
        if (player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_USE)) {
          Block block = player.getTargetBlock(null, 5);
          if (block.getState() instanceof Sign) {
            PlotPlayer<Player> plotPlayer = PlotPlayer.from(player);
            Plot plot = plotPlayer.getCurrentPlot();
            if (PlotUtilsV6.getPlot(block.getLocation()) != null
                || player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_NOTONPLOT)) {
              if (plot != null
                  || player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_NOPLOTOWNER)) {
                if (plot != null && plot.hasOwner()
                    || plot != null && plot.isOwner(player.getUniqueId())
                    || player.hasPermission(MessagesData.SCHILD_COMMAND_PERMISSION_NOPLOTOWNER)) {
                  Sign sign = (Sign) block.getState();
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
        if (player.hasPermission("citybuild.schild.admin")) {
          player.sendMessage("§cPlotSquaredV6 is missing.");
        } else {
          player.sendMessage(MessagesData.SCHILD_COMMAND_MESSAGE_NOPLOTSQUARED);
        }
      }
    }
    return false;
  }
}
