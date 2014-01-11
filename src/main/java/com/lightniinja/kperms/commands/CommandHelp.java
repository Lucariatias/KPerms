package com.lightniinja.kperms.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.KPermsPlugin;

public class CommandHelp {
	private KPermsPlugin plugin;
	public CommandHelp(KPermsPlugin plugin) {
		this.plugin = plugin;
	}
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("kperms.help")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("no-permission")));
			return;
		}
		
		HashMap<String, String> commands = new HashMap<String, String>();
		commands.put("help", "View all avaliable commands.");
		commands.put("reload", "Reload the plugin and config.");
		commands.put("user <username>", "View <username>s permissions and groups.");
		commands.put("user <username> permission <add/remove> <permission>", "Adds or removes <permission> from <username>.");
		commands.put("user <username> group <set/add/remove> <group>", "Sets, adds, or removes <group> from <username>.");
		commands.put("group <group>", "View <group>s permissions.");
		commands.put("group <group> <add/remove>", "Adds or removes a group.");
		commands.put("group <group> permission <add/remove>", "Adds or removes a permission from a group.");
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l*=*=*=*=*=*=*=*=* &r" + plugin.getConfigManager().getMessage("prefix") + " &c&l*=*=*=*=*=*=*=*=*"));
		for(String str: commands.keySet()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/kperms &e" + str + " &7| &d" + commands.get(str)));
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l*=*=*=*=*=*=*=*=* &r" + plugin.getConfigManager().getMessage("prefix") + " &c&l*=*=*=*=*=*=*=*=*"));
	}
}
