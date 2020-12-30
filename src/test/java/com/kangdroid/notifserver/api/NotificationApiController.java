package com.kangdroid.notifserver.api;

import com.kangdroid.notifserver.dto.NotificationDTO;
import com.kangdroid.notifserver.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationApiController {
    private final NotificationService notificationService;
    @PostMapping("/post/notifPost")
    public void postNotificationLists(@RequestBody NotificationDTO notificationDTO) {
        notificationService.save(notificationDTO);
    }
}
