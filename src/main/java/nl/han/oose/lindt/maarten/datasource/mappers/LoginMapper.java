package nl.han.oose.lindt.maarten.datasource.mappers;

import nl.han.oose.lindt.maarten.datasource.dao.LoginDAO;
import nl.han.oose.lindt.maarten.services.dto.UserDTO;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;

import javax.inject.Inject;

public class LoginMapper {
    LoginDAO loginDAO;

    public LoginMapper() {
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public UserVerbindingDTO login(UserDTO user) {
        return loginDAO.login(user.getUser(), user.getPassword());
    }
}
