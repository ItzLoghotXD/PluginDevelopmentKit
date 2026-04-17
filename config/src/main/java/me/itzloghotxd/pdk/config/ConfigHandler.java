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

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Handles the creation, loading, saving, and reloading of a YAML configuration file.
 * This class ensures that a default configuration is created if it does not exist, and provides
 * utility methods to interact with the file.
 *
 * @author ItzLoghotXD
 * @since 0.2.0
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
    public ConfigHandler(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name.endsWith(".yml") ? name : name + ".yml";
        file = new File(plugin.getDataFolder(), this.name);
        configuration = new YamlConfiguration();
    }

    protected void load(boolean b) {
        try {
            configuration.load(file);
        } catch (IOException e) {
            handleConfigLoadingException(e, b);
        } catch (InvalidConfigurationException e) {
            handleConfigLoadingException(e, b);
        }
    }

    /**
     * Saves the default configuration file if it does not already exist.
     * If an error occurs during loading, the plugin is disabled.
     */
    public void saveDefault() {
        if (!file.exists()) {
            try {
                plugin.saveResource(name, false);
            } catch (IllegalArgumentException i) {
                plugin.getLogger().log(Level.WARNING, "[PDK CONFIG] Could not find config file: " + name + " inside the jar.");
            }
        }

        if (!file.exists()) {
            plugin.getLogger().log(Level.SEVERE, "[PDK CONFIG] File " + name + " doesn't exist.");
            plugin.getLogger().log(Level.INFO, "[PDK CONFIG] An empty file will be created.");
            try {
                if (file.getParentFile() != null && !file.getParentFile().exists()) {
                    if (!file.getParentFile().mkdirs()) {
                        throw new IOException("Failed to create directories: " + file.getParentFile().getAbsolutePath());
                    }
                }

                if (!file.createNewFile()) {
                    throw new IOException("Failed to create file: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                handleConfigLoadingException(e, true);
                return;
            }
        }

        load(true);
    }

    protected void save() {
        if (configuration == null || file == null) return;

        try {
            configuration.save(file);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "[PDK CONFIG] Could not save config file: " + name);
            e.printStackTrace();
        }
    }

    protected void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    protected FileConfiguration getConfig() {
        return configuration;
    }

    protected String getName() {
        return name.substring(0, name.length() - 4);
    }

    private void handleConfigLoadingException(Exception e, boolean b) {
        plugin.getLogger().log(Level.SEVERE,"[PDK CONFIG] ============= CONFIGURATION ERROR =============");
        plugin.getLogger().log(Level.SEVERE,"[PDK CONFIG] There was an error loading " + name);
        plugin.getLogger().log(Level.SEVERE,"[PDK CONFIG] Please check for any obvious configuration mistakes");
        plugin.getLogger().log(Level.SEVERE,"[PDK CONFIG] such as using tabs for spaces or forgetting to end quotes");
        plugin.getLogger().log(Level.SEVERE,"[PDK CONFIG] before reporting to the developer.");
        plugin.getLogger().log(Level.SEVERE,"[PDK CONFIG] ============= CONFIGURATION ERROR =============");
        e.printStackTrace();
        if (b) {
            plugin.getLogger().log(Level.SEVERE, "The plugin will now disable...");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}