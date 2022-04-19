package com.company.models;

import java.sql.Date;

public class PhoneNumber {
    private int id, abonentId, rateId;
    private String hasBenefit, phone;
    private Date buyDate;

    public PhoneNumber(int id, int abonentId, int rateId, String hasBenefit, String phone, Date buyDate) {
        this.id = id;
        this.abonentId = abonentId;
        this.rateId = rateId;
        this.hasBenefit = hasBenefit;
        this.phone = phone;
        this.buyDate = buyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbonentId() {
        return abonentId;
    }

    public void setAbonentId(int abonentId) {
        this.abonentId = abonentId;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public String getHasBenefit() {
        return hasBenefit;
    }

    public void setHasBenefit(String hasBenefit) {
        this.hasBenefit = hasBenefit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", abonentId=" + abonentId +
                ", rateId=" + rateId +
                ", hasBenefit='" + hasBenefit + '\'' +
                ", phone='" + phone + '\'' +
                ", buyDate=" + buyDate +
                '}';
    }
}
