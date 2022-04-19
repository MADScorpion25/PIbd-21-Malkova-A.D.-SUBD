package com.company.tableopers;

import com.company.config.JdbcConnection;
import com.company.interfaces.RateDao;
import com.company.models.*;
import com.company.models.Rate;
import com.company.models.Rate;
import com.company.models.Rate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RateSqlDao implements RateDao<Rate, Integer> {
    private static final Logger LOGGER =
            Logger.getLogger(RateSqlDao.class.getName());
    private final Optional<Connection> connection;

    public RateSqlDao() {
        this.connection = JdbcConnection.getConnection();
    }
    @Override
    public Optional<Rate> get(String name) {
        return connection.flatMap(conn -> {
            Optional<Rate> rate = Optional.empty();
            String sql = "SELECT * FROM rate WHERE ratename = \'" + name + "\'";

            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int id = resultSet.getInt("rateid");
                    int intercityRate = resultSet.getInt("intersityrate");
                    int baseRate = resultSet.getInt("baserate");
                    int rateRate = resultSet.getInt("abonentrate");
                    int intercityLimit = resultSet.getInt("intersitylimit");
                    int baseLimit = resultSet.getInt("baselimit");
                    int smsLimit = resultSet.getInt("smslimit");
                    int smsCoast = resultSet.getInt("smscoast");

                    rate = Optional.of(
                            new Rate(id, intercityRate, baseRate, rateRate, intercityLimit, baseLimit, smsLimit, smsCoast, name));

                    LOGGER.log(Level.INFO, "Found {0} in database", rate.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return rate;
        });
    }

    @Override
    public Collection<Rate> getAll() {
        Collection<Rate> rates = new ArrayList<>();
        String sql = "SELECT * FROM rate";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("rateid");
                    int intercityRate = resultSet.getInt("intersityrate");
                    int baseRate = resultSet.getInt("baserate");
                    int rateRate = resultSet.getInt("abonentrate");
                    int intercityLimit = resultSet.getInt("intersitylimit");
                    int baseLimit = resultSet.getInt("baselimit");
                    int smsLimit = resultSet.getInt("smslimit");
                    int smsCoast = resultSet.getInt("smscoast");
                    String name = resultSet.getString("ratename");

                    Rate rate = new Rate(id, intercityRate, baseRate, rateRate, intercityLimit, baseLimit, smsLimit, smsCoast, name);

                    rates.add(rate);

                    LOGGER.log(Level.INFO, "Found {0} in database", rate);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return rates;
    }

    @Override
    public Optional<Integer> save(Rate rate) {
        String message = "The rate to be added should not be null";
        Rate notNullRate = Objects.requireNonNull(rate, message);
        String sql = "INSERT INTO "
                + "rate(intersityrate, baserate, abonentrate, intersitylimit, baselimit, smslimit, smscoast, ratename) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, notNullRate.getIntercityRate());
                statement.setInt(2, notNullRate.getBaseRate());
                statement.setInt(3, notNullRate.getAbonentRate());
                statement.setInt(4, notNullRate.getIntercityLimit());
                statement.setInt(5, notNullRate.getBaseLimit());
                statement.setInt(6, notNullRate.getSmsLimit());
                statement.setInt(7, notNullRate.getSmsCoast());
                statement.setString(8, notNullRate.getRateName());

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
                        new Object[]{notNullRate,
                                (numberOfInsertedRows > 0)});
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(Rate rate) {
        String message = "The rate to be updated should not be null";
        Rate notNullRate = Objects.requireNonNull(rate, message);
        String sql = "UPDATE rate "
                + "SET "
                + "intersityrate = ?, "
                + "baserate = ?, "
                + "abonentrate = ?, "
                + "intersitylimit = ?, "
                + "baselimit = ? "
                + "smslimit = ? "
                + "smscoast = ? "
                + "ratename = ? "
                + "WHERE "
                + "rateid = ?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, notNullRate.getIntercityRate());
                statement.setInt(2, notNullRate.getBaseRate());
                statement.setInt(3, notNullRate.getAbonentRate());
                statement.setInt(4, notNullRate.getIntercityLimit());
                statement.setInt(5, notNullRate.getBaseLimit());
                statement.setInt(6, notNullRate.getSmsLimit());
                statement.setInt(7, notNullRate.getSmsCoast());
                statement.setString(8, notNullRate.getRateName());
                statement.setInt(9, notNullRate.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the rate updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(String name) {
        String message = "The rate to be deleted should not be null";
        String sql = "DELETE FROM rate WHERE ratename = \'" + name + "\'";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the rate deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
