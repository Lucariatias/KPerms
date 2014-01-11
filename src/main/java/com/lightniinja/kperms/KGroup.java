package com.lightniinja.kperms;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class KGroup {
	private String groupName;
	private KPermsPlugin plugin;
	private File configFile;
	private FileConfiguration config;
	public KGroup(String groupName, KPermsPlugin plugin) {
		this.groupName = groupName;
		this.plugin = plugin;
		this.configFile = new File(plugin.getDataFolder() + "/groups/" + groupName +".yml");
		this.config = YamlConfiguration.loadConfiguration(configFile);
	}
	
	public String getName() {
		return groupName;
	}
	
	public List<String> getPermissions() {
		return config.getStringList("permissions");
	}
	public boolean make() {
		config.set("permissions", "");
		try {
			config.save(configFile);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean addPermission(String permission) {
		List<String> groupPermissions = config.getStringList("permissions");
		groupPermissions.add(permission);
		config.set("permissions", groupPermissions);
		try {
			config.save(configFile);
			plugin.refreshAllPermissions();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean removePermission(String permission) {
		List<String> groupPermissions = config.getStringList("permissions");
		groupPermissions.remove(permission);
		config.set("permissions", groupPermissions);
		try {
			config.save(configFile);
			plugin.refreshAllPermissions();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean hasPermission(String permission) {
		if(config.getStringList("permissions").contains(permission))
			return true;
		return false;
	}
	public boolean isGenerated() {
		return configFile.exists();
	}

	public boolean remove() {
		if(!configFile.exists())
			return false;
		return configFile.delete();
	}
}
