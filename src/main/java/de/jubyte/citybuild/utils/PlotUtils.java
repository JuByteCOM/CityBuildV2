package de.jubyte.citybuild.utils;

import com.plotsquared.core.plot.Plot;

import java.util.Objects;

public class PlotUtils {

    public static Plot getPlot(org.bukkit.Location location) {
        com.plotsquared.core.location.Location loc2 = new com.plotsquared.core.location.Location(Objects.requireNonNull(location.getWorld()).getName(),
                (int)location.getX(),
                (int)location.getY(),
                (int)location.getZ(),
                location.getYaw(),
                location.getPitch());
        return loc2.getPlot();
    }

    public static boolean isInPlot(org.bukkit.Location location) {
        com.plotsquared.core.location.Location loc2 = new com.plotsquared.core.location.Location(Objects.requireNonNull(location.getWorld()).getName(),
                (int)location.getX(),
                (int)location.getY(),
                (int)location.getZ(),
                location.getYaw(),
                location.getPitch());
        Plot plot = loc2.getPlot();
        return plot != null;
    }
}
