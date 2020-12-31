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
    public void postNotificationLists(@RequestBody NotificationDTO notificationDTO) {
        notificationService.save(notificationDTO);
    }

    @GetMapping("/post/notifGet/{id}")
    public NotificationResponseDTO getNotificationById(@PathVariable Long id) {
        return notificationService.findById(id);
    }
}
