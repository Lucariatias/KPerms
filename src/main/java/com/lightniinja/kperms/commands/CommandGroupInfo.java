package com.lightniinja.kperms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.KGroup;
import com.lightniinja.kperms.KPermsPlugin;

public class CommandGroupInfo {
	private KPermsPlugin plugin;
	public CommandGroupInfo(KPermsPlugin plugin) {
		this.plugin = plugin;
	}
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("kperms.group.info")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("no-permission")));
			return;
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &e*" + plugin.getConfigManager().getMessage("prefix") + "&e*  &bGroup Info: &6" + args[1]));
		KGroup kGroup = new KGroup(args[1], plugin);
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c   Permissions:"));
		for(String str: kGroup.getPermissions()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &e" + str));
		}
	}
}
