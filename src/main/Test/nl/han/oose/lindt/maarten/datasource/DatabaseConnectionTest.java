package nl.han.oose.lindt.maarten.datasource;

import nl.han.oose.lindt.maarten.datasource.dao.FailedConnectionException;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DatabaseConnectionTest {

    private DatabaseConnection sut;
    private DatabaseProperties databaseProperties;
    @BeforeEach
    void setUp() {
        sut = new DatabaseConnection();
        databaseProperties = mock(DatabaseProperties.class);
        sut.setDatabaseProperties(databaseProperties);
    }

    @Test
    void testGetConnectionThrowsError() {
        // Setup
        when(databaseProperties.getConnectionString()).thenReturn("geen connectionString");
        // Run the test
        assertThrows(FailedConnectionException.class, () -> sut.getConnection());

        // Verify the results
    }

}
