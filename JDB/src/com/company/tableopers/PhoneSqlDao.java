package com.company.tableopers;

import com.company.config.JdbcConnection;
import com.company.interfaces.PhoneDao;
import com.company.models.Abonent;
import com.company.models.PhoneNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhoneSqlDao implements PhoneDao<PhoneNumber, Integer> {
    private static final Logger LOGGER =
            Logger.getLogger(AbonentSqlDao.class.getName());
    private final Optional<Connection> connection;

    public PhoneSqlDao() {
        this.connection = JdbcConnection.getConnection();
    }
    @Override
    public Optional<PhoneNumber> get(String phone) {
        return connection.flatMap(conn -> {
            Optional<PhoneNumber> phoneNumber = Optional.empty();
            String sql = "SELECT * FROM phoneNumber WHERE phone = \'" + phone + "\'";

            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int phoneId = resultSet.getInt("phoneid");
                    int abonentId = resultSet.getInt("abonentid");
                    int rateId = resultSet.getInt("rateid");
                    String hasBenefit = resultSet.getString("hasbenefit");
                    Date buyDate = resultSet.getDate("buydate");

                    phoneNumber = Optional.of(
                            new PhoneNumber(phoneId, abonentId, rateId, hasBenefit, phone, buyDate));

                    LOGGER.log(Level.INFO, "Found {0} in database", phoneNumber.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return phoneNumber;
        });
    }

    @Override
    public Collection<PhoneNumber> getAll() {
        Collection<PhoneNumber> phones = new ArrayList<>();
        String sql = "SELECT * FROM phonenumber";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int phoneId = resultSet.getInt("phoneid");
                    int abonentId = resultSet.getInt("abonentid");
                    int rateId = resultSet.getInt("rateid");
                    String hasBenefit = resultSet.getString("hasbenefit");
                    String phone = resultSet.getString("phone");
                    Date buyDate = resultSet.getDate("buydate");

                    PhoneNumber phoneNumber = new PhoneNumber(phoneId, abonentId, rateId, hasBenefit, phone, buyDate);

                    phones.add(phoneNumber);

                    LOGGER.log(Level.INFO, "Found {0} in database", phoneNumber );
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return phones;
    }

    @Override
    public Optional<Integer> save(PhoneNumber phoneNumber) {
        String message = "The phonenumber to be added should not be null";
        PhoneNumber notNullPhone = Objects.requireNonNull(phoneNumber, message);
        String sql = "INSERT INTO "
                + "phonenumber(abonentid, rateid, hasbenefit, phone, buydate) "
                + "VALUES(?, ?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, notNullPhone.getAbonentId());
                statement.setInt(2, notNullPhone.getRateId());
                statement.setString(3, notNullPhone.getHasBenefit());
                statement.setString(4, notNullPhone.getPhone());
                statement.setDate(5, notNullPhone.getBuyDate());

                int numberOfInsertedRows = statement.executeUpdate();

                // Retrieve the auto-generated id
                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = Optional.of(resultSet.getInt(1));
                        }
                    }
                }

                LOGGER.log(
                        Level.INFO,
                        "{0} created successfully? {1}",
                        new Object[]{notNullPhone,
                                (numberOfInsertedRows > 0)});
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        String message = "The phonenumber to be updated should not be null";
        PhoneNumber notNullPhone = Objects.requireNonNull(phoneNumber, message);
        String sql = "UPDATE phonenumber "
                + "SET "
                + "abonentid = ?, "
                + "rateid = ?, "
                + "hasbenefit = ?, "
                + "phone = ?, "
                + "buydate = ? "
                + "WHERE "
                + "phoneid = ?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, notNullPhone.getAbonentId());
                statement.setInt(2, notNullPhone.getRateId());
                statement.setString(3, notNullPhone.getHasBenefit());
                statement.setString(4, notNullPhone.getPhone());
                statement.setDate(5, notNullPhone.getBuyDate());
                statement.setInt(6, notNullPhone.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the phone updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(String phone) {
        String message = "The phone to be deleted should not be null";
        String sql = "DELETE FROM phonenumber WHERE phone = \'" + phone + "\'";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the phone deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public Collection<PhoneNumber> getByDate(Date filter) {
        Collection<PhoneNumber> phones = new ArrayList<>();
        String sql = "SELECT * FROM phonenumber WHERE buydate > \'" + filter +"\'";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int phoneId = resultSet.getInt("phoneid");
                    int abonentId = resultSet.getInt("abonentid");
                    int rateId = resultSet.getInt("rateid");
                    String hasBenefit = resultSet.getString("hasbenefit");
                    String phone = resultSet.getString("phone");
                    Date buyDate = resultSet.getDate("buydate");

                    PhoneNumber phoneNumber = new PhoneNumber(phoneId, abonentId, rateId, hasBenefit, phone, buyDate);

                    phones.add(phoneNumber);

                    LOGGER.log(Level.INFO, "Found {0} in database", phoneNumber );
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return phones;
    }
}
