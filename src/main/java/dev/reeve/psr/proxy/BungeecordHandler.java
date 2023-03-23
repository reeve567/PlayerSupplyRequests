package dev.reeve.psr.proxy;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BungeecordHandler extends ProxyHandler {
	private static final String channel = "BungeeCord";

	public BungeecordHandler(Plugin plugin) {
		super(plugin);
	}

	@Override
	public void register() {
		plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, channel);
	}

	@Override
	public void unregister() {
		plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, channel);
	}

	@Override
	public void sendPlayersToServer(String server, Player... players) {
		for (Player player : players) {
			ByteArrayDataOutput output = ByteStreams.newDataOutput();
			output.writeUTF("Connect");
			output.writeUTF(server);

			player.sendPluginMessage(plugin, channel, output.toByteArray());
		}
	}
}
