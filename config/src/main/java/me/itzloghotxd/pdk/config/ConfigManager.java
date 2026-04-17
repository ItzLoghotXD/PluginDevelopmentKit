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
 * Manages multiple configuration files by mapping them to an {@link String}.
 * This class provides utility functions to save, reload, and retrieve configurations efficiently.
 *
 * @author ItzLoghotXD
 * @since 0.2.0
 */
public class ConfigManager {
    private final Map<String, ConfigHandler> configurations = new HashMap<>();
    private final JavaPlugin plugin;

    /**
     * Constructs a new ConfigManager.
     *
     * @param plugin The plugin instance using this manager
     */
    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Saves all registered configuration files.
     */
    public void save() {
        configurations.values().forEach(ConfigHandler::save);
    }

    /**
     * Saves a specific configuration file.
     *
     * @param configs The {@link String} key associated with the configuration file.
     */
    public void save(String... configs) {
        for (String config : configs) {
            get(config).save();
        }
    }

    /**
     * Reloads all registered configuration files.
     */
    public void reload() {
        configurations.values().forEach(ConfigHandler::reload);
    }

    /**
     * Reloads a specific configuration file.
     *
     * @param configs The {@link String} key associated with the configuration file.
     */
    public void reload(String... configs) {
        for (String config : configs) {
            get(config).reload();
        }
    }

    private ConfigHandler get(@NotNull String config) {
        ConfigHandler handler = configurations.get(config);
        if (handler == null) throw new IllegalStateException("Config file not registered: " + config);
        return handler;
    }

    /**
     * Registers configuration file in the manager.
     *
     * @param configs The {@link ConfigHandler} instance to associate with its name.
     */
    public void register(ConfigHandler... configs) {
        for (ConfigHandler config : configs) {
            configurations.put(config.getName(), config);
        }
        plugin.getLogger().log(Level.INFO, "[PDK CONFIG] Successfully registered " + configs.length + " config file(s). Total: " + configurations.size());
    }

    /**
     * Saves the default configuration files if it does not already exist.
     */
    public void saveDefault() {
        configurations.values().forEach(ConfigHandler::saveDefault);
        plugin.getLogger().log(Level.INFO, "[PDK CONFIG] Successfully saved " + configurations.size() + " config file(s)!");
    }

    /**
     * Checks if a configuration file is registered.
     *
     * @param config The {@link String} key associated with the configuration file.
     * @return True if the configuration file is registered, false otherwise.
     */
    public boolean has(String config) {
        return configurations.containsKey(config);
    }

    protected ConfigManager clear() {
        configurations.clear();
        return this;
    }

    /**
     * Retrieves the {@link FileConfiguration} associated with a registered configuration file.
     *
     * @param config The {@link String} key associated with the configuration file.
     * @return The {@link FileConfiguration} instance.
     */
    public FileConfiguration getConfig(String config) {
        return get(config).getConfig();
    }
}