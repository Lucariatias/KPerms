package com.lightniinja.kperms;

import org.bukkit.ChatColor;

public class ConfigManager {
	private KPermsPlugin plugin;
	public ConfigManager(KPermsPlugin plugin) {
		this.plugin = plugin;
	}
	public String getDefaultGroup() {
		return plugin.getConfig().getString("defaultGroup");
	}
	public String getMessage(String id) {
		if(plugin.getConfig().getString("messages." + id) == null) {
			return ChatColor.translateAlternateColorCodes('&', "&cLanguage values not completed - check config.yml");
		}
		return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages." + id));
	}
}
