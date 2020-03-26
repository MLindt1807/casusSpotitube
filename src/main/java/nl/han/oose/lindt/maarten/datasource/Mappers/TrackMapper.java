package nl.han.oose.lindt.maarten.datasource.Mappers;



import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.datasource.vertaler.TrackVertaler;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.util.List;

public class TrackMapper {



    private TrackVertaler trackVertaler;
    TrackDAO trackDAO;

    public TrackMapper(){

    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setTrackVertaler(TrackVertaler trackVertaler) {
        this.trackVertaler = trackVertaler;
    }

    public List<TrackDTO> getAll() {
        ResultSet tracks = trackDAO.getAll();
        List<TrackDTO> trackDTOS = trackVertaler.tracksResultsetToTrackDTOArrayList(tracks);
       return trackDTOS;


    }

    public List<TrackDTO> getAllTracksNotInCurrentPlaylist(int playlistID) {
        ResultSet tracks = trackDAO.getAllTracksNotInCurrentPlaylist(playlistID);
        var tracksDTOs = trackVertaler.tracksResultsetToTrackDTOArrayList(tracks);
        return tracksDTOs;
    }

    public void checkTrack(TrackDTO incomingTrack, int playlistID) {
        ResultSet resultSet = trackDAO.getTrack(incomingTrack.getId());
        List<TrackDTO> tracks = trackVertaler.tracksResultsetToTrackDTOArrayList(resultSet);

        if(!(tracks.size() == 1)){
            throw new NotConsistantDataException();
        }
    }


    public List<PlaylistDTO> getTracksForPlaylists(List<PlaylistDTO> playlists) {

       return trackDAO.getTracksForPlaylists(playlists);
    }

    public List<TrackDTO> getAllTracksForPlaylist(int idOfPlaylist) {
        return trackDAO.getAllTracksForPlaylists(idOfPlaylist);
    }
}
