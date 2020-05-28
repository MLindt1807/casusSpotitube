package nl.han.oose.lindt.maarten.datasource.translators;

import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginTranslatorTest {

    private LoginTranslator loginTranslatorUnderTest;

    @BeforeEach
    void setUp() {
        loginTranslatorUnderTest = new LoginTranslator();
    }

    @Test
    void testResultSetToLoginReturnsUserVerbindingDTO() {
        // Setup

        // Run the test
        final UserVerbindingDTO result = loginTranslatorUnderTest.resultSetToLogin("gebruiker", "verbindingToken");

        // Verify the results
        Assertions.assertEquals(UserVerbindingDTO.class, result.getClass());
    }
}
