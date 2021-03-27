package de.crashmash.citybuild.data;

import de.crashmash.citybuild.CityBuildV2;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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

    //Todo: TPHERE-Command
    public static String TPHERE_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.TpHere.Permissions.Use");
    public static String TPHERE_COMMAND_MESSAGE_PLAYERNOTFOUND = plugin.getMessagesConfig()
            .getString("Commands.TpHere.Messages.PlayerNotFound").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String TPHERE_COMMAND_MESSAGE_TPTARGETTOYOU = plugin.getMessagesConfig()
            .getString("Commands.TpHere.Messages.TeleportPlayerToYou").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String TPHERE_COMMAND_MESSAGE_TPTARGETTOYOU_USAGE = plugin.getMessagesConfig()
            .getString("Commands.TpHere.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);

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
    public static String STARTKICK_COMMAND_MESSAGE_LASTSECOND_COUNTER = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.LastsecondCounter").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_KICKED = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.PlayerKicked").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_NOT_KICKED = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.PlayerNotKicked").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_BANSCREEN = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.BanScreen").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String STARTKICK_COMMAND_MESSAGE_COOLDOWN = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Messages.Cooldown").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static boolean STARTKICK_COMMAND_SETTING_KICK_BYPASS = plugin.getMessagesConfig()
            .getBoolean("Commands.StartKick.Settings.KickBypass");
    public static long STARTKICK_COMMAND_SETTING_COOLDOWN = plugin.getMessagesConfig()
            .getLong("Commands.StartKick.Settings.Cooldown");
    public static int STARTKICK_COMMAND_SETTING_DURATION = plugin.getMessagesConfig()
            .getInt("Commands.StartKick.Settings.Duration");
    public static int STARTKICK_COMMAND_SETTING_COUNTER = plugin.getMessagesConfig()
            .getInt("Commands.StartKick.Settings.Counter");
    public static List<Integer> STARTKICK_COMMAND_SETTING_COUNTER_TIMES = plugin.getMessagesConfig()
        .getIntegerList("Commands.StartKick.Settings.CounterTimes");
    public static boolean STARTKICK_COMMAND_SETTING_PLAY_SOUND = plugin.getMessagesConfig()
            .getBoolean("Commands.StartKick.Settings.PlaySound");
    public static String STARTKICK_COMMAND_SETTING_START_SOUND = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Settings.StartSound");
    public static String STARTKICK_COMMAND_SETTING_COUNTER_SOUND = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Settings.CounterSound");
    public static String STARTKICK_COMMAND_SETTING_STARTKICK_SOUND = plugin.getMessagesConfig()
            .getString("Commands.StartKick.Settings.StartKickSound");

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

    //Todo: Head-Command
    public static String HEAD_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.Head.Permissions.Use");
    public static String HEAD_COMMAND_PERMISSION_BYPASS = plugin.getMessagesConfig()
            .getString("Commands.Head.Permissions.Bypass");
    public static String HEAD_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.Head.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String HEAD_COMMAND_MESSAGE_ADDED_HEAD = plugin.getMessagesConfig()
            .getString("Commands.Head.Messages.AddedHead").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String HEAD_COMMAND_MESSAGE_COOLDOWN = plugin.getMessagesConfig()
            .getString("Commands.Head.Messages.Cooldown").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String HEAD_COMMAND_ITEM_DISPLAYNAME = plugin.getMessagesConfig()
            .getString("Commands.Head.Item.Displayname").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static long HEAD_COMMAND_SETTINGS_COOLDOWN = plugin.getMessagesConfig()
            .getLong("Commands.Head.Settings.Cooldown");

    //Todo: BreakBlock-Command
    public static String BREAKBLOCK_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Permissions.Use");
    public static String BREAKBLOCK_COMMAND_PERMISSION_BYPASS = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Permissions.Bypass");
    public static String BREAKBLOCK_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String BREAKBLOCK_COMMAND_MESSAGE_DISABLED_WORLDS = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.DisabledWorld").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String BREAKBLOCK_COMMAND_MESSAGE_DISABLED_BLOCK_HEIGHT = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.DisabledBlockHeight").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String BREAKBLOCK_COMMAND_MESSAGE_BLOCKED_BLOCK = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.BlockedBlock").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_COMMAND = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.ConfirmCommand").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_BLOCK_REMOVED = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.BlockRemoved").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String BREAKBLOCK_COMMAND_MESSAGE_COODLOWN = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.Cooldown").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String BREAKBLOCK_COMMAND_MESSAGE_ISNT_PLOT_OWNER = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.IsntPlotOwner").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String BREAKBLOCK_COMMAND_MESSAGE_BLOCK_ISNT_ON_PLOT = plugin.getMessagesConfig()
            .getString("Commands.Breakblock.Messages.BlockIsntOnPlot").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static List<String> BREAKBLOCK_COMMAND_SETTINGS_AVIABLE_WORLDS = plugin.getMessagesConfig()
            .getStringList("Commands.Breakblock.Settings.AviableWorlds");
    public static List<Integer> BREAKBLOCK_COMMAND_SETTINGS_DISABLES_BLOCK_HEIGHTS = plugin.getMessagesConfig()
            .getIntegerList("Commands.Breakblock.Settings.DisabledBlockHeights");
    public static List<String> BREAKBLOCK_COMMAND_SETTINGS_BLOCKED_BLOCKS = plugin.getMessagesConfig()
            .getStringList("Commands.Breakblock.Settings.BlockedBlocks");
    public static boolean BREAKBLOCK_COMMAND_SETTINGS_DROP_BLOCK = plugin.getMessagesConfig()
            .getBoolean("Commands.Breakblock.Settings.DropBlock");
    public static long BREAKBLOCK_COMMAND_SETTINGS_COOLDOWN = plugin.getMessagesConfig()
            .getLong("Commands.Breakblock.Settings.Cooldown");

    //Todo: CommandSpy-Command
    public static String COMMANDSPY_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.CommandSpy.Permissions.Use");
    public static String COMMANDSPY_COMMAND_PERMISSION_BYPASS = plugin.getMessagesConfig()
            .getString("Commands.CommandSpy.Permissions.Bypass");
    public static String COMMANDSPY_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.CommandSpy.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String COMMANDSPY_COMMAND_MESSAGE_ENABLED_ALL = plugin.getMessagesConfig()
            .getString("Commands.CommandSpy.Messages.EnabledAll").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String COMMANDSPY_COMMAND_MESSAGE_ENABLED_PLAYER = plugin.getMessagesConfig()
            .getString("Commands.CommandSpy.Messages.EnabledPlayer").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String COMMANDSPY_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE = plugin.getMessagesConfig()
            .getString("Commands.CommandSpy.Messages.TargetPlayerOffline").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String COMMANDSPY_COMMAND_MESSAGE_DISABLED = plugin.getMessagesConfig()
            .getString("Commands.CommandSpy.Messages.Disabled").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String COMMANDSPY_COMMAND_MESSAGE_COMMAND = plugin.getMessagesConfig()
            .getString("Commands.CommandSpy.Messages.Command").replaceAll("&", "§").replace("[prefix]", PREFIX);

    //Todo: AllOrNothing-Command
    public static String ALLORNOTHING_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Permissions.Use");
    public static String ALLORNOTHING_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ALLORNOTHING_COMMAND_MESSAGE_USAGE_ADMIN = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.UsageAdmin").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ALLORNOTHING_COMMAND_MESSAGE_COUNTER = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.Counter").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ALLORNOTHING_COMMAND_MESSAGE_LAST_SECOND_COUNTER = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.LastSecondCounter").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ALLORNOTHING_COMMAND_MESSAGE_ALLREADY_RUN_COMMAND = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.AlreadyRunCommand").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ALLORNOTHING_COMMAND_MESSAGE_RESULT = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.Result").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ALLORNOTHING_COMMAND_MESSAGE_SET_MAX_INGAMEMONEY = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.SetMaxIngamemoney").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ALLORNOTHING_COMMAND_MESSAGE_SET_MAX_REALMONEY = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.SetMaxRealmoney").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String ALLORNOTHING_COMMAND_MESSAGE_SETTINGS_INFORMATION = plugin.getMessagesConfig()
            .getString("Commands.AllOrNothing.Messages.SettingsInformation").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static int ALLORNOTHING_COMMAND_SETTINGS_COUNTER = plugin.getMessagesConfig()
            .getInt("Commands.AllOrNothing.Settings.Counter");
    public static List<Integer> ALLORNOTHING_COMMAND_SETTINGS_COUNTERTIMES = plugin.getMessagesConfig()
            .getIntegerList("Commands.AllOrNothing.Settings.CounterTimes");
    public static int ALLORNOTHING_COMMAND_SETTINGS_MAX_INGAMEMONEY = plugin.getMessagesConfig()
            .getInt("Commands.AllOrNothing.Settings.MaxIngamemoney");
    public static int ALLORNOTHING_COMMAND_SETTINGS_MIN_INGAMEMONEY = plugin.getMessagesConfig()
            .getInt("Commands.AllOrNothing.Settings.MinIngamemoney");
    public static int ALLORNOTHING_COMMAND_SETTINGS_MAX_REALMONEY = plugin.getMessagesConfig()
            .getInt("Commands.AllOrNothing.Settings.MaxRealmoney");
    public static int ALLORNOTHING_COMMAND_SETTINGS_MIN_REALMONEY = plugin.getMessagesConfig()
            .getInt("Commands.AllOrNothing.Settings.MinRealmoney");

    //Todo: MuteP-Command
    public static String MUTEP_COMMAND_PERMISSION_USE = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Permissions.Use");
    public static String MUTEP_COMMAND_PERMISSION_BYPASS_MUTE = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Permissions.MuteBypass");
    public static String MUTEP_COMMAND_PERMISSION_BYPASS_TIME = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Permissions.TimeBypass");
    public static String MUTEP_COMMAND_MESSAGE_USAGE = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Messages.Usage").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MUTEP_COMMAND_MESSAGE_COOLDOWN = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Messages.Cooldown").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MUTEP_COMMAND_MESSAGE_TARGET_PLAYER_OFFLINE = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Messages.TargetPlayerOffline").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MUTEP_COMMAND_MESSAGE_SELF_MUTE = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Messages.SelfMute").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MUTEP_COMMAND_MESSAGE_MUTE_BYPASS = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Messages.MuteBypass").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MUTEP_COMMAND_MESSAGE_MUTE_SUCCESFUL = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Messages.MuteSuccesful").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MUTEP_COMMAND_MESSAGE_MUTE_SCREEN = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Messages.MuteScreen").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static String MUTEP_COMMAND_MESSAGE_PLAYER_IS_MUTED = plugin.getMessagesConfig()
            .getString("Commands.MuteP.Messages.PlayerAlreadyMuted").replaceAll("&", "§").replace("[prefix]", PREFIX);
    public static int MUTEP_COMMAND_SETTINGS_COOLDOWN = plugin.getMessagesConfig()
            .getInt("Commands.MuteP.Settings.Cooldown");
    public static int MUTEP_COMMAND_SETTINGS_DURATION = plugin.getMessagesConfig()
            .getInt("Commands.MuteP.Settings.Duration");

    //Todo: ColoredChat
    public static String SETTINGS_PERMISSION_COLORED_CHAT = plugin.getMessagesConfig()
            .getString("Settings.ColoredChat.Permissions.Use");
}
