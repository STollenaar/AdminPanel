package adminpanel.tollenaar.stephen.Panel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Message {
	Core plugin;

	public Message(Core instance) {
		this.plugin = instance;
	}

	public void sendmessage(boolean quiet, String subject, String sort, String moderator, String tijd, String reason) {
		String message = null;
		if (quiet == true) {

			if (sort.equals("tempban")) {
				message = plugin.getAnnouncer() + moderator + " quietly temporay banned " + subject + " for " + tijd
						+ " reason  " + reason + ".";
			}
			if (sort.equals("permban")) {
				message = plugin.getAnnouncer() + moderator + " quietly banned " + subject + " reason " + reason + ".";
			}
			if (sort.equals("demote")) {
				message = plugin.getAnnouncer() + moderator + " quietly demoted " + subject + ".";
			}
			if (sort.equals("promote")) {
				message = plugin.getAnnouncer() + moderator + " quietly promoted " + subject + ".";
			}
			if (sort.equals("unban")) {
				message = plugin.getAnnouncer() + moderator + " quietly unbanned " + subject + ".";
			}
			if (sort.equals("note")) {
				message = plugin.getAnnouncer() + moderator + " quietly noted " + subject + " "
						+ reason.replace("_", " ") + ".";
			}
		} else {
			if (sort.equals("tempban")) {
				message = plugin.getAnnouncer() + moderator + " temporary banned " + subject + " for " + tijd
						+ " reason  " + reason + ".";
			}
			if (sort.equals("permban")) {
				message = plugin.getAnnouncer() + moderator + " banned " + subject + " reason " + reason + ".";
			}
			if (sort.equals("demote")) {
				message = plugin.getAnnouncer() + moderator + " demoted " + subject + ".";
			}
			if (sort.equals("promote")) {
				message = plugin.getAnnouncer() + moderator + " promoted " + subject + ".";
			}
			if (sort.equals("unban")) {
				message = plugin.getAnnouncer() + moderator + " unbanned " + subject + ".";
			}
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.sendMessage(message);
			}
		}
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (quiet) {
				PermissionUser user = PermissionsEx.getUser(players);
				if (user.has("AdminPanel.quiet")) {
					players.sendMessage(message);
				}
				continue;
			}
			players.sendMessage(message);
		}
	}

}
