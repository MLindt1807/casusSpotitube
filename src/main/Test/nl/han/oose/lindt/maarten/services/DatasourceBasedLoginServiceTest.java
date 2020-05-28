package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.dao.LoginDAO;
import nl.han.oose.lindt.maarten.services.dto.UserDTO;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.registry.infomodel.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DatasourceBasedLoginServiceTest {

    private DatasourceBasedLoginService sut;
    private LoginDAO loginDAO;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        sut = new DatasourceBasedLoginService();

        loginDAO = mock(LoginDAO.class);
        userDTO = mock(UserDTO.class);

        sut.setLoginDAO(loginDAO);


    }


    @Test
    void loginReturns_UserVerbindingDTO() {
        //arrange
        when(loginDAO.login(any(),any())).thenReturn(new UserVerbindingDTO());
        when(userDTO.getUser()).thenReturn("");
        when(userDTO.getPassword()).thenReturn("");

        //act
        var result = sut.login(userDTO);

        //assert
        assertEquals(UserVerbindingDTO.class , result.getClass());
    }
}