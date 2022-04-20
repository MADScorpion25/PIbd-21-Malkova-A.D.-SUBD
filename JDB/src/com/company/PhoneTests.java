package com.company;

import com.company.interfaces.PhoneDao;
import com.company.models.PhoneNumber;
import com.company.tableopers.PhoneSqlDao;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

public class PhoneTests {
    private static final Logger LOGGER =
            Logger.getLogger(PhoneTests.class.getName());
    private static final PhoneDao<PhoneNumber, Integer> PHONE_DAO = new PhoneSqlDao();

    public static void main(String[] args) {
//        PhoneNumber phone = new PhoneNumber(2,3,1,"Yes", "5630", Date.valueOf("2018-01-26"));
//        addPhone(phone).ifPresent(phone::setId);
//        getAllPhones().forEach(System.out::println);
//        getPhoneByDate(Date.valueOf("2019-01-01")).forEach(System.out::println);
        PhoneNumber fphone = new PhoneNumber(2,1026,1,"Yes", "5630", Date.valueOf("2018-01-26"));
        PhoneNumber sphone = new PhoneNumber(2,1026,1,"Yes", "5648", Date.valueOf("2019-01-26"));
        addPhone(fphone).ifPresent(fphone::setId);
        addPhone(sphone).ifPresent(sphone::setId);
        System.out.println("--------------------------");
        getAllPhones().forEach(System.out::println);
        fphone.setPhone("7777");
        updatePhone(fphone);
        System.out.println("--------------------------");
        System.out.println(getPhone("7777"));
        System.out.println("--------------------------");
        getPhoneByDate(Date.valueOf("2019-01-01")).forEach(System.out::println);
        deletePhone("7777");
        deletePhone("5648");
    }

    // Static helper methods referenced above
    public static PhoneNumber getPhone(String phone) {
        Optional<PhoneNumber> phoneNum = PHONE_DAO.get(phone);
        return phoneNum.get();
    }

    public static Collection<PhoneNumber> getAllPhones() {
        return PHONE_DAO.getAll();
    }

    public static void updatePhone(PhoneNumber phone) {
        PHONE_DAO.update(phone);
    }

    public static Optional<Integer> addPhone(PhoneNumber phone) {
        return PHONE_DAO.save(phone);
    }

    public static void deletePhone(String phone) {
        PHONE_DAO.delete(phone);
    }
    public static Collection<PhoneNumber> getPhoneByDate(Date date){
        return PHONE_DAO.getByDate(date);
    }
}
