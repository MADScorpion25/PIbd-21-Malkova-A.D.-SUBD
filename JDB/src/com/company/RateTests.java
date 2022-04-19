package com.company;

import com.company.interfaces.RateDao;
import com.company.models.Rate;
import com.company.tableopers.RateSqlDao;

import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

public class RateTests {
    private static final Logger LOGGER =
            Logger.getLogger(AbonentTests.class.getName());
    private static final RateDao<Rate, Integer> RATE_DAO = new RateSqlDao();

    public static void main(String[] args) {
        //Rate rate = new Rate(2,3,200,60,40,200,4,"Base");
        //addRate(rate).ifPresent(rate::setId);
        getAllRates().forEach(System.out::println);
    }

    // Static helper methods referenced above
    public static Rate getRate(String email) {
        Optional<Rate> rate = RATE_DAO.get(email);
        return rate.get();
    }

    public static Collection<Rate> getAllRates() {
        return RATE_DAO.getAll();
    }

    public static void updateRate(Rate rate) {
        RATE_DAO.update(rate);
    }

    public static Optional<Integer> addRate(Rate rate) {
        return RATE_DAO.save(rate);
    }

    public static void deleteRate(String email) {
        RATE_DAO.delete(email);
    }
}
