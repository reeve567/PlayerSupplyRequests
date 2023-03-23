package dev.reeve.psr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.reeve.psr.proxy.BungeecordHandler;
import dev.reeve.psr.proxy.ProxyHandler;
import dev.reeve.psr.proxy.VelocityHandler;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class PlayerSupplyRequests extends JavaPlugin {
	private Config config;
	private MessageHandler messageHandler;
	private ProxyHandler proxyHandler;
	private final String configFileName = "config.json";
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	@Getter
	private static final Logger logger = Logger.getLogger(PlayerSupplyRequests.class.getName());

	@SneakyThrows
	@Override
	public void onEnable() {
		File configFile = new File(getDataFolder(), configFileName);
		if (!configFile.exists()) {
			logger.warning("Could not find a configuration file, generating a default one, and using that.");
			getDataFolder().mkdir();

			BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
			config = new Config();
			writer.write(gson.toJson(config));
			writer.flush();
			writer.close();
		}

		switch (config.getProxyType()) {
			case BUNGEECORD -> proxyHandler = new BungeecordHandler(this);
			case VELOCITY -> proxyHandler = new VelocityHandler(this);
		}
		messageHandler = new MessageHandler(config, proxyHandler);

	}
}
