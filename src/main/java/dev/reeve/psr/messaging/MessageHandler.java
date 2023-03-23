package dev.reeve.psr.messaging;

import dev.reeve.psr.Config;
import dev.reeve.psr.PlayerSupplyRequests;
import dev.reeve.psr.proxy.ProxyHandler;
import org.bukkit.plugin.Plugin;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Logger;

public abstract class MessageHandler implements Closeable {
	protected final Config config;
	protected final ProxyHandler proxyHandler;
	protected final Plugin plugin;

	protected static final Logger logger = PlayerSupplyRequests.getLogger();

	public MessageHandler(Config config, ProxyHandler proxyHandler, Plugin plugin) {
		this.config = config;
		this.proxyHandler = proxyHandler;
		this.plugin = plugin;
	}

	public abstract void init();
}
