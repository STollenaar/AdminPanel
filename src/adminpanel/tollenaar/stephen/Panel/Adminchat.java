package adminpanel.tollenaar.stephen.Panel;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

@SuppressWarnings("deprecation")
public class Adminchat implements Listener, CommandExecutor {
Core plugin;

HashSet<UUID> active = new HashSet<UUID>();
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	if(sender instanceof Player){
	PermissionUser user = PermissionsEx.getUser((Player) sender);

	if(user != null && !user.has("AdminPanel.chat")){
		sender.sendMessage(plugin.getStaffAnnouncer() + "You don't have permissions for this command!");
		return true;
	}else{
		Player player = (Player) sender;
		if(!active.contains(player.getUniqueId())){
		active.add(player.getUniqueId());
		sender.sendMessage(plugin.getStaffAnnouncer()+"Staff chat enabled");
		return true;
		}else{
			active.remove(player.getUniqueId());
			sender.sendMessage(plugin.getStaffAnnouncer() + "Staff chat disabled");
			return true;
		}
	}
	}else{
		sender.sendMessage(plugin.getStaffAnnouncer() + "You must be a player to talk to your buddy's.");
		return true;
	}
}



@EventHandler
public void onplayerchat(PlayerChatEvent event){
	Player player = event.getPlayer();
	PermissionUser user = PermissionsEx.getUser(player);
		if(user.has("AdminPanel.chat")){
	if(active.contains(player.getUniqueId())){
		if(!Character.toString(event.getMessage().charAt(0)).equals("!")){
		String message = plugin.getStaffMessage() + player.getName() + ": " + ChatColor.BLUE  + event.getMessage();
		event.setCancelled(true);
		messenger(message);
		}else{
		event.setMessage(event.getMessage().replaceFirst("!", ""));
		}
	}else if(Character.toString(event.getMessage().charAt(0)).equals("@")){
		String temp = event.getMessage().replaceFirst("@", "");
		String message = plugin.getStaffMessage() + player.getName() + ": " + ChatColor.BLUE  + temp;
		event.setCancelled(true);
		messenger(message);
	}
}
		
}

public void messenger(String message){
	for(Player players: Bukkit.getServer().getOnlinePlayers()){
		PermissionUser user = PermissionsEx.getUser(players);
		if(user.has("AdminPanel.chat")){
			players.sendMessage(message);
		}
	}
}

public Adminchat(Core instance){
	this.plugin = instance;
}
}
