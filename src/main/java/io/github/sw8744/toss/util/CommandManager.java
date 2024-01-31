package io.github.sw8744.toss.util;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.github.sw8744.toss.economy.Exchange.oreData;
import static io.github.sw8744.toss.stock.Stock.stockStatus;

public class CommandManager implements TabCompleter, CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("toss")) {
                if (args.length == 0) {
                    p.sendMessage("§e/toss help");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("help")) {
                        p.sendMessage("§e/toss help");
                        p.sendMessage("§e/toss gamble");
                        p.sendMessage("§e/toss money");
                    } else if (args[0].equalsIgnoreCase("gamble")) {
                        p.sendMessage("§e/toss gamble");
                    } else if (args[0].equalsIgnoreCase("money")) {
                        p.sendMessage("§e/toss money");
                    }
                    else if (args[0].equalsIgnoreCase("stock")) {
                        for(int i=0; i<stockStatus.size(); i++) {
                            JSONObject stock = (JSONObject) stockStatus.get(i);
                            JSONArray stockPrice = (JSONArray) stock.get("price");
                            if(stock.get("status").toString().equals("true")) {
                                if(stockPrice.size() < 2) {
                                    p.sendMessage("§e" + stock.get("name") + " : " + stockPrice.get(stockPrice.size() - 1) + " 0");
                                    continue;
                                }
                                int delta = (int) stockPrice.get(stockPrice.size() - 1) - (int) stockPrice.get(stockPrice.size() - 2);
                                if(delta > 0) {
                                    p.sendMessage("§a" + stock.get("name") + " : " + stockPrice.get(stockPrice.size() - 1) + " ▲" + delta);
                                }
                                else if(delta < 0) {
                                    p.sendMessage("§4" + stock.get("name") + " : " + stockPrice.get(stockPrice.size() - 1) + " ▼" + delta);
                                }
                                else {
                                    p.sendMessage("§e" + stock.get("name") + " : " + stockPrice.get(stockPrice.size() - 1) + delta);
                                }

                            }

                        }
                    }
                    else if (args[0].equalsIgnoreCase("exchange")) {
                        JSONObject oreStatus = oreData;
                        ArrayList<String> oreList = new ArrayList<String>();
                        oreList.add("Iron");
                        oreList.add("Gold");
                        oreList.add("Emerald");
                        oreList.add("Diamond");
                        oreList.add("Netherite");
                        for(int i=0; i<oreStatus.size(); i++) {
                            JSONArray price = (JSONArray) oreStatus.get(oreList.get(i));
                            if(price.size() < 2) {
                                p.sendMessage("§e" + oreList.get(i) + " : " + price.get(price.size() - 1) + " 0");
                                continue;
                            }
                            int delta = (int) price.get(price.size() - 1) - (int) price.get(price.size() - 2);
                            if(delta > 0) {
                                p.sendMessage("§a" + oreList.get(i) + " : " + price.get(price.size() - 1) + " ▲" + delta);
                            }
                            else if(delta < 0) {
                                p.sendMessage("§4" + oreList.get(i) + " : " + price.get(price.size() - 1) + " ▼" + Integer.toString(-delta));
                            }
                            else {
                                p.sendMessage("§e" + oreList.get(i) + " : " + price.get(price.size() - 1) + delta);
                            }
                        }
                    }
                    else {
                        p.sendMessage("§e/toss help");
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("gamble")) {
                        if (args[1].equalsIgnoreCase("help")) {
                            p.sendMessage("§e/toss gamble help");
                            p.sendMessage("§e/toss gamble start");
                        } else if (args[1].equalsIgnoreCase("start")) {
                            p.sendMessage("§e/toss gamble start");
                        } else {
                            p.sendMessage("§e/toss gamble help");
                        }
                    } else if (args[0].equalsIgnoreCase("money")) {
                        if (args[1].equalsIgnoreCase("help")) {
                            p.sendMessage("§e/toss money help");
                            p.sendMessage("§e/toss money reset");
                            p.sendMessage("§e/toss money send");
                        } else if (args[1].equalsIgnoreCase("reset")) {
                            p.sendMessage("§e/toss money reset");
                        } else if (args[1].equalsIgnoreCase("send")) {
                            p.sendMessage("§e/toss money send <player> <money>");
                        } else {
                            p.sendMessage("§e/toss money help");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("exchange")) {
                        if (args[1].equalsIgnoreCase("help")) {
                            p.sendMessage("§e/toss exchange help");
                            p.sendMessage("§e/toss exchange start");
                        } else if (args[1].equalsIgnoreCase("start")) {
                            p.sendMessage("§e/toss exchange start");
                        } else {
                            p.sendMessage("§e/toss exchange help");
                        }
                    }
                    else if(args[0].equalsIgnoreCase("stock")) {
                        if (args[1].equalsIgnoreCase("help")) {
                            p.sendMessage("§e/toss stock help");
                            p.sendMessage("§e/toss stock start");
                        } else if (args[1].equalsIgnoreCase("start")) {
                            p.sendMessage("§e/toss stock start");
                        } else {
                            for(int i=0; i<stockStatus.size(); i++) {
                                JSONObject stock = (JSONObject) stockStatus.get(i);
                                if(stock.get("status").equals("true")) {
                                    p.sendMessage("§e" + stock.get("name") + " : " + stock.get("price"));
                                }
                            }
                        }
                    }
                    else {
                        p.sendMessage("§e/toss help");
                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("money")) {
                        if (args[1].equalsIgnoreCase("send")) {
                            p.sendMessage("§e/toss money send <player> <money>");
                        } else {
                            p.sendMessage("§e/toss money help");
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        List<String> tab = new ArrayList<String>();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("toss")) {
                if (args.length == 1) {
                    tab.add("help");
                    tab.add("exchange");
                    tab.add("stock");
                    tab.add("gamble");
                    tab.add("money");
                }
                else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("gamble")) {
                        tab.add("help");
                        tab.add("start");
                    }
                    else if (args[0].equalsIgnoreCase("money")) {
                        tab.add("help");
                        tab.add("reset");
                        tab.add("send");
                    }
                    else if (args[0].equalsIgnoreCase("exchange")) {
                        tab.add("help");
                        tab.add("start");
                    }
                    else if (args[0].equalsIgnoreCase("stock")) {
                        tab.add("help");
                        tab.add("start");
                    }

                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("money")) {
                        if (args[1].equalsIgnoreCase("send")) {
                            for (Player player : p.getServer().getOnlinePlayers()) {
                                tab.add(player.getName());
                            }
                        }
                    }
                }
            }
        }
        return tab;
    }
}
