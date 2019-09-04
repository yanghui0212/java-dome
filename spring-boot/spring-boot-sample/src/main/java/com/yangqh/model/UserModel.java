package com.yangqh.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yangq
 * Create time in 2018-07-18 12:48
 */
@Data
@Table(name = "user")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
