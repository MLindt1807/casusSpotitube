package nl.han.oose.lindt.maarten.datasource.Mappers;


import nl.han.oose.lindt.maarten.datasource.dao.FailedResultsetReadingException;
import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackMapper {

    TrackDAO trackDAO;

    public TrackMapper(){

    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public List<TrackDTO> getAll() {
        ResultSet tracks = trackDAO.getAll();

       return tracksResultsetToTrackDTOArrayList(tracks);


    }

    public List<TrackDTO> getAllTracksNotInCurrentPlaylist(int playlistID) {
        ResultSet tracks = trackDAO.getAllTracksNotInCurrentPlaylist(playlistID);

        return tracksResultsetToTrackDTOArrayList(tracks);
    }

    public void CheckTrack(TrackDTO incomingTrack, int playlistID) {
        ResultSet resultSet = trackDAO.getTrack(incomingTrack.getId());
        List<TrackDTO> tracks = tracksResultsetToTrackDTOArrayList(resultSet);

        if(!(tracks.size() == 1 && playlistID == tracks.get(0).getId())){
            throw new NotConsistantDataException();
        }
    }

    private List<TrackDTO> tracksResultsetToTrackDTOArrayList(ResultSet resultSet){
        List<TrackDTO> tracksToReturn = new ArrayList<>();
        try {
            while(resultSet.next()) {
                tracksToReturn.add(new TrackDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("performer"),
                        resultSet.getInt("duration"),
                        resultSet.getString("album"),
                        resultSet.getInt("playcount"),
                        resultSet.getString("publicationDate"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("offlineAvailable")));

            }
        } catch (SQLException e) {
            throw new FailedResultsetReadingException();
        }
        return tracksToReturn;
    }




}
