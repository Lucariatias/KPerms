package com.lightniinja.kperms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.KGroup;

public class CommandGroupPRemove {
	private KPermsPlugin plugin;
	public CommandGroupPRemove(KPermsPlugin plugin) {
		this.plugin = plugin;
	}
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("kperms.group.permission.remove")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("no-permission")));
			return;
		}
		KGroup kGroup = new KGroup(args[1], plugin);
		if(kGroup.removePermission(args[4])) {
			String str = ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("gremoved-permission"));
			str = str.replaceAll("%perm", args[4]);
			str = str.replaceAll("%g", args[1]);
			sender.sendMessage(str);
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("unsuccessful")));
		}
	}
}
