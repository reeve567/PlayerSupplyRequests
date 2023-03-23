package dev.reeve.psr;

import dev.reeve.psr.messaging.InstanceType;
import dev.reeve.psr.messaging.Messager;
import dev.reeve.psr.proxy.ProxyType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Config {
	private String host = "localhost";
	private String virtualHost = "/";
	private int port = 5672;
	private String username = "guest";
	private String password = "guest";
	private ProxyType proxyType = ProxyType.BUNGEECORD;
	private InstanceType instanceType = InstanceType.LOBBY;
	private Messager messager = Messager.RABBITMQ;
	private RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();
	private KafkaConfig kafkaConfig = new KafkaConfig();

	@Data
	public static class KafkaConfig {
		private String clientId = "supplier1";
		private String groupId = "requester1";
		private List<String> topics = new ArrayList<>();
	}

	@Data
	public static class RabbitMQConfig {
		private List<String> exchanges = new ArrayList<>();
	}
}
