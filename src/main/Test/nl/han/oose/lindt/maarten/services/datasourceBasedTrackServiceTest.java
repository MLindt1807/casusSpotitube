package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.Mappers.TrackMapper;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class datasourceBasedTrackServiceTest {

    private datasourceBasedTrackService sut;
    private TrackMapper trackMapper;
    @BeforeEach
    void setUp() {
        sut = new datasourceBasedTrackService();
        trackMapper = mock(TrackMapper.class);
        sut.setTrackMapper(trackMapper);
    }

    @Test
    void testGetAllTracks() {
        // Setup

        // Configure TrackMapper.getAll(...).
        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));
        when(sut.trackMapper.getAll()).thenReturn(trackDTOS);

        // Run the test
        final TracksDTO result = sut.getAllTracks();

        // Verify the results
        assertEquals(result.getTracks(), trackDTOS);
        verify(trackMapper, times(1)).getAll();

    }

    @Test
    void testGetAllTracksNotInCurrentPlaylist() {
        // Setup

        // Configure TrackMapper.getAllTracksNotInCurrentPlaylist(...).
        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));
        when(sut.trackMapper.getAllTracksNotInCurrentPlaylist(0)).thenReturn(trackDTOS);

        // Run the test
        final TracksDTO result = sut.getAllTracksNotInCurrentPlaylist(0);

        // Verify the results
        assertEquals(result.getTracks(),trackDTOS );

    }



    @Test
    void testCheckTrack() {
        // Setup
        final TrackDTO incomingTrack = new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false);

        // Run the test
        sut.checkTrack(incomingTrack, 0);

        // Verify the results
        verify(sut.trackMapper).checkTrack(incomingTrack, 0);
    }
}
