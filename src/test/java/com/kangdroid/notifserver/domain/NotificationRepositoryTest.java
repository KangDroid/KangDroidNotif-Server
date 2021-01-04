package com.kangdroid.notifserver.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationRepositoryTest {

    @Autowired
    NotificationRepository notificationRepository;

    @After
    public void cleanup() {
        notificationRepository.deleteAll();
    }

    @Test
    public void get_notif_test() {
        // Let
        String notifTitle = "KakaoTalk";
        String content = "Hello, World!";
        String reqPackage = "com.kangdroid.test";
        Date todayDate = Calendar.getInstance().getTime();
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String curDate = formatDate.format(todayDate);
        // Let - Create simple data
        notificationRepository.save(Notification.builder()
                .reqPackage(reqPackage)
                .title(notifTitle)
                .content(content)
                .genDate(curDate)
                .build()
        );

        // When
        List<Notification> notificationList = notificationRepository.findAll();

        // Check
        Notification notificationResult = notificationList.get(0);
        assertThat(notificationResult.getContent()).isEqualTo(content);
        assertThat(notificationResult.getTitle()).isEqualTo(notifTitle);
        assertThat(notificationResult.getGenDate()).isEqualTo(curDate);
        assertThat(notificationResult.getReqPackage()).isEqualTo(reqPackage);
    }
}
