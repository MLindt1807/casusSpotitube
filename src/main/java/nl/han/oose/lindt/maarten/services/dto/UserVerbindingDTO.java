package nl.han.oose.lindt.maarten.services.dto;

import javax.xml.registry.infomodel.User;

public class UserVerbindingDTO {
    public String user;
    public String token;

    public UserVerbindingDTO(UserDTO user, String token){
        this.user = user.getName();
        this.token = token;
    }


}
