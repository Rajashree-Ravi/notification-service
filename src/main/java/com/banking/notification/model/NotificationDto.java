package com.banking.notification.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a notification in banking application.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

	@ApiModelProperty(notes = "Unique identifier of the Notification.", example = "1")
	private Long id;

	@ApiModelProperty(notes = "Type of Event.", example = "TransactionCompletedEvent", required = true)
	@NotBlank
	private String eventType;
	
	@ApiModelProperty(notes = "Type of Notification.", example = "Alert", required = true)
	@NotBlank
	private String notificationType;

	@ApiModelProperty(notes = "Message for notification.", required = true)
	@NotBlank
	private String notificationMessage;

	@ApiModelProperty(notes = "Unique identifier of the Transaction.", example = "1", required = true)
	@NotNull
	private Long transactionReference;

	@ApiModelProperty(notes = "Unique identifier of the Fraudulence.", example = "1", required = true)
	private Long fraudulenceReference;

	@ApiModelProperty(notes = "Event time.", required = true)
	@NotNull
	private LocalDateTime eventTime;

}
