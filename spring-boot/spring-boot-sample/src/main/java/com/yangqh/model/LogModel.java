package com.yangqh.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yangq
 * Create time in 2018-07-20 21:34
 */
@Data
@Table(name = "log")
public class LogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Long id;

    private String name;
}
