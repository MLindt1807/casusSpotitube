package nl.han.oose.lindt.maarten.datasource.mappers;

import nl.han.oose.lindt.maarten.datasource.dao.PlaylistDAO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class PlaylistMapperTest {

    private PlaylistMapperJDBC sut;
    private PlaylistDAO playlistDAO;

    @BeforeEach
    void setUp() {
        sut = new PlaylistMapperJDBC();
        playlistDAO = mock(PlaylistDAO.class);
        sut.setPlaylistDAO(playlistDAO);
    }

    @Test
    void testGetAll() {
        // Setup

        // Run the test
        final List<PlaylistDTO> result = sut.getAll();

        // Verify the results
        verify(playlistDAO,times(1)).getAll();
    }

    @Test
    void testDeletePlaylist() {
        // Setup

        // Run the test
        sut.deletePlaylist(0);

        // Verify the results
        verify(playlistDAO,times(1)).deletePlaylist(0);
    }

    @Test
    void testCreatePlaylist() {
        // Setup
        final PlaylistDTO playlist = new PlaylistDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)));

        // Run the test
        sut.createPlaylist(playlist);

        // Verify the results
        verify(playlistDAO,times(1)).createPlaylist(playlist.getName(),true);
    }

    @Test
    void testReplacePlaylist() {
        // Setup
        final PlaylistDTO replacementPlaylist = new PlaylistDTO(0, "name", false, Arrays.asList(new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)));

        // Run the test
        sut.replacePlaylist(replacementPlaylist);

        // Verify the results
        verify(playlistDAO,times(1)).replacePlaylist(replacementPlaylist.getId(),replacementPlaylist.getName(),replacementPlaylist.isOwner());
    }

    @Test
    void testAddTrack() {
        // Setup
        final TrackDTO track = new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false);

        // Run the test
        sut.addTrack(0, track);

        // Verify the results
        verify(playlistDAO, times(1)).addTrackToPlaylist(0,track.getId());
    }

    @Test
    void testDeleteTrackFromPlaylist() {
        // Setup

        // Run the test
        sut.deleteTrackFromPlaylist(0, 0);

        // Verify the results
        verify(playlistDAO, times(1)).deleteTrackFromPlaylist(0, 0);

    }


}
