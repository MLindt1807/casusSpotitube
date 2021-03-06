package nl.han.oose.lindt.maarten.datasource.Mappers;

import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.datasource.vertaler.TrackVertaler;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class TrackMapperTest {

    private TrackMapper sut;
    private TrackDAO trackDAO;
    private TrackVertaler trackVertaler;

    @BeforeEach
    void setUp() {
        sut = new TrackMapper();
        trackDAO = mock(TrackDAO.class);
        sut.setTrackDAO(trackDAO);
        trackVertaler = mock(TrackVertaler.class);
        sut.setTrackVertaler(trackVertaler);

    }

    @Test
    void testGetAll()  {
        // Setup

        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));


        when(trackVertaler.tracksResultsetToTrackDTOArrayList(any(ResultSet.class))).thenReturn(trackDTOS);

        // Run the test
        final List<TrackDTO> result = sut.getAll();

        // Verify the results
        assertEquals(trackDTOS,result);
    }

    @Test
    void testGetAllTracksNotInCurrentPlaylist()  {
        // Setup
        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));


        when(trackVertaler.tracksResultsetToTrackDTOArrayList(any(ResultSet.class))).thenReturn(trackDTOS);

        // Run the test
        final List<TrackDTO> result = sut.getAllTracksNotInCurrentPlaylist(0);

        // Verify the results
        assertEquals(result,trackDTOS);
    }

    @Test
    void testCheckTrackNoTracks() {
        // Setup
        final TrackDTO incomingTrack = new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false);

        final List<TrackDTO> trackDTOS = new ArrayList<>();

        when(trackVertaler.tracksResultsetToTrackDTOArrayList(any(ResultSet.class))).thenReturn(trackDTOS);


        // Run the test
        assertThrows(NotConsistantDataException.class, () -> sut.checkTrack(incomingTrack, 1));


        // Verify the results

    }


    @Test
    void testGetTracksForPlaylists() {
        // Setup
        final List<PlaylistDTO> playlists = Arrays.asList(new PlaylistDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false))));

        // Configure TrackDAO.getTracksForPlaylists(...).
        final List<PlaylistDTO> playlistDTOS = Arrays.asList(new PlaylistDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false))));
        when(trackDAO.getTracksForPlaylists(playlists)).thenReturn(playlistDTOS);

        // Run the test
        final List<PlaylistDTO> result = sut.getTracksForPlaylists(playlists);

        // Verify the results
        assertEquals(playlistDTOS, result);
    }

    @Test
    void testGetAllTracksForPlaylist() {
        // Setup

        // Configure TrackDAO.getAllTracksForPlaylists(...).
        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));
        when(sut.trackDAO.getAllTracksForPlaylists(0)).thenReturn(trackDTOS);

        // Run the test
        final List<TrackDTO> result = sut.getAllTracksForPlaylist(0);

        // Verify the results
        assertEquals(result, trackDTOS);
    }
}
