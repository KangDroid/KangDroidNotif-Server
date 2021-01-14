package com.kangdroid.notifserver.dto;

import com.kangdroid.notifserver.domain.Notification;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NotificationListResponseDto {
    private Long id;
    private String reqPackage;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public NotificationListResponseDto(Notification notification) {
        this.id = notification.getId();
        this.reqPackage = notification.getReqPackage();
        this.title = notification.getTitle();
        this.content = notification.getContent();
        this.createdDate = notification.getCreatedDate();
    }
}
