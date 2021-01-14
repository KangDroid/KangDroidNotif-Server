package com.kangdroid.notifserver.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT p FROM Notification p ORDER BY p.id DESC")
    List<Notification> findAllDesc();
}
