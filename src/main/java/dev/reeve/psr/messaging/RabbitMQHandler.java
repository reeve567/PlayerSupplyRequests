package dev.reeve.psr.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dev.reeve.psr.Config;
import dev.reeve.psr.PlayerSupplyRequests;
import dev.reeve.psr.proxy.ProxyHandler;
import org.bukkit.plugin.Plugin;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class RabbitMQHandler extends MessageHandler {
	private Connection connection;
	private Channel channel;

	public RabbitMQHandler(Config config, ProxyHandler proxyHandler, Plugin plugin) {
		super(config, proxyHandler, plugin);
		init();
	}

	@Override
	public void init() {
		ConnectionFactory factory = new ConnectionFactory();

		factory.setUsername(config.getUsername());
		factory.setPassword(config.getPassword());
		factory.setHost(config.getHost());
		factory.setVirtualHost(config.getVirtualHost());
		factory.setPort(config.getPort());

		try {
			connection = factory.newConnection();
			channel = connection.createChannel(0);


			//channel.exchangeDeclare(config.getExchangeName(), BuiltinExchangeType.DIRECT, false, false, null);
//			for (String queue : config.getQueues()) {
//				channel.queueDeclare(queue, true, false, false, null);
//				channel.queueBind(queue, config.getExchangeName(), queue);
//			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (TimeoutException e) {
			logger.severe("Could not connect to RabbitMQ!");
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws IOException {
		connection.close();
	}
}
