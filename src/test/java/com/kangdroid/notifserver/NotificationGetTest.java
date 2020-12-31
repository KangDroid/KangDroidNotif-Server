package com.kangdroid.notifserver;

import com.kangdroid.notifserver.domain.Notification;
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
import java.util.List;

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
        ResponseEntity<NotificationDTO> responseEntity = testRestTemplate.postForEntity(url, notificationDTO, NotificationDTO.class);

        // Check for HTTP Code, since there might be 404 error somehow
        // But Skip checking whether it is correctly posted in server.
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        /**
         * Since on Linux systems, posting above data would result ID != 1,
         * but on macOS systems, posting above data would result ID == 1 though.
         * Therefore, dynamically check ID of its database.
         *
         * It is pretty much sure that notificationRepository does contains only one of data,
         * so check ID dynamically, and assert if there is more than one dbs on notificationRepository.
         */
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList.size()).isEqualTo(1);

        // Now, we get data from server.
        String getUrl = "http://localhost:" + this.port + "/get/notifGet/{id}";
        NotificationResponseDTO notifObject = testRestTemplate.getForObject(getUrl, NotificationResponseDTO.class, notificationList.get(0).getId());

        // Assert
        assertThat(notifObject.getContent()).isEqualTo(content);
        assertThat(notifObject.getTitle()).isEqualTo(title);
        assertThat(notifObject.getGenDate()).isEqualTo(curDate);
    }

    @Test
    public void test_notif_count() throws Exception {
        // Delete All Repos first.
        this.notificationRepository.deleteAll();

        // When
        String url = "http://localhost:" + this.port + "/get/NotifCount";
        String reponseString = testRestTemplate.getForObject(url, String.class);

        // Assert
        assertThat(reponseString).isEqualTo("0");
    }
}
