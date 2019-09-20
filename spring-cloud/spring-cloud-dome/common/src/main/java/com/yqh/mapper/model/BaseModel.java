package com.yqh.mapper.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

/**
 * @author yangq
 * Create time in 2018-10-24 15:42
 */
@Data
public class BaseModel {

    @TableField("creation_time")
    private Date creationTime;

    @TableField("last_update_time")
    private Date lastUpdateTime;

    @TableField("created_by")
    private String createdBy;

    @TableField("updated_by")
    private String updatedBy;

    @TableField("remark")
    private String remark;

    @TableField("version")
    @Version
    private Long version;
}
