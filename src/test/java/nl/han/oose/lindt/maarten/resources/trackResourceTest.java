package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.PlaylistService;
import nl.han.oose.lindt.maarten.services.TrackService;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Track;

import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class trackResourceTest {
    TrackService trackService;
    trackResource sut;
    final String tokenString = "1234-1234";

    @BeforeEach
    void setUp() {
        sut = new trackResource();

        this.trackService = mock(TrackService.class);
        sut.setTrackService(trackService);
    }



    @Test
    void getAllExceptFromPlaylistWithNoPlaylistID() {
//        arrange

        var tracks = createRandomTracksDTO();
        when(trackService.getAllTracks()).thenReturn(tracks);


//        act
        var result =  sut.GetAllExceptFromPlaylist(tokenString, null);

//        assert
        assertEquals(HTTP_OK, result.getStatus());
        assertEquals(tracks, result.getEntity());
    }

    @Test
    void getAllExceptFromPlaylistWithPlaylistID() {
//        arrange
        int id = 1;
        var tracks = createRandomTracksDTO();
        when(trackService.getAllTracksNotInCurrentPlaylist(1)).thenReturn(tracks);


//        act
        var result =  sut.GetAllExceptFromPlaylist(tokenString, 1);

//        assert
        assertEquals(HTTP_OK, result.getStatus());
        assertEquals(tracks, result.getEntity());
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
