package com.yangqh;

import com.google.gson.Gson;
import com.yangqh.nosql.entity.ControllerLogMongo;
import com.yangqh.nosql.repository.ControllerLogMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author yangq
 * Create time in 2018-07-20 11:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MongoDBTest {

    @Autowired
    private ControllerLogMongoRepository controllerLogMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        ControllerLogMongo logMongo = new ControllerLogMongo();
        logMongo.setMethod("get");
        logMongo = controllerLogMongoRepository.save(logMongo);
        log.info("data--->{}", new Gson().toJson(logMongo));
        List<ControllerLogMongo> logMongoList = controllerLogMongoRepository.findAll();
        log.info("data--->{}", new Gson().toJson(logMongoList));
        Optional<ControllerLogMongo> controllerLogMongoRepositoryById = controllerLogMongoRepository.findById("5b515893d2a8883b78279bfb");
    }
}
