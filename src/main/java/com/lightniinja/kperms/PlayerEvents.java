package com.lightniinja.kperms;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

	private KPermsPlugin plugin;
	public PlayerEvents(KPermsPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		KPlayer kPlayer = new KPlayer(event.getPlayer(), plugin);
		if(!kPlayer.isGenerated()) {
			kPlayer.make();
			plugin.getLogger().info("Generated playerdata for " + event.getPlayer().getName());
		}
		kPlayer.assignPermissions();
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		KPlayer kPlayer = new KPlayer(event.getPlayer(), plugin);
		kPlayer.clearPermissions();
	}
	@EventHandler
	public void onKick(PlayerKickEvent event) {
		KPlayer kPlayer = new KPlayer(event.getPlayer(), plugin);
		kPlayer.clearPermissions();
	}
	
}
