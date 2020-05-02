package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tracks")
public class TrackResource {

    private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAllExceptFromPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") Integer playlistID){

        if(playlistID == null){
            return Response.status(200).entity(trackService.getAllTracks()).build();
        }else{
            return Response.status(200).entity(trackService.getAllTracksNotInCurrentPlaylist(playlistID)).build();
        }

    }


}
