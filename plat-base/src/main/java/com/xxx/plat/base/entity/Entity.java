package com.xxx.plat.base.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class Entity implements Storeable<String>, Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Date createDt;
    private Date updateDt;
    private String createBy;
    private String updateBy;
    private Boolean deleted;

    public Entity() {
        this.deleted = Boolean.FALSE;
    }

    public Date getUpdateDt() {
        return this.updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDt() {
        return this.createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void logicDelete() {
        this.setDeleted(Boolean.TRUE);
    }
}
