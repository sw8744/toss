package io.github.sw8744.toss.economy;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import java.util.*;

public class Exchange {
    static FileConfiguration oreConfig = Bukkit.getPluginManager().getPlugin("Toss").getConfig();
    static JSONObject oreData = new JSONObject();
    public static int defaultIronPrice = oreConfig.getInt("defaultOre.Iron");
    public static int defaultGoldPrice = oreConfig.getInt("defaultOre.Gold");
    public static int defaultEmeraldPrice = oreConfig.getInt("defaultOre.Emerald");
    public static int defaultDiamondPrice = oreConfig.getInt("defaultOre.Diamond");
    public static int defaultNetheritePrice = oreConfig.getInt("defaultOre.Netherite");
    public static Map<String, Material> oreMaterial = new HashMap<String, Material>();
    public static void resetExchange() {
        ArrayList<Integer> orePrice = new ArrayList<Integer>();
        orePrice.add(defaultIronPrice);
        oreData.put("Iron", orePrice);
        orePrice.set(0, defaultGoldPrice);
        oreData.put("Gold", orePrice);
        orePrice.set(0, defaultEmeraldPrice);
        oreData.put("Emerald", orePrice);
        orePrice.set(0, defaultDiamondPrice);
        oreData.put("Diamond", orePrice);
        orePrice.set(0, defaultNetheritePrice);
        oreData.put("Netherite", orePrice);
        oreMaterial.put("Iron", Material.IRON_INGOT);
        oreMaterial.put("Gold", Material.GOLD_INGOT);
        oreMaterial.put("Emerald", Material.EMERALD);
        oreMaterial.put("Diamond", Material.DIAMOND);
        oreMaterial.put("Netherite", Material.NETHERITE_INGOT);
    }

    public static ArrayList<Integer> importOre(String ore) {
        return (ArrayList<Integer>) oreData.get(ore);
    }

    public static void updateOre() {
        Random random = new Random();
        ArrayList<Integer> orePrice = new ArrayList<Integer>();
        int ironPrice = importOre("Iron").get(-1);
        int goldPrice = importOre("Gold").get(-1);
        int emeraldPrice = importOre("Emerald").get(-1);
        int diamondPrice = importOre("Diamond").get(-1);
        int netheritePrice = importOre("Netherite").get(-1);
        ironPrice = random.nextInt(defaultGoldPrice - defaultIronPrice + 1) + defaultIronPrice;
        goldPrice = random.nextInt(defaultEmeraldPrice - defaultGoldPrice + 1) + defaultGoldPrice;
        emeraldPrice = random.nextInt(defaultDiamondPrice - defaultEmeraldPrice + 1) + defaultEmeraldPrice;
        diamondPrice = random.nextInt(defaultNetheritePrice - defaultDiamondPrice + 1) + defaultDiamondPrice;
        netheritePrice = random.nextInt(defaultNetheritePrice + 1) + defaultDiamondPrice;
        orePrice = importOre("Iron");
        orePrice.add(ironPrice);
        oreData.replace("Iron", orePrice);

        orePrice = importOre("Gold");
        orePrice.add(goldPrice);
        oreData.replace("Gold", orePrice);

        orePrice = importOre("Emerald");
        orePrice.add(emeraldPrice);
        oreData.replace("Emerald", orePrice);

        orePrice = importOre("Diamond");
        orePrice.add(diamondPrice);
        oreData.replace("Diamond", orePrice);

        orePrice = importOre("Netherite");
        orePrice.add(netheritePrice);
        oreData.replace("Netherite", orePrice);
    }

    public static void buyOre(Player p, String ore, int amount) {
        int playerMoney = Money.importMoney(p);
        int orePrice = importOre(ore).get(-1) * amount;

        if(playerMoney < orePrice) {
            p.sendMessage("§4돈이 부족합니다!");
            return;
        }
        else {
            playerMoney -= orePrice;
            Money.setMoney(p, playerMoney);
            p.getInventory().addItem(new ItemStack(oreMaterial.get(ore), amount));
            p.sendMessage("§e" + ore + " " + Integer.toString(amount) + "개를 " + Integer.toString(orePrice) + "원에 구매했습니다.");
        }
    }

    public static void sellOre(Player p, String ore, int amount) {
        int playerMoney = Money.importMoney(p);
        int orePrice = importOre(ore).get(-1) * amount;
        if(p.getInventory().containsAtLeast(new ItemStack(oreMaterial.get(ore)), amount)) {
            p.getInventory().removeItem(new ItemStack(oreMaterial.get(ore), amount));
            playerMoney += orePrice;
            Money.setMoney(p, playerMoney);
            p.sendMessage("§e" + ore + " " + Integer.toString(amount) + "개를 " + Integer.toString(orePrice) + "원에 판매했습니다.");
        }
        else {
            p.sendMessage("§4" + ore + " " + Integer.toString(amount) + "개를 가지고 있지 않습니다!");
            return;
        }
    }
}
