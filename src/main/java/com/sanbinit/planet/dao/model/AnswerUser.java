package com.sanbinit.planet.dao.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="AnswerUser")
public class AnswerUser extends BaseModel {
    @Column(name = "QAUid")
    private String QAUid;
    @Column(name = "QAid")
    private String QAid;
    @Column(name = "USid")
    private String USid;

    public String getQAUid() {
        return QAUid;
    }

    public void setQAUid(String QAUid) {
        this.QAUid = QAUid;
    }

    public String getQAid() {
        return QAid;
    }

    public void setQAid(String QAid) {
        this.QAid = QAid;
    }

    public String getUSid() {
        return USid;
    }

    public void setUSid(String USid) {
        this.USid = USid;
    }
}
