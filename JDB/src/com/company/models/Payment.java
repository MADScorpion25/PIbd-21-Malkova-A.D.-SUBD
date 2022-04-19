package com.company.models;

import java.sql.Date;

public class Payment {
    private int id, accountStatus, phoneId;
    private Date paymentDate;

    public Payment(int id, int accountStatus, int phoneId, Date paymentDate) {
        this.id = id;
        this.accountStatus = accountStatus;
        this.phoneId = phoneId;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", accountStatus=" + accountStatus +
                ", phoneId=" + phoneId +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
