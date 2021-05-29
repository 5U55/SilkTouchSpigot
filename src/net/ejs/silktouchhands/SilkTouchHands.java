package net.ejs.silktouchhands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class SilkTouchHands extends JavaPlugin implements Listener {

	private static SilkTouchHands plugin;
	@Override
	public void onEnable() {
		this.getCommand("silktouch").setExecutor(new SilkTouchCommand());
		this.getCommand("unsilktouch").setExecutor(new UnsilkTouchCommand());
		this.getCommand("checksilktouch").setExecutor(new CheckSilkTouchCommand());
		Bukkit.getPluginManager().registerEvents(this, this);
		plugin = this;
	}

	@Override
	public void onDisable() {

	}
	
	public static SilkTouchHands getPlugin() {
		return plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!player.getPersistentDataContainer().has(new NamespacedKey(SilkTouchHands.getPlugin(), "cansilktouch"), PersistentDataType.STRING)) {
			player.getPersistentDataContainer().set(new NamespacedKey(SilkTouchHands.getPlugin(), "cansilktouch"), PersistentDataType.STRING, "");
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getPlayer().getPersistentDataContainer().get(new NamespacedKey(SilkTouchHands.getPlugin(), "cansilktouch"), PersistentDataType.STRING).equals("enderman")) {
			if (event.getPlayer().getMainHand() == null||event.getPlayer().getItemInHand().getType().equals(Material.AIR)) {
				event.setDropItems(false);
				event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(),
						new ItemStack(event.getBlock().getType(), 1));
			}
		}
	}
}
