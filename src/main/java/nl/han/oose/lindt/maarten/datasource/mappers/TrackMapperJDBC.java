package nl.han.oose.lindt.maarten.datasource.mappers;



import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.datasource.translators.TrackArrayTranslator;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.util.List;

@Default
public class TrackMapperJDBC implements TrackMapper {



    private TrackArrayTranslator trackVertaler;
    TrackDAO trackDAO;

    public TrackMapperJDBC(){

    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setTrackVertaler(TrackArrayTranslator trackVertaler) {
        this.trackVertaler = trackVertaler;
    }

    @Override
    public List<TrackDTO> getAll() {
        ResultSet tracks = trackDAO.getAll();
        List<TrackDTO> trackDTOS = trackVertaler.resultSetToDTO(tracks);
       return trackDTOS;


    }

    @Override
    public List<TrackDTO> getAllTracksNotInCurrentPlaylist(int playlistID) {
        ResultSet tracks = trackDAO.getAllTracksNotInCurrentPlaylist(playlistID);
        var tracksDTOs = trackVertaler.resultSetToDTO(tracks);
        return tracksDTOs;
    }

    @Override
    public void checkTrack(TrackDTO incomingTrack, int playlistID) {
        ResultSet resultSet = trackDAO.getTrack(incomingTrack.getId());
        List<TrackDTO> tracks = trackVertaler.resultSetToDTO(resultSet);

        if(!(tracks.size() == 1)){
            throw new NotConsistantDataException();
        }
    }


    @Override
    public List<PlaylistDTO> getTracksForPlaylists(List<PlaylistDTO> playlists) {

       return trackDAO.getTracksForPlaylists(playlists);
    }

    @Override
    public List<TrackDTO> getAllTracksForPlaylist(int idOfPlaylist) {
        return trackDAO.getAllTracksForPlaylists(idOfPlaylist);
    }
}
