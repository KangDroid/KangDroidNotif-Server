package com.kangdroid.notifserver.service;

import com.kangdroid.notifserver.domain.Notification;
import com.kangdroid.notifserver.domain.NotificationRepository;
import com.kangdroid.notifserver.dto.NotificationDTO;
import com.kangdroid.notifserver.dto.NotificationListResponseDto;
import com.kangdroid.notifserver.dto.NotificationResponseDTO;
import com.kangdroid.notifserver.notify.HostNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<NotificationListResponseDto> findAllDesc() {
        return notificationRepository.findAllDesc().stream()
                .map(NotificationListResponseDto::new)
                .collect(Collectors.toList());
    }

    public String getNotificationCount() {
        return String.valueOf(notificationRepository.findAll().size());
    }

    public boolean deleteNotification(Long id) {
        boolean isSucceed = true;
        try {
            notificationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            System.out.println(emptyResultDataAccessException.getMessage());
            isSucceed = false;
        }

        return isSucceed;
    }

    public void deleteNotificationAll() {
        notificationRepository.deleteAll();
    }
}
