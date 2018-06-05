package com.csx.pay.permission.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author csx
 * @Package com.csx.pay.permission.entity
 * @Description: 权限基类
 * @date 2018/6/4 0004
 */
public class PermissionBaseEntity implements Serializable {
    private static final long serialVersionUID = -1164227376672815589L;
    /**主键ID*/
    private Long id;
    /**版本号默认为0*/
    private Integer version = 0;
    /**状态 PublicStatusEnum*/
    private String status;
    /**创建人*/
    private String creater;
    /**创建时间*/
    private Date createTime = new Date();
    /**修改人*/
    private String editor;
    /**修改时间*/
    private Date editTime;
    /**描述*/
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
