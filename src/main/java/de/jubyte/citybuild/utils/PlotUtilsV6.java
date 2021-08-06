package de.jubyte.citybuild.utils;

import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;

public class PlotUtilsV6 {

    public static boolean isInPlot(org.bukkit.Location location) {
        return Location.at(location.getWorld().getName(), (int) location.getX(), (int) location.getY(), (int) location.getZ(), location.getYaw(), location.getPitch()).getPlot() != null;
    }

    public static Plot getPlot(org.bukkit.Location location) {
        return Location.at(location.getWorld().getName(), (int) location.getX(), (int) location.getY(), (int) location.getZ(), location.getYaw(), location.getPitch()).getPlot();
    }
}
