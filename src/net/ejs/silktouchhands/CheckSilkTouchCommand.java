package net.ejs.silktouchhands;

import java.util.Arrays;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;

public class CheckSilkTouchCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arguments) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (arguments.length == 1) {
				player = sender.getServer().getPlayerExact(arguments[0]);
			}
			if (player == null) {
				sender.sendMessage(ChatColor.AQUA + arguments[0] + " is offline");
			}
			else if (arguments.length > 1) {
				player.sendMessage(ChatColor.RED + Arrays.toString(arguments) + " is not valid input, only list 0-1 players");
			} else {
				PersistentDataContainer data = player.getPersistentDataContainer();
				if(data.get(new NamespacedKey(SilkTouchHands.getPlugin(), "cansilktouch"), PersistentDataType.STRING).equals("silktouch")) {
					player.sendMessage(ChatColor.GOLD + player.getName()+" has silk touch hands");
				} else {
					player.sendMessage(ChatColor.GOLD + player.getName()+" does not have silk touch hands");
				}
			}
		}
		return true;
	}
}
