package com.yqh.dto.base;

import lombok.Data;

import java.util.Date;

/**
 * @author yangq
 * Create time in 2019-09-20 09:54
 */
@Data
public class BaseDto {
    private Date creationTime;

    private Date lastUpdateTime;

    private String createdBy;

    private String updatedBy;

    private String remark;

    private Long version;
}
