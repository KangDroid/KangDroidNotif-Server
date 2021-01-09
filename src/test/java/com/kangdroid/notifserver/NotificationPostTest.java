package com.kangdroid.notifserver;

import com.kangdroid.notifserver.domain.Notification;
import com.kangdroid.notifserver.domain.NotificationRepository;
import com.kangdroid.notifserver.dto.NotificationDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationPostTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    @After
    public void cleanup() {
        this.notificationRepository.deleteAll();
    }

    @Test
    public void testPostNotification() throws Exception {
        // Let - Default Data
        String reqPackage = "com.kangdroid.test";
        String title = "KakaoTalk";
        String content = "Hello, World!";
        Date todayDate = Calendar.getInstance().getTime();
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String curDate = formatDate.format(todayDate);
        NotificationDTO notificationDTO = NotificationDTO.builder()
                .reqPackage(reqPackage)
                .reqTitle(title)
                .reqContent(content)
                .reqGenDate(curDate)
                .build();

        // Let - URL
        String url = "http://localhost:" + this.port + "/post/notifPost";

        // When
        ResponseEntity<NotificationDTO> responseEntity = testRestTemplate.postForEntity(url, notificationDTO, NotificationDTO.class);

        // Check - HTTP Code
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); // OK Sign Check

        // Check - Whether POST worked correctly!
        List<Notification> notificationList = this.notificationRepository.findAll();
        Notification notificationTesting = notificationList.get(0);

        assertThat(notificationTesting.getTitle()).isEqualTo(title);
        assertThat(notificationTesting.getContent()).isEqualTo(content);
        assertThat(notificationTesting.getGenDate()).isEqualTo(curDate);
        assertThat(notificationTesting.getReqPackage()).isEqualTo(reqPackage);
    }

    @Test
    public void testBaseTimeEntity() {
        // Let
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        notificationRepository.save(Notification.builder()
        .title("KakaoTalk")
        .content("Hello, World!")
        .reqPackage("com.test")
        .genDate("Test")
        .build());

        // When
        List<Notification> notificationList = notificationRepository.findAll();

        // Then
        Notification notification = notificationList.get(0);

        assertThat(notification.getCreatedDate()).isAfter(now);
        assertThat(notification.getModifiedDate()).isAfter(now);
    }
}
