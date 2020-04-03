package nl.han.oose.lindt.maarten.datasource.vertaler;

import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;

public class loginVertaler {
    public loginVertaler(){

    }


    public UserVerbindingDTO resultSetToLogin(String gebruiker, String verbindingToken) {
        return new UserVerbindingDTO(gebruiker,verbindingToken);
    }
}
