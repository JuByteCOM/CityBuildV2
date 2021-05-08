package de.crashmash.citybuild;

import com.google.common.base.Charsets;
import de.crashmash.citybuild.commands.*;
import de.crashmash.citybuild.data.ConfigData;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.listener.*;
import de.crashmash.citybuild.manager.Locations;
import de.crashmash.citybuild.manager.cooldown.Cooldown;
import de.crashmash.citybuild.manager.cooldown.CooldownPlayer;
import de.crashmash.citybuild.manager.food.FoodLocation;
import de.crashmash.citybuild.manager.mutep.MuteP;
import de.crashmash.citybuild.manager.mutep.MutepPlayer;
import de.crashmash.citybuild.manager.startkick.StartKickCache;
import de.crashmash.citybuild.manager.startkick.StartKickPlayer;
import de.crashmash.citybuild.storage.*;
import de.crashmash.citybuild.manager.playerdata.AbstractPlayerDataHandler;
import de.crashmash.citybuild.manager.playerdata.implementation.DefaultPlayerDataHandlerImplementation;
import de.crashmash.citybuild.manager.playerdata.implementation.DKBansPlayerDataHandlerImplementation;
import de.crashmash.citybuild.utils.LibDownloader;
import de.crashmash.citybuild.utils.SignEdit;
import de.crashmash.citybuild.utils.SignEdit_1_16_R3;
import de.crashmash.developerapi.commands.AbstractCommand;
import net.pretronic.libraries.logging.PretronicLogger;
import net.pretronic.libraries.logging.PretronicLoggerFactory;
import net.pretronic.libraries.logging.bridge.slf4j.SLF4JStaticBridge;
import net.pretronic.libraries.logging.level.LogLevel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityBuildV2 extends JavaPlugin {

    private static CityBuildV2 PLUGIN;
    private Storage storage;
    private static SignEdit signedit;
    private StartKickCache startKickCache;

    private final List<String> VOTING_YES = new ArrayList<>();
    private final List<String> VOTING_NO = new ArrayList<>();

    private final Map<Player, StartKickPlayer> STARTKICKPLAYER_MAP = new HashMap<>();
    private final Map<Player, CooldownPlayer> COOLDWNPLAYER_MAP = new HashMap<>();
    private final Map<Player, Player> COMMANDSPY_MAP = new HashMap<>();
    private final Map<Player, MutepPlayer> MUTEPPLAYER_MAP = new HashMap<>();

    private AbstractPlayerDataHandler playerDataHandler;

    private FileConfiguration newConfig = null;
    private final File configFile = new File(getDataFolder(), "messages.yml");

    @Override
    public void onEnable() {
        PLUGIN = this;

        if (!Bukkit.getPluginManager().isPluginEnabled("McNative"))
            LibDownloader.downloadLib(LibDownloader.Library.HTMMLUNIT);

        PretronicLogger logger = PretronicLoggerFactory.getLogger();
        logger.setLevel(LogLevel.INFO);
        SLF4JStaticBridge.trySetLogger(logger);

        sendMessage("§aEnabled");

        loadMySQLConfig();
        loadConfig();
        loadMessagesConfig();

        this.storage = new Storage();
        storage.createConnection();

        startKickCache = new StartKickCache();

        loadListener();
        setupSignEdit();
        loadCommands();

        loadLocations();
        loadPlayers();

        findPlayerDataProvider();
    }

    private void findPlayerDataProvider() {

        if (Bukkit.getPluginManager().isPluginEnabled("DKBans")) {
            this.playerDataHandler = new DKBansPlayerDataHandlerImplementation();
            return;
        }


        this.playerDataHandler = new DefaultPlayerDataHandlerImplementation();
    }

    @Override
    public void onDisable() {
        sendMessage("§cDisabled");

        if(storage.isConnected()) {
            storage.deleteConnection();
        }
    }

    private void loadCommands() {
        if(ConfigData.CONFIG_COMMAND_SCHILD_ACTIVE) {
            AbstractCommand command = new SigneditCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_ALLORNOTHING_ACTIVE) {
            AbstractCommand command = new AllOrNothingCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_BREAKBLOCK_ACTIVE) {
            AbstractCommand command = new BreakblockCommand();
            command.register();
        }
        AbstractCommand cityBuildCommand = new CityBuildCommand();
        cityBuildCommand.register();
        if(ConfigData.CONFIG_COMMAND_CHATCLEAR_ACTIVE) {
            AbstractCommand command = new ClearChatCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_COMMANDSPY_ACTIVE) {
            AbstractCommand command = new CommandSpyCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_FOOD_ACTIVE) {
            AbstractCommand command = new FoodCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_HEAD_ACTIVE) {
            AbstractCommand command = new HeadCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_JA_ACTIVE) {
            AbstractCommand command = new JaCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_NEIN_ACTIVE) {
            AbstractCommand command = new NeinCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_SLOWCHAT_ACTIVE) {
            AbstractCommand command = new SlowChatCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_STARTKICK_ACTIVE) {
            AbstractCommand command = new StartkickCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_STATUS_ACTIVE) {
            AbstractCommand command = new StatusCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_TELEPORT_ACTIVE) {
            AbstractCommand command = new TeleportCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_UNSTARTKICK_ACTIVE) {
            AbstractCommand command = new UnstartKickCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_MUTEP_ACTIVE) {
            AbstractCommand command = new MutePCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_UNMUTEP_ACTIVE) {
            AbstractCommand command = new UnmutePCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_TPHERE_ACTIVE) {
            AbstractCommand command = new TpHereCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_GLOW_ACTIVE) {
            AbstractCommand command = new GlowCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_GAMEMODE_ACTIVE) {
            AbstractCommand command = new GameModeCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_SPAWN_ACTIVE) {
            AbstractCommand command = new SpawnCommand();
            command.register();
        }
    }

    private void loadListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new SignChangeListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new EntityDeathListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new AsynPlayerChatListener(), this);
        pluginManager.registerEvents(new PlayerLoginListener(), this);
        pluginManager.registerEvents(new PlayerCommandPreprocessListener(), this);
    }

    private void sendMessage(String status) {
        Bukkit.getConsoleSender().sendMessage("§7================================================");
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eStatus: " + status);
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eVersion: §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eAuthors: §6" + getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7================================================");
    }

    public void loadLocations() {
        Bukkit.getServer().getScheduler().runTaskLater(this, () -> FoodLocation.setLocations(FoodSQL.loadFood()),10);
        Bukkit.getServer().getScheduler().runTaskLater(this, () -> Locations.setLocations(LocationSQL.loadLocations()),10);
    }

    private boolean setupSignEdit() {
        signedit = new SignEdit_1_16_R3();
        return true;
    }

    public void reloadMessagesConfig() {
        this.newConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defConfigStream = this.getResource("messages.yml");
        if (defConfigStream != null) {
            this.newConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }
    }

    private void loadConfig() {
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
    }

    private void loadMySQLConfig() {
        File file = new File(getDataFolder(), "mysql.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("mysql.yml", false);
        }
    }

    public FileConfiguration getMySQLConfig() {
        File file = new File(getDataFolder(), "mysql.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public void loadMessagesConfig() {
        File file = new File(getDataFolder(), "messages.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
    }

    public FileConfiguration getMessagesConfig() {
        if (this.newConfig == null) {
            this.reloadMessagesConfig();
        }

        return this.newConfig;
    }

    private void loadPlayers() {
        for(Player players : Bukkit.getOnlinePlayers()) {
            if (players.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE))
                StatusSQL.createPlayer(players.getUniqueId());
            CooldownSQL.createPlayer(players.getUniqueId());
            //Todo: Map Einträge
            if(CooldownSQL.playerExists(players.getUniqueId())) {
                if(!CityBuildV2.getPLUGIN().getCOOLDWNPLAYER_MAP().containsKey(players)) {
                    Cooldown.createCooldownPlayer(players);
                }
            }
            if(MutepSQL.playerExists(players.getUniqueId())) {
                if(!(CityBuildV2.getPLUGIN().getMUTEPPLAYER_MAP().containsKey(players))) {
                    MuteP.createMutePPlayer(players);
                    for (int i = 0; i < 100; i++) {for (int g = 0; g < 100; g++) { for (int e = 0; e < 100; e++) { for (int r = 0; r < 100; r++) {}}}}
                }
            }
            GlowSQL.createPlayer(players.getUniqueId());

        }
    }

    public static SignEdit getSignEdit() {
        return signedit;
    }

    public static CityBuildV2 getPLUGIN() {
        return PLUGIN;
    }

    public Storage getStorage() {
        return storage;
    }

    public List<String> getVOTING_YES() {
        return VOTING_YES;
    }

    public List<String> getVOTING_NO() {
        return VOTING_NO;
    }

    public Map<Player, StartKickPlayer> getSTARTKICKPLAYER_MAP() {
        return STARTKICKPLAYER_MAP;
    }

    public Map<Player, CooldownPlayer> getCOOLDWNPLAYER_MAP() {
        return COOLDWNPLAYER_MAP;
    }

    public Map<Player, Player> getCOMMANDSPY_MAP() {
        return COMMANDSPY_MAP;
    }

    public Map<Player, MutepPlayer> getMUTEPPLAYER_MAP() {
        return MUTEPPLAYER_MAP;
    }

    public StartKickCache getStartKickCache() {
        return startKickCache;
    }
}
