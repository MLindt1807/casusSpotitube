package nl.han.oose.lindt.maarten.datasource.dao;

import nl.han.oose.lindt.maarten.datasource.DatabaseConnection;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAO {

    private DatabaseConnection databaseConnection;
    private Connection connection;

    public TrackDAO(){

    }



    @Inject
    private void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

    }

    @Inject
    private void setConnection(){
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
            preparedStatement.setInt(1,playlistID);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
    }


}
