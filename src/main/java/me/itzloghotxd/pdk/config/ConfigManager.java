package me.itzloghotxd.pdk.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private final Map<Enum, ConfigHandler> configurations = new HashMap<>();

    public ConfigManager() {
    }

    public void saveAllFiles() {
        configurations.values().forEach(ConfigHandler::save);
    }

    public void saveFile(Enum type) {
        getFile(type).save();
    }

    public void reloadAllFiles() {
        configurations.values().forEach(ConfigHandler::reload);
    }

    public void reloadFile(Enum type) {
        getFile(type).reload();
    }

    public ConfigHandler getFile(Enum type) {
        return (ConfigHandler)configurations.get(type);
    }

    public void registerFile(Enum type, ConfigHandler config) {
        configurations.put(type, config);
    }

    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig(Enum type) {
        return getFile(type).getConfig();
    }

    public Map<Enum, ConfigHandler> getConfigurations() {
        return configurations;
    }
}
