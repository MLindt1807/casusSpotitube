package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.PlaylistService;
import nl.han.oose.lindt.maarten.services.TrackService;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistsDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Track;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class playlistResourceTest {

    playlistResource sut;
    PlaylistService playlistService;
    TrackService trackService;
    String tokenString = "1234-1234";

    @BeforeEach
    void setUp() {
        sut = new playlistResource();

        this.playlistService = mock(PlaylistService.class);
        sut.setPlaylistService(playlistService);
        this.trackService = mock(TrackService.class);
        sut.setTrackService(trackService);
    }



    @Test
    void getAllPlaylistsCorrectStatusAndResult() {
//        arrange
        var playlists = createRandomPlaylistsDTO();
        when(playlistService.getAll()).thenReturn(playlists);


//        act
        var result =  sut.getAllPlaylists(tokenString);

//        assert
        assertEquals(HTTP_OK, result.getStatus());
        assertEquals(playlists, result.getEntity());
    }

    @Test
    void deletePlaylistDeleteIsCalledAndStatusOK() {
        //        arrange

//        act

        var result = sut.deletePlaylist(tokenString,1);

//        assert
        verify(playlistService, times(1)).deletePlaylist(1);
        assertEquals(HTTP_OK, result.getStatus());
    }

    @Test
    void addPlaylistIsCalledAndStatusGoed() {
        //        arrange
        PlaylistDTO playlist = createPlaylistDTO();
//        act

        var result = sut.addPlaylist(tokenString,playlist);

//        assert
        verify(playlistService, times(1)).addPlaylist(playlist);
        assertEquals(HTTP_CREATED, result.getStatus());
    }

    @Test
    void editPlaylist() {
        //        arrange
        PlaylistDTO playlist = createPlaylistDTO();
        int id = 2;
//        act

        var result = sut.editPlaylist(tokenString,id,playlist);

//        assert
        verify(playlistService, times(1)).replacePlaylist(id,playlist);
        assertEquals(HTTP_OK, result.getStatus());
    }

    @Test
    void getAllOfPlaylistGeeftGoedeTerugEnStatusOKE() {
        //        arrange
        int id = 1;
        var tracks = createRandomTracksDTO();
        when(playlistService.getAllTracksOfPlaylist(id)).thenReturn(tracks);


//        act
        var result =  sut.GetAllOfPlaylist(tokenString, id);

//        assert
        assertEquals(HTTP_OK, result.getStatus());
        assertEquals(tracks, result.getEntity());
    }





    @Test
    void addTrackToPlaylist() {
        //        arrange

        int id = 2;
        var track = createRandomTrack();
//        act

        var result = sut.addTrackToPlaylist(tokenString,id,track);

//        assert
        verify(playlistService, times(1)).addTrack(id,track);
        verify(trackService, times(1)).checkTrack(track, id);
        assertEquals(HTTP_CREATED, result.getStatus());
    }




    @Test
    void deleteTrackFromPlaylist() {
        //        arrange

        int playlistID = 2;
        var track = createRandomTrack();
//        act

        var result = sut.deleteTrackFromPlaylist(tokenString,playlistID,track.getId());

//        assert
        verify(playlistService, times(1)).deleteTrackFromPlaylist(playlistID,track.getId());
        assertEquals(HTTP_OK, result.getStatus());
    }

    private PlaylistsDTO createRandomPlaylistsDTO(){
        List<PlaylistDTO> playlistsArray  = new ArrayList<PlaylistDTO>();
        playlistsArray.add(createPlaylistDTO());
        PlaylistsDTO playlists = new PlaylistsDTO(playlistsArray);

        return playlists;
    }

    private PlaylistDTO createPlaylistDTO(){
        return new PlaylistDTO(1, "Death metal", true, new ArrayList<TrackDTO>());
    }

    private TracksDTO createRandomTracksDTO() {
        List<TrackDTO> trackArray  = new ArrayList<>();
        trackArray.add(createRandomTrack());
        TracksDTO tracks = new TracksDTO(trackArray);

        return tracks;
    }

    private TrackDTO createRandomTrack(){
    return new TrackDTO(3,"Ocean and a rock","Lisa Hannigan", 337,"Sea sew", null,null,null,false);
    }
}
