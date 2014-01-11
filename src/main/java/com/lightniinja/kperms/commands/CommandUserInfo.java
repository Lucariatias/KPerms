package com.lightniinja.kperms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.KPlayer;

public class CommandUserInfo {
	private KPermsPlugin plugin;
	public CommandUserInfo(KPermsPlugin plugin) {
		this.plugin = plugin;
	}
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("kperms.user.info")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("no-permission")));
			return;
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &e*" + plugin.getConfigManager().getMessage("prefix") + "&e*  &bUser Info: &6" + args[1]));
		KPlayer kPlayer = new KPlayer(plugin.getServer().getOfflinePlayer(args[1]), plugin);
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c   Permissions:"));
		for(String str: kPlayer.getPermissions()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &e" + str));
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c   Groups:"));
		for(String str: kPlayer.getGroups()) {
			if(str.equalsIgnoreCase(kPlayer.getPrimaryGroup()))
				continue;
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &e" + str));
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &bPrimary group: &2" + kPlayer.getPrimaryGroup()));
	}
}
