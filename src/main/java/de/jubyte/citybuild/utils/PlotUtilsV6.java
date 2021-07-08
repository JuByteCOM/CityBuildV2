package de.jubyte.citybuild.utils;

import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;

import java.util.Objects;

public class PlotUtilsV6 {

    public static Plot getPlot(org.bukkit.Location location) {
        return Location.at(Objects.requireNonNull(location.getWorld()).getName(), (int) location.getX(), (int) location.getY(), (int) location.getZ(), location.getYaw(), location.getPitch()).getPlot();
    }

    public static boolean isInPlot(org.bukkit.Location location) {
        return Location.at(Objects.requireNonNull(location.getWorld()).getName(), (int) location.getX(), (int) location.getY(), (int) location.getZ(), location.getYaw(), location.getPitch()).getPlot() != null;
    }
}
