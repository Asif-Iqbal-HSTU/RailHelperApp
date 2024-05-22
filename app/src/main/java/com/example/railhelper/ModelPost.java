package com.example.railhelper;

public class ModelPost {

    String pId, pTitle, pDescr, uid, uName, uEmail, pTime;

    public ModelPost() {

    }

    public ModelPost(String pId, String pTitle, String pDescr, String uid, String uName, String uEmail, String pTime) {
        this.pId = pId;
        this.pTitle = pTitle;
        this.pDescr = pDescr;
        this.uid = uid;
        this.uName = uName;
        this.uEmail = uEmail;
        this.pTime = pTime;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpDescr() {
        return pDescr;
    }

    public void setpDescr(String pDescr) {
        this.pDescr = pDescr;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }
}
