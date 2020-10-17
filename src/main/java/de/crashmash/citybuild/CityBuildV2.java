package de.crashmash.citybuild;

import de.crashmash.citybuild.commands.SchildCommand;
import de.crashmash.citybuild.listener.SignChangeListener;
import de.crashmash.citybuild.storage.Storage;
import de.crashmash.citybuild.utils.SignEdit;
import de.crashmash.citybuild.utils.SignEdit_1_16_R2;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class CityBuildV2 extends JavaPlugin {

    private static CityBuildV2 plugin;
    private Storage storage;
    private static SignEdit signedit;

    @Override
    public void onEnable() {
        plugin = this;

        sendMessage("§aEnabled");

        loadMySQLConfig();
        loadConfig();
        loadMessagesConfig();

        loadCommands();
        loadListener();
        setupSignEdit();

        this.storage = new Storage();
        storage.createConnection();
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
    }

    private void loadListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new SignChangeListener(), this);
    }

    private void sendMessage(String status) {
        Bukkit.getConsoleSender().sendMessage("§7================================================");
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eStatus: " + status);
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eVersion: §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eAuthors: §6" + getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7================================================");
    }

    private boolean setupSignEdit() {
        signedit = new SignEdit_1_16_R2();
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
}
