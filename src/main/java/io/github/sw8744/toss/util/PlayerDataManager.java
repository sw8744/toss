package io.github.sw8744.toss.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PlayerDataManager {
    private String path = Bukkit.getServer().getPluginManager().getPlugin("Toss").getDataFolder().getAbsolutePath();
    private File file = new File(path + "/playerData.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);


    public FileConfiguration getConfig() {
        return config;
    }

    public void savePlayerData() {
        if(config == null) {
            return;
        }
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean exists() {
        return file.exists();
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setConfig(String path, Object value) {
        config.set(path, value);
    }
}
