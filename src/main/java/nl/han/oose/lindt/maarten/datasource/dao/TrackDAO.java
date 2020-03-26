package nl.han.oose.lindt.maarten.datasource.dao;

import nl.han.oose.lindt.maarten.datasource.DatabaseConnection;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO {

    private DatabaseConnection databaseConnection;
    private Connection connection;

    public TrackDAO() {

    }


    @Inject
    private void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

    }

    @Inject
    private void setConnection() {
        this.connection = databaseConnection.getConnection();
    }

    public ResultSet getAll() {

        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM tracks");
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
    }

    public ResultSet getTrack(Integer id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM tracks where id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
    }

    public ResultSet getAllTracksNotInCurrentPlaylist(int playlistID) {
        PreparedStatement preparedStatement;
        try {
            System.out.println(playlistID + "not in playlist");
            preparedStatement = connection.prepareStatement("SELECT * FROM tracks where id not in(select trackID from tracksInPlaylists where playlistID = ?)");
            preparedStatement.setInt(1, playlistID);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
    }


    public List<PlaylistDTO> getTracksForPlaylists(List<PlaylistDTO> playlists) {


        for (PlaylistDTO playlist : playlists) {

            List<TrackDTO> tracksToAdd = getAllTracksForPlaylists(playlist.getId());

            for (TrackDTO trackToAdd : tracksToAdd) {

                playlist.addTrack(trackToAdd);

            }
        }

        return playlists;
    }


    public List<TrackDTO> getAllTracksForPlaylists(int idOfPlaylist) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<TrackDTO> returnTracks = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM tracks where id in (select trackID from tracksinplaylists where playlistID = ?)");

            preparedStatement.setInt(1, idOfPlaylist);
            resultSet = preparedStatement.executeQuery();


        } catch (SQLException e) {

            throw new FailedQueryException();
        }


        try {
            if (!(resultSet == null)) {
                while (resultSet.next()) {
                    returnTracks.add(new TrackDTO(
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

            }
        } catch (SQLException e) {

            throw new FailedResultsetReadingException();
        }

        return returnTracks;
    }
}
