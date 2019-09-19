package com.yqh.mapper.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Version;
import java.util.Date;

/**
 * @author yangq
 * Create time in 2018-10-24 15:42
 */
@Data
public class BaseModel {

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "remark")
    private String remark;

    @Column(name = "version")
    @Version
    private Long version;
}
