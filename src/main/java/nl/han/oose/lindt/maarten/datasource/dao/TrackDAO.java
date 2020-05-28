package nl.han.oose.lindt.maarten.datasource.dao;

import nl.han.oose.lindt.maarten.datasource.DatabaseConnection;
import nl.han.oose.lindt.maarten.datasource.databaseExceptions.FailedQueryException;
import nl.han.oose.lindt.maarten.datasource.translators.TrackArrayTranslator;
import nl.han.oose.lindt.maarten.services.dto.PlaylistWithBooleanOwnerDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrackDAO {

    private DatabaseConnection databaseConnection;
    private Connection connection;
    private TrackArrayTranslator trackArrayTranslator;
    private PreparedStatement preparedStatement;

    public TrackDAO() {

    }

    @Inject
    public void setTrackArrayTranslator(TrackArrayTranslator trackArrayTranslator) {
        this.trackArrayTranslator = trackArrayTranslator;
    }

    @Inject
    private void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

    }

    @Inject
    private void setConnection() {
        this.connection = databaseConnection.getConnection();
    }

    public List<TrackDTO> getAll() {

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM tracks");
            return trackArrayTranslator.resultSetToDTO(preparedStatement.executeQuery());

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
    }

    public List<TrackDTO> getTrack(Integer id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM tracks where id = ?");
            preparedStatement.setInt(1, id);
            return trackArrayTranslator.resultSetToDTO(preparedStatement.executeQuery());

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
    }

    public List<TrackDTO> getAllTracksNotInCurrentPlaylist(int playlistID) {

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM tracks where id not in(select trackID from tracksInPlaylists where playlistID = ?)");
            preparedStatement.setInt(1, playlistID);
            return trackArrayTranslator.resultSetToDTO(preparedStatement.executeQuery());

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
    }


    public List<PlaylistWithBooleanOwnerDTO> getTracksForPlaylists(List<PlaylistWithBooleanOwnerDTO> playlists) {


        for (PlaylistWithBooleanOwnerDTO playlist : playlists) {

            List<TrackDTO> tracksToAdd = getAllTracksForPlaylists(playlist.getId());

            for (TrackDTO trackToAdd : tracksToAdd) {

                playlist.addTrack(trackToAdd);

            }
        }

        return playlists;
    }


    public List<TrackDTO> getAllTracksForPlaylists(int idOfPlaylist) {
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM tracks where id in (select trackID from tracksinplaylists where playlistID = ?)");

            preparedStatement.setInt(1, idOfPlaylist);
            resultSet = preparedStatement.executeQuery();


        } catch (SQLException e) {

            throw new FailedQueryException();
        }




        return trackArrayTranslator.resultSetToDTO(resultSet);
    }
}
