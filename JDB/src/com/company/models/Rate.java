package com.company.models;

public class Rate {
    private int id, intercityRate, baseRate, abonentRate, intercityLimit, baseLimit, smsLimit, smsCoast;
    private String rateName;

    public Rate(int id, int intercityRate, int baseRate, int abonentRate, int intercityLimit, int baseLimit, int smsLimit, int smsCoast, String rateName) {
        this.id = id;
        this.intercityRate = intercityRate;
        this.baseRate = baseRate;
        this.abonentRate = abonentRate;
        this.intercityLimit = intercityLimit;
        this.baseLimit = baseLimit;
        this.smsLimit = smsLimit;
        this.smsCoast = smsCoast;
        this.rateName = rateName;
    }
    public Rate(int intercityRate, int baseRate, int abonentRate, int intercityLimit, int baseLimit, int smsLimit, int smsCoast, String rateName) {
        this.intercityRate = intercityRate;
        this.baseRate = baseRate;
        this.abonentRate = abonentRate;
        this.intercityLimit = intercityLimit;
        this.baseLimit = baseLimit;
        this.smsLimit = smsLimit;
        this.smsCoast = smsCoast;
        this.rateName = rateName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntercityRate() {
        return intercityRate;
    }

    public void setIntercityRate(int intercityRate) {
        this.intercityRate = intercityRate;
    }

    public int getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(int baseRate) {
        this.baseRate = baseRate;
    }

    public int getAbonentRate() {
        return abonentRate;
    }

    public void setAbonentRate(int abonentRate) {
        this.abonentRate = abonentRate;
    }

    public int getIntercityLimit() {
        return intercityLimit;
    }

    public void setIntercityLimit(int intercityLimit) {
        this.intercityLimit = intercityLimit;
    }

    public int getBaseLimit() {
        return baseLimit;
    }

    public void setBaseLimit(int baseLimit) {
        this.baseLimit = baseLimit;
    }

    public int getSmsLimit() {
        return smsLimit;
    }

    public void setSmsLimit(int smsLimit) {
        this.smsLimit = smsLimit;
    }

    public int getSmsCoast() {
        return smsCoast;
    }

    public void setSmsCoast(int smsCoast) {
        this.smsCoast = smsCoast;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", intercityRate=" + intercityRate +
                ", baseRate=" + baseRate +
                ", abonentRate=" + abonentRate +
                ", intercityLimit=" + intercityLimit +
                ", baseLimit=" + baseLimit +
                ", smsLimit=" + smsLimit +
                ", smsCoast=" + smsCoast +
                ", rateName='" + rateName + '\'' +
                '}';
    }
}
