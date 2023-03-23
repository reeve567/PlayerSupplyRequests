package dev.reeve.psr.proxy;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class ProxyHandler {
	protected Plugin plugin;

	ProxyHandler(Plugin plugin) {
		this.plugin = plugin;
	}
	abstract void register();
	abstract void unregister();
	abstract void sendPlayersToServer(String server, Player... players);
}
