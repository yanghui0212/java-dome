package com.yqh.mapper.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
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

    @TableField(value = "creation_time")
    private Date creationTime;

    @TableField(value = "last_update_time")
    private Date lastUpdateTime;

    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(value = "updated_by", fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "version")
    @Version
    private Long version;
}
