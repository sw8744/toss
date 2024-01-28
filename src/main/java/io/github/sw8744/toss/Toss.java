package io.github.sw8744.toss;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.sw8744.toss.stock.Stock;
import io.github.sw8744.toss.util.PlayerDataManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

public final class Toss extends JavaPlugin {

    public void bukkitScheduler() {
        BukkitScheduler scheduler = getServer().getScheduler();
        int stockUpdateTime = getConfig().getInt("stockUpdateTime") * 20;
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Stock.updateStock();
            }
        }, 0, stockUpdateTime);
    }
    @Override
    public void onEnable() {
        Bukkit.getServer().getConsoleSender().sendMessage("§bToss §ePlugin Enabled");
        saveConfig();
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        Stock.importStock();
        bukkitScheduler();
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("§bToss §ePlugin Disabled");
    }
}
