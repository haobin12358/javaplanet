package com.sanbinit.planet.dao.model;

import org.joda.time.DateTime;

import javax.persistence.Transient;

public class BaseModel {

    private Byte isdelete;
    private DateTime createtime;
    private DateTime updatetime;

    public Byte getIsdelete() {
        return isdelete;
    }

    public DateTime getCreatetime() {
        return createtime;
    }

    public DateTime getUpdatetime() {
        return updatetime;
    }

    public void setIsdelete(Byte isdelete) {
        this.isdelete = isdelete;
    }

    public void setCreatetime(DateTime createtime) {
        this.createtime = createtime;
    }

    public void setUpdatetime(DateTime updatetime) {
        this.updatetime = updatetime;
    }
}
