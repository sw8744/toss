package io.github.sw8744.toss.economy;

import io.github.sw8744.toss.util.PlayerDataManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class Money {
    static PlayerDataManager playerDataManager = new PlayerDataManager();
    static FileConfiguration playerData = playerDataManager.getConfig();

    public static void resetMoney(Player p) {
        if(!playerData.getKeys(false).contains(p.getName())) {
            playerDataManager.setConfig(p.getName().toString() + ".money", 0);
        }
        playerDataManager.savePlayerData();
    }

    public static int importMoney(Player p) {
        int money = playerData.getInt(p.getName() + ".money");
        return money;
    }

    public static void setMoney(Player p, int amount) {
        playerData.set(p.getName() + ".money", amount);
    }

    public static void addMoney(Player p, int amount) {
        int money = importMoney(p);
        playerData.set(p.getName() + ".money", money + amount);
    }

    public static void sendMoney(Player sender, Player receiver, int money) {
        int senderMoney = importMoney(sender);
        int receiverMoney = importMoney(receiver);
        if(senderMoney >= money) {
            senderMoney -= money;
            receiverMoney += money;
            playerDataManager.setConfig(sender.getName() + ".money", senderMoney);
            playerDataManager.setConfig(receiver.getName() + ".money", receiverMoney);
            playerDataManager.savePlayerData();
            sender.sendMessage("§e" + receiver.getName() + "님에게 "+ Integer.toString(money) + "원을 송금했습니다.");
        }
        else {
            sender.sendMessage("§4송금할 돈이 부족합니다.");
        }
    }
}
