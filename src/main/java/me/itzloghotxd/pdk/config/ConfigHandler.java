package me.itzloghotxd.pdk.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Handles the creation, loading, saving, and reloading of a YAML configuration file.
 * This class ensures that a default configuration is created if it does not exist and provides
 * utility methods to interact with the file.
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings("unused")
public class ConfigHandler {
    private final JavaPlugin plugin;
    private final String name;
    private final File file;
    private FileConfiguration configuration;

    /**
     * Constructs a new ConfigHandler for managing a specific configuration file.
     *
     * @param plugin The JavaPlugin instance using this configuration handler.
     * @param name   The name of the configuration file (without ".yml" extension).
     */
    public ConfigHandler(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name + ".yml";
        this.file = new File(plugin.getDataFolder(), this.name);
        this.configuration = new YamlConfiguration();
    }

    /**
     * Saves the default configuration file if it does not already exist.
     * If an error occurs during loading, the plugin is disabled.
     */
    public void saveDefaultConfig() {
        if (!file.exists()) {
            plugin.saveResource(name, false);
        }

        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException var1) {
            var1.printStackTrace();
            plugin.getLogger().severe("============= CONFIGURATION ERROR =============");
            plugin.getLogger().severe("There was an error loading " + name);
            plugin.getLogger().severe("Please check for any obvious configuration mistakes");
            plugin.getLogger().severe("such as using tabs for spaces or forgetting to end quotes");
            plugin.getLogger().severe("before reporting to the developer. The plugin will now disable..");
            plugin.getLogger().severe("============= CONFIGURATION ERROR =============");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    /**
     * Saves the current configuration state to the file.
     * If an error occurs, it logs an error message.
     */
    public void save() {
        if (configuration == null || file == null) return;

        try {
            getConfig().save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save config file: " + name);
            e.printStackTrace();
        }
    }

    /**
     * Reloads the configuration from the file, replacing the current configuration instance.
     */
    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Retrieves the current {@link FileConfiguration} instance.
     *
     * @return The {@link FileConfiguration} object for this config file.
     */
    public FileConfiguration getConfig() {
        return configuration;
    }
}
