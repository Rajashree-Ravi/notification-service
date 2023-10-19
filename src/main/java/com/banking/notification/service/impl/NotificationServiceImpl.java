package com.banking.notification.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.notification.entity.Notification;
import com.banking.notification.model.NotificationDto;
import com.banking.notification.repository.NotificationRepository;
import com.banking.notification.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public NotificationDto getNotificationById(long id) {
		Optional<Notification> notification = notificationRepository.findById(id);
		return (notification.isPresent() ? mapper.map(notification.get(), NotificationDto.class) : null);
	}

	@Override
	public NotificationDto createNotification(NotificationDto notificationDto) {
		Notification notification = mapper.map(notificationDto, Notification.class);
		return mapper.map(notificationRepository.save(notification), NotificationDto.class);
	}

}
