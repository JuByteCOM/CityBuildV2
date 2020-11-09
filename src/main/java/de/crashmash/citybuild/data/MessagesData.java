package de.crashmash.citybuild.data;

import de.crashmash.citybuild.CityBuildV2;

import java.util.Objects;

public class MessagesData {

    //Todo: General
    public static String PREFIX = Objects.requireNonNull(CityBuildV2.getPlugin().getMessagesConfig().getString("Prefix"))
            .replaceAll("&", "§");
    public static String NOPERMS = Objects.requireNonNull(CityBuildV2.getPlugin().getMessagesConfig().getString("NoPerms"))
            .replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ISNOT_PLAYER = CityBuildV2.getPlugin().getMessagesConfig().getString("IsntPlayer")
            .replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String DEACTIVATED = CityBuildV2.getPlugin().getMessagesConfig().getString("Deactivated")
            .replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MYSQL_ERROR = CityBuildV2.getPlugin().getMessagesConfig().getString("MySQLError")
            .replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Schild-Command
    public static String SCHILD_COMMAND_PERMISSION_USE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Permissions.Use");
    public static String SCHILD_COMMAND_PERMISSION_NOTONPLOT = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Permissions.IsntOnPlot");
    public static String SCHILD_COMMAND_PERMISSION_NOPLOTOWNER = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Permissions.IsntPlotOwner");
    public static String SCHILD_COMMAND_PERMISSION_COLOR = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Permissions.UseColor");
    public static String SCHILD_COMMAND_MESSAGE_NOPLOTSQUARED = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Messages.NoPlotsquared").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SCHILD_COMMAND_MESSAGE_NOTSIGN = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Messages.IsntSign").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SCHILD_COMMAND_MESSAGE_SIGNNOTONPLOT = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Messages.SignIsntOnPlot").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SCHILD_COMMAND_MESSAGE_PLAYERNOTONPLOT = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Messages.PlayerIsntOnPlot").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SCHILD_COMMAND_MESSAGE_NOPLOTOWNER = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Schild.Messages.IsntPlotOwner").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: ClearChat-Command
    public static String CLEARCHAT_COMMAND_PERMISSION_USE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.ClearChat.Permissions.Use");
    public static String CLEARCHAT_COMMAND_MESSAGE_USAGE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.ClearChat.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String CLEARCHAT_COMMAND_MESSAGE_CLEARED = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.ClearChat.Messages.Cleared").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Status-Command
    public static String STATUS_COMMAND_PERMISSION_USE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Status.Permissions.Use");
    public static String STATUS_COMMAND_MESSAGE_USAGE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Status.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STATUS_COMMAND_MESSAGE_HASNOTSTATUS = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Status.Messages.HasNotStatus").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STATUS_COMMAND_MESSAGE_SETSTATUS = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Status.Messages.SetStatus").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Food-Command
    public static String FOOD_COMMAND_PERMISSION_USE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Food.Permissions.Use");
    public static String FOOD_COMMAND_MESSAGE_HELP = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Food.Messages.Help").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_REMOVED = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Food.Messages.PigRemoved").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_SPAWNED = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Food.Messages.PigSpawned").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_LIST = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Food.Messages.List").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_HOVER = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Food.Messages.Hover").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_CLICK = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Food.Messages.Click").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_NAME = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Food.Settings.PigName").replaceAll("&", "§");
    public static double FOOD_COMMAND_SETTINGS_HEALTH = CityBuildV2.getPlugin().getMessagesConfig()
            .getDouble("Commands.Food.Settings.Health");
    public static int FOOD_COMMAND_SETTINGS_EXP = CityBuildV2.getPlugin().getMessagesConfig()
            .getInt("Commands.Food.Settings.Exp");
    public static int FOOD_COMMAND_SETTINGS_RESPAWNTIME = CityBuildV2.getPlugin().getMessagesConfig()
            .getInt("Commands.Food.Settings.RespawnTime");

    //Todo: Teleport-Command
    public static String TELEPORT_COMMAND_PERMISSION_USE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Teleport.Permissions.Use");
    public static String TELEPORT_COMMAND_MESSAGE_PLAYERNOTFOUND = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Teleport.Messages.PlayerNotFound").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String TELEPORT_COMMAND_MESSAGE_TPTOPLAYER = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Teleport.Messages.TeleportedToPlayer").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String TELEPORT_COMMAND_MESSAGE_TPTOLOCATION = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Teleport.Messages.TeleportedToLocation").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String TELEPORT_COMMAND_MESSAGE_FALSELOCATION = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Teleport.Messages.FalseLocation").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Ja-Command
    public static String YES_COMMAND_MESSAGE_USAGE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Yes.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String YES_COMMAND_MESSAGE_ALREADYVOTED = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Yes.Messages.AlreadyVoted").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String YES_COMMAND_MESSAGE_VOTEDFORNO = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Yes.Messages.VotedForNo").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String YES_COMMAND_MESSAGE_SUCCESSVOTED = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.Yes.Messages.SuccessVoted").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Nein-Command
    public static String NO_COMMAND_MESSAGE_USAGE = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.No.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String NO_COMMAND_MESSAGE_ALREADYVOTED = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.No.Messages.AlreadyVoted").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String NO_COMMAND_MESSAGE_VOTEDFORYES = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.No.Messages.VotedForYes").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String NO_COMMAND_MESSAGE_SUCCESSVOTED = CityBuildV2.getPlugin().getMessagesConfig()
            .getString("Commands.No.Messages.SuccessVoted").replaceAll("&", "§").replace("[prefix]", PREFIX);
}
