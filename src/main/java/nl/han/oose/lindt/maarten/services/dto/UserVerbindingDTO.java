package nl.han.oose.lindt.maarten.services.dto;

import javax.xml.registry.infomodel.User;

public class UserVerbindingDTO {

    public String token;
    public String user;


    public UserVerbindingDTO(){}

    public UserVerbindingDTO(String user, String token){
        this.token = token;
        this.user = user;

    }

    public String getToken() {
        return token;
    }
    public String getUser() {
        return user;
    }


}
