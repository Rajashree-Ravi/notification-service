package com.banking.notification.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.banking.notification.model.FraudulenceDto;
import com.banking.notification.model.TransactionDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

	@Value("${consumer.config.bootstrap-servers}")
	private String consumerServers;

	@Value("${consumer.config.group-id}")
	private String groupId;

	@Bean
	public ConsumerFactory<String, TransactionDto> transactionConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerServers);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
		configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.banking.notification.model.TransactionDto");

		return new DefaultKafkaConsumerFactory<>(configProps);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TransactionDto> transactionKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TransactionDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(transactionConsumerFactory());

		return factory;
	}

	@Bean
	public ConsumerFactory<String, FraudulenceDto> fradulenceConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerServers);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
		configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.banking.notification.model.FraudulenceDto");

		return new DefaultKafkaConsumerFactory<>(configProps);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FraudulenceDto> fradulenceKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FraudulenceDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(fradulenceConsumerFactory());

		return factory;
	}

}