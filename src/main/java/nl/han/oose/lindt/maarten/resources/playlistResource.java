package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.PlaylistService;
import nl.han.oose.lindt.maarten.services.TrackService;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists/")
public class playlistResource {
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
    public Response getAllPlaylists(@QueryParam("token") String token){

        return Response.status(200).entity(playlistService.getAll()).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        playlistService.deletePlaylist(id);
        return Response.status(200).entity(playlistService.getAll()).build();
    }

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlist){
        playlistService.addPlaylist(playlist);
        return Response.status(200).entity(playlistService.getAll()).build();
    }


    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlist){
        playlistService.replacePlaylist(id,playlist);
        return Response.status(200).entity(playlistService.getAll()).build();
    }

    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAllOfPlaylist(@QueryParam("token") String token, @PathParam("id") int idOfPlaylist){
        TracksDTO tracksInPlaylist = playlistService.getAllTracksOfPlaylist(idOfPlaylist);
        return Response.status(200).entity(tracksInPlaylist).build();
    }

    @Path("/{id}/tracks")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int idOfPlaylist, TrackDTO track){

        trackService.checkTrack(track, idOfPlaylist);

        playlistService.addTrack(idOfPlaylist, track);


        return Response.status(201).entity(playlistService.getAllTracksOfPlaylist(idOfPlaylist)).build();
    }

    @Path("/{playlistID}/tracks/{trackID}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token,
                                            @PathParam("playlistID") int playlistID,
                                            @PathParam("trackID") int trackID){

        playlistService.deleteTrackFromPlaylist(playlistID, trackID);

        return Response.status(200).entity(playlistService.getAllTracksOfPlaylist(playlistID)).build();
    }

}
