package de.crashmash.citybuild.data;

import de.crashmash.citybuild.CityBuildV2;

import java.util.List;
import java.util.Objects;

public class MessagesData {
    
    private final static CityBuildV2 plugin = CityBuildV2.getPlugin();

    //Todo: General
    public static String PREFIX = Objects.requireNonNull(plugin.getMessagesConfig().getString("Prefix"))
            .replaceAll("&", "§");
    public static String NOPERMS = Objects.requireNonNull(plugin.getMessagesConfig().getString("NoPerms"))
            .replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ISNOT_PLAYER = plugin.getMessagesConfig().getString("IsntPlayer")
            .replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String DEACTIVATED = plugin.getMessagesConfig().getString("Deactivated")
            .replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MYSQL_ERROR = plugin.getMessagesConfig().getString("MySQLError")
            .replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Schild-Command
    public static String SCHILD_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.Schild.Permissions.Use");
    public static String SCHILD_COMMAND_PERMISSION_NOTONPLOT = plugin.getMessagesConfig()
            .getString("Commands.Schild.Permissions.IsntOnPlot");
    public static String SCHILD_COMMAND_PERMISSION_NOPLOTOWNER = plugin.getMessagesConfig()
            .getString("Commands.Schild.Permissions.IsntPlotOwner");
    public static String SCHILD_COMMAND_PERMISSION_COLOR = plugin.getMessagesConfig()
            .getString("Commands.Schild.Permissions.UseColor");
    public static String SCHILD_COMMAND_MESSAGE_NOPLOTSQUARED = plugin.getMessagesConfig()
            .getString("Commands.Schild.Messages.NoPlotsquared").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SCHILD_COMMAND_MESSAGE_NOTSIGN = plugin.getMessagesConfig()
            .getString("Commands.Schild.Messages.IsntSign").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SCHILD_COMMAND_MESSAGE_SIGNNOTONPLOT = plugin.getMessagesConfig()
            .getString("Commands.Schild.Messages.SignIsntOnPlot").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SCHILD_COMMAND_MESSAGE_PLAYERNOTONPLOT = plugin.getMessagesConfig()
            .getString("Commands.Schild.Messages.PlayerIsntOnPlot").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SCHILD_COMMAND_MESSAGE_NOPLOTOWNER = plugin.getMessagesConfig()
            .getString("Commands.Schild.Messages.IsntPlotOwner").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: ClearChat-Command
    public static String CLEARCHAT_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.ClearChat.Permissions.Use");
    public static String CLEARCHAT_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.ClearChat.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String CLEARCHAT_COMMAND_MESSAGE_CLEARED = plugin.getMessagesConfig()
            .getString("Commands.ClearChat.Messages.Cleared").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Status-Command
    public static String STATUS_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.Status.Permissions.Use");
    public static String STATUS_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.Status.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STATUS_COMMAND_MESSAGE_HASNOTSTATUS = plugin.getMessagesConfig()
            .getString("Commands.Status.Messages.HasNotStatus").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STATUS_COMMAND_MESSAGE_SETSTATUS = plugin.getMessagesConfig()
            .getString("Commands.Status.Messages.SetStatus").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Food-Command
    public static String FOOD_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.Food.Permissions.Use");
    public static String FOOD_COMMAND_MESSAGE_HELP = plugin.getMessagesConfig()
            .getString("Commands.Food.Messages.Help").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_REMOVED = plugin.getMessagesConfig()
            .getString("Commands.Food.Messages.PigRemoved").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_SPAWNED = plugin.getMessagesConfig()
            .getString("Commands.Food.Messages.PigSpawned").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_LIST = plugin.getMessagesConfig()
            .getString("Commands.Food.Messages.List").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_HOVER = plugin.getMessagesConfig()
            .getString("Commands.Food.Messages.Hover").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String FOOD_COMMAND_MESSAGE_NAME = plugin.getMessagesConfig()
            .getString("Commands.Food.Settings.PigName").replaceAll("&", "§");
    public static double FOOD_COMMAND_SETTINGS_HEALTH = plugin.getMessagesConfig()
            .getDouble("Commands.Food.Settings.Health");
    public static int FOOD_COMMAND_SETTINGS_EXP = plugin.getMessagesConfig()
            .getInt("Commands.Food.Settings.Exp");
    public static int FOOD_COMMAND_SETTINGS_RESPAWNTIME = plugin.getMessagesConfig()
            .getInt("Commands.Food.Settings.RespawnTime");

    //Todo: Teleport-Command
    public static String TELEPORT_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.Teleport.Permissions.Use");
    public static String TELEPORT_COMMAND_MESSAGE_PLAYERNOTFOUND = plugin.getMessagesConfig()
            .getString("Commands.Teleport.Messages.PlayerNotFound").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String TELEPORT_COMMAND_MESSAGE_TPTOPLAYER = plugin.getMessagesConfig()
            .getString("Commands.Teleport.Messages.TeleportedToPlayer").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String TELEPORT_COMMAND_MESSAGE_TPTOLOCATION = plugin.getMessagesConfig()
            .getString("Commands.Teleport.Messages.TeleportedToLocation").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String TELEPORT_COMMAND_MESSAGE_FALSELOCATION = plugin.getMessagesConfig()
            .getString("Commands.Teleport.Messages.FalseLocation").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Startkick-Command
    public static String STARTKICK_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Permissions.Use");
    public static String STARTKICK_COMMAND_PERMISSION_KICK_BYPASS = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Permissions.KickBypass");
    public static String STARTKICK_COMMAND_PERMISSION_TIME_BYPASS = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Permissions.TimeBypass");
    public static String STARTKICK_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_ALLREADY_POLL = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.AlreadyPoll").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_TARGET_PLAYER_OFFLINE = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.TargetPlayerOffline").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_SELF_KICK = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.SelfKick").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_KICK_BYPASS = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.KickBypass").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_STARTED = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.Started").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_VOTE_FOR_YES = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.VoteForYes").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_VOTE_FOR_NO = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.VoteForNo").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_COUNTER = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.Counter").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_KICKED = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.PlayerKicked").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_NOT_KICKED = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.PlayerNotKicked").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_BANSCREEN = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.BanScreen").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static boolean STARTKICK_COMMAND_SETTING_KICK_BYPASS = plugin.getMessagesConfig()
            .getBoolean("Commands.StartKick.Settings.KickBypass");
    public static int STARTKICK_COMMAND_SETTING_COOLDOWN = plugin.getMessagesConfig()
            .getInt("Commands.StartKick.Settings.Cooldown");
    public static int STARTKICK_COMMAND_SETTING_DURATION = plugin.getMessagesConfig()
            .getInt("Commands.StartKick.Settings.Duration");
    public static int STARTKICK_COMMAND_SETTING_COUNTER = plugin.getMessagesConfig()
            .getInt("Commands.StartKick.Settings.Counter");
    public static List<Integer> STARTKICK_COMMAND_SETTING_COUNTER_TIMES = plugin.getMessagesConfig()
        .getIntegerList("Commands.StartKick.Settings.CounterTimes");

    //Todo: Unstartkick-Command
    public static String UNSTARTKICK_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.Unstartkick.Permissions.Use");
    public static String UNSTARTKICK_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.Unstartkick.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String UNSTARTKICK_COMMAND_MESSAGE_PLAYER_NOT_FOUND = plugin.getMessagesConfig()
            .getString("Commands.Unstartkick.Messages.PlayerNotFound").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String UNSTARTKICK_COMMAND_MESSAGE_NOT_KICKED = plugin.getMessagesConfig()
            .getString("Commands.Unstartkick.Messages.NotKicked").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String UNSTARTKICK_COMMAND_MESSAGE_UNKICKED = plugin.getMessagesConfig()
            .getString("Commands.Unstartkick.Messages.Unkicked").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Ja-Command
    public static String YES_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.YesCommand.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String YES_COMMAND_MESSAGE_ALREADY_VOTED = plugin.getMessagesConfig()
            .getString("Commands.YesCommand.Messages.AlreadyVoted").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String YES_COMMAND_MESSAGE_VOTED_FOR_NO = plugin.getMessagesConfig()
            .getString("Commands.YesCommand.Messages.VotedForNo").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String YES_COMMAND_MESSAGE_SUCCESFUL_VOTED = plugin.getMessagesConfig()
            .getString("Commands.YesCommand.Messages.SuccesfulVoted").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String YES_COMMAND_MESSAGE_NO_STARTKICK = plugin.getMessagesConfig()
            .getString("Commands.YesCommand.Messages.NoStartKick").replaceAll("&", "§").replace("[prefix]", PREFIX);


    //Todo: Nein-Command
    public static String NO_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.NoCommand.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String NO_COMMAND_MESSAGE_ALREADY_VOTED = plugin.getMessagesConfig()
            .getString("Commands.NoCommand.Messages.AlreadyVoted").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String NO_COMMAND_MESSAGE_VOTED_FOR_YES = plugin.getMessagesConfig()
            .getString("Commands.NoCommand.Messages.VotedForYes").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String NO_COMMAND_MESSAGE_SUCCESFUL_VOTED = plugin.getMessagesConfig()
            .getString("Commands.NoCommand.Messages.SuccesfulVoted").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String NO_COMMAND_MESSAGE_NO_STARTKICK = plugin.getMessagesConfig()
            .getString("Commands.NoCommand.Messages.NoStartKick").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: Slowchat-Command
    public static String SLOWCHAT_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.SlowChat.Permissions.Use");
    public static String SLOWCHAT_COMMAND_PERMISSION_BYPASS = plugin.getMessagesConfig()
            .getString("Commands.SlowChat.Permissions.Bypass");
    public static String SLOWCHAT_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.SlowChat.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SLOWCHAT_COMMAND_MESSAGE_ACTIVATED = plugin.getMessagesConfig()
            .getString("Commands.SlowChat.Messages.Activated").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SLOWCHAT_COMMAND_MESSAGE_DEACTIVATED = plugin.getMessagesConfig()
            .getString("Commands.SlowChat.Messages.Deactivated").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String SLOWCHAT_COMMAND_MESSAGE_CHATTET_TO_FAST = plugin.getMessagesConfig()
            .getString("Commands.SlowChat.Messages.WriteToFast").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static long SLOWCHAT_COMMAND_SETTINGS_CHAT_COOLDOWN = plugin.getMessagesConfig()
            .getLong("Commands.SlowChat.Settings.ChatCooldown");

    //Tod: ColoredChat
    public static String SETTINGS_PERMISSION_COLORED_CHAT = plugin.getMessagesConfig()
            .getString("Settings.ColoredChat.Permissions.Use");
}
