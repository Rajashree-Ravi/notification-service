package com.banking.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
