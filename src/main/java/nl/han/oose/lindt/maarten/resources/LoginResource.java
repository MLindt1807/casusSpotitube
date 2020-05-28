package nl.han.oose.lindt.maarten.resources;

//import nl.han.oose.lindt.maarten.services.UserService;
import nl.han.oose.lindt.maarten.services.DatasourceBasedLoginService;
import nl.han.oose.lindt.maarten.services.dto.*;


import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/login")
public class LoginResource {
    private DatasourceBasedLoginService loginService;

    @Inject
    public void setLoginService(DatasourceBasedLoginService loginService) {
        this.loginService = loginService;
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        UserVerbindingDTO login = loginService.login(user);
        return Response.status(200).entity(login).build();
    }













}
