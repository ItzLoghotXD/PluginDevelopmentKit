/*
 * Copyright (c) 2026 ItzLoghotXD
 *
 * This file is part of "Plugin Development Kit - PDK" Library.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 */

package me.itzloghotxd.pdk.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Manages multiple configuration folder by mapping them to an {@link String}.
 * This class provides utility functions to save, reload, and retrieve configurations efficiently.
 *
 * @author ItzLoghotXD
 * @since 0.2.2
 */
public class ConfigFolderManager {
    private final Map<String, ConfigFolderHandler> configurationFolders = new HashMap<>();
    private final JavaPlugin plugin;

    /**
     * Constructs a new ConfigFolderManager.
     *
     * @param plugin The plugin instance using this manager
     */
    public ConfigFolderManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Loads all registered configuration folders.
     */
    public void load() {
        configurationFolders.values().forEach(ConfigFolderHandler::load);
    }

    /**
     * Saves all registered configuration folders.
     */
    public void save() {
        configurationFolders.values().forEach(ConfigFolderHandler::save);
    }

    /**
     * Saves a specific configuration folder.
     *
     * @param configFolder The {@link String} key associated with the configuration folder.
     */
    public void save(String... configFolder) {
        for (String config : configFolder) {
            get(config).save();
        }
    }

    /**
     * Reloads all registered configuration folders.
     */
    public void reload() {
        configurationFolders.values().forEach(ConfigFolderHandler::reload);
    }

    /**
     * Reloads a specific configuration folder.
     *
     * @param configFolder The {@link String} key associated with the configuration folder.
     */
    public void reload(String... configFolder) {
        for (String config : configFolder) {
            get(config).reload();
        }
    }

    private ConfigFolderHandler get(@NotNull String configFolder) {
        ConfigFolderHandler handler = configurationFolders.get(configFolder);
        if (handler == null) throw new IllegalStateException("Config folder not registered: " + configFolder);
        return handler;
    }

    /**
     * Registers configuration folder in the manager.
     *
     * @param configFolders The {@link ConfigFolderHandler} instance to associate with its name.
     */
    public void register(ConfigFolderHandler... configFolders) {
        for (ConfigFolderHandler configFolder : configFolders) {
            configurationFolders.put(configFolder.getName(), configFolder);
        }
        plugin.getLogger().log(Level.INFO, "[PDK CONFIG] Successfully registered " + configFolders.length + " config folder(s). Total: " + configurationFolders.size());
    }

    /**
     * Checks if a configuration folder is registered.
     *
     * @param configFolder The {@link String} key associated with the configuration folder.
     * @return True if the configuration folder is registered, false otherwise.
     */
    public boolean has(String configFolder) {
        return configurationFolders.containsKey(configFolder);
    }

    /**
     * Retrieves the {@link FileConfiguration} associated with a registered configuration file.
     *
     * @param configFolder The {@link String} key associated with the configuration folder.
     * @param configFile The {@link String} key associated with the configuration file. (relative to {@code configFolder})
     * @return The {@link FileConfiguration} instance.
     */
    public FileConfiguration getConfig(String configFolder, String configFile) {
        return get(configFolder).getConfigManager().getConfig(configFolder+"/"+configFile);
    }

    /**
     * Retrieves a map of all registered configuration folders.
     *
     * @return A {@link Map} containing all registered {@link ConfigFolderHandler} instances.
     */
    public Map<String, ConfigFolderHandler> getConfigurations() {
        return configurationFolders;
    }
}