package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.PlaylistService;
import nl.han.oose.lindt.maarten.services.TrackService;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Arrays;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class playlistResourceTest {

    private playlistResource sut;
    private TrackService trackService;
    private PlaylistService playlistService;
    private final int randomPlaylistID = 0;

    @BeforeEach
    void setUp() {
        sut = new playlistResource();
        trackService = mock(TrackService.class);
        playlistService = mock(PlaylistService.class);
        sut.setTrackService(trackService);
        sut.setPlaylistService(playlistService);
    }

    @Test
    void testGetAllPlaylists() {
        // Setup

        // Run the test
        final Response result = sut.getAllPlaylists("token");

        // Verify the results
        verify(playlistService, times(1)).getAll();
        assertEquals(result.getStatus(),HTTP_OK);
    }

    @Test
    void testDeletePlaylist() {
        // Setup

        // Run the test
        final Response result = sut.deletePlaylist("token", randomPlaylistID);

        // Verify the results
        verify(playlistService,times(1)).deletePlaylist(randomPlaylistID);
        assertEquals(result.getStatus(),HTTP_OK);
    }

    @Test
    void testAddPlaylist() {
        // Setup
        final PlaylistDTO playlist = new PlaylistDTO(randomPlaylistID, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)));

        // Run the test
        final Response result = sut.addPlaylist("token", playlist);

        // Verify the results
        verify(playlistService, times(1)).addPlaylist(playlist);
        assertEquals(result.getStatus(),HTTP_CREATED);
    }

    @Test
    void testEditPlaylist() {
        // Setup
        final PlaylistDTO playlist = new PlaylistDTO(randomPlaylistID, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)));

        // Run the test
        final Response result = sut.editPlaylist("token", randomPlaylistID, playlist);

        // Verify the results
        verify(playlistService, times(1)).replacePlaylist(randomPlaylistID, playlist);
        assertEquals(result.getStatus(),HTTP_OK);
    }

    @Test
    void testGetAllOfPlaylist() {
        // Setup

        // Run the test
        final Response result = sut.getAllOfPlaylist("token", randomPlaylistID);

        // Verify the results
        verify(playlistService, times(1)).getAllTracksOfPlaylist(randomPlaylistID);
    assertEquals(result.getStatus(),HTTP_OK);
    }

    @Test
    void testAddTrackToPlaylist() {
        // Setup
        final TrackDTO track = new TrackDTO(randomPlaylistID, "title", "performer", 0, "album", 0, "publicationDate", "description", false);

        // Run the test
        final Response result = sut.addTrackToPlaylist("token", randomPlaylistID, track);

        // Verify the results
        verify(trackService,times(1)).checkTrack(track, randomPlaylistID );
        verify(playlistService, times(1)).addTrack(randomPlaylistID,track);
        assertEquals(result.getStatus(),HTTP_CREATED);
    }

    @Test
    void testDeleteTrackFromPlaylist() {
        // Setup

        // Run the test
        final Response result = sut.deleteTrackFromPlaylist("token", 0, 0);

        // Verify the results
        verify(playlistService, times(1)).deleteTrackFromPlaylist(randomPlaylistID,0);
        assertEquals(result.getStatus(),HTTP_OK);
    }
}
