package com.lightniinja.kperms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.KGroup;
import com.lightniinja.kperms.KPermsPlugin;

public class CommandGroupAdd {
	
	private KPermsPlugin plugin;
	
	public CommandGroupAdd(KPermsPlugin plugin) {
		this.plugin = plugin;
	}
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("kperms.group.add")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("no-permission")));
			return;
		}
		KGroup kGroup = new KGroup(args[1], plugin);
		if(kGroup.isGenerated()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("already-created")));
		} else {
			if(kGroup.make()) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("group-created")));
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessage("prefix") + " " + plugin.getConfigManager().getMessage("unsuccessful")));
			}
		}
	}
}
