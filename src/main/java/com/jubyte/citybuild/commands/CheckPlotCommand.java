package com.jubyte.citybuild.commands;

import com.jubyte.citybuild.CityBuildV2;
import com.jubyte.citybuild.data.ConfigData;
import com.jubyte.citybuild.data.MessagesData;
import com.jubyte.citybuild.manager.checkplot.CheckPlotPlayer;
import com.jubyte.citybuild.utils.PlotUtilsV6;
import com.jubyte.developerapi.commands.AbstractCommand;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CheckPlotCommand extends AbstractCommand {

  public CheckPlotCommand() {
    super(
        ConfigData.CONFIG_COMMAND_CHECKPLOT_NAME,
        null,
        "With this command, you can clear plots.",
        ConfigData.CONFIG_COMMAND_CHECKPLOT_ALIASES);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {

    if (commandSender instanceof Player) {

      Player player = (Player) commandSender;

      if (strings.length == 0) {
        if (Bukkit.getPluginManager().getPlugin("PlotSquared") != null) {
          if (player.hasPermission(MessagesData.CHECKPLOT_USE_PERM)) {
            if (PlotUtilsV6.isInPlot(player.getLocation())) {
              Plot plot = PlotUtilsV6.getPlot(player.getLocation());
              if (!plot.hasOwner()) {
                player.sendMessage(MessagesData.CHECKPLOT_CANT_USE);
                return false;
              }
              UUID ownerUUID = plot.getOwner();
              CheckPlotPlayer checkPlotPlayer =
                  CityBuildV2.getPLUGIN().getCheckPlotCache().getPlayerByUUID(ownerUUID);
              long clearTime = MessagesData.CHECKPLOT_UNIT.toMillis(MessagesData.CHECKPLOT_TIME);
              long clearAllowed = checkPlotPlayer.getLastJoin() + clearTime;
              if (clearAllowed < System.currentTimeMillis() && checkPlotPlayer.getLastJoin() != 0) {
                player.sendMessage(MessagesData.CHECKPLOT_CLEAR_READY);
                if (player.hasPermission(MessagesData.CHECKPLOT_CLEAR_PERM)) {
                  plot.unclaim();
                  player.sendMessage(MessagesData.CHECKPLOT_CLEARED);
                }
              } else {
                player.sendMessage(
                    MessagesData.CHECKPLOT_CLEAR_NOT_READY.replace(
                        "[date]",
                        new SimpleDateFormat("dd.MM.yyyy").format(new Date(clearAllowed))));
              }

            } else {
              player.sendMessage(MessagesData.CHECKPLOT_NOT_IN_PLOT);
            }
          }
        }
      }
    }

    return false;
  }
}
