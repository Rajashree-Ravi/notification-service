package com.banking.notification.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String eventType;

	@NotBlank
	private String notificationType;

	@NotBlank
	private String notificationMessage;

	@NotNull
	private Long transactionReference;

	private Long fraudulenceReference;

	@NotNull
	private LocalDateTime eventTime;

	public Notification updateWith(Notification notification) {
		return new Notification(this.id, notification.eventType, notification.notificationType,
				notification.notificationMessage, notification.transactionReference, notification.fraudulenceReference,
				notification.eventTime);
	}
}
