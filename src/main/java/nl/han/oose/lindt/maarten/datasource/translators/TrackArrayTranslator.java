package nl.han.oose.lindt.maarten.datasource.translators;

import nl.han.oose.lindt.maarten.datasource.dao.FailedResultsetReadingException;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackArrayTranslator {
    public TrackArrayTranslator() {
    }


    public List<TrackDTO> resultSetToDTO(ResultSet resultSet) {
        List<TrackDTO> tracksToReturn = new ArrayList<TrackDTO>();
        try {
            while (resultSet.next()) {
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