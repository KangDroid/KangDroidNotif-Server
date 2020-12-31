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
        String title = "KakaoTalk";
        String content = "Hello, World!";
        Date todayDate = Calendar.getInstance().getTime();
        DateFormat formatDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String curDate = formatDate.format(todayDate);
        NotificationDTO notificationDTO = NotificationDTO.builder()
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
    }
}
