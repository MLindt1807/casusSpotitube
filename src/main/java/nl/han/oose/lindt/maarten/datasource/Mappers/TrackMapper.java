package nl.han.oose.lindt.maarten.datasource.Mappers;

import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.datasource.vertaler.TrackVertaler;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;

import javax.inject.Inject;
import java.util.List;

public interface TrackMapper {


    List<TrackDTO> getAll();

    List<TrackDTO> getAllTracksNotInCurrentPlaylist(int playlistID);

    void checkTrack(TrackDTO incomingTrack, int playlistID);

    List<PlaylistDTO> getTracksForPlaylists(List<PlaylistDTO> playlists);

    List<TrackDTO> getAllTracksForPlaylist(int idOfPlaylist);
}
