package nl.han.oose.lindt.maarten.datasource;
import nl.han.oose.lindt.maarten.datasource.DAO.FailedConnectionException;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private DatabaseProperties databaseProperties;

    public DatabaseConnection(){

    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties){
        this.databaseProperties = databaseProperties;
    }

    public Connection getConnection() {
        try {

            return DriverManager.getConnection(databaseProperties.getConnectionString());
        } catch (SQLException e ) {
            System.out.println(e);
            throw new FailedConnectionException();
        }
    }
}
