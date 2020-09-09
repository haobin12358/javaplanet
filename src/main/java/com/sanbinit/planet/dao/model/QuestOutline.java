package com.sanbinit.planet.dao.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="QuestOutline")
public class QuestOutline  extends BaseModel{
    @Column(name = "QOid")
    private String QOid;
    @Column(name = "QOicon")
    private String QOicon;
    @Column(name = "QOname")
    private String QOname;
    @Column(name = "QOstatus")
    private int QOstatus;
    @Column(name = "QOcreateId")
    private String QOcreateId;
    @Column(name = "QOtype")
    private int QOtype;

    public String getQOid() {
        return QOid;
    }

    public void setQOid(String QOid) {
        this.QOid = QOid;
    }

    public String getQOicon() {
        return QOicon;
    }

    public void setQOicon(String QOicon) {
        this.QOicon = QOicon;
    }

    public String getQOname() {
        return QOname;
    }

    public void setQOname(String QOname) {
        this.QOname = QOname;
    }

    public int getQOstatus() {
        return QOstatus;
    }

    public void setQOstatus(int QOstatus) {
        this.QOstatus = QOstatus;
    }

    public String getQOcreateId() {
        return QOcreateId;
    }

    public void setQOcreateId(String QOcreateId) {
        this.QOcreateId = QOcreateId;
    }

    public int getQOtype() {
        return QOtype;
    }

    public void setQOtype(int QOtype) {
        this.QOtype = QOtype;
    }
}
