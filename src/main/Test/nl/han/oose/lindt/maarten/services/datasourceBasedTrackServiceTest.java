package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class datasourceBasedTrackServiceTest {

    private DatasourceBasedTrackService sut;
    private TrackDAO trackDAO;
    private List<TrackDTO> tracks;
    private List<TrackDTO> tracks2;

    @BeforeEach
    void setUp() {
        sut = new DatasourceBasedTrackService();
        trackDAO = mock(TrackDAO.class);
        sut.setTrackDAO(trackDAO);

        tracks = new ArrayList<TrackDTO>();
        tracks.add(new TrackDTO());

        tracks2 = new ArrayList<TrackDTO>();
    }

    @Test
    void testGetAllTracks() {
        // Setup

        // Configure TrackMapper.getAll(...).
        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));
        when(sut.trackDAO.getAll()).thenReturn(trackDTOS);

        // Run the test
        final TracksDTO result = sut.getAllTracks();

        // Verify the results
        assertEquals(result.getTracks(), trackDTOS);
        verify(trackDAO, times(1)).getAll();

    }

    @Test
    void testGetAllTracksNotInCurrentPlaylist() {
        // Setup

        // Configure TrackMapper.getAllTracksNotInCurrentPlaylist(...).
        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));
        when(sut.trackDAO.getAllTracksNotInCurrentPlaylist(0)).thenReturn(trackDTOS);

        // Run the test
        final TracksDTO result = sut.getAllTracksNotInCurrentPlaylist(0);

        // Verify the results
        assertEquals(result.getTracks(),trackDTOS );

    }



    @Test
    void testCheckTrack() {
        // Setup
        final TrackDTO incomingTrack = new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false);

        when(trackDAO.getTrack(any())).thenReturn(tracks);
        // Run the test
        sut.checkTrack(incomingTrack, 0);

        // Verify the results
        verify(sut.trackDAO).getTrack( 0);
    }

    @Test
    void testCheckTrackThrowsError() {
        // Setup
        final TrackDTO incomingTrack = new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false);

        when(trackDAO.getTrack(any())).thenReturn(tracks2);
        // Run the test


        // Verify the results
        assertThrows(NotConsistantDataException.class, ()-> sut.checkTrack(incomingTrack, 0));
    }
}
