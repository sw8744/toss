package io.github.sw8744.toss.stock;

import org.bukkit.Bukkit;
import org.joml.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;


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
                int maxStockDelta = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getInt("maxStockDelta");
                int delta = random.nextInt(maxStockDelta * 2) - maxStockDelta;
                int newPrice = lastPrice + delta;

                if (delta > 0){
                    int messageNumber = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockUpMessage").size());
                    Bukkit.getConsoleSender().sendMessage("§4" + stock.get("name") + ", " + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("message").get(messageNumber));
                }

                else if (newPrice <= 0) {
                    int messageNumber = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockFailMessage").size());
                    Bukkit.getConsoleSender().sendMessage("§4" + stock.get("name") + ", " + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("message").get(messageNumber));
                    stock.replace("status", false);
                    int randomStockNumber = random.nextInt(stockStatus.size());


                }

                else if (delta < 0){
                    int messageNumber = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockDownMessage").size());
                    Bukkit.getConsoleSender().sendMessage("§4" + stock.get("name") + ", " + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("message").get(messageNumber));
                }

                stockPrice.add(newPrice);
                stock.replace("price", stockPrice);
            }
        }
    }

    public static void buyStock(String name, int amount) {
        JSONObject stock = new JSONObject();
        for(int i=0; i<stockStatus.size(); i++) {
            JSONObject stock_temp = (JSONObject) stockStatus.get(i);
            if(stock_temp.get("name").equals(name)) {
                stock = (JSONObject) stockStatus.get(i);
                break;
            }
        }
        if(stock.equals(new JSONObject())) {
            Bukkit.getConsoleSender().sendMessage("§4" + name + "은(는) 존재하지 않는 주식입니다!");
            return;
        }
        if(stock.get("status").equals(false)) {
            Bukkit.getConsoleSender().sendMessage("§4" + name + "은(는) 현재 상장 폐지된 주식입니다!");
            return;
        }
        JSONArray stockPrice = (JSONArray) stock.get("price");
        int price = (int) stockPrice.get(stockPrice.size() - 1);
        // TODO: 플레이어 돈 연동 필요
    }

    public static void sellStock(String name, int amount) {
        JSONObject stock = new JSONObject();
        for(int i=0; i<stockStatus.size(); i++) {
            JSONObject stock_temp = (JSONObject) stockStatus.get(i);
            if(stock_temp.get("name").equals(name)) {
                stock = (JSONObject) stockStatus.get(i);
                break;
            }
        }
        if(stock.equals(new JSONObject())) {
            Bukkit.getConsoleSender().sendMessage("§4" + name + "은(는) 존재하지 않는 주식입니다!");
            return;
        }
        if(stock.get("status").equals(false)) {
            Bukkit.getConsoleSender().sendMessage("§4" + name + "은(는) 현재 상장 폐지된 주식입니다!");
            return;
        }
        JSONArray stockPrice = (JSONArray) stock.get("price");
        int price = (int) stockPrice.get(stockPrice.size() - 1);
        // TODO: 플레이어 돈 연동 필요
    }
}
