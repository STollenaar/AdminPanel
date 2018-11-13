package adminpanel.tollenaar.stephen.Admin;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import adminpanel.tollenaar.stephen.Panel.Core;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CommandsAdmin implements CommandExecutor{
	private Core plugin;
	private Storage storage;
	
	public CommandsAdmin(Core instance){
		this.plugin = instance;
		this.storage = instance.storage;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
		PermissionUser user = PermissionsEx.getUser(player);
		if(user.has("AdminPanel.adminmode")){
			if(storage.getActive(player.getUniqueId()) == null){
				storage.addActive(player);
			}else{
				storage.restoreAdmin(player);
			}
		}
		}else{
			sender.sendMessage(plugin.getAnnouncer() + "This command can only be executed by a player in game.");
		}
		return true;
	}

}
