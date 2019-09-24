package com.yqh.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
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

    @Test
    public void jwt() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJpZFwiOjQ5MjI1NTk1ODA0MzYwMjk0NCxcInVzZXJOYW1lXCI6XCJ1c2VyXCIsXCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJwaW5cIjpcIlwifSJ9.r5js_itj9nzT3wjHYao0jlUwPRIZxnj4B9DOrqxcO2k";
        String user = JWT.decode(token).getSubject();
        log.info("xxx");

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("401");
        }
    }
}
