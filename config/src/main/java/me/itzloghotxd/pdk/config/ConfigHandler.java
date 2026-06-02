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

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Handles the creation, loading, saving, and reloading of a YAML configuration file.
 * This class ensures that a default configuration is created if it does not exist, and provides
 * utility methods to interact with the file.
 *
 * @author ItzLoghotXD
 */
public class ConfigHandler {
    private final JavaPlugin plugin;
    private final String name;
    private final File file;
    private FileConfiguration configuration;

    /**
     * Constructs a new ConfigHandler for managing a specific configuration file.
     *
     * @param plugin The plugin instance using this configuration handler.
     * @param name The name of the configuration file (without ".yml" extension) [Case sensitive].
     */
    public ConfigHandler(@NotNull JavaPlugin plugin, @NotNull String name) {
        this.plugin = plugin;
        this.name = name.endsWith(".yml") ? name : name + ".yml";
        file = new File(plugin.getDataFolder(), this.name);
        configuration = new YamlConfiguration();
    }

    protected void load(boolean b) {
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            handleConfigLoadingException(e, b);
        }
    }

    /**
     * Saves the default configuration resource from the JAR if missing, then loads it.
     */
    public void saveDefault() {
        if (!file.exists()) {
            try {
                plugin.saveResource(name, false);
            } catch (IllegalArgumentException i) {
                plugin.getLogger().warning("[PDK CONFIG] Could not find config file: \"" + name + "\" inside the jar.");
            }
        }

        load(true);
    }

    /**
     * Saves the in-memory configuration to disk.
     */
    public void save() {
        if (configuration == null || file == null) return;

        try {
            configuration.save(file);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "[PDK CONFIG] Could not save config file: " + name, e);
        }
    }

    /**
     * Reloads the configuration from disk.
     */
    public void reload() {
        configuration = new YamlConfiguration();
        load(false);
    }

    /**
     * Retrieves the {@link FileConfiguration} associated with this configuration file.
     *
     * @return The {@link FileConfiguration} instance.
     */
    public @NotNull FileConfiguration getConfig() {
        return configuration;
    }

    /**
     * Gets the configuration file name without the ".yml" extension.
     *
     * @return The configuration name.
     */
    public @NotNull String getName() {
        int index = name.lastIndexOf('.');
        return index == -1 ? name : name.substring(0, index);
    }

    private void handleConfigLoadingException(Exception e, boolean b) {
        plugin.getLogger().severe("[PDK CONFIG] ============= CONFIGURATION ERROR =============");
        plugin.getLogger().severe("[PDK CONFIG] There was an error loading " + name);
        plugin.getLogger().severe("[PDK CONFIG] Please check for any obvious configuration mistakes");
        plugin.getLogger().severe("[PDK CONFIG] such as using tabs for spaces or forgetting to end quotes");
        plugin.getLogger().severe("[PDK CONFIG] before reporting to the developer.");
        plugin.getLogger().severe("[PDK CONFIG] ============= CONFIGURATION ERROR =============");
        plugin.getLogger().log(Level.SEVERE, "", e);
        if (b) {
            plugin.getLogger().severe( "The plugin will now disable...");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}