package com.sanbinit.planet.dao.model;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Date;

public class BaseModel {

    @Column(name="isdelete")
    private int isdelete;
    @Column(name="createtime")
    private Date createtime;
    @Column(name="updatetime")
    private Date updatetime;

    public int getIsdelete() {
        return isdelete;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}
