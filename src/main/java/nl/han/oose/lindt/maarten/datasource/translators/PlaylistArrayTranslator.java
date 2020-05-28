package nl.han.oose.lindt.maarten.datasource.translators;

import nl.han.oose.lindt.maarten.datasource.databaseExceptions.FailedResultsetReadingException;
import nl.han.oose.lindt.maarten.services.dto.IncomingPlaylistBooleanDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistArrayTranslator{

    public PlaylistArrayTranslator() {
    }

    public List<IncomingPlaylistBooleanDTO> resultSetToDTO(ResultSet resultSet) {
        List<IncomingPlaylistBooleanDTO> playlistsToReturn = new ArrayList<>();
        try {
            while(resultSet.next()) {
                playlistsToReturn.add(new IncomingPlaylistBooleanDTO(
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
