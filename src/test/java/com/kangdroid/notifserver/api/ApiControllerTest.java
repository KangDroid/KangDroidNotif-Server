package com.kangdroid.notifserver.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ApiController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class ApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void test_notif_count() throws Exception {
        mvc.perform(get("/get/NotifCount"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }
}
