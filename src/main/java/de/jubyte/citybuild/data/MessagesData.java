package de.jubyte.citybuild.data;

import de.crashmash.developerapi.utils.MessageHandler;
import de.jubyte.citybuild.CityBuildV2;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MessagesData {

    private final static CityBuildV2 plugin = CityBuildV2.getPLUGIN();
    private final static MessageHandler messageHandler = CityBuildV2.getPLUGIN().getMessageHandler();

    //Todo: General
    public static String NOPERMS = messageHandler.getPrefixString("NoPerms");
    public static String ISNOT_PLAYER = messageHandler.getPrefixString("IsntPlayer");

    //Todo: Schild-Command
    public static String SCHILD_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Signedit.Permissions.Use");
    public static String SCHILD_COMMAND_PERMISSION_NOTONPLOT = messageHandler.getString("Commands.Signedit.Permissions.IsntOnPlot");
    public static String SCHILD_COMMAND_PERMISSION_NOPLOTOWNER = messageHandler.getString("Commands.Signedit.Permissions.IsntPlotOwner");
    public static String SCHILD_COMMAND_PERMISSION_COLOR = messageHandler.getPrefixString("Commands.Signedit.Permissions.UseColor");
    public static String SCHILD_COMMAND_MESSAGE_NOPLOTSQUARED = messageHandler.getPrefixString("Commands.Signedit.Messages.NoPlotsquared");
    public static String SCHILD_COMMAND_MESSAGE_NOTSIGN = messageHandler.getPrefixString("Commands.Signedit.Messages.IsntSign");
    public static String SCHILD_COMMAND_MESSAGE_SIGNNOTONPLOT = messageHandler.getPrefixString("Commands.Signedit.Messages.SignIsntOnPlot");
    public static String SCHILD_COMMAND_MESSAGE_PLAYERNOTONPLOT = messageHandler.getPrefixString("Commands.Signedit.Messages.PlayerIsntOnPlot");
    public static String SCHILD_COMMAND_MESSAGE_NOPLOTOWNER = messageHandler.getPrefixString("Commands.Signedit.Messages.IsntPlotOwner");

    //Todo: ClearChat-Command
    public static String CLEARCHAT_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.ClearChat.Permissions.Use");
    public static String CLEARCHAT_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.ClearChat.Messages.Usage");
    public static String CLEARCHAT_COMMAND_MESSAGE_CLEARED = messageHandler.getPrefixString("Commands.ClearChat.Messages.Cleared");

    //Todo: Status-Command
    public static String STATUS_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Status.Permissions.Use");
    public static String STATUS_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Status.Messages.Usage");
    public static String STATUS_COMMAND_MESSAGE_HASNOTSTATUS = messageHandler.getPrefixString("Commands.Status.Messages.HasNotStatus");
    public static String STATUS_COMMAND_MESSAGE_SETSTATUS = messageHandler.getPrefixString("Commands.Status.Messages.SetStatus");

    //Todo: Food-Command
    public static String FOOD_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Food.Permissions.Use");
    public static String FOOD_COMMAND_MESSAGE_HELP = messageHandler.getPrefixString("Commands.Food.Messages.Help");
    public static String FOOD_COMMAND_MESSAGE_REMOVED = messageHandler.getPrefixString("Commands.Food.Messages.PigRemoved");
    public static String FOOD_COMMAND_MESSAGE_SPAWNED = messageHandler.getPrefixString("Commands.Food.Messages.PigSpawned");
    public static String FOOD_COMMAND_MESSAGE_LIST = messageHandler.getPrefixString("Commands.Food.Messages.List");
    public static String FOOD_COMMAND_MESSAGE_HOVER = messageHandler.getPrefixString("Commands.Food.Messages.Hover");
    public static String FOOD_COMMAND_MESSAGE_NUMBERFORMATEXCEPTION = messageHandler.getPrefixString("Commands.Food.Messages.NumberFormatException");
    public static String FOOD_COMMAND_MESSAGE_NAME = messageHandler.getPrefixString("Commands.Food.Settings.PigName");
    public static double FOOD_COMMAND_SETTINGS_HEALTH = plugin.getMessagesConfig().getDouble("Commands.Food.Settings.Health");
    public static int FOOD_COMMAND_SETTINGS_EXP = plugin.getMessagesConfig().getInt("Commands.Food.Settings.Exp");
    public static long FOOD_COMMAND_SETTINGS_RESPAWNTIME = plugin.getMessagesConfig().getLong("Commands.Food.Settings.RespawnTime");

    //Todo: Teleport-Command
    public static String TELEPORT_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Teleport.Permissions.Use");
    public static String TELEPORT_COMMAND_MESSAGE_PLAYERNOTFOUND = messageHandler.getPrefixString("Commands.Teleport.Messages.PlayerNotFound");
    public static String TELEPORT_COMMAND_MESSAGE_TPTOPLAYER = messageHandler.getPrefixString("Commands.Teleport.Messages.TeleportedToPlayer");
    public static String TELEPORT_COMMAND_MESSAGE_TPTOLOCATION = messageHandler.getPrefixString("Commands.Teleport.Messages.TeleportedToLocation");
    public static String TELEPORT_COMMAND_MESSAGE_FALSELOCATION = messageHandler.getPrefixString("Commands.Teleport.Messages.FalseLocation");

    //Todo: TPHERE-Command
    public static String TPHERE_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.TpHere.Permissions.Use");
    public static String TPHERE_COMMAND_MESSAGE_PLAYERNOTFOUND = messageHandler.getPrefixString("Commands.TpHere.Messages.PlayerNotFound");
    public static String TPHERE_COMMAND_MESSAGE_TPTARGETTOYOU = messageHandler.getPrefixString("Commands.TpHere.Messages.TeleportPlayerToYou");
    public static String TPHERE_COMMAND_MESSAGE_TPTARGETTOYOU_USAGE = messageHandler.getPrefixString("Commands.TpHere.Messages.Usage");

    //Todo: Startkick-Command
    public static String STARTKICK_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.StartKick.Permissions.Use");
    public static String STARTKICK_COMMAND_PERMISSION_KICK_BYPASS = messageHandler.getString("Commands.StartKick.Permissions.KickBypass");
    public static String STARTKICK_COMMAND_PERMISSION_TIME_BYPASS = messageHandler.getString("Commands.StartKick.Permissions.TimeBypass");
    public static String STARTKICK_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.StartKick.Messages.Usage");
    public static String STARTKICK_COMMAND_MESSAGE_ALLREADY_POLL = messageHandler.getPrefixString("Commands.StartKick.Messages.AlreadyPoll");
    public static String STARTKICK_COMMAND_MESSAGE_TARGET_PLAYER_OFFLINE = messageHandler.getPrefixString("Commands.StartKick.Messages.TargetPlayerOffline");
    public static String STARTKICK_COMMAND_MESSAGE_SELF_KICK = messageHandler.getPrefixString("Commands.StartKick.Messages.SelfKick");
    public static String STARTKICK_COMMAND_MESSAGE_KICK_BYPASS = messageHandler.getPrefixString("Commands.StartKick.Messages.KickBypass");
    public static String STARTKICK_COMMAND_MESSAGE_STARTED = messageHandler.getPrefixString("Commands.StartKick.Messages.Started");
    public static String STARTKICK_COMMAND_MESSAGE_VOTE_FOR_YES = messageHandler.getPrefixString("Commands.StartKick.Messages.VoteForYes");
    public static String STARTKICK_COMMAND_MESSAGE_VOTE_FOR_NO = messageHandler.getPrefixString("Commands.StartKick.Messages.VoteForNo");
    public static String STARTKICK_COMMAND_MESSAGE_COUNTER = messageHandler.getPrefixString("Commands.StartKick.Messages.Counter");
    public static String STARTKICK_COMMAND_MESSAGE_LASTSECOND_COUNTER = messageHandler.getPrefixString("Commands.StartKick.Messages.LastsecondCounter");
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_KICKED = messageHandler.getPrefixString("Commands.StartKick.Messages.PlayerKicked");
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_NOT_KICKED = messageHandler.getPrefixString("Commands.StartKick.Messages.PlayerNotKicked");
    public static String STARTKICK_COMMAND_MESSAGE_PLAYER_BANSCREEN = messageHandler.getPrefixString("Commands.StartKick.Messages.BanScreen");
    public static String STARTKICK_COMMAND_MESSAGE_COOLDOWN = messageHandler.getPrefixString("Commands.StartKick.Messages.Cooldown");
    public static boolean STARTKICK_COMMAND_SETTING_KICK_BYPASS = plugin.getMessagesConfig().getBoolean("Commands.StartKick.Settings.KickBypass");
    public static long STARTKICK_COMMAND_SETTING_COOLDOWN = plugin.getMessagesConfig().getLong("Commands.StartKick.Settings.Cooldown");
    public static int STARTKICK_COMMAND_SETTING_DURATION = plugin.getMessagesConfig().getInt("Commands.StartKick.Settings.Duration");
    public static int STARTKICK_COMMAND_SETTING_COUNTER = plugin.getMessagesConfig().getInt("Commands.StartKick.Settings.Counter");
    public static List<Integer> STARTKICK_COMMAND_SETTING_COUNTER_TIMES = plugin.getMessagesConfig().getIntegerList("Commands.StartKick.Settings.CounterTimes");
    public static boolean STARTKICK_COMMAND_SETTING_PLAY_SOUND = plugin.getMessagesConfig().getBoolean("Commands.StartKick.Settings.PlaySound");
    public static String STARTKICK_COMMAND_SETTING_START_SOUND = messageHandler.getString("Commands.StartKick.Settings.StartSound");
    public static String STARTKICK_COMMAND_SETTING_COUNTER_SOUND = messageHandler.getString("Commands.StartKick.Settings.CounterSound");
    public static String STARTKICK_COMMAND_SETTING_STARTKICK_SOUND = messageHandler.getString("Commands.StartKick.Settings.StartKickSound");

    //Todo: Unstartkick-Command
    public static String UNSTARTKICK_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Unstartkick.Permissions.Use");
    public static String UNSTARTKICK_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Unstartkick.Messages.Usage");
    public static String UNSTARTKICK_COMMAND_MESSAGE_PLAYER_NOT_FOUND = messageHandler.getPrefixString("Commands.Unstartkick.Messages.PlayerNotFound");
    public static String UNSTARTKICK_COMMAND_MESSAGE_NOT_KICKED = messageHandler.getPrefixString("Commands.Unstartkick.Messages.NotKicked");
    public static String UNSTARTKICK_COMMAND_MESSAGE_UNKICKED = messageHandler.getPrefixString("Commands.Unstartkick.Messages.Unkicked");

    //Todo: Ja-Command
    public static String YES_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.YesCommand.Messages.Usage");
    public static String YES_COMMAND_MESSAGE_ALREADY_VOTED = messageHandler.getPrefixString("Commands.YesCommand.Messages.AlreadyVoted");
    public static String YES_COMMAND_MESSAGE_VOTED_FOR_NO = messageHandler.getPrefixString("Commands.YesCommand.Messages.VotedForNo");
    public static String YES_COMMAND_MESSAGE_SUCCESFUL_VOTED = messageHandler.getPrefixString("Commands.YesCommand.Messages.SuccesfulVoted");
    public static String YES_COMMAND_MESSAGE_NO_STARTKICK = messageHandler.getPrefixString("Commands.YesCommand.Messages.NoStartKick");


    //Todo: Nein-Command
    public static String NO_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.NoCommand.Messages.Usage");
    public static String NO_COMMAND_MESSAGE_ALREADY_VOTED = messageHandler.getPrefixString("Commands.NoCommand.Messages.AlreadyVoted");
    public static String NO_COMMAND_MESSAGE_VOTED_FOR_YES = messageHandler.getPrefixString("Commands.NoCommand.Messages.VotedForYes");
    public static String NO_COMMAND_MESSAGE_SUCCESFUL_VOTED = messageHandler.getPrefixString("Commands.NoCommand.Messages.SuccesfulVoted");
    public static String NO_COMMAND_MESSAGE_NO_STARTKICK = messageHandler.getPrefixString("Commands.NoCommand.Messages.NoStartKick");

    //Todo: Slowchat-Command
    public static String SLOWCHAT_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.SlowChat.Permissions.Use");
    public static String SLOWCHAT_COMMAND_PERMISSION_BYPASS = messageHandler.getString("Commands.SlowChat.Permissions.Bypass");
    public static String SLOWCHAT_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.SlowChat.Messages.Usage");
    public static String SLOWCHAT_COMMAND_MESSAGE_ACTIVATED = messageHandler.getPrefixString("Commands.SlowChat.Messages.Activated");
    public static String SLOWCHAT_COMMAND_MESSAGE_DEACTIVATED = messageHandler.getPrefixString("Commands.SlowChat.Messages.Deactivated");
    public static String SLOWCHAT_COMMAND_MESSAGE_CHATTET_TO_FAST = messageHandler.getPrefixString("Commands.SlowChat.Messages.WriteToFast");
    public static long SLOWCHAT_COMMAND_SETTINGS_CHAT_COOLDOWN = plugin.getMessagesConfig().getLong("Commands.SlowChat.Settings.ChatCooldown");

    //Todo: Head-Command
    public static String HEAD_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Head.Permissions.Use");
    public static String HEAD_COMMAND_PERMISSION_BYPASS = messageHandler.getPrefixString("Commands.Head.Permissions.Bypass");
    public static String HEAD_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Head.Messages.Usage");
    public static String HEAD_COMMAND_MESSAGE_ADDED_HEAD = messageHandler.getPrefixString("Commands.Head.Messages.AddedHead");
    public static String HEAD_COMMAND_MESSAGE_COOLDOWN = messageHandler.getPrefixString("Commands.Head.Messages.Cooldown");
    public static String HEAD_COMMAND_ITEM_DISPLAYNAME = messageHandler.getString("Commands.Head.Item.Displayname");
    public static long HEAD_COMMAND_SETTINGS_COOLDOWN = plugin.getMessagesConfig().getLong("Commands.Head.Settings.Cooldown");

    //Todo: BreakBlock-Command
    public static String BREAKBLOCK_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Breakblock.Permissions.Use");
    public static String BREAKBLOCK_COMMAND_PERMISSION_BYPASS = messageHandler.getString("Commands.Breakblock.Permissions.Bypass");
    public static String BREAKBLOCK_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Breakblock.Messages.Usage");
    public static String BREAKBLOCK_COMMAND_MESSAGE_DISABLED_WORLDS = messageHandler.getPrefixString("Commands.Breakblock.Messages.DisabledWorld");
    public static String BREAKBLOCK_COMMAND_MESSAGE_DISABLED_BLOCK_HEIGHT = messageHandler.getPrefixString("Commands.Breakblock.Messages.DisabledBlockHeight");
    public static String BREAKBLOCK_COMMAND_MESSAGE_BLOCKED_BLOCK = messageHandler.getPrefixString("Commands.Breakblock.Messages.BlockedBlock");
    public static String BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_COMMAND = messageHandler.getPrefixString("Commands.Breakblock.Messages.ConfirmCommand");
    public static String BREAKBLOCK_COMMAND_MESSAGE_CONFIRM_BLOCK_REMOVED = messageHandler.getPrefixString("Commands.Breakblock.Messages.BlockRemoved");
    public static String BREAKBLOCK_COMMAND_MESSAGE_COODLOWN = messageHandler.getPrefixString("Commands.Breakblock.Messages.Cooldown");
    public static String BREAKBLOCK_COMMAND_MESSAGE_ISNT_PLOT_OWNER = messageHandler.getPrefixString("Commands.Breakblock.Messages.IsntPlotOwner");
    public static String BREAKBLOCK_COMMAND_MESSAGE_BLOCK_ISNT_ON_PLOT = messageHandler.getPrefixString("Commands.Breakblock.Messages.BlockIsntOnPlot");
    public static List<String> BREAKBLOCK_COMMAND_SETTINGS_AVIABLE_WORLDS = plugin.getMessagesConfig().getStringList("Commands.Breakblock.Settings.AviableWorlds");
    public static List<Integer> BREAKBLOCK_COMMAND_SETTINGS_DISABLES_BLOCK_HEIGHTS = plugin.getMessagesConfig().getIntegerList("Commands.Breakblock.Settings.DisabledBlockHeights");
    public static List<String> BREAKBLOCK_COMMAND_SETTINGS_BLOCKED_BLOCKS = plugin.getMessagesConfig().getStringList("Commands.Breakblock.Settings.BlockedBlocks");
    public static boolean BREAKBLOCK_COMMAND_SETTINGS_DROP_BLOCK = plugin.getMessagesConfig().getBoolean("Commands.Breakblock.Settings.DropBlock");
    public static long BREAKBLOCK_COMMAND_SETTINGS_COOLDOWN = plugin.getMessagesConfig().getLong("Commands.Breakblock.Settings.Cooldown");

    //Todo: CommandSpy-Command
    public static String COMMANDSPY_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.CommandSpy.Permissions.Use");
    public static String COMMANDSPY_COMMAND_PERMISSION_BYPASS = messageHandler.getString("Commands.CommandSpy.Permissions.Bypass");
    public static String COMMANDSPY_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.CommandSpy.Messages.Usage");
    public static String COMMANDSPY_COMMAND_MESSAGE_ENABLED_ALL = messageHandler.getPrefixString("Commands.CommandSpy.Messages.EnabledAll");
    public static String COMMANDSPY_COMMAND_MESSAGE_ENABLED_PLAYER = messageHandler.getPrefixString("Commands.CommandSpy.Messages.EnabledPlayer");
    public static String COMMANDSPY_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE = messageHandler.getPrefixString("Commands.CommandSpy.Messages.TargetPlayerOffline");
    public static String COMMANDSPY_COMMAND_MESSAGE_DISABLED = messageHandler.getPrefixString("Commands.CommandSpy.Messages.Disabled");
    public static String COMMANDSPY_COMMAND_MESSAGE_COMMAND = messageHandler.getPrefixString("Commands.CommandSpy.Messages.Command");

    //Todo: AllOrNothing-Command
    public static String ALLORNOTHING_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.AllOrNothing.Permissions.Use");
    public static String ALLORNOTHING_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.Usage");
    public static String ALLORNOTHING_COMMAND_MESSAGE_USAGE_ADMIN = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.UsageAdmin");
    public static String ALLORNOTHING_COMMAND_MESSAGE_COUNTER = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.Counter");
    public static String ALLORNOTHING_COMMAND_MESSAGE_LAST_SECOND_COUNTER = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.LastSecondCounter");
    public static String ALLORNOTHING_COMMAND_MESSAGE_ALLREADY_RUN_COMMAND = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.AlreadyRunCommand");
    public static String ALLORNOTHING_COMMAND_MESSAGE_RESULT = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.Result");
    public static String ALLORNOTHING_COMMAND_MESSAGE_SET_MAX_INGAMEMONEY = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.SetMaxIngamemoney");
    public static String ALLORNOTHING_COMMAND_MESSAGE_SET_MAX_REALMONEY = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.SetMaxRealmoney");
    public static String ALLORNOTHING_COMMAND_MESSAGE_SETTINGS_INFORMATION = messageHandler.getPrefixString("Commands.AllOrNothing.Messages.SettingsInformation");
    public static int ALLORNOTHING_COMMAND_SETTINGS_COUNTER = plugin.getMessagesConfig().getInt("Commands.AllOrNothing.Settings.Counter");
    public static List<Integer> ALLORNOTHING_COMMAND_SETTINGS_COUNTERTIMES = plugin.getMessagesConfig().getIntegerList("Commands.AllOrNothing.Settings.CounterTimes");
    public static int ALLORNOTHING_COMMAND_SETTINGS_MAX_INGAMEMONEY = plugin.getMessagesConfig().getInt("Commands.AllOrNothing.Settings.MaxIngamemoney");
    public static int ALLORNOTHING_COMMAND_SETTINGS_MIN_INGAMEMONEY = plugin.getMessagesConfig().getInt("Commands.AllOrNothing.Settings.MinIngamemoney");
    public static int ALLORNOTHING_COMMAND_SETTINGS_MAX_REALMONEY = plugin.getMessagesConfig().getInt("Commands.AllOrNothing.Settings.MaxRealmoney");
    public static int ALLORNOTHING_COMMAND_SETTINGS_MIN_REALMONEY = plugin.getMessagesConfig().getInt("Commands.AllOrNothing.Settings.MinRealmoney");

    //Todo: MuteP-Command
    public static String MUTEP_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.MuteP.Permissions.Use");
    public static String MUTEP_COMMAND_PERMISSION_BYPASS_MUTE = messageHandler.getString("Commands.MuteP.Permissions.MuteBypass");
    public static String MUTEP_COMMAND_PERMISSION_BYPASS_TIME = messageHandler.getString("Commands.MuteP.Permissions.TimeBypass");
    public static String MUTEP_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.MuteP.Messages.Usage");
    public static String MUTEP_COMMAND_MESSAGE_COOLDOWN = messageHandler.getPrefixString("Commands.MuteP.Messages.Cooldown");
    public static String MUTEP_COMMAND_MESSAGE_TARGET_PLAYER_OFFLINE = messageHandler.getPrefixString("Commands.MuteP.Messages.TargetPlayerOffline");
    public static String MUTEP_COMMAND_MESSAGE_SELF_MUTE = messageHandler.getPrefixString("Commands.MuteP.Messages.SelfMute");
    public static String MUTEP_COMMAND_MESSAGE_MUTE_BYPASS = messageHandler.getPrefixString("Commands.MuteP.Messages.MuteBypass");
    public static String MUTEP_COMMAND_MESSAGE_MUTE_SUCCESFUL = messageHandler.getPrefixString("Commands.MuteP.Messages.MuteSuccesful");
    public static String MUTEP_COMMAND_MESSAGE_MUTE_SCREEN = messageHandler.getPrefixString("Commands.MuteP.Messages.MuteScreen");
    public static String MUTEP_COMMAND_MESSAGE_PLAYER_IS_MUTED = messageHandler.getPrefixString("Commands.MuteP.Messages.PlayerAlreadyMuted");
    public static int MUTEP_COMMAND_SETTINGS_COOLDOWN = plugin.getMessagesConfig().getInt("Commands.MuteP.Settings.Cooldown");
    public static int MUTEP_COMMAND_SETTINGS_DURATION = plugin.getMessagesConfig().getInt("Commands.MuteP.Settings.Duration");

    //Todo: Unmutep-Command
    public static String UNMUTEP_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.UnmuteP.Permissions.Use");
    public static String UNMUTEP_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.UnmuteP.Messages.Usage");
    public static String UNMUTEP_COMMAND_MESSAGE_ISNT_MUTED = messageHandler.getPrefixString("Commands.UnmuteP.Messages.IsntMuted");
    public static String UNMUTEP_COMMAND_MESSAGE_PLAYER_NOT_FOUND = messageHandler.getPrefixString("Commands.UnmuteP.Messages.PlayerNotFound");
    public static String UNMUTEP_COMMAND_MESSAGE_UNMUTED = messageHandler.getPrefixString("Commands.UnmuteP.Messages.Unmuted");
    public static String UNMUTEP_COMMAND_MESSAGE_UNMUTED_TARGETPLAYER = messageHandler.getPrefixString("Commands.UnmuteP.Messages.UnmutedTargetPlayer");

    //Todo: Glow-Command
    public static String GLOW_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Glow.Permissions.Use");
    public static String GLOW_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Glow.Messages.Usage");
    public static String GLOW_COMMAND_TOGGLE_ON = messageHandler.getPrefixString("Commands.Glow.Messages.ToggleOn");
    public static String GLOW_COMMAND_TOGGLE_OFF = messageHandler.getPrefixString("Commands.Glow.Messages.ToggleOff");

    //Todo: GameMode-Command
    public static String GAMEMODE_COMMAND_PERMISSION_USE_SELF = messageHandler.getString("Commands.GameMode.Permissions.UseSelf");
    public static String GAMEMODE_COMMAND_PERMISSION_USE_OTHER = messageHandler.getString("Commands.GameMode.Permissions.UseOther");
    public static String GAMEMODE_COMMAND_MESSAGE_PLAYER_NOT_FOUND = messageHandler.getPrefixString("Commands.GameMode.Messages.PlayerNotFound");
    public static String GAMEMODE_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.GameMode.Messages.Usage");
    public static String GAMEMODE_COMMAND_MESSAGE_SURVIVAL_SELF = messageHandler.getPrefixString("Commands.GameMode.Messages.Survival.Self");
    public static String GAMEMODE_COMMAND_MESSAGE_SURVIVAL_OTHER = messageHandler.getPrefixString("Commands.GameMode.Messages.Survival.Other");
    public static String GAMEMODE_COMMAND_MESSAGE_CREATIVE_SELF = messageHandler.getPrefixString("Commands.GameMode.Messages.Creative.Self");
    public static String GAMEMODE_COMMAND_MESSAGE_CREATIVE_OTHER = messageHandler.getPrefixString("Commands.GameMode.Messages.Creative.Other");
    public static String GAMEMODE_COMMAND_MESSAGE_ADVENTURE_SELF = messageHandler.getPrefixString("Commands.GameMode.Messages.Adventure.Self");
    public static String GAMEMODE_COMMAND_MESSAGE_ADVENTURE_OTHER = messageHandler.getPrefixString("Commands.GameMode.Messages.Adventure.Other");
    public static String GAMEMODE_COMMAND_MESSAGE_SPECTATOR_SELF = messageHandler.getPrefixString("Commands.GameMode.Messages.Spectator.Self");
    public static String GAMEMODE_COMMAND_MESSAGE_SPECTATOR_OTHER = messageHandler.getPrefixString("Commands.GameMode.Messages.Spectator.Other");

    //Todo: Spawn-Command
    public static String SPAWN_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Spawn.Permissions.Use");
    public static String SPAWN_COMMAND_PERMISSION_ADMIN = messageHandler.getString("Commands.Spawn.Permissions.Admin");
    public static String SPAWN_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Spawn.Messages.Usage");
    public static String SPAWN_COMMAND_MESSAGE_ADMINUSAGE = messageHandler.getPrefixString("Commands.Spawn.Messages.AdminUsage");
    public static String SPAWN_COMMAND_MESSAGE_SPAWN_NOT_FOUND = messageHandler.getPrefixString("Commands.Spawn.Messages.SpawnNotFound");
    public static String SPAWN_COMMAND_MESSAGE_TELEPORT_TO_SPAWN = messageHandler.getPrefixString("Commands.Spawn.Messages.TeleportToSpawn");
    public static String SPAWN_COMMAND_MESSAGE_SPAWN_SET = messageHandler.getPrefixString("Commands.Spawn.Messages.SpawnSet");
    public static String SPAWN_COMMAND_MESSAGE_SPAWN_REMOVE = messageHandler.getPrefixString("Commands.Spawn.Messages.SpawnRemove");
    public static boolean SPAWN_COMMAND_SETTING_SPAWN_ON_JOIN = plugin.getMessagesConfig().getBoolean("Commands.Spawn.Settings.SpawnOnJoin");

    //Todo: GiftRank
    public static String GIFTRANK_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.GiftRank.Permissions.Use");
    public static String GIFTRANK_COMMAND_PERMISSION_BYPASS = messageHandler.getString("Commands.GiftRank.Permissions.Bypass");
    public static String GIFTRANK_COMMAND_PERMISSION_HIGHER_RANK = messageHandler.getString("Commands.GiftRank.Permissions.HigherRank");
    public static String GIFTRANK_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.GiftRank.Messages.Usage");
    public static String GIFTRANK_COMMAND_MESSAGE_ADDED_RANK = messageHandler.getPrefixString("Commands.GiftRank.Messages.AddedRank");
    public static String GIFTRANK_COMMAND_MESSAGE_COOLDOWN = messageHandler.getPrefixString("Commands.GiftRank.Messages.Cooldown");
    public static String GIFTRANK_COMMAND_MESSAGE_HIGHER_RANK = messageHandler.getPrefixString("Commands.GiftRank.Messages.HigherRank");
    public static String GIFTRANK_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE = messageHandler.getPrefixString("Commands.GiftRank.Messages.TargetPlayerOffline");
    public static String GIFTRANK_COMMAND_MESSAGE_KICK_SCREEN = messageHandler.getPrefixString("Commands.GiftRank.Messages.KickScreen");
    public static String GIFTRANK_COMMAND_MESSAGE_RANK_SET = messageHandler.getPrefixString("Commands.GiftRank.Messages.RankSet");
    public static long GIFTRANK_COMMAND_SETTING_COOLDOWN = plugin.getMessagesConfig().getLong("Commands.GiftRank.Settings.Cooldown");
    public static boolean GIFTRANK_COMMAND_SETTING_KICK = plugin.getMessagesConfig().getBoolean("Commands.GiftRank.Settings.KickTargetPlayer");
    public static String GIFTRANK_COMMAND_SETTINGS_DISPATCHCOMMAND = messageHandler.getString("Commands.GiftRank.Settings.DispatchCommand");

    //Todo: Sudo
    public static String SUDO_COMMAND_PERMISSION_USE = messageHandler.getString("Commands.Sudo.Permissions.Use");
    public static String SUDO_COMMAND_PERMISSION_BYPASS = messageHandler.getString("Commands.Sudo.Permissions.Bypass");
    public static String SUDO_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Sudo.Messages.Usage");
    public static String SUDO_COMMAND_MESSAGE_HAS_BYPASS = messageHandler.getPrefixString("Commands.Sudo.Messages.HasBypass");
    public static String SUDO_COMMAND_MESSAGE_HAS_PERFORMED = messageHandler.getPrefixString("Commands.Sudo.Messages.Performed");
    public static String SUDO_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE = messageHandler.getPrefixString("Commands.Sudo.Messages.TargetPlayerOffline");

    //Todo: Fly
    public static String FLY_COMMAND_PERMISSION_USE_SELF = messageHandler.getString("Commands.Fly.Permissions.Use.Self");
    public static String FLY_COMMAND_PERMISSION_USE_OTHER = messageHandler.getString("Commands.Fly.Permissions.Use.Other");
    public static String FLY_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Fly.Messages.Usage");
    public static String FLY_COMMAND_MESSAGE_ACTIVATED_SELF = messageHandler.getPrefixString("Commands.Fly.Messages.Activated.Self");
    public static String FLY_COMMAND_MESSAGE_ACTIVATED_OTHER = messageHandler.getPrefixString("Commands.Fly.Messages.Activated.Other");
    public static String FLY_COMMAND_MESSAGE_DEACTIVATED_SELF = messageHandler.getPrefixString("Commands.Fly.Messages.Deactivated.Self");
    public static String FLY_COMMAND_MESSAGE_DEACTIVATED_OTHER = messageHandler.getPrefixString("Commands.Fly.Messages.Deactivated.Other");
    public static String FLY_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE = messageHandler.getPrefixString("Commands.Fly.Messages.TargetPlayerOffline");

    //Todo: Clear
    public static String CLEAR_COMMAND_PERMISSION_CLEAR_SELF = messageHandler.getString("Commands.Clear.Permissions.Use.Self");
    public static String CLEAR_COMMAND_PERMISSION_CLEAR_OTHER = messageHandler.getString("Commands.Clear.Permissions.Use.Other");
    public static String CLEAR_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Clear.Messages.Usage");
    public static String CLEAR_COMMAND_MESSAGE_CLEARED_SELF = messageHandler.getPrefixString("Commands.Clear.Messages.Cleared.Self");
    public static String CLEAR_COMMAND_MESSAGE_CLEARED_OTHER = messageHandler.getPrefixString("Commands.Clear.Messages.Cleared.Other");
    public static String CLEAR_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE = messageHandler.getPrefixString("Commands.Clear.Messages.TargetPlayerOffline");

    //Todo: Feed
    public static String FEED_COMMAND_PERMISSION_SELF = messageHandler.getString("Commands.Feed.Permissions.Use.Self");
    public static String FEED_COMMAND_PERMISSION_OTHER = messageHandler.getString("Commands.Feed.Permissions.Use.Other");
    public static String FEED_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Feed.Messages.Usage");
    public static String FEED_COMMAND_MESSAGE_SELF = messageHandler.getPrefixString("Commands.Feed.Messages.Self");
    public static String FEED_COMMAND_MESSAGE_OTHER = messageHandler.getPrefixString("Commands.Feed.Messages.Other");
    public static String FEED_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE = messageHandler.getPrefixString("Commands.Feed.Messages.TargetPlayerOffline");

    //Todo: Heal
    public static String HEAL_COMMAND_PERMISSION_SELF = messageHandler.getString("Commands.Heal.Permissions.Use.Self");
    public static String HEAL_COMMAND_PERMISSION_OTHER = messageHandler.getString("Commands.Heal.Permissions.Use.Other");
    public static String HEAL_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Heal.Messages.Usage");
    public static String HEAL_COMMAND_MESSAGE_SELF = messageHandler.getPrefixString("Commands.Heal.Messages.Self");
    public static String HEAL_COMMAND_MESSAGE_OTHER = messageHandler.getPrefixString("Commands.Heal.Messages.Other");
    public static String HEAL_COMMAND_MESSAGE_TARGETPLAYER_OFFLINE = messageHandler.getPrefixString("Commands.Heal.Messages.TargetPlayerOffline");

    //Todo: Farmworld
    public static String FARMWORLD_COMMAND_PERMISSION_ADMIN = messageHandler.getString("Commands.Farmworld.Permissions.Admin");
    public static String FARMWORLD_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Farmworld.Messages.Usage");
    public static String FARMWORLD_COMMAND_MESSAGE_TELEPORTED = messageHandler.getPrefixString("Commands.Farmworld.Messages.Teleported");
    public static String FARMWORLD_COMMAND_MESSAGE_NOT_FOUND = messageHandler.getPrefixString("Commands.Farmworld.Messages.NotFound");
    public static String FARMWORLD_COMMAND_MESSAGE_WARP_SET = messageHandler.getPrefixString("Commands.Farmworld.Messages.WarpSet");
    public static String FARMWORLD_COMMAND_MESSAGE_WARP_REMOVE = messageHandler.getPrefixString("Commands.Farmworld.Messages.WarpRemove");

    //Todo: Nether
    public static String NETHER_COMMAND_PERMISSION_ADMIN = messageHandler.getString("Commands.Nether.Permissions.Admin");
    public static String NETHER_COMMAND_MESSAGE_USAGE = messageHandler.getPrefixString("Commands.Nether.Messages.Usage");
    public static String NETHER_COMMAND_MESSAGE_TELEPORTED = messageHandler.getPrefixString("Commands.Nether.Messages.Teleported");
    public static String NETHER_COMMAND_MESSAGE_NOT_FOUND = messageHandler.getPrefixString("Commands.Nether.Messages.NotFound");
    public static String NETHER_COMMAND_MESSAGE_WARP_SET = messageHandler.getPrefixString("Commands.Nether.Messages.WarpSet");
    public static String NETHER_COMMAND_MESSAGE_WARP_REMOVE = messageHandler.getPrefixString("Commands.Nether.Messages.WarpRemove");

    //Todo: ColoredChat
    public static String SETTINGS_PERMISSION_COLORED_CHAT = plugin.getMessagesConfig().getString("Settings.ColoredChat.Permissions.Use");

    /*
    CheckPlot
     */
    public static String CHECKPLOT_USE_PERM = messageHandler.getString("Commands.CheckPlot.Permission.Use");
    public static String CHECKPLOT_CLEAR_PERM = messageHandler.getString("Commands.CheckPlot.Permission.Clear");
    public static String CHECKPLOT_USAGE = messageHandler.getPrefixString("Commands.CheckPlot.Messages.Usage");
    public static String CHECKPLOT_CLEAR_READY = messageHandler.getPrefixString("Commands.CheckPlot.Messages.ClearReady");
    public static String CHECKPLOT_CLEAR_NOT_READY = messageHandler.getPrefixString("Commands.CheckPlot.Messages.ClearNotReady");
    public static String CHECKPLOT_CANT_USE = messageHandler.getPrefixString("Commands.CheckPlot.Messages.CantUse");
    public static String CHECKPLOT_CLEARED = messageHandler.getPrefixString("Commands.CheckPlot.Messages.Cleared");
    public static Long CHECKPLOT_TIME = Long.valueOf(messageHandler.getString("Commands.CheckPlot.Settings.TimeToClear"));
    public static TimeUnit CHECKPLOT_UNIT = TimeUnit.valueOf(messageHandler.getString("Commands.CheckPlot.Settings.Unit"));
    public static String CHECKPLOT_NOT_IN_PLOT = messageHandler.getPrefixString("Commands.CheckPlot.Messages.NotInPlot");

}
