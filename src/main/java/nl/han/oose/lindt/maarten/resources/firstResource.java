package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.UserService;
import nl.han.oose.lindt.maarten.services.dto.UserDTO;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/spotitube")
public class firstResource {
    //private UserService userService;

//    @Inject
//    public void setItemService(UserService userService) {
//        this.userService = userService;
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(){
        return Response.ok().entity("hoi").build();
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        //var verbinding = new UserVerbindingDTO(user, "1234-1234-1234");
        return Response.ok().build();
    }


}
