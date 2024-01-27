package io.github.sw8744.toss.stock;

import org.bukkit.Bukkit;
import org.joml.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Stock {
    static JSONArray stockStatus = new JSONArray();

    public static void importStock() {
        List<String> stockList = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getStringList("stockName");
        int stockNumber = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getInt("stockNumber");
        int defaultStockPrice = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getInt("defaultStockPrice");

        for (int i = 0; i < stockList.size(); i++) {
            JSONObject stock = new JSONObject();

            stock.put("name", stockList.get(i));
            if(i < stockNumber) {
                stock.put("status", true);
            } else {
                stock.put("status", false);
            }

            JSONArray stockPrice = new JSONArray();
            stockPrice.add(defaultStockPrice);

            stockStatus.add(stock);
        }
    }

    public static void updateStock() {
        Random random = new Random();
        for(int i = 0; i < stockStatus.size(); i++) {
            JSONObject stock = (JSONObject) stockStatus.get(i);
            if(stock.get("status").equals(true)) {
                JSONArray stockPrice = (JSONArray) stock.get("price");
                int lastPrice = (int) stockPrice.get(stockPrice.size() - 1);
                int newPrice = lastPrice + random.nextInt(100) - 50;
                if(newPrice < 0){
                    int messageNumber = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockDownMessage").size());
                    Bukkit.getConsoleSender().sendMessage("§4" + stock.get("name") + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("message").get(messageNumber));
                };
                if (newPrice > 0){
                    int messageNumber = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockUpMessage").size());
                    Bukkit.getConsoleSender().sendMessage("§4" + stock.get("name") + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("message").get(messageNumber));
                };
                stockPrice.add(newPrice);
                stock.replace("price", stockPrice);
            }
        }
    }
}
