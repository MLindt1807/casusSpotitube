package nl.han.oose.lindt.maarten.datasource.translators;

import nl.han.oose.lindt.maarten.datasource.dao.FailedResultsetReadingException;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistArrayTranslator{



    public List<PlaylistDTO> resultSetToDTO(ResultSet resultSet) {
        List<PlaylistDTO> playlistsToReturn = new ArrayList<>();
        try {
            while(resultSet.next()) {
                playlistsToReturn.add(new PlaylistDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("owner"),
                        new ArrayList<>()));
            }
        } catch ( SQLException e) {
            throw new FailedResultsetReadingException();
        }

        return playlistsToReturn;
    }
}
