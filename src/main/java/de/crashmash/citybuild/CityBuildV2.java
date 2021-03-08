package de.crashmash.citybuild;

import de.crashmash.citybuild.commands.*;
import de.crashmash.citybuild.data.MessagesData;
import de.crashmash.citybuild.listener.*;
import de.crashmash.citybuild.manager.cooldown.CooldownPlayer;
import de.crashmash.citybuild.manager.food.FoodLocation;
import de.crashmash.citybuild.manager.cooldown.Cooldown;
import de.crashmash.citybuild.manager.startkick.StartKick;
import de.crashmash.citybuild.manager.startkick.StartKickPlayer;
import de.crashmash.citybuild.storage.*;
import de.crashmash.citybuild.utils.SignEdit;
import de.crashmash.citybuild.utils.SignEdit_1_16_R3;
import de.crashmash.developerapi.utils.AutoUpdater;
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
import java.util.*;

public class CityBuildV2 extends JavaPlugin {

    private static CityBuildV2 plugin;
    private Storage storage;
    private static SignEdit signedit;

    private final List<String> VOTING_YES = new ArrayList<>();
    private final List<String> VOTING_NO = new ArrayList<>();

    private final Map<Player, StartKickPlayer> STARTKICKPLAYER_MAP = new HashMap<>();
    private final Map<Player, CooldownPlayer> COOLDWNPLAYER_MAP = new HashMap<>();
    private final Map<Player, Player> COMMANDSPY_MAP = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        PretronicLogger logger = PretronicLoggerFactory.getLogger();
        logger.setLevel(LogLevel.INFO);
        SLF4JStaticBridge.trySetLogger(logger);

        sendMessage("§aEnabled");

        loadMySQLConfig();
        loadConfig();
        loadMessagesConfig();

        loadCommands();
        loadListener();
        setupSignEdit();

        this.storage = new Storage();
        storage.createConnection();

        loadLocations();
        loadPlayers();

        //AutoUpdater updater = new AutoUpdater(CityBuildV2.getPlugin(), 88621, this.getFile(), AutoUpdater.UpdateType.CHECK_DOWNLOAD, true);
        //System.out.println(updater.getResult());
        //System.out.println(updater.getVersion());
    }

    @Override
    public void onDisable() {
        sendMessage("§cDisabled");

        if(storage.isConnected()) {
            storage.deleteConnection();
        }
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("schild")).setExecutor(new SchildCommand());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new ClearChatCommand());
        Objects.requireNonNull(getCommand("status")).setExecutor(new StatusCommand());
        Objects.requireNonNull(getCommand("food")).setExecutor(new FoodCommand());
        Objects.requireNonNull(getCommand("tp")).setExecutor(new TeleportCommand());
        Objects.requireNonNull(getCommand("startkick")).setExecutor(new StartkickCommand());
        Objects.requireNonNull(getCommand("ja")).setExecutor(new JaCommand());
        Objects.requireNonNull(getCommand("nein")).setExecutor(new NeinCommand());
        Objects.requireNonNull(getCommand("unstartkick")).setExecutor(new UnstartKickCommand());
        Objects.requireNonNull(getCommand("slowchat")).setExecutor(new SlowChatCommand());
        Objects.requireNonNull(getCommand("head")).setExecutor(new HeadCommand());
        Objects.requireNonNull(getCommand("breakblock")).setExecutor(new BreakblockCommand());
        Objects.requireNonNull(getCommand("commandspy")).setExecutor(new CommandSpyCommand());

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
    }

    private boolean setupSignEdit() {
        signedit = new SignEdit_1_16_R3();
        return true;
    }

    public static File getFileFolder() {
        return new File(plugin.getDataFolder().toString());
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
        File file = new File(getFileFolder(), "mysql.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    private void loadMessagesConfig() {
        File file = new File(getDataFolder(), "messages.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
    }

    private void loadPlayers() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission(MessagesData.STATUS_COMMAND_PERMISSION_USE))
                StatusSQL.createPlayer(all.getUniqueId());
            StartkickSQL.createPlayer(all.getUniqueId());
            CooldownSQL.createPlayer(all.getUniqueId());
            //Todo: Map Einträge
            if(StartkickSQL.playerExists(all.getUniqueId())) {
                if(!CityBuildV2.getPlugin().getSTARTKICKPLAYER_MAP().containsKey(all)) {
                    StartKick.createStartKickPlayer(all);
                }
            }
            if(CooldownSQL.playerExists(all.getUniqueId())) {
                if(!CityBuildV2.getPlugin().getCOOLDWNPLAYER_MAP().containsKey(all)) {
                    Cooldown.createCooldownPlayer(all);
                }
            }
        }
    }

    public FileConfiguration getMessagesConfig() {
        File file = new File(getFileFolder(), "messages.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static SignEdit getSignEdit() {
        return signedit;
    }

    public static CityBuildV2 getPlugin() {
        return plugin;
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
}
