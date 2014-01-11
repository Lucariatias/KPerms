package com.lightniinja.kperms;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class KPlayer {
	private String playerName;
	private File configFile;
	private FileConfiguration config;
	private KPermsPlugin plugin;
	public KPlayer(OfflinePlayer player, KPermsPlugin plugin) {
		this.playerName = player.getName();
		this.plugin = plugin;
		this.configFile = new File(plugin.getDataFolder().getPath() + "/playerdata/" + playerName + ".yml");
		this.config = YamlConfiguration.loadConfiguration(configFile);
	}
	public boolean make() {
		config.set("primaryGroup", new ConfigManager(plugin).getDefaultGroup());
		try {
			config.save(configFile);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public String getPrimaryGroup() {
		return config.getString("primaryGroup");
	}
 	public List<String> getGroups() {
 		return config.getStringList("groups");
 	}
	public boolean setPrimaryGroup(String g) {
		config.set("primaryGroup", g);
		try {
			config.save(configFile);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean isMemberOfGroup(String g) {
		if(config.getStringList("groups").contains(g))
			return true;
		return false;
	}
	public void clearPermissions() {
		for(PermissionAttachmentInfo attachmentInfo: plugin.getServer().getPlayerExact(playerName).getEffectivePermissions()) {
			if(attachmentInfo.getAttachment() == null)
				return;
			plugin.getServer().getPlayerExact(playerName).removeAttachment(attachmentInfo.getAttachment());
		}
	}
	public boolean addGroup(String group) {
		List<String> groups = config.getStringList("groups");
		groups.add(group);
		config.set("groups", group);
		try {
			config.save(configFile);
			plugin.refreshAllPermissions();
			return true;
		} catch (IOException e) {
			System.out.print(e.getMessage());
			return false;
		}
	}
	public boolean removeGroup(String groupName) {
		List<String> groups = config.getStringList("groups");
		groups.remove(groupName);
		config.set("groups", groups);
		try {
			config.save(configFile);
			plugin.refreshAllPermissions();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean addPermission(String permission) {
		List<String> permissions = config.getStringList("permissions");
		permissions.add(permission);
		config.set("permissions", permissions);
		try {
			config.save(configFile);
			plugin.refreshAllPermissions();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean removePermission(String s) {
		List<String> g = config.getStringList("permissions");
		g.remove(s);
		config.set("permissions", g);
		try {
			config.save(configFile);
			plugin.refreshAllPermissions();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean hasPermission(String s) {
		List<String> p = config.getStringList("permissions");
		List<String> g = config.getStringList("groups");
		g.add(config.getString("primaryGroup"));
		if(p.contains(s)) {
			return true;
		}
		for(String str: g) {
			if(new KGroup(str, plugin).getPermissions().contains(s)) {
				return true;
			}
		}
		return false;
	}
	public List<String> getPermissions() {
		List<String> perms = new ArrayList<String>();
		for(String s: config.getStringList("groups")) {
			for(String perm: new KGroup(s, plugin).getPermissions()) {
				perms.add(perm);
			}
		}
		for(String perm: new KGroup(config.getString("primaryGroup"), plugin).getPermissions()) {
			perms.add(perm);
		}
		for(String perm: config.getStringList("permissions")) {
			perms.add(perm);
		}
		return perms;
	}
	public void assignPermissions() {
		for(String s: this.getPermissions()) {
			if(s.startsWith("-")) {
				plugin.getServer().getPlayerExact(playerName).addAttachment(plugin, s.replace("-", ""), false);
			} else {
				plugin.getServer().getPlayerExact(playerName).addAttachment(plugin, s, true);
			}
		}
	}
	public boolean isGenerated() {
		if(configFile.exists())
			return true;
		return false;
	}
}
