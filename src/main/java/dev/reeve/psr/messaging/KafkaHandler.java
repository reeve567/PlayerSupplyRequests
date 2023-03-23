package dev.reeve.psr.messaging;

import dev.reeve.psr.Config;
import dev.reeve.psr.proxy.ProxyHandler;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.util.Properties;

public class KafkaHandler extends MessageHandler {
	private KafkaProducer<String, String> producer;
	private KafkaConsumer<String, String> consumer;
	private BukkitTask consumerTask;

	public KafkaHandler(Config config, ProxyHandler proxyHandler, Plugin plugin) {
		super(config, proxyHandler, plugin);
		init();
	}

	public void init() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.CLIENT_ID_CONFIG, config.getKafkaConfig().getClientId());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, config.getKafkaConfig().getGroupId());
		properties.put(ProducerConfig.ACKS_CONFIG, "all");

		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

		producer = new KafkaProducer<>(properties);
		consumer = new KafkaConsumer<>(properties);

		if (config.getInstanceType() == InstanceType.LOBBY) {
			consumerTask = Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
				boolean running = true;
				while (running) {
					ConsumerRecords<String, String> records = consumer.poll(50);

					records.forEach(record -> {
						logger.info("Record: " + record.value());
					});
				}
			});
		} else {
			consumerTask = Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
				boolean running = true;
				while (running) {
					ConsumerRecords<String, String> records = consumer.poll(50);

					records.forEach(record -> {
						logger.info("Record: " + record.value());
					});
					//TODO: Handle records
				}
			});
		}
	}

	@Override
	public void close() throws IOException {
		if (producer != null) {
			producer.close();
		}

		if (consumer != null) {
			consumer.close();
			consumerTask.cancel();
		}
	}
}
