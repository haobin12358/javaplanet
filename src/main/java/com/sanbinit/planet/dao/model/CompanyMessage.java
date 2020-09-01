package com.sanbinit.planet.dao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.xml.soap.Text;
import java.awt.*;
import java.beans.Transient;

@Table(name="CompanyMessage")
public class CompanyMessage extends BaseModel{

    @Column(name="CMid")
    private String CMid;
    @Column(name="CMtitle")
    private String CMtitle;
    @Column(name="CMmessage")
    private String CMmessage;
    @Column(name="CMindex")
    private Integer CMindex;
    @Column(name="CMreadnum")
    private Integer CMreadnum;

    public String getCMid() {
        return CMid;
    }

    public void setCMid(String CMid) {
        this.CMid = CMid;
    }

    public String getCMtitle() {
        return CMtitle;
    }

    public void setCMtitle(String CMtitle) {
        this.CMtitle = CMtitle;
    }

    public String getCMmessage() {
        return CMmessage;
    }

    public void setCMmessage(String CMmessage) {
        this.CMmessage = CMmessage;
    }

    public Integer getCMindex() {
        return CMindex;
    }

    public void setCMindex(Integer CMindex) {
        this.CMindex = CMindex;
    }

    public Integer getCMreadnum() {
        return CMreadnum;
    }

    public void setCMreadnum(Integer CMreadnum) {
        this.CMreadnum = CMreadnum;
    }
}
