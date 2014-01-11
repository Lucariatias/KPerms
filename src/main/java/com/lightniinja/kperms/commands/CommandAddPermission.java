package com.lightniinja.kperms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.KPlayer;

public class CommandAddPermission {
	private KPermsPlugin plugin;
	public CommandAddPermission(KPermsPlugin plugin) {
		this.plugin = plugin;
	}
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("kperms.user.permission.add")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("no-permission")));
			return;
		}
		KPlayer kPlayer = new KPlayer(plugin.getServer().getOfflinePlayer(args[1]), plugin);
		if(kPlayer.addPermission(args[4])) {
			kPlayer.clearPermissions();
			kPlayer.assignPermissions();
			String str = ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("added-permission"));
			str = str.replaceAll("%perm", args[4]);
			str = str.replaceAll("%player", args[1]);
			sender.sendMessage(str);
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("unsuccessful")));
		}
	}
}
