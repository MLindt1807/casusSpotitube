package nl.han.oose.lindt.maarten.resources;

//import nl.han.oose.lindt.maarten.services.UserService;
import nl.han.oose.lindt.maarten.services.TrackService;
import nl.han.oose.lindt.maarten.services.dto.*;
import nl.han.oose.lindt.maarten.services.PlaylistService;


import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/login")
public class loginResource {
    //private UserService userService;

//    @Inject
//    public void setItemService(UserService userService) {
//        this.userService = userService;
//    }




//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response test(){
//        var object = new UserDTO("user1", "userpw");
//        return Response.ok().entity(object).build();
//    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        var verbinding = new UserVerbindingDTO( user.getUser(), "1234-1234-1234");
        return Response.status(200).entity(verbinding).build();
    }













}
