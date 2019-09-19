package com.yqh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yqh.dto.ResultDto;
import com.yqh.util.ResultDtoFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangq
 * Create time in 2019-09-19 11:38
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) throws JsonProcessingException {
        ResultDto result = ResultDtoFactory.build("xxxx");
        log.info(new Gson().toJson(result));
        ObjectMapper mapper = new ObjectMapper();
        log.info(mapper.writeValueAsString(result));
    }
}
