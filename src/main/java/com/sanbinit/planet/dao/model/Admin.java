package com.sanbinit.planet.dao.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="Admin")
public class Admin extends BaseModel{

    @Column(name="ADid")
    private String ADid;
    @Column(name="ADnum")
    private int ADnum;
    @Column(name="ADname")
    private String ADname;
    @Column(name="ADtelphone")
    private String ADtelphone;
    @Column(name="ADpassword")
    private String ADpassword;
    @Column(name="ADfirstpwd")
    private String ADfirstpwd;
    @Column(name="ADfirstname")
    private String ADfirstname;
    @Column(name="ADheader")
    private String ADheader;
    @Column(name="ADlevel")
    private int ADlevel;
    @Column(name="ADstatus")
    private int ADstatus;
    @Column(name="ADunread")
    private int ADunread;
    @Column(name="ADjavapassword")
    private String ADjavapassword;
    @Column(name="ADsalt")
    private String ADsalt;

    public String getADid() {
        return ADid;
    }

    public void setADid(String ADid) {
        this.ADid = ADid;
    }

    public int getADnum() {
        return ADnum;
    }

    public void setADnum(int ADnum) {
        this.ADnum = ADnum;
    }

    public String getADname() {
        return ADname;
    }

    public void setADname(String ADname) {
        this.ADname = ADname;
    }

    public String getADtelphone() {
        return ADtelphone;
    }

    public void setADtelphone(String ADtelphone) {
        this.ADtelphone = ADtelphone;
    }

    public String getADpassword() {
        return ADpassword;
    }

    public void setADpassword(String ADpassword) {
        this.ADpassword = ADpassword;
    }

    public String getADfirstpwd() {
        return ADfirstpwd;
    }

    public void setADfirstpwd(String ADfirstpwd) {
        this.ADfirstpwd = ADfirstpwd;
    }

    public String getADfirstname() {
        return ADfirstname;
    }

    public void setADfirstname(String ADfirstname) {
        this.ADfirstname = ADfirstname;
    }

    public String getADheader() {
        return ADheader;
    }

    public void setADheader(String ADheader) {
        this.ADheader = ADheader;
    }

    public int getADlevel() {
        return ADlevel;
    }

    public void setADlevel(int ADlevel) {
        this.ADlevel = ADlevel;
    }

    public int getADstatus() {
        return ADstatus;
    }

    public void setADstatus(int ADstatus) {
        this.ADstatus = ADstatus;
    }

    public int getADunread() {
        return ADunread;
    }

    public void setADunread(int ADunread) {
        this.ADunread = ADunread;
    }

    public String getADjavapassword() {
        return ADjavapassword;
    }

    public void setADjavapassword(String ADjavapassword) {
        this.ADjavapassword = ADjavapassword;
    }

    public String getADsalt() {
        return ADsalt;
    }

    public void setADsalt(String ADsalt) {
        this.ADsalt = ADsalt;
    }
}
