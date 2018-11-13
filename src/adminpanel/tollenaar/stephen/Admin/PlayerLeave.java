package adminpanel.tollenaar.stephen.Admin;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import adminpanel.tollenaar.stephen.Panel.FileWriters;
import adminpanel.tollenaar.stephen.Panel.Core;

public class PlayerLeave implements Listener{
	private FileWriters fw;
	private Storage storage;
	public PlayerLeave (Core instance){
		this.fw = instance.fw;
		this.storage = instance.storage;
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(storage.getActive(player.getUniqueId()) != null){
			fw.savePlayerData(player.getUniqueId());
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		fw.loadPlayerData(event.getPlayer());
	}
}
