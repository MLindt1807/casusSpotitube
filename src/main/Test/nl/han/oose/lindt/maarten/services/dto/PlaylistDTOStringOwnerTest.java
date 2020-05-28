package nl.han.oose.lindt.maarten.services.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PlaylistDTOStringOwnerTest {

    @Mock
    private List<TrackDTO> mockTracks;
    private PlaylistDTOStringOwner sut;

    final private String TEST_STRING = "ik ben een random teststring";
    final private int TEST_INT = 1;
    final private String TEST_BOOLEAN = "owner";

    @BeforeEach
    void setUp() {
        initMocks(this);
        sut = new PlaylistDTOStringOwner(0, "name", "false", mockTracks);

    }

    @Test
    void testGetID() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sut.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(sut, 1);

        int id = sut.getId();

        assertEquals(1, id);
    }

    @Test
    void testSetID() throws NoSuchFieldException, IllegalAccessException {
        sut.setId(TEST_INT);

        final Field field = sut.getClass().getDeclaredField("id");
        field.setAccessible(true);

        assertEquals(field.get(sut), TEST_INT);


    }

    @Test
        void testSetName() throws IllegalAccessException, NoSuchFieldException {
        final Field field = sut.getClass().getDeclaredField("name");
        field.setAccessible(true);

        sut.setName(TEST_STRING);


        assertEquals(field.get(sut), TEST_STRING);
    }

    @Test
    void testGetName() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sut.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(sut, TEST_STRING);

        var result = sut.getName();

        assertEquals(TEST_STRING, result);
    }

    // todo jaja
//    @Test
//    void testSetOwner() throws IllegalAccessException, NoSuchFieldException {
//        final Field field = sut.getClass().getDeclaredField("owner");
//        field.setAccessible("true");
//
//        sut.setOwner(TEST_BOOLEAN);
//
//        assertEquals(field.get(sut), TEST_BOOLEAN);
//
//    }

//    @Test
//    void testIsowner() throws NoSuchFieldException, IllegalAccessException {
//        final Field field = sut.getClass().getDeclaredField("owner");
//        field.setAccessible(true);
//        field.set(sut, TEST_BOOLEAN);
//
//        var result = sut.isOwner();
//
//        assertEquals(TEST_BOOLEAN, result);
//    }

    @Test
    void testSetTrack() throws IllegalAccessException, NoSuchFieldException {
        final Field field = sut.getClass().getDeclaredField("tracks");
        field.setAccessible(true);

        sut.setTracks(mockTracks);

        assertEquals(field.get(sut), mockTracks);
    }

    @Test
    void testGetTracks() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sut.getClass().getDeclaredField("tracks");
        field.setAccessible(true);
        field.set(sut, mockTracks);

        var result = sut.getTracks();

        assertEquals(mockTracks, result);
    }

    @Test
    void testAddTrack() {
        // Setup
        final TrackDTO track = new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false);


        // Run the test
        sut.addTrack(track);

        // Verify the results
        verify(mockTracks, times(1)).add(track);

    }
}
