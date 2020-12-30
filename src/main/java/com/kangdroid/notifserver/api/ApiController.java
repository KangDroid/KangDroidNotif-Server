package com.kangdroid.notifserver.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/get/NotifCount")
    public String getNotifCount() {
        return "10";
    }
}
