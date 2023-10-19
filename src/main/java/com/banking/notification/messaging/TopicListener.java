package com.banking.notification.messaging;

import java.time.LocalDateTime;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.banking.notification.model.FraudulenceDto;
import com.banking.notification.model.NotificationDto;
import com.banking.notification.model.TransactionDto;
import com.banking.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

	@Value("${consumer.config.transaction.topic.name}")
	private String transactionTopicName;

	@Value("${consumer.config.fraudulent.topic.name}")
	private String fraudulentTopicName;

	@Autowired
	NotificationService notificationService;

	@KafkaListener(topics = "${consumer.config.transaction.topic.name}", groupId = "${consumer.config.group-id}")
	public void consumeTransaction(ConsumerRecord<String, TransactionDto> payload) {
		log.info("Topic : {}", transactionTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Transaction : {}", payload.value());

		TransactionDto transaction = payload.value();

		NotificationDto notification = new NotificationDto();
		notification.setEventType(transactionTopicName);
		notification.setNotificationType("Success");
		notification.setTransactionReference(transaction.getId());
		notification.setNotificationMessage("Transaction successful");
		notification.setEventTime(LocalDateTime.now());

		notificationService.createNotification(notification);
	}

	@KafkaListener(topics = "${consumer.config.fraudulent.topic.name}", groupId = "${consumer.config.group-id}")
	public void consumeSuspiciousActivity(ConsumerRecord<String, FraudulenceDto> payload) {
		log.info("Topic : {}", fraudulentTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Fraudulence  : {}", payload.value());

		FraudulenceDto fraudulence = payload.value();

		NotificationDto notification = new NotificationDto();
		notification.setEventType(fraudulentTopicName);
		notification.setNotificationType("Alert");
		notification.setTransactionReference(fraudulence.getTransactionReference());
		notification.setFraudulenceReference(fraudulence.getId());
		notification.setNotificationMessage("Suspicious Activity Detected");
		notification.setEventTime(LocalDateTime.now());

		notificationService.createNotification(notification);
	}

}