package com.sanbinit.planet.dao.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "Quest")
public class Quest extends BaseModel {
    @Column(name = "QUid")
    private String QUid;
    @Column(name = "QOid")
    private String QOid;
    @Column(name = "QUquest")
    private String QUquest;
    @Column(name = "QUstatus")
    private int QUstatus;
    @Column(name = "QUcreateId")
    private String QUcreateId;

    public String getQUid() {
        return QUid;
    }

    public void setQUid(String QUid) {
        this.QUid = QUid;
    }

    public String getQOid() {
        return QOid;
    }

    public void setQOid(String QOid) {
        this.QOid = QOid;
    }

    public String getQUquest() {
        return QUquest;
    }

    public void setQUquest(String QUquest) {
        this.QUquest = QUquest;
    }

    public int getQUstatus() {
        return QUstatus;
    }

    public void setQUstatus(int QUstatus) {
        this.QUstatus = QUstatus;
    }

    public String getQUcreateId() {
        return QUcreateId;
    }

    public void setQUcreateId(String QUcreateId) {
        this.QUcreateId = QUcreateId;
    }

}
