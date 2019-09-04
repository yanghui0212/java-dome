package com.yqh.springcloud.model;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @author yangq
 * Create time in 2018-08-01 12:40
 */
@Data
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Long id;

    private String userName;

    private String passWord;

    List<RoleModel> roles = Lists.newArrayList();

}
