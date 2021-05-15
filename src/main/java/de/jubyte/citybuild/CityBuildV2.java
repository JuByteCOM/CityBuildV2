package de.jubyte.citybuild;

import com.google.common.base.Charsets;
import de.crashmash.developerapi.commands.AbstractCommand;
import de.crashmash.developerapi.utils.MessageHandler;
import de.jubyte.citybuild.commands.*;
import de.jubyte.citybuild.data.ConfigData;
import de.jubyte.citybuild.listener.*;
import de.jubyte.citybuild.manager.checkplot.CheckPlotCache;
import de.jubyte.citybuild.manager.cooldown.CooldownCache;
import de.jubyte.citybuild.manager.food.FoodLocation;
import de.jubyte.citybuild.manager.glow.GlowCache;
import de.jubyte.citybuild.manager.locations.Locations;
import de.jubyte.citybuild.manager.mutep.MutePCache;
import de.jubyte.citybuild.manager.startkick.StartKickCache;
import de.jubyte.citybuild.manager.status.StatusCache;
import de.jubyte.citybuild.storage.FoodSQL;
import de.jubyte.citybuild.storage.LocationSQL;
import de.jubyte.citybuild.storage.Storage;
import de.jubyte.citybuild.utils.LibDownloader;
import de.jubyte.citybuild.utils.SignEdit;
import de.jubyte.citybuild.utils.SignEdit_1_16_R3;
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
    private MessageHandler messageHandler;

    private StartKickCache startKickCache;
    private CooldownCache cooldownCache;
    private MutePCache mutePCache;
    private GlowCache glowCache;
    private StatusCache statusCache;
    private CheckPlotCache checkPlotCache;

    private final List<String> VOTING_YES = new ArrayList<>();
    private final List<String> VOTING_NO = new ArrayList<>();

    private final Map<Player, Player> COMMANDSPY_MAP = new HashMap<>();

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

        File file = new File(getDataFolder(), "messages.yml");
        this.messageHandler = new MessageHandler(file, "Prefix", "[prefix]");

        this.storage = new Storage();
        storage.createConnection();

        startKickCache = new StartKickCache();
        cooldownCache = new CooldownCache();
        mutePCache = new MutePCache();
        glowCache = new GlowCache();
        statusCache = new StatusCache();
        checkPlotCache = new CheckPlotCache();

        loadListener();
        setupSignEdit();
        loadCommands();

        loadLocations();
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
        if(ConfigData.CONFIG_COMMAND_GIFTRANK_ACTIVE) {
            AbstractCommand command = new GiftRankCommand();
            command.register();
        }

        new CheckPlotCommand().register();
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

    public Map<Player, Player> getCOMMANDSPY_MAP() {
        return COMMANDSPY_MAP;
    }

    public StartKickCache getStartKickCache() {
        return startKickCache;
    }

    public CooldownCache getCooldownCache() {
        return cooldownCache;
    }

    public MutePCache getMutePCache() {
        return mutePCache;
    }

    public GlowCache getGlowCache() {
        return glowCache;
    }

    public StatusCache getStatusCache() {
        return statusCache;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public CheckPlotCache getCheckPlotCache() {
        return checkPlotCache;
    }
}
