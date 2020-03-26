package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.dto.UserDTO;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class loginResourceTest {

    private loginResource sut;

    @BeforeEach
    void setup() {
        this.sut = new loginResource();
    }

    @Test
    void loginReturnsUser() {
        // Arrange
        var userDTO = new UserDTO("user","wachtwoord");
        var userVerbinding = new UserVerbindingDTO(userDTO.getUser(), "random text");
        // Act
        var response = sut.login(userDTO);

        // Assert

        assertEquals(HTTP_OK, response.getStatus());
        //todo check user
    }
}
