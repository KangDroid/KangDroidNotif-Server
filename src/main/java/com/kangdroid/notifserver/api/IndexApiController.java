package com.kangdroid.notifserver.api;

import com.kangdroid.notifserver.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexApiController {
    private final NotificationService notificationService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("notifications", notificationService.findAllDesc());
        return "index";
    }
}
