package com.lightniinja.kperms;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.lightniinja.kperms.commands.CommandMain;

public class KPermsPlugin extends JavaPlugin {
	
	private ConfigManager configManager;
	
	public void onEnable() {
		saveDefaultConfig();
		configManager = new ConfigManager(this);
		File defaultGroupFile = new File(this.getDataFolder() + "/groups/" + new ConfigManager(this).getDefaultGroup() + ".yml");
		if(!defaultGroupFile.exists()) {
			setupFolders();
		}
		getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
		getCommand("kperms").setExecutor(new CommandMain(this));
	}
	
	public void onDisable() {
		saveConfig();
	}
	
	public ConfigManager getConfigManager() {
		return configManager;
	}
	
	public boolean setupFolders() {
		File playerDataDirectory = new File(getDataFolder() + "/playerdata/");
		if(!playerDataDirectory.isDirectory()) {
			if(!playerDataDirectory.mkdir())
				return false;
		}
		File groupDirectory = new File(getDataFolder(), "groups");
		if(!groupDirectory.isDirectory()) {
			if(!groupDirectory.mkdir())
				return false;
		}
		if(new KGroup(configManager.getDefaultGroup(), this).make()) {
			return false;
		}
		return true;
	}
	
	public void refreshAllPermissions() {
		for(Player player: getServer().getOnlinePlayers()) {
			KPlayer kPlayer = new KPlayer(player, this);
			kPlayer.clearPermissions();
			kPlayer.assignPermissions();
		}
	}
	public String[] getGroups() {
		File folder = new File(getDataFolder() + "/groups");
		File[] listOfFiles = folder.listFiles();
		String[] groups = new String[listOfFiles.length];
		for(int i = 0; i < listOfFiles.length; i++) {
			groups[i] = listOfFiles[i].getName().split(".")[0];
		}
		return groups;
	}
	
}
