package com.yqh.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangq
 * Create time in 2019-09-20 18:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void register() {
    }

    @Test
    public void login() throws Exception {
        MvcResult mvcResult = this.mvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\n" +
                                "\t\"userName\":\"user\",\n" +
                                "\t\"password\":\"123456\"\n" +
                                "}")
        ).
                andExpect(status().isOk()).
                andReturn();
        log.info("------");
    }

    @Test
    public void logout() {
    }
}
