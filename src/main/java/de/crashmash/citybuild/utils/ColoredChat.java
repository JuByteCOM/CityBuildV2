package de.crashmash.citybuild.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColoredChat {

    private static final Pattern pattern = Pattern.compile("(?<!\\\\)(#[a-fA-F0-9]{6})");
    public static String format(String message) {
        if(Bukkit.getVersion().contains("1.16")) {
            Matcher match = pattern.matcher(message);
            while (match.find()) {
                String color = message.substring(match.start(), match.end());
                message = message.replace(color, "" + ChatColor.of(color));
                match = pattern.matcher(message);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
