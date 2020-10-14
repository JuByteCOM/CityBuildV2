package de.crashmash.citybuild;

import de.crashmash.citybuild.storage.Storage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class CityBuildV2 extends JavaPlugin {

    private static CityBuildV2 plugin;
    private Storage storage;

    @Override
    public void onEnable() {
        plugin = this;

        sendMessage("§aEnabled");

        loadMySQLConfig();

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

    private void loadMySQLConfig() {
        File file = new File(getDataFolder(), "mysql.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("mysql.yml", false);
        }
    }

    public static File getFileFolder() {
        return new File(plugin.getDataFolder().toString());
    }

    public FileConfiguration getMySQLConfig() {
        File file = new File(getFileFolder(), "mysql.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    private void sendMessage(String status) {
        Bukkit.getConsoleSender().sendMessage("§7================================================");
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eStatus: " + status);
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eVersion: §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§9CityBuild§eV2 §7| §eAuthors: §6" + getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7================================================");
    }

    public static CityBuildV2 getPlugin() {
        return plugin;
    }
}
