package com.kangdroid.notifserver.service;

import com.kangdroid.notifserver.domain.NotificationRepository;
import com.kangdroid.notifserver.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional
    public Long save(NotificationDTO notificationDTO) {
        return this.notificationRepository.save(notificationDTO.toEntity()).getId();
    }
}
