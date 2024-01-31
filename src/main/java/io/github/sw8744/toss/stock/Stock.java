package io.github.sw8744.toss.stock;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.joml.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.github.sw8744.toss.economy.Money.importMoney;
import static io.github.sw8744.toss.economy.Money.setMoney;
import static io.github.sw8744.toss.util.AllPlayerSend.sendAllPlayer;


public class Stock {
    public static JSONArray stockStatus = new JSONArray();

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
            stock.put("price", stockPrice);
            stockStatus.add(stock);
        }
    }

    public static void updateStock() {
        Random random = new Random();
        sendAllPlayer("-------< NEWS >--------");
        ArrayList<Integer> bypassNumbers = new ArrayList<Integer>();
        for(int i = 0; i < stockStatus.size(); i++) {
            if(bypassNumbers.contains(i)) {
                continue;
            }
            JSONObject stock = (JSONObject) stockStatus.get(i);

            if(stock.get("status").equals(true)) {
                JSONArray stockPrice = (JSONArray) stock.get("price");
                int lastPrice = (int) stockPrice.get(stockPrice.size() - 1);
                int maxStockDelta = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getInt("maxStockDelta");
                int delta = random.nextInt(maxStockDelta * 2) - maxStockDelta;
                int newPrice = lastPrice + delta;

                if (delta > 0){
                    int messageNumber = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockUpMessage").size());
                    sendAllPlayer("§a" + stock.get("name") + ", " + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockUpMessage").get(messageNumber) + " ▲" + Integer.toString(delta));
                }

                else if (newPrice <= 0) {
                    int messageNumber = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockFailMessage").size());
                    sendAllPlayer("§4" + stock.get("name") + ", " + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockFailMessage").get(messageNumber));
                    stock.replace("status", false);
                    int randomStockNumber = random.nextInt(stockStatus.size());
                    JSONObject randomStock = (JSONObject) stockStatus.get(randomStockNumber);
                    while(randomStock.get("status").equals(true)) {
                            randomStockNumber = random.nextInt(stockStatus.size());
                            randomStock = (JSONObject) stockStatus.get(randomStockNumber);
                    }
                    randomStock.replace("status", true);
                    stockStatus.set(randomStockNumber, randomStock);
                    bypassNumbers.add(randomStockNumber);
                    int messageNumber2 = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockSuccessMessage").size());
                    sendAllPlayer("§a" + randomStock.get("name") + ", " + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockSuccessMessage").get(messageNumber2));
                }

                else if (delta < 0){
                    int messageNumber = random.nextInt(Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockDownMessage").size());
                    sendAllPlayer("§4" + stock.get("name") + ", " + Bukkit.getPluginManager().getPlugin("Toss").getConfig().getList("stockDownMessage").get(messageNumber) + " ▼" + Integer.toString(-delta));
                }

                else if (delta == 0) {
                    sendAllPlayer("§7" + stock.get("name") + " 0");
                }

                stockPrice.add(newPrice);
                stock.replace("price", stockPrice);
            }
        }
        sendAllPlayer("----------------------");
    }

    public static void buyStock(Player p, String name, int amount) {
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
        int price = (int) stockPrice.get(-1) * amount;
        int playerMoney = importMoney(p);
        if(playerMoney < price) {
            Bukkit.getConsoleSender().sendMessage("§4돈이 부족합니다!");
            return;
        }
        else {
            playerMoney -= price;
            setMoney(p, playerMoney);
        }

    }

    public static void sellStock(Player p, String name, int amount) {
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
