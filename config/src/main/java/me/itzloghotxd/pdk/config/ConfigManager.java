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
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Manages multiple configuration files by mapping them to an {@link String} config.
 * This class provides utility functions to save, reload, and retrieve configurations efficiently.
 *
 * @author ItzLoghotXD
 * @since 0.0.2
 */
public class ConfigManager {
    private final Map<String, ConfigHandler> configurations = new HashMap<>();
    private final Plugin plugin;

    /**
     * Constructs a new ConfigManager.
     *
     * @param plugin The plugin instance using this manager
     */
    public ConfigManager(Plugin plugin) {
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

    /**
     * Retrieves a registered configuration file by its associated {@link String} config.
     *
     * @param config The {@link String} key associated with the configuration file.
     * @return The corresponding {@link ConfigHandler} instance.
     * @throws IllegalStateException if the configuration file is not registered.
     */
    public ConfigHandler get(@NotNull String config) {
        ConfigHandler handler = configurations.get(config);
        if (handler == null) throw new IllegalStateException("Config file not registered: " + config);
        return handler;
    }

    /**
     * Registers a configuration file in the manager. And then,
     * Saves the default configuration files if it does not already exist.
     *
     * @param configs The {@link ConfigHandler} instance to associate with the given key.
     */
    public void register(ConfigHandler... configs) {
        for (ConfigHandler config : configs) {
            configurations.put(config.getName(), config);
            config.saveDefaultConfig();
        }
        plugin.getLogger().log(Level.INFO, "[PDK CONFIG] Successfully registered " + configs.length + " config file(s). Total: " + configurations.size());
        plugin.getLogger().log(Level.INFO, "[PDK CONFIG] Successfully saved " + configs.length + " config file(s)!");
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