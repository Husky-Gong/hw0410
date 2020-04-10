package com.zx.domain;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer aid;

    private String apwd;

    private Double money;

    private Integer uid;

    public Account() {
    }

    public Account(Integer aid, String apwd, Double money, Integer uid) {
        this.aid = aid;
        this.apwd = apwd;
        this.money = money;
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getApwd() {
        return apwd;
    }

    public void setApwd(String apwd) {
        this.apwd = apwd;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", apwd='" + apwd + '\'' +
                ", money=" + money +
                ", uid=" + uid +
                '}';
    }
}