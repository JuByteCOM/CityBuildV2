package de.crashmash.citybuild.data;

import de.crashmash.citybuild.CityBuildV2;

public class ConfigData {

    public static boolean CONFIG_COMMAND_SCHILD = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Schild");
    public static boolean CONFIG_COMMAND_CHATCLEAR = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.ChatClear");
    public static boolean CONFIG_COMMAND_SIGN = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Sign");
    public static boolean CONFIG_COMMAND_UNSIGN = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Unsign");
    public static boolean CONFIG_COMMAND_STATUS = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Status");
    public static boolean CONFIG_COMMAND_FOOD = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Food");
    public static boolean CONFIG_COMMAND_TELEPORT = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.Teleport");
    public static boolean CONFIG_COMMAND_STARTKICK = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.StartKick");
    public static boolean CONFIG_COMMAND_JA = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.YesCommand");
    public static boolean CONFIG_COMMAND_NEIN = CityBuildV2.getPlugin().getConfig().getBoolean("Settings.Commands.NoCommand");

}
