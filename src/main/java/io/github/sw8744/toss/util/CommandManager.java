package io.github.sw8744.toss.util;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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
                    } else {
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
                    } else {
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
                    tab.add("gamble");
                    tab.add("money");
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("gamble")) {
                        tab.add("help");
                        tab.add("start");
                    } else if (args[0].equalsIgnoreCase("money")) {
                        tab.add("help");
                        tab.add("reset");
                        tab.add("send");
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
