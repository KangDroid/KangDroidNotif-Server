package com.kangdroid.notifserver.service;

import com.kangdroid.notifserver.domain.Notification;
import com.kangdroid.notifserver.domain.NotificationRepository;
import com.kangdroid.notifserver.dto.NotificationDTO;
import com.kangdroid.notifserver.dto.NotificationResponseDTO;
import com.kangdroid.notifserver.notify.HostNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public List<NotificationResponseDTO> findAll() {
        List<Notification> notificationList = notificationRepository.findAll();
        List<NotificationResponseDTO> responseDTOList = new ArrayList<>();

        for (Notification notification : notificationList) {
            responseDTOList.add(new NotificationResponseDTO(notification));
        }

        return responseDTOList;
    }

    public String getNotificationCount() {
        return String.valueOf(notificationRepository.findAll().size());
    }
}
