package com.kangdroid.notifserver;

import com.kangdroid.notifserver.domain.NotificationRepository;
import com.kangdroid.notifserver.dto.NotificationDTO;
import com.kangdroid.notifserver.dto.NotificationResponseDTO;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationGetTest {
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
    public void testGetNotification() throws Exception {
        // Let - Default
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

        // Post Should be tested with "testPostNotification()", so in here, we post it.
        // Let - URL
        String url = "http://localhost:" + this.port + "/post/notifPost";
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, notificationDTO, Long.class);

        // Check for HTTP Code, since there might be 404 error somehow
        // But Skip checking whether it is correctly posted in server.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Now, we get data from server.
        String getUrl = "http://localhost:" + this.port + "/post/notifGet/{id}";
        NotificationResponseDTO notifObject = testRestTemplate.getForObject(getUrl, NotificationResponseDTO.class, 1);

        // Assert
        assertThat(notifObject.getContent()).isEqualTo(content);
        assertThat(notifObject.getTitle()).isEqualTo(title);
        assertThat(notifObject.getGenDate()).isEqualTo(curDate);
    }
}
