package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.Mappers.PlaylistMapper;
import nl.han.oose.lindt.maarten.datasource.Mappers.PlaylistMapperJDBC;
import nl.han.oose.lindt.maarten.datasource.Mappers.TrackMapper;
import nl.han.oose.lindt.maarten.datasource.Mappers.TrackMapperJDBC;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistsDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
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

    private datasourceBasedPlaylistService sut;
    private PlaylistMapper playlistMapper   ;
    private TrackMapper trackMapper   ;

    @BeforeEach
    void setUp() {
        sut = new datasourceBasedPlaylistService();
        playlistMapper = mock(PlaylistMapperJDBC.class);
        trackMapper = mock(TrackMapperJDBC.class);

        sut.setPlaylistMapper(playlistMapper);
        sut.setTrackMapper(trackMapper);
    }

    @Test
    void testGetAll() {
        // Setup

        // Configure PlaylistMapper.getAll(...).
        final List<PlaylistDTO> playlistDTOS = Arrays.asList(new PlaylistDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false))));
        when(playlistMapper.getAll()).thenReturn(playlistDTOS);

        // Run the test
        final PlaylistsDTO result = sut.getAll();

        // Verify the results
//        assertEquals(result.getPlaylists(),playlistDTOS ); // why
        verify(playlistMapper, times(1)).getAll();
        verify(trackMapper, times(1)).getTracksForPlaylists(playlistDTOS);
    }

    @Test
    void testDeletePlaylist() {
        // Setup

        // Run the test
        sut.deletePlaylist(0);

        // Verify the results
        verify(sut.playlistMapper, times(1)).deletePlaylist(0);
    }

    @Test
    void testAddPlaylist() {
        // Setup
        final PlaylistDTO playlist = new PlaylistDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)));

        // Run the test
        sut.addPlaylist(playlist);

        // Verify the results
        verify(sut.playlistMapper).createPlaylist(any(PlaylistDTO.class));
    }

    @Test
    void testReplacePlaylist() {
        // Setup
        final PlaylistDTO replacementPlaylist = new PlaylistDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)));

        // Run the test
        sut.replacePlaylist(0, replacementPlaylist);

        // Verify the results
        verify(sut.playlistMapper).replacePlaylist(any(PlaylistDTO.class));
    }

    @Test
    void testReplacePlaylistThrowsError() {
        // Setup
        final PlaylistDTO replacementPlaylist = new PlaylistDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)));

        // Run the test and verify
        assertThrows(NotConsistantDataException.class, () -> sut.replacePlaylist(1,replacementPlaylist));
        verify(playlistMapper,times(0)).replacePlaylist(any(PlaylistDTO.class));
    }

    @Test
    void testGetAllTracksOfPlaylist() {
        // Setup

        // Configure TrackMapper.getAllTracksForPlaylist(...).
        final List<TrackDTO> trackDTOS = Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false));
        when(trackMapper.getAllTracksForPlaylist(0)).thenReturn(trackDTOS);

        // Run the test
        final TracksDTO result = sut.getAllTracksOfPlaylist(0);

        // Verify the results
        assertEquals(result.getTracks(), trackDTOS);
        verify(sut.trackMapper).getAllTracksForPlaylist(0);

    }

    @Test
    void testAddTrack() {
        // Setup
        final TrackDTO track = new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false);

        // Run the test
        sut.addTrack(0, track);

        // Verify the results
        verify(sut.playlistMapper).addTrack(0, track);
    }

    @Test
    void testDeleteTrackFromPlaylist() {
        // Setup

        // Run the test
        sut.deleteTrackFromPlaylist(0, 0);

        // Verify the results
        verify(sut.playlistMapper, times(1)).deleteTrackFromPlaylist(0, 0);
    }
}
