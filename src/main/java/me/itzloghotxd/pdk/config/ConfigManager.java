package me.itzloghotxd.pdk.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages multiple configuration files by mapping them to an {@link Enum} type.
 * This class provides utility functions to save, reload, and retrieve configurations efficiently.
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings("unused")
public class ConfigManager {
    private final Map<Enum<?>, ConfigHandler> configurations = new HashMap<>();

    /**
     * Constructs a new ConfigManager.
     */
    public ConfigManager() {
    }

    /**
     * Saves all registered configuration files.
     */
    public void saveAllFiles() {
        configurations.values().forEach(ConfigHandler::save);
    }

    /**
     * Saves a specific configuration file.
     *
     * @param type The {@link Enum} key associated with the configuration file.
     */
    public void saveFile(Enum<?> type) {
        getFile(type).save();
    }

    /**
     * Reloads all registered configuration files.
     */
    public void reloadAllFiles() {
        configurations.values().forEach(ConfigHandler::reload);
    }

    /**
     * Reloads a specific configuration file.
     *
     * @param type The {@link Enum} key associated with the configuration file.
     */
    public void reloadFile(Enum<?> type) {
        getFile(type).reload();
    }

    /**
     * Retrieves a registered configuration file by its associated {@link Enum} type.
     *
     * @param type The {@link Enum} key associated with the configuration file.
     * @return The corresponding ConfigHandler instance.
     * @throws IllegalStateException if the configuration file is not registered.
     */
    public ConfigHandler getFile(Enum<?> type) {
        ConfigHandler handler = configurations.get(type);
        if (handler == null) {
            throw new IllegalStateException("Config file not registered: " + type.name());
        }
        return handler;
    }

    /**
     * Registers a configuration file in the manager.
     *
     * @param type  The {@link Enum} key used to identify this configuration file.
     * @param config The {@link ConfigHandler} instance to associate with the given key.
     */
    public void registerFile(Enum<?> type, ConfigHandler config) {
        configurations.put(type, config);
    }

    /**
     * Saves the default configuration files if it does not already exist.
     */
    public void saveDefault() {
        configurations.values().forEach(ConfigHandler::saveDefaultConfig);
    }

    /**
     * Checks if a configuration file is registered.
     *
     * @param type The {@link Enum} key associated with the configuration file.
     * @return True if the configuration file is registered, false otherwise.
     */
    public boolean hasConfig(Enum<?> type) {
        return configurations.containsKey(type);
    }

    /**
     * Loads a configuration file into a {@link FileConfiguration} object.
     *
     * @param file The file to load the configuration from.
     * @return A {@link FileConfiguration} instance containing the loaded data.
     */
    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Retrieves the {@link FileConfiguration} associated with a registered configuration file.
     *
     * @param type The {@link Enum} key associated with the configuration file.
     * @return The {@link FileConfiguration} instance.
     */
    public FileConfiguration getConfig(Enum<?> type) {
        return getFile(type).getConfig();
    }

    /**
     * Retrieves a map of all registered configurations.
     *
     * @return A {@link Map} containing all registered ConfigHandler instances.
     */
    public Map<Enum<?>, ConfigHandler> getConfigurations() {
        return configurations;
    }
}
