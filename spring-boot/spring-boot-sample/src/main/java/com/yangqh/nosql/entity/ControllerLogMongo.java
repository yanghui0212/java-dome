package com.yangqh.nosql.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author yangq
 * Create time in 2018-07-20 11:16
 */
@Data
@Document(collection = "controller_log_mongo")
public class ControllerLogMongo {

    @Id
    private String id;
    private String requestURL;
    private String requestURI;
    private String queryString;
    private String remoteAddr;
    private String remoteHost;
    private int remotePort;
    private String localAddr;
    private String localName;
    private String method;
    private String headers;
    private String parameters;
    private String classMethod;
    private String args;
    private long times;
}
