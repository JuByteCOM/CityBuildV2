package de.jubyte.citybuild.commands;

import com.plotsquared.core.plot.Plot;
import de.crashmash.developerapi.commands.AbstractCommand;
import de.jubyte.citybuild.data.MessagesData;
import de.jubyte.citybuild.manager.playerdata.CachedPlayerData;
import de.jubyte.citybuild.utils.PlotUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class CheckPlotCommand extends AbstractCommand {

    public CheckPlotCommand() {
        super("checkplot");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){

            Player player = (Player) commandSender;

            if(strings.length == 0){

                if(player.hasPermission(MessagesData.CHECKPLOT_USE_PERM)){
                    if(PlotUtils.isInPlot(player.getLocation())){

                        Plot plot = PlotUtils.getPlot(player.getLocation());

                        UUID ownerUUID = plot.getOwner();

                        Optional<CachedPlayerData> search = CachedPlayerData.getCacheEntry(ownerUUID);

                        long lastOnlineTime;

                        if(search.isPresent()) {
                            lastOnlineTime = (long) search.get().getCacheValue(CachedPlayerData.CacheDataType.LAST_JOIN);
                        }else{
                            lastOnlineTime = CityBuildV2.getPLUGIN().getPlayerDataHandler().getLastJoin(player.getUniqueId());
                            new CachedPlayerData(player.getUniqueId()).cache(CachedPlayerData.CacheDataType.LAST_JOIN, lastOnlineTime);
                        }
                        long clearTime = MessagesData.CHECKPLOT_UNIT.toMillis(MessagesData.CHECKPLOT_TIME);

                        long clearAllowed = lastOnlineTime + clearTime;

                        boolean clear = clearAllowed < System.currentTimeMillis();

                        if(clear){
                            player.sendMessage(MessagesData.CHECKPLOT_CLEAR_READY);
                            if(player.hasPermission(MessagesData.CHECKPLOT_CLEAR_PERM)){
                                plot.deletePlot(() -> player.sendMessage(MessagesData.CHECKPLOT_CLEARED));
                            }
                        }else{
                            player.sendMessage(MessagesData.CHECKPLOT_CLEAR_NOT_READY.replace("[date]", new SimpleDateFormat("dd.MM.yyyy").format(new Date(clearAllowed))));
                        }

                    }else{
                        player.sendMessage(MessagesData.CHECKPLOT_NOT_IN_PLOT);
                    }
                }

            }

        }

        return false;
    }
}
