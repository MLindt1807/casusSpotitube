package nl.han.oose.lindt.maarten.datasource.translators;

import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;

public class LoginTranslator {
    public LoginTranslator(){

    }



    public UserVerbindingDTO resultSetToLogin(String gebruiker, String verbindingToken) {
        return new UserVerbindingDTO(gebruiker,verbindingToken);
    }
}
