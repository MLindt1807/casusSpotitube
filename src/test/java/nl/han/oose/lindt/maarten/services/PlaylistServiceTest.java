package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.Mappers.PlaylistMapper;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistsDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PlaylistServiceTest {
    datasourceBasedPlaylistService sut;
    PlaylistMapper playlistMapper;
    String tokenString = "1234-1234";

    @BeforeEach
    void setUp() {
        this.sut = new datasourceBasedPlaylistService();

        this.playlistMapper = mock(PlaylistMapper.class);

        sut.setPlaylistMapper(playlistMapper);

    }



    @Test
    void getAll() {
        //arrange
        var playlists = createPlaylists();

        when(playlistMapper.getAll()).thenReturn( playlists);


        //act

        PlaylistsDTO result = sut.getAll();

        // assert
        assertEquals(playlists,result.getPlaylists());
    }



    @Test
    void deletePlaylistCallsToMapper() {

//        act
        sut.deletePlaylist(1);

//        assert
        verify(playlistMapper, times(1)).deletePlaylist(1);
    }

    @Test
    void addPlaylistCallsMapper() {
        PlaylistDTO playlist = createPlaylist();
        sut.addPlaylist(playlist);

//        assert
        verify(playlistMapper, times(1)).createPlaylist(playlist);

    }

    @Test
    void replacePlaylistThrowsErrorIFNottheSame() {

        // arrange
        var item = createPlaylist();

        // Act & Assert
        assertThrows(NotConsistantDataException.class, () -> sut.replacePlaylist(2, item));
    }
    @Test
    void replacePlaylistCallsMapper() {
        //        arrange
        PlaylistDTO playlist = createPlaylist();

//        act

        sut.replacePlaylist(playlist.getId(),playlist);

//        assert
        verify(playlistMapper, times(1)).replacePlaylist(playlist);
    }

    @Test
    void getAllTracksOfPlaylist() {
        //        arrange
        int id = 1;
        var tracks = createRandomTracksDTO();
        when(playlistMapper.getAllTracksForPlaylist(id)).thenReturn(tracks);


//        act
        var result =  sut.getAllTracksOfPlaylist( id);

//        assert

        assertEquals(tracks, result.getTracks());
    }

    @Test
    void addTrack() {
        //        arrange

        int id = 2;
        var track = createRandomTrack();
//        act

        sut.addTrack(id,track);

//        assert
        verify(playlistMapper, times(1)).addTrack(id,track);

    }

    @Test
    void deleteTrackFromPlaylist() {
        //        arrange

        int playlistID = 2;
        var track = createRandomTrack();
//        act

        sut.deleteTrackFromPlaylist(playlistID,track.getId());

//        assert
        verify(playlistMapper, times(1)).deleteTrackFromPlaylist(playlistID,track.getId());

    }

    private List<PlaylistDTO> createPlaylists(){
        List<PlaylistDTO> playlistsArray  = new ArrayList<PlaylistDTO>();
        playlistsArray.add(createPlaylist());


        return playlistsArray;
    }

    private PlaylistDTO createPlaylist() {

            return new PlaylistDTO(1, "Death metal", true, new ArrayList<TrackDTO>());

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

