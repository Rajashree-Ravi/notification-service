package com.banking.notification.service;

import com.banking.notification.model.NotificationDto;

public interface NotificationService {

	NotificationDto getNotificationById(long id);

	NotificationDto createNotification(NotificationDto notificationDto);

}
