package nl.han.oose.lindt.maarten.services.dto;

public class UserDTO {



    private String user;
    private String password;

    public UserDTO(){
    }

    public UserDTO(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
