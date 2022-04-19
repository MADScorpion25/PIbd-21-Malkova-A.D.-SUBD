package com.company.tableopers;

import com.company.config.JdbcConnection;
import com.company.interfaces.AbonentDao;
import com.company.models.Abonent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbonentSqlDao implements AbonentDao<Abonent, Integer> {

    private static final Logger LOGGER =
            Logger.getLogger(AbonentSqlDao.class.getName());
    private final Optional<Connection> connection;

    public AbonentSqlDao() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Abonent> get(String email) {
        return connection.flatMap(conn -> {
            Optional<Abonent> abonent = Optional.empty();
            String sql = "SELECT * FROM abonent WHERE email = \'" + email + "\'";

            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int id = resultSet.getInt("abonentid");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    String patronimyc = resultSet.getString("patronimyc");
                    Date birthDate = resultSet.getDate("birthdate");

                    abonent = Optional.of(
                            new Abonent(id, firstName, lastName, patronimyc, email, birthDate));

                    LOGGER.log(Level.INFO, "Found {0} in database", abonent.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return abonent;
        });
    }

    @Override
    public Collection<Abonent> getAll() {
        Collection<Abonent> abonents = new ArrayList<>();
        String sql = "SELECT * FROM abonent";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("abonentid");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    String patronimyc = resultSet.getString("patronimyc");
                    String email = resultSet.getString("email");
                    Date birthDate = resultSet.getDate("birthdate");

                    Abonent abonent = new Abonent(id, firstName, lastName, patronimyc, email, birthDate);

                    abonents.add(abonent);

                    //LOGGER.log(Level.INFO, "Found {0} in database", abonent);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return abonents;
    }

    public Optional<Integer> save(Abonent abonent) {
        String message = "The abonent to be added should not be null";
        Abonent notNullAbonent = Objects.requireNonNull(abonent, message);
        String sql = "INSERT INTO "
                + "abonent(firstname, lastname, patronimyc, email, birthdate) "
                + "VALUES(?, ?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, notNullAbonent.getFirstName());
                statement.setString(2, notNullAbonent.getLastName());
                statement.setString(3, notNullAbonent.getPatronimyc());
                statement.setString(4, notNullAbonent.getEmail());
                statement.setDate(5, notNullAbonent.getBirthDate());

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
                        new Object[]{notNullAbonent,
                                (numberOfInsertedRows > 0)});
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(Abonent abonent) {
        String message = "The abonent to be updated should not be null";
        Abonent notNullAbonent = Objects.requireNonNull(abonent, message);
        String sql = "UPDATE abonent "
                + "SET "
                + "firstname = ?, "
                + "lastname = ?, "
                + "patronimyc = ?, "
                + "email = ?, "
                + "birthdate = ? "
                + "WHERE "
                + "abonentid = ?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, notNullAbonent.getFirstName());
                statement.setString(2, notNullAbonent.getLastName());
                statement.setString(3, notNullAbonent.getPatronimyc());
                statement.setString(4, notNullAbonent.getEmail());
                statement.setDate(5, notNullAbonent.getBirthDate());
                statement.setInt(6, notNullAbonent.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the abonent updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(String email) {
        String message = "The abonent to be deleted should not be null";
        String sql = "DELETE FROM abonent WHERE email = \'" + email + "\'";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the abonent deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
    public Collection<Abonent> getByMask(String mask) {
        Collection<Abonent> abonents = new ArrayList<>();
        String sql = "SELECT * FROM abonent WHERE email LIKE \'" + mask + "\'";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("abonentid");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    String patronimyc = resultSet.getString("patronimyc");
                    String email = resultSet.getString("email");
                    Date birthDate = resultSet.getDate("birthdate");

                    Abonent abonent = new Abonent(id, firstName, lastName, patronimyc, email, birthDate);

                    abonents.add(abonent);

                    //LOGGER.log(Level.INFO, "Found {0} in database", abonent);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return abonents;
    }
    @Override
    public Collection<Abonent> getSorted() {
        Collection<Abonent> abonents = new ArrayList<>();
        String sql = "SELECT * FROM abonent ORDER BY lastname";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("abonentid");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    String patronimyc = resultSet.getString("patronimyc");
                    String email = resultSet.getString("email");
                    Date birthDate = resultSet.getDate("birthdate");

                    Abonent abonent = new Abonent(id, firstName, lastName, patronimyc, email, birthDate);

                    abonents.add(abonent);

                    //LOGGER.log(Level.INFO, "Found {0} in database", abonent);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return abonents;
    }
}
