package nl.han.oose.lindt.maarten.resources;

//import nl.han.oose.lindt.maarten.services.UserService;
import nl.han.oose.lindt.maarten.services.TrackService;
import nl.han.oose.lindt.maarten.services.datasourceBasedLoginService;
import nl.han.oose.lindt.maarten.services.dto.*;
import nl.han.oose.lindt.maarten.services.PlaylistService;


import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;


@Path("/login")
public class loginResource {
    private datasourceBasedLoginService  loginService;

    @Inject
    public void setLoginService(datasourceBasedLoginService loginService) {
        this.loginService = loginService;
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        UserVerbindingDTO login = loginService.login(user);
//        UserVerbindingDTO login = new UserVerbindingDTO("hoi","token");
        return Response.status(200).entity(login).build();
    }













}
