package de.jubyte.citybuild;

import com.google.common.base.Charsets;
import com.jubyte.developerapi.commands.AbstractCommand;
import com.jubyte.developerapi.utils.MessageHandler;
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
import de.jubyte.citybuild.utils.SignEdit;
import de.jubyte.citybuild.utils.SignEdit_ProtocolLib;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
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

    private FileConfiguration newMessageConfig = null;
    private final File messageFile = new File(getDataFolder(), "messages.yml");

    private FileConfiguration newMySQLConfig = null;
    private final File mySQLFile = new File(getDataFolder(), "mysql.yml");

    @Override
    public void onEnable() {
        PLUGIN = this;

        sendMessage("§aEnabled");

        loadMySQLConfig();
        loadConfig();
        loadMessagesConfig();

        setConfigKeys();
        setMessagesConfigKeys();
        setMySQLConfigKeys();

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

        if(storage != null) {
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
        if(ConfigData.CONFIG_COMMAND_CHECKPLOT_ACTIVE) {
            AbstractCommand command = new CheckPlotCommand();
            command.register();
        }
        if (ConfigData.CONFIG_COMMAND_SUDO_ACTIVE){
            AbstractCommand command = new SudoCommand();
            command.register();
        }
        if (ConfigData.CONFIG_COMMAND_CLEAR_ACTIVE){
            AbstractCommand command = new ClearCommand();
            command.register();
        }
        if (ConfigData.CONFIG_COMMAND_FLY_ACTIVE){
            AbstractCommand command = new FlyCommand();
            command.register();
        }
        if (ConfigData.CONFIG_COMMAND_FEED_ACTIVE){
            AbstractCommand command = new FeedCommand();
            command.register();
        }
        if (ConfigData.CONFIG_COMMAND_HEAL_ACTIVE){
            AbstractCommand command = new HealCommand();
            command.register();
        }
        if (ConfigData.CONFIG_COMMAND_NETHER_ACTIVE){
            AbstractCommand command = new NetherCommand();
            command.register();
        }
        if (ConfigData.CONFIG_COMMAND_FARMWORLD_ACTIVE){
            AbstractCommand command = new FarmworldCommand();
            command.register();
        }
        if(ConfigData.CONFIG_COMMAND_INVSEE_ACTIVE) {
            AbstractCommand invseeCommand = new InvseeCommand();
            invseeCommand.register();
        }
        if(ConfigData.CONFIG_COMMAND_GOD_ACTIVE) {
            AbstractCommand godCommand = new GodCommand();
            godCommand.register();
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
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new PrepareAnvilListener(), this);
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

    private void setupSignEdit() {
        signedit = new SignEdit_ProtocolLib();
    }

    public void saveMessagesConfig() {
        try {
            this.getMessagesConfig().save(this.messageFile);
        } catch (IOException var2) {
            Bukkit.getLogger().warning("Could not save config to " + this.messageFile + ": "+ var2);
        }

    }

    public void saveMySQLConfig() {
        try {
            this.getMySQLConfig().save(this.mySQLFile);
        } catch (IOException var2) {
            Bukkit.getLogger().warning("Could not save config to " + this.mySQLFile + ": "+ var2);
        }

    }

    public void reloadMessagesConfig() {
        this.newMessageConfig = YamlConfiguration.loadConfiguration(this.messageFile);
        InputStream defConfigStream = this.getResource("messages.yml");
        if (defConfigStream != null) {
            this.newMessageConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }
    }

    public void reloadMySQLConfig() {
        this.newMySQLConfig = YamlConfiguration.loadConfiguration(this.mySQLFile);
        InputStream defConfigStream = this.getResource("mysql.yml");
        if (defConfigStream != null) {
            this.newMySQLConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }
    }

    public void loadConfig() {
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
    }

    public void loadMySQLConfig() {
        File file = new File(getDataFolder(), "mysql.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("mysql.yml", false);
        }
    }

    public FileConfiguration getMySQLConfig() {
        if (this.newMySQLConfig == null) {
            this.reloadMySQLConfig();
        }
        return this.newMySQLConfig;
    }

    public void loadMessagesConfig() {
        File file = new File(getDataFolder(), "messages.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
    }

    public FileConfiguration getMessagesConfig() {
        if (this.newMessageConfig == null) {
            this.reloadMessagesConfig();
        }
        return this.newMessageConfig;
    }

    public void setConfigKeys() {
        List<String> keyList = new ArrayList<>(this.getConfig().getDefaults().getKeys(true));
        for(String key : getConfig().getKeys(true)) {
            keyList.remove(key);
        }
        if(keyList.size() >= 1) {
            for(String key : keyList) {
                getConfig().set(key, this.getConfig().getDefaults().get(key));
            }
            saveConfig();
        }
        keyList.clear();
    }

    public void setMessagesConfigKeys() {
        List<String> keyList = new ArrayList<>(this.getMessagesConfig().getDefaults().getKeys(true));
        for(String key : getMessagesConfig().getKeys(true)) {
            keyList.remove(key);
        }
        if(keyList.size() >= 1) {
            for(String key : keyList) {
                getMessagesConfig().set(key, this.getMessagesConfig().getDefaults().get(key));
            }
            saveMessagesConfig();
        }
        keyList.clear();
    }

    public void setMySQLConfigKeys() {
        List<String> keyList = new ArrayList<>(this.getMySQLConfig().getDefaults().getKeys(true));
        for(String key : getMySQLConfig().getKeys(true)) {
            keyList.remove(key);
        }
        if(keyList.size() >= 1) {
            for(String key : keyList) {
                getMySQLConfig().set(key, this.getMySQLConfig().getDefaults().get(key));
            }
            saveMySQLConfig();
        }
        keyList.clear();
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
