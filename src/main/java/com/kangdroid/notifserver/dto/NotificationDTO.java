package com.kangdroid.notifserver.dto;

import com.kangdroid.notifserver.domain.Notification;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * API Request[with param] --> param = Notification DTO
 * --> DTO to Notification --> Service --> Service to Notification Repository.
 */
@Getter
@NoArgsConstructor
public class NotificationDTO {
    private String reqPackage;
    private String title;
    private String content;

    @Builder
    public NotificationDTO(String reqPackage, String reqTitle, String reqContent) {
        this.reqPackage = reqPackage;
        this.title = reqTitle;
        this.content = reqContent;
    }

    public Notification toEntity() {
        return Notification.builder()
                .reqPackage(this.reqPackage)
                .title(this.title)
                .content(this.content)
                .build();
    }
}
