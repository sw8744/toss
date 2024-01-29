package io.github.sw8744.toss.economy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Random;

public class Exchange {
    static FileConfiguration oreConfig = Bukkit.getPluginManager().getPlugin("Toss").getConfig();
    static JSONObject oreData = new JSONObject();
    public static int defaultIronPrice = oreConfig.getInt("defaultOre.Iron");
    public static int defaultGoldPrice = oreConfig.getInt("defaultOre.Gold");
    public static int defaultEmeraldPrice = oreConfig.getInt("defaultOre.Emerald");
    public static int defaultDiamondPrice = oreConfig.getInt("defaultOre.Diamond");
    public static int defaultNetheritePrice = oreConfig.getInt("defaultOre.Netherite");
    public static void resetExchange() {
        oreData.put("Iron", defaultIronPrice);
        oreData.put("Gold", defaultGoldPrice);
        oreData.put("Emerald", defaultEmeraldPrice);
        oreData.put("Diamond", defaultDiamondPrice);
        oreData.put("Netherite", defaultNetheritePrice);
    }

    public static int importOre(String ore) {
        return (int) oreData.get(ore);
    }

    public static void updateOre() {
        Random random = new Random();
        int ironPrice = importOre("Iron");
        int goldPrice = importOre("Gold");
        int emeraldPrice = importOre("Emerald");
        int diamondPrice = importOre("Diamond");
        int netheritePrice = importOre("Netherite");
        ironPrice = random.nextInt(defaultGoldPrice - defaultIronPrice + 1) + defaultIronPrice;
        goldPrice = random.nextInt(defaultEmeraldPrice - defaultGoldPrice + 1) + defaultGoldPrice;
        emeraldPrice = random.nextInt(defaultDiamondPrice - defaultEmeraldPrice + 1) + defaultEmeraldPrice;
        diamondPrice = random.nextInt(defaultNetheritePrice - defaultDiamondPrice + 1) + defaultDiamondPrice;
        netheritePrice = random.nextInt(defaultNetheritePrice + 1) + defaultDiamondPrice;

    }
}
