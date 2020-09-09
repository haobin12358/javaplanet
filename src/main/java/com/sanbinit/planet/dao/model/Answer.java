package com.sanbinit.planet.dao.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="Answer")
public class Answer  extends BaseModel{
    @Column(name = "QAid")
    private String QAid;
    @Column(name = "QUid")
    private String QUid;
    @Column(name = "QAcontent")
    private String QAcontent;
    @Column(name = "QAcreateId")
    private String QAcreateId;

    public String getQAid() {
        return QAid;
    }

    public void setQAid(String QAid) {
        this.QAid = QAid;
    }

    public String getQUid() {
        return QUid;
    }

    public void setQUid(String QUid) {
        this.QUid = QUid;
    }

    public String getQAcontent() {
        return QAcontent;
    }

    public void setQAcontent(String QAcontent) {
        this.QAcontent = QAcontent;
    }

    public String getQAcreateId() {
        return QAcreateId;
    }

    public void setQAcreateId(String QAcreateId) {
        this.QAcreateId = QAcreateId;
    }
}
