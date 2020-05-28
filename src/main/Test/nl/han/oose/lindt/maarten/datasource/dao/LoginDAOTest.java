package nl.han.oose.lindt.maarten.datasource.dao;

import nl.han.oose.lindt.maarten.datasource.databaseExceptions.FailedQueryException;
import nl.han.oose.lindt.maarten.resources.LoginResource;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoginDAOTest {
    private LoginDAO sut;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    void setUp() {
        sut = new LoginDAO();

        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        sut.setConnection(connection);

    }

    @Test
    void test1ThrowsError() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(anyInt(),anyString());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);


        assertThrows(FailedQueryException.class, ()->sut.login("",""));
    }
}