package nl.han.oose.lindt.maarten.datasource.translators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TrackArrayTranslatorTest {
    private TrackArrayTranslator sut;
    private ResultSet resultSet;
    @BeforeEach
    void setUp() {
        sut = new TrackArrayTranslator();
        resultSet = mock(ResultSet.class);

    }

    @Test
    void testResultSetToDTOThrowsError()  {
        // Setup


        // Run the test
        var result = sut.resultSetToDTO(resultSet);
        // Verify the results
        assertEquals(ArrayList.class, result.getClass());
    }
}