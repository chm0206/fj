package com.chm.fj.entity;

public class UserInfo {
    private String userID;

    private String userName;

    private String userPass;

    private String creatDte;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID == null ? null : userID.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getCreatDte() {
        return creatDte;
    }

    public void setCreatDte(String creatDte) {
        this.creatDte = creatDte == null ? null : creatDte.trim();
    }
}