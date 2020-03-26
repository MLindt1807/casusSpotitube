package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.Mappers.PlaylistMapper;
import nl.han.oose.lindt.maarten.datasource.Mappers.TrackMapper;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TrackServiceTest {
    datasourceBasedTrackService sut;
    TrackMapper trackMapper;

    @BeforeEach
    void setUp() {
        this.sut = new datasourceBasedTrackService();

        this.trackMapper = mock(TrackMapper.class);

        sut.setTrackMapper(trackMapper);
    }

    @Test
    void getAllTracks() {
//        arrange

        var tracks = createRandomTracksDTO();
        when(trackMapper.getAll()).thenReturn(tracks);


//        act
        var result =  sut.getAllTracks();

//        assert

        assertEquals(tracks, result.getTracks());
    }

    @Test
    void getAllTracksNotInCurrentPlaylist() {
//        arrange
        int id = 1;
        var tracks = createRandomTracksDTO();
        when(trackMapper.getAllTracksNotInCurrentPlaylist(1)).thenReturn(tracks);


//        act
        var result =  sut.getAllTracksNotInCurrentPlaylist(id);

//        assert

        assertEquals(tracks, result.getTracks());
    }



    @Test
    void checkTrack() {
        //        arrange

        int id = 2;
        var track = createRandomTrack();
//        act

        sut.checkTrack(track,id);

//        assert

        verify(trackMapper, times(1)).checkTrack(track, id);
        
    }

    private List<TrackDTO> createRandomTracksDTO() {
        List<TrackDTO> trackArray  = new ArrayList<>();
        trackArray.add(createRandomTrack());


        return trackArray;
    }

    private TrackDTO createRandomTrack(){
        return new TrackDTO(3,"Ocean and a rock","Lisa Hannigan", 337,"Sea sew", null,null,null,false);
    }
}