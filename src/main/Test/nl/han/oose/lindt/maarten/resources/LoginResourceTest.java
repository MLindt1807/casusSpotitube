package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.DatasourceBasedLoginService;
import nl.han.oose.lindt.maarten.services.dto.UserDTO;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginResourceTest {

    private LoginResource sut;
    private DatasourceBasedLoginService loginService;
    private UserVerbindingDTO userVerbindingDTO;

    @BeforeEach
    void setUp() {
        sut = new LoginResource();

        loginService = mock(DatasourceBasedLoginService.class);
        userVerbindingDTO = mock(UserVerbindingDTO.class);

        sut.setLoginService(loginService);
    }

    @Test
    void loginReturnsStatuscode200() {

        //arrange
        when(loginService.login(any())).thenReturn(userVerbindingDTO);

        //act
        var result = sut.login(new UserDTO());
        //assert
        assertEquals(200, result.getStatus());
    }

    @Test
    void loginReturnscorrectBody() {

        //arrange
        when(loginService.login(any())).thenReturn(userVerbindingDTO);
        //act
        var result = sut.login(new UserDTO());
        //assert
        assertEquals(userVerbindingDTO, result.getEntity());
    }
}