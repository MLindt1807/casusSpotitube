package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.mappers.TrackMapper;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class DatasourceBasedTrackService implements TrackService {


    TrackMapper trackMapper;

    @Inject
    public void setTrackMapper(TrackMapper trackMapper) {
        this.trackMapper = trackMapper;
    }

    @Override
    public TracksDTO getAllTracks() {
        List<TrackDTO> tracks = trackMapper.getAll();
        return new TracksDTO(tracks);
    }

    @Override
    public TracksDTO getAllTracksNotInCurrentPlaylist(int playlistID) {
        List<TrackDTO> tracks = trackMapper.getAllTracksNotInCurrentPlaylist(playlistID);
        return new TracksDTO(tracks);
    }

    @Override
    public void checkTrack(TrackDTO incomingTrack, int idOfPlaylist) {
       trackMapper.checkTrack(incomingTrack, idOfPlaylist);
    }
}
