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

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Handles the creation, loading, saving, and reloading of a YAML configuration folder.
 *
 * @author ItzLoghotXD
 * @since 0.2.2
 */
public class ConfigFolderHandler {
    private final JavaPlugin plugin;
    private final File folder;
    private final ConfigManager configuration;

    /**
     * Constructs a new ConfigFolderHandler for managing a specific configuration folder.
     *
     * @param plugin The plugin instance using this configuration folder handler.
     * @param name The name of the configuration folder [Case sensitive].
     */
    public ConfigFolderHandler(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        folder = new File(plugin.getDataFolder(), name);
        configuration = new ConfigManager(plugin);
    }

    protected void load() {
        if (!folder.exists() && !folder.mkdirs()) {
            plugin.getLogger().log(Level.SEVERE, "[PDK CONFIG] Failed to create folder: " + folder.getAbsolutePath());
            return;
        }

        scan(folder);
    }

    private void scan(File dir) {
        List<ConfigHandler> handlers = new ArrayList<>();
        collectHandlers(dir, handlers);
        if (!handlers.isEmpty()) {
            configuration.register(handlers.toArray(new ConfigHandler[0]));
        }
    }

    private void collectHandlers(File dir, List<ConfigHandler> handlers) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                collectHandlers(file, handlers);
                continue;
            }
            if (file.getName().endsWith(".yml")) {
                ConfigHandler handler = new ConfigHandler(plugin, plugin.getDataFolder().toPath().relativize(file.toPath()).toString().replace("\\", "/"));
                handler.load(false);
                handlers.add(handler);
            }
        }
    }

    protected void save() {
        configuration.save();
    }

    protected void reload() {
        configuration.clear();
        configuration.load();
    }

    protected String getName() {
        return folder.getName();
    }

    protected ConfigManager getConfigManager() {
        return configuration;
    }
}