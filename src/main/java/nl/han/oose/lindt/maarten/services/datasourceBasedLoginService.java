package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.Mappers.LoginMapper;
import nl.han.oose.lindt.maarten.services.dto.UserDTO;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;

import javax.inject.Inject;

public class datasourceBasedLoginService {
    LoginMapper loginMapper;

    public datasourceBasedLoginService() {
    }

    @Inject
    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public UserVerbindingDTO login(UserDTO user) {
        return loginMapper.login(user);
    }
}
