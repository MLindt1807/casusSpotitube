package nl.han.oose.lindt.maarten.resources;

//import nl.han.oose.lindt.maarten.services.UserService;
import nl.han.oose.lindt.maarten.services.TrackService;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.UserDTO;
import nl.han.oose.lindt.maarten.services.dto.UserVerbindingDTO;
import nl.han.oose.lindt.maarten.services.PlaylistService;


import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/spotitube")
public class firstResource {
    //private UserService userService;

//    @Inject
//    public void setItemService(UserService userService) {
//        this.userService = userService;
//    }



    private PlaylistService playlistService;

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }



    private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(){
        var object = new UserDTO("user1", "userpw");
        return Response.ok().entity(object).build();
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        var verbinding = new UserVerbindingDTO( user.getUser(), "1234-1234-1234");
        return Response.status(200).entity(verbinding).build();
    }

    @Path("/playlists/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token){

        return Response.status(200).entity(playlistService.getAll()).build();
    }

    @Path("/playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        playlistService.deletePlaylist(id);
        return Response.status(200).entity(playlistService.getAll()).build();
    }

    @Path("/playlists/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlist){
        playlistService.addPlaylist(playlist);
        return Response.status(200).entity(playlistService.getAll()).build();
    }


    @Path("/playlists/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlist){
        playlistService.replacePlaylist(id,playlist);
        return Response.status(200).entity(playlistService.getAll()).build();
    }

    @Path("/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAllExceptFromPlaylist(@QueryParam("token") String token){
        return Response.status(200).entity(trackService.getAll()).build();
    }

//TODO aanpassen naar tracks except playlist.

//    @Path("/tracks")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response GetAllExceptFromPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int idOfPlaylist){
//       // List<Integer> tracksInPlaylist = playlistService.getAllTrackIDOfPlaylist(idOfPlaylist);
//        return Response.status(200).entity(trackService.getAll()).build();
//    }

    @Path("/playlists/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAllOfPlaylist(@QueryParam("token") String token, @PathParam("id") int idOfPlaylist){
        List<TrackDTO> tracksInPlaylist = playlistService.getAllTracksOfPlaylist(idOfPlaylist);
        return Response.status(200).entity(tracksInPlaylist).build();
    }

    @Path("/playlists/{id}/tracks")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int idOfPlaylist, TrackDTO track){
        boolean trackOKE = trackService.checkTrack(track);

        if(trackOKE) {
            playlistService.addTrack(idOfPlaylist, track);
        }
        return Response.status(201).entity(playlistService.getAllTracksOfPlaylist(idOfPlaylist)).build();
    }
//    @Path("/playlists/{id}/tracks")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response AddTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int idOfPlaylist, List<TrackDTO> tracks){
//
//        return Response.status(200).entity(tracksInPlaylist).build();
//    }

}
