package io.github.sw8744.toss;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.sw8744.toss.stock.Stock;

public final class Toss extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getConsoleSender().sendMessage("§bToss §ePlugin Enabled");
        this.saveDefaultConfig();
        Stock.importStock();
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("§bToss §ePlugin Disabled");
    }
}
