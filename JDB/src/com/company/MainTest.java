package com.company;

import com.company.interfaces.AbonentDao;
import com.company.models.Abonent;
import com.company.tableopers.AbonentSqlDao;
import jdk.nashorn.api.scripting.AbstractJSObject;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainTest {
    private static final Logger LOGGER =
            Logger.getLogger(AbonentTests.class.getName());
    private static final AbonentDao<Abonent, Integer> ABONENT_DAO = new AbonentSqlDao();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("1.Create");
            System.out.println("2.Update");
            System.out.println("3.Delete");
            System.out.println("4.Read");
            System.out.println("5.Sort");
            System.out.println("6.Exit");
            int cmd = sc.nextInt();

            if(cmd == 1){
                Abonent abonent = new Abonent();
                sc.nextLine();
                System.out.println("First name:");
                abonent.setFirstName(sc.nextLine());
                System.out.println("Last name:");
                abonent.setLastName(sc.nextLine());
                System.out.println("Patronimyc:");
                abonent.setPatronimyc(sc.nextLine());
                System.out.println("Email:");
                abonent.setEmail(sc.nextLine());
                System.out.println("Birth date:");
                abonent.setBirthDate(Date.valueOf(sc.nextLine()));
                addAbonent(abonent).ifPresent(abonent::setId);
            }
            else if(cmd == 2){
                System.out.println("Enter email: ");
                Abonent abonent;
                sc.nextLine();
                abonent = getAbonent(sc.nextLine());
                System.out.println("Enter change column:");
                String cmnd = sc.nextLine();
                if(cmnd.equals("email")){
                    System.out.println("Enter changes:");
                    abonent.setEmail(sc.nextLine());
                }
                else if(cmnd.equals("firstname")){
                    System.out.println("Enter changes:");
                    abonent.setFirstName(sc.nextLine());
                }
                else if(cmnd.equals("lastname")){
                    System.out.println("Enter changes:");
                    abonent.setLastName(sc.nextLine());
                }
                else if(cmnd.equals("birthdate")){
                    System.out.println("Enter changes:");
                    abonent.setBirthDate(Date.valueOf(sc.nextLine()));
                }
                updateAbonent(abonent);
            }
            else if(cmd == 3){
                sc.nextLine();
                System.out.println("Enter email: ");
                deleteAbonent(sc.nextLine());
            }
            else if(cmd == 4){
                getAllAbonents().forEach(System.out::println);
            }
            else if(cmd == 5){
                getAllAbonentsSorted().forEach(System.out::println);
            }
            else if(cmd == 6){
                break;
            }
            else{
                System.out.println("Command not found.");
            }
        }
    }
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
