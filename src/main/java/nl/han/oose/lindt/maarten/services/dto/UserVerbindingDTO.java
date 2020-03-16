package nl.han.oose.lindt.maarten.services.dto;

import javax.xml.registry.infomodel.User;

public class UserVerbindingDTO {


    public String name;
    public String token;

    public UserVerbindingDTO(){}

    public UserVerbindingDTO(String name, String token){
        this.name = name;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public String getName() {
        return name;
    }


}
