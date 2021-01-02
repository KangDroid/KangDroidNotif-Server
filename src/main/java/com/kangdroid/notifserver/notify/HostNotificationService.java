package com.kangdroid.notifserver.notify;

import com.kangdroid.notifserver.dto.NotificationDTO;

import java.io.IOException;

public class HostNotificationService {
    /**
     * Send Notification to macOS.
     * @param notificationDTO Notification object sent from KangDroidNotif-App | web
     */
    public void showNotificationOnHost(NotificationDTO notificationDTO) {
        if (System.getProperty("os.name").toLowerCase().equals("mac os x")) {
            final String commandArgs = "display notification \"" + notificationDTO.getContent() + "\"" + " with title \"" + notificationDTO.getTitle() + "\"";
            ProcessBuilder hostBuilder = new ProcessBuilder("osascript", "-e", commandArgs);
            try {
                hostBuilder.inheritIO().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
