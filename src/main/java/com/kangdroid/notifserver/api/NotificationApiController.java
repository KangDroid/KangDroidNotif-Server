package com.kangdroid.notifserver.api;

import com.kangdroid.notifserver.dto.NotificationDTO;
import com.kangdroid.notifserver.dto.NotificationResponseDTO;
import com.kangdroid.notifserver.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NotificationApiController {
    private final NotificationService notificationService;

    @PostMapping("/post/notifPost")
    public NotificationDTO postNotificationLists(@RequestBody NotificationDTO notificationDTO) {
        notificationService.save(notificationDTO);
        return notificationDTO;
    }

    @GetMapping("/get/notifGet/{id}")
    public NotificationResponseDTO getNotificationById(@PathVariable Long id) {
        return notificationService.findById(id);
    }

    @GetMapping("/get/NotifCount")
    public String getNotifCount() {
        return notificationService.getNotificationCount();
    }
}
