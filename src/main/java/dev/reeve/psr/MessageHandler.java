package dev.reeve.psr;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dev.reeve.psr.proxy.ProxyHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class MessageHandler {
	private Connection connection;
	private ProxyHandler proxyHandler;
	private static final Logger logger = PlayerSupplyRequests.getLogger();

	MessageHandler(Config config, ProxyHandler proxyHandler) {
		ConnectionFactory factory = new ConnectionFactory();

		factory.setUsername(config.getUsername());
		factory.setPassword(config.getPassword());
		factory.setHost(config.getHost());
		factory.setPort(config.getPort());

		try {
			connection = factory.newConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (TimeoutException e) {
			logger.severe("Could not connect to RabbitMQ!");
			throw new RuntimeException(e);
		}
	}


}
