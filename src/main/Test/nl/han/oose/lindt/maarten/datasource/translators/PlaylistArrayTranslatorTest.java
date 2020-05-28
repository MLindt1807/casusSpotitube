package nl.han.oose.lindt.maarten.datasource.translators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class PlaylistArrayTranslatorTest {

    private PlaylistArrayTranslator playlistArrayTranslatorUnderTest;
    private ResultSet resultSet;
    @BeforeEach
    void setUp() {
        playlistArrayTranslatorUnderTest = new PlaylistArrayTranslator();
        resultSet = mock(ResultSet.class);

    }

    @Test
    void testResultSetToDTOThrowsError()  {
        // Setup


        // Run the test
        var result = playlistArrayTranslatorUnderTest.resultSetToDTO(resultSet);
        // Verify the results
        assertEquals(ArrayList.class, result.getClass());
    }
}
