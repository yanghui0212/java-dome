package com.yangqh.nosql.repository;

import com.yangqh.nosql.entity.ControllerLogMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author yangq
 * Create time in 2018-07-20 11:17
 */
public interface ControllerLogMongoRepository extends MongoRepository<ControllerLogMongo, String> {

    List<ControllerLogMongo> findByMethod();


}
