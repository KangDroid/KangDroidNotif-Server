package com.kangdroid.notifserver.service;

import com.kangdroid.notifserver.domain.Notification;
import com.kangdroid.notifserver.domain.NotificationRepository;
import com.kangdroid.notifserver.dto.NotificationDTO;
import com.kangdroid.notifserver.dto.NotificationResponseDTO;
import com.kangdroid.notifserver.notify.HostNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private HostNotificationService hostNotificationService = new HostNotificationService();

    @Transactional
    public Long save(NotificationDTO notificationDTO) {
        hostNotificationService.showNotificationOnHost(notificationDTO);
        return this.notificationRepository.save(notificationDTO.toEntity()).getId();
    }

    public NotificationResponseDTO findById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Such ID: " + id));

        return new NotificationResponseDTO(notification);
    }

    public String getNotificationCount() {
        return String.valueOf(notificationRepository.findAll().size());
    }
}
