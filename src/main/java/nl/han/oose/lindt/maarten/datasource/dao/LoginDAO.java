package nl.han.oose.lindt.maarten.datasource.dao;

import nl.han.oose.lindt.maarten.datasource.DatabaseConnection;
import nl.han.oose.lindt.maarten.datasource.databaseExceptions.FailedQueryException;
import nl.han.oose.lindt.maarten.datasource.translators.LoginTranslator;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotFoundException;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LoginDAO {
    private LoginTranslator LoginTranslator;
    private DatabaseConnection databaseConnection;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;
    private PreparedStatement preparedStatement3;

    public LoginDAO() {
    }

    @Inject
    public void setLoginTranslator(LoginTranslator loginTranslator) {
        this.LoginTranslator = loginTranslator;
    }

    @Inject
    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Inject
    public void setConnection() {
        this.connection = databaseConnection.getConnection();
    }

    public UserVerbindingDTO login(String user, String password) {

        try {

            int aantal = getAantal(user, password);


            if (aantal == 1) {
                setVerbindingToken(user);
                ResultSet finalResult = getFinalResultSet(user);

                String gebruiker = "";
                String token = "";

                while (finalResult.next()) {
                    gebruiker = finalResult.getString("gebruikersnaam");
                    token = finalResult.getString("verbindingtoken");
                }
                return LoginTranslator.resultSetToLogin(gebruiker, token);
            }

            throw new FailedQueryException();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new FailedQueryException();
        }
    }

    private ResultSet getFinalResultSet(String user) throws SQLException {
        preparedStatement3 = connection.prepareStatement("SELECT gebruikersnaam, verbindingtoken FROM gebruiker where gebruikersnaam = ?");
        preparedStatement3.setString(1, user);
        return preparedStatement3.executeQuery();
    }

    private void setVerbindingToken(String user) throws SQLException {
        preparedStatement2 = connection.prepareStatement("UPDATE gebruiker SET verbindingtoken = ? where gebruikersnaam = ?;");
        preparedStatement2.setString(1, UUID.randomUUID().toString());
        preparedStatement2.setString(2, user);
        preparedStatement2.executeUpdate();
    }

    private int getAantal(String user, String password) throws SQLException {

        preparedStatement = connection.prepareStatement("SELECT count(*) as aantal FROM gebruiker where gebruikersnaam = ? and wachtwoord = ?");
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, password);

        var result = preparedStatement.executeQuery();
        int aantal = 0;
        while (result.next()) {
            aantal = result.getInt("aantal");
        }
        return aantal;
    }

    public String getGebruikerFromToken(String token) {
        PreparedStatement preparedStatement;
        var gebruikersnaam = "";
        try {

            preparedStatement = connection.prepareStatement("SELECT gebruikersnaam FROM gebruiker where verbindingtoken = ?");
            preparedStatement.setString(1, token);

            var result = preparedStatement.executeQuery();

            if (result.next()) {
                gebruikersnaam = result.getString("gebruikersnaam");
            }


        } catch (SQLException e) {
            throw new NotFoundException();
        }
        return gebruikersnaam;
    }
}