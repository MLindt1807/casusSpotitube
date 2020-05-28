package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class DatasourceBasedTrackService implements TrackService {



    TrackDAO trackDAO;

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }


    @Override
    public TracksDTO getAllTracks() {
        List<TrackDTO> tracks = trackDAO.getAll();
        return new TracksDTO(tracks);
    }

    @Override
    public TracksDTO getAllTracksNotInCurrentPlaylist(int playlistID) {
        List<TrackDTO> tracks = trackDAO.getAllTracksNotInCurrentPlaylist(playlistID);
        return new TracksDTO(tracks);
    }

    @Override
    public void checkTrack(TrackDTO incomingTrack, int idOfPlaylist) {
       var tracks  = trackDAO.getTrack(incomingTrack.getId());
        if(!(tracks.size() == 1)){
            throw new NotConsistantDataException();
        }
    }
}
