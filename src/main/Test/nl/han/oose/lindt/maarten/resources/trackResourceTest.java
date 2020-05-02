package nl.han.oose.lindt.maarten.resources;

import nl.han.oose.lindt.maarten.services.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class trackResourceTest {

    private TrackResource sut;
    private TrackService trackService;
    @BeforeEach
    void setUp() {
        sut = new TrackResource();
        trackService = mock(TrackService.class);
        sut.setTrackService(trackService);
    }

    @Test
    void testGetAllExceptFromPlaylistWithoutPlaylistID() {
        // Setup

        // Run the test
        final Response result = sut.GetAllExceptFromPlaylist("token", null);

        // Verify the results
        verify(trackService, times(1)).getAllTracks();
        assertEquals(result.getStatus(), HTTP_OK);
    }
    @Test
    void testGetAllExceptFromPlaylistWithPlaylistID() {
        // Setup

        // Run the test
        final Response result = sut.GetAllExceptFromPlaylist("token", 0);

        // Verify the results
        verify(trackService, times(1)).getAllTracksNotInCurrentPlaylist(0);
        assertEquals(result.getStatus(), HTTP_OK);
    }
}
