package nl.han.oose.lindt.maarten.datasource.mappers;

import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;

import java.util.List;

public interface TrackMapper {


    List<TrackDTO> getAll();

    List<TrackDTO> getAllTracksNotInCurrentPlaylist(int playlistID);

    void checkTrack(TrackDTO incomingTrack, int playlistID);

    List<PlaylistDTO> getTracksForPlaylists(List<PlaylistDTO> playlists);

    List<TrackDTO> getAllTracksForPlaylist(int idOfPlaylist);
}
