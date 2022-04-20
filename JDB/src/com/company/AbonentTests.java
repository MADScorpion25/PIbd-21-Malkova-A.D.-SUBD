package com.company;

import com.company.interfaces.AbonentDao;
import com.company.models.Abonent;
import com.company.tableopers.AbonentSqlDao;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

public class AbonentTests {

    private static final Logger LOGGER =
            Logger.getLogger(AbonentTests.class.getName());
    private static final AbonentDao<Abonent, Integer> ABONENT_DAO = new AbonentSqlDao();

    public static void main(String[] args) {
//        for(int i = 0; i < 1000; i++){
//            Abonent abonent = new Abonent(Character.getName(i), Character.getName(i++), "", i +"@jourrapide.com", Date.valueOf("2002-01-01"));
//            addAbonent(abonent).ifPresent(abonent::setId);
//        }
        Abonent fabonent = new Abonent("John", "Pharell", "", "JohnPharell@jourrapide.com", Date.valueOf("2001-05-01"));
        Abonent sabonent = new Abonent("Jane", "Ostin", "", "JaneOstin@jourrapide.com", Date.valueOf("2005-05-11"));
        addAbonent(fabonent).ifPresent(fabonent::setId);
        addAbonent(sabonent).ifPresent(sabonent::setId);
        System.out.println("--------------------------");
        getAllAbonents().forEach(System.out::println);
        fabonent.setFirstName("Sally");
        updateAbonent(fabonent);
        System.out.println("--------------------------");
        System.out.println(getAbonent("JohnPharell@jourrapide.com"));
        System.out.println("--------------------------");
        getAllAbonentsByMask("Ja%").forEach(System.out::println);
        System.out.println("--------------------------");
        //deleteAbonent("JohnPharell@jourrapide.com");
        deleteAbonent("JaneOstin@jourrapide.com");
    }

    // Static helper methods referenced above
    public static Abonent getAbonent(String email) {
        Optional<Abonent> abonent = ABONENT_DAO.get(email);
        return abonent.get();
    }

    public static Collection<Abonent> getAllAbonents() {
        return ABONENT_DAO.getAll();
    }

    public static void updateAbonent(Abonent customer) {
        ABONENT_DAO.update(customer);
    }

    public static Optional<Integer> addAbonent(Abonent customer) {
        return ABONENT_DAO.save(customer);
    }

    public static void deleteAbonent(String email) {
        ABONENT_DAO.delete(email);
    }
    public static Collection<Abonent> getAllAbonentsByMask(String mask) {
        return ABONENT_DAO.getByMask(mask);
    }
    public static Collection<Abonent> getAllAbonentsSorted() {
        return ABONENT_DAO.getSorted();
    }
}
