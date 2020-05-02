package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.mappers.LoginMapper;
import nl.han.oose.lindt.maarten.services.dto.UserDTO;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;

import javax.inject.Inject;

public class DatasourceBasedLoginService {
    LoginMapper loginMapper;

    public DatasourceBasedLoginService() {
    }

    @Inject
    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public UserVerbindingDTO login(UserDTO user) {
        return loginMapper.login(user);
    }
}
