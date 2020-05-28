package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.dao.LoginDAO;
import nl.han.oose.lindt.maarten.datasource.dao.PlaylistDAO;
import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.services.dto.*;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class datasourceBasedPlaylistServiceTest {

    private DatasourceBasedPlaylistService sut;
    private PlaylistDAO playlistDAO;
    private TrackDAO trackDAO;
    private LoginDAO loginDAO;
    private int randomPlaylistID = 0;
    IncomingPlaylistBooleanDTO booleanPlaylist;

    @BeforeEach
    void setUp() {
        sut = new DatasourceBasedPlaylistService();
        playlistDAO = mock(PlaylistDAO.class);
        trackDAO = mock(TrackDAO.class);
        loginDAO = mock(LoginDAO.class);

        sut.setPlaylistDAO(playlistDAO);
        sut.setTrackDAO(trackDAO);
        sut.setLoginDAO(loginDAO);

        booleanPlaylist = mock(IncomingPlaylistBooleanDTO.class);


    }

    @Test
    void testGetAll() {
        // Setup

        // Configure PlaylistMapper.getAll(...).

        final List<IncomingPlaylistBooleanDTO> playlistDTOStringOwners = Arrays.asList( new IncomingPlaylistBooleanDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false))));
        when(playlistDAO.getAll("token")).thenReturn(playlistDTOStringOwners);

        // Run the test
        final PlaylistsDTO result = sut.getAll("token");

        // Verify the results
        verify(playlistDAO, times(1)).getAll("token");
        verify(trackDAO, times(1)).getTracksForPlaylists(playlistDTOStringOwners);
    }

    @Test
    void testDeletePlaylist() {
        // Setup

        // Run the test
        sut.deletePlaylist(0);

        // Verify the results
        verify(sut.playlistDAO, times(1)).deletePlaylist(0);
    }

    @Test
    void testAddPlaylist() {
        // Setup
        var playlist = new IncomingPlaylistBooleanDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)));

        // Run the test
        sut.addPlaylist("token", playlist);

        // Verify the results
        verify(sut.playlistDAO).createPlaylist(any(), any());
    }



    @Test
    void replacePlaylists_throwsError(){
        //arrange
        when(booleanPlaylist.getId()).thenReturn(1);
        //act

        //assert
        assertThrows(NotConsistantDataException.class, ()-> sut.replacePlaylist("token", booleanPlaylist, 2 ));
    }

    @Test
    void replacePlaylists_callsPlaylistDAO(){
        //arrange
        when(booleanPlaylist.getId()).thenReturn(1);
        when(booleanPlaylist.getName()).thenReturn("name");
        when(loginDAO.getGebruikerFromToken(any())).thenReturn("naamgebruiker");
        doNothing().when(playlistDAO).replacePlaylist(1,"name","naamgebruiker");
        //act
        sut.replacePlaylist("token", booleanPlaylist,1);
        //assert
        verify(playlistDAO,times(1)).replacePlaylist(1,"name","naamgebruiker");
    }

    @Test
    void testGetAllTracksOfPlaylist() {
        // Setup

        // Configure TrackMapper.getAllTracksForPlaylist(...).
        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));
        when(trackDAO.getAllTracksForPlaylists(0)).thenReturn(trackDTOS);

        // Run the test
        final TracksDTO result = sut.getAllTracksOfPlaylist(0);

        // Verify the results
        assertEquals(result.getTracks(), trackDTOS);
        verify(sut.trackDAO).getAllTracksForPlaylists(0);

    }

    @Test
    void testAddTrack() {
        // Setup
        final TrackDTO track = new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false);

        // Run the test
        sut.addTrack(0, track);

        // Verify the results
        verify(sut.playlistDAO).addTrackToPlaylist(0, track.getId());
    }

    @Test
    void testDeleteTrackFromPlaylist() {
        // Setup

        // Run the test
        sut.deleteTrackFromPlaylist(0, 0);

        // Verify the results
        verify(sut.playlistDAO, times(1)).deleteTrackFromPlaylist(0, 0);
    }
}
