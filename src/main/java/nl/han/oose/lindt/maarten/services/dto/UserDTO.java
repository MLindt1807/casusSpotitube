package nl.han.oose.lindt.maarten.services.dto;

public class UserDTO {
    private String user;
    private String password;

    public UserDTO(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return user;
    }
}
