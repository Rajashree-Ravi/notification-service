package com.banking.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.notification.exception.BankingException;
import com.banking.notification.model.NotificationDto;
import com.banking.notification.service.NotificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(produces = "application/json", value = "Operations pertaining to manage notifications in banking application")
@RequestMapping("/api/notification")
public class NotificationController {

	@Autowired
	NotificationService notificationService;

	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieve specific notification with the specified notification id", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved notification with the notification id"),
			@ApiResponse(code = 404, message = "Notification with specified notification id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<NotificationDto> getNotificationById(@PathVariable("id") long id) {

		NotificationDto notification = notificationService.getNotificationById(id);
		if (notification != null)
			return new ResponseEntity<>(notification, HttpStatus.OK);
		else
			throw new BankingException("notification-not-found", String.format("Notification with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping("/createNotification")
	@ApiOperation(value = "Create a new notification", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created a notification"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notification) {
		return new ResponseEntity<>(notificationService.createNotification(notification), HttpStatus.CREATED);
	}

}
