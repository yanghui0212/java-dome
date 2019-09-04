package com.yqh.mode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangq
 * Create time in 2018-09-17 15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String name;

    private int age;

    private String messageId;
}
