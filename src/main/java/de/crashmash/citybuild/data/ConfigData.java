package de.crashmash.citybuild.data;

import de.crashmash.citybuild.CityBuildV2;

public class ConfigData {

    public static boolean CONFIG_COMMAND_SCHILD = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Schild");
    public static boolean CONFIG_COMMAND_CHATCLEAR = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.ChatClear");
    public static boolean CONFIG_COMMAND_SIGN = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Sign");
    public static boolean CONFIG_COMMAND_UNSIGN = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Unsign");
    public static boolean CONFIG_COMMAND_STATUS = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Status");

}
