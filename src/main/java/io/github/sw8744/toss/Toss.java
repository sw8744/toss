package io.github.sw8744.toss;

import io.github.sw8744.toss.enchant.Enchant;
import io.github.sw8744.toss.util.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.sw8744.toss.stock.Stock;
import io.github.sw8744.toss.util.PlayerDataManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

import static io.github.sw8744.toss.economy.Exchange.resetExchange;
import static io.github.sw8744.toss.economy.Exchange.updateOre;
import static io.github.sw8744.toss.economy.Money.resetMoney;
import io.github.sw8744.toss.util.PlayerDataManager;

public final class Toss extends JavaPlugin implements Listener {

    public void bukkitScheduler() {
        BukkitScheduler scheduler = getServer().getScheduler();
        int stockUpdateTime = getConfig().getInt("stockUpdateTime") * 20;
        int oreUpdateTime = getConfig().getInt("oreUpdateTime") * 20;
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Stock.updateStock();
            }
        }, stockUpdateTime, stockUpdateTime);
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                updateOre();
            }
        }, oreUpdateTime, oreUpdateTime);
    }
    @Override
    public void onEnable() {
        PlayerDataManager playerDataManager = new PlayerDataManager();
        Bukkit.getServer().getConsoleSender().sendMessage("§bToss §ePlugin Enabled");
        playerDataManager.savePlayerData();
        this.getCommand("toss").setExecutor(new CommandManager());
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        Stock.importStock();
        resetExchange();
        bukkitScheduler();
        Bukkit.getPluginManager().registerEvents(new Enchant(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("§bToss §ePlugin Disabled");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        resetMoney(e.getPlayer());

    }
}
