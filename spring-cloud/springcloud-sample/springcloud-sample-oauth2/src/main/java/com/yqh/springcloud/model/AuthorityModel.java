package com.yqh.springcloud.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yangq
 * Create time in 2018-08-01 12:51
 */
@Data
@Table(name = "authority")
public class AuthorityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Long id;

    private String name;

    private String url;

    private Integer seq;

    private String model;

}
