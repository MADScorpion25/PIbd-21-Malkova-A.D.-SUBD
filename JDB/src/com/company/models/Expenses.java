package com.company.models;

import java.sql.Date;

public class Expenses {
    private int id, intercityMinutes, baseMinutes, smsExpenses, phoneId;
    private Date month;

    public Expenses(int id, int intercityMinutes, int baseMinutes, int smsExpenses, int phoneId, Date month) {
        this.id = id;
        this.intercityMinutes = intercityMinutes;
        this.baseMinutes = baseMinutes;
        this.smsExpenses = smsExpenses;
        this.phoneId = phoneId;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntercityMinutes() {
        return intercityMinutes;
    }

    public void setIntercityMinutes(int intercityMinutes) {
        this.intercityMinutes = intercityMinutes;
    }

    public int getBaseMinutes() {
        return baseMinutes;
    }

    public void setBaseMinutes(int baseMinutes) {
        this.baseMinutes = baseMinutes;
    }

    public int getSmsExpenses() {
        return smsExpenses;
    }

    public void setSmsExpenses(int smsExpenses) {
        this.smsExpenses = smsExpenses;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", intercityMinutes=" + intercityMinutes +
                ", baseMinutes=" + baseMinutes +
                ", smsExpenses=" + smsExpenses +
                ", phoneId=" + phoneId +
                ", month=" + month +
                '}';
    }
}
