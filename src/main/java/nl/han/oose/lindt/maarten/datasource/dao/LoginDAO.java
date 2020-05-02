package nl.han.oose.lindt.maarten.datasource.dao;

import nl.han.oose.lindt.maarten.datasource.DatabaseConnection;
import nl.han.oose.lindt.maarten.datasource.translators.LoginTranslator;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class LoginDAO {
    private LoginTranslator LoginTranslator;
    private DatabaseConnection databaseConnection;
    private Connection connection;

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
        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement("SELECT count(*) as aantal FROM gebruiker where gebruikersnaam = ? and wachtwoord = ?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            var result = preparedStatement.executeQuery();
            System.out.println("heir kom ik");
            int aantal = 0;
            while(result.next()){
                aantal = result.getInt("aantal");
            }


            if(aantal == 1) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE gebruiker SET verbindingtoken = ? where gebruikersnaam = ?;");
                preparedStatement2.setString(1, UUID.randomUUID().toString());
                preparedStatement2.setString(2, user);
                preparedStatement2.executeUpdate();
                PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT gebruikersnaam, verbindingtoken FROM gebruiker where gebruikersnaam = ?");
                preparedStatement3.setString(1, user);
                var finalResult = preparedStatement3.executeQuery();
                String gebruiker  ="";
                String token = "";

                while(finalResult.next()){
                    System.out.println("k doe dingen" );
                    gebruiker = finalResult.getString("gebruikersnaam");
                    token = finalResult.getString("verbindingtoken");
                }
                return LoginTranslator.resultSetToLogin(gebruiker,token);
            }

            throw new FailedQueryException();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new FailedQueryException();
        }
    }


}
