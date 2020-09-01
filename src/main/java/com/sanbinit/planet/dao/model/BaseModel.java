package com.sanbinit.planet.dao.model;

import org.joda.time.DateTime;

import javax.persistence.Transient;

public class BaseModel {

    private int isdelete;
    private DateTime createtime;
    private DateTime updatetime;

    public int getIsdelete() {
        return isdelete;
    }

    public DateTime getCreatetime() {
        return createtime;
    }

    public DateTime getUpdatetime() {
        return updatetime;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public void setCreatetime(DateTime createtime) {
        this.createtime = createtime;
    }

    public void setUpdatetime(DateTime updatetime) {
        this.updatetime = updatetime;
    }

    public void setAll(int isdelete, DateTime createtime, DateTime updatetime){
        this.isdelete = isdelete;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }
}
