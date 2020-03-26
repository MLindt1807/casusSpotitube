package nl.han.oose.lindt.maarten.datasource.DAO;

import nl.han.oose.lindt.maarten.datasource.*;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDAO {
    private DatabaseConnection databaseConnection;
    private Connection connection;
    public PlaylistDAO(){

    }

    @Inject
    private void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

    }

    @Inject
    private void setConnection(){
       this.connection = databaseConnection.getConnection();
    }

    public ResultSet getAll()  {


        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM playlists");
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }

    }

    public ResultSet getAllTracksForPlaylist(int id) {


        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM tracks where id in (select trackID from tracksinplaylists where playlistID = ?)");
            preparedStatement.setInt(1,id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {

            throw new FailedQueryException();
        }
    }

    public void deletePlaylist(int id) {

        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement2;
        try {
            preparedStatement = connection.prepareStatement("delete from tracksInPlaylists where playlistID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement2 = connection.prepareStatement("delete from playlists where id = ?");
            preparedStatement2.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            throw new FailedQueryException();
        }


    }

    public void createPlaylist(String name, boolean owner) {

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("insert into playlists(name,owner) values (?, ?)");
            preparedStatement.setString(1,name);
            preparedStatement.setBoolean(2,owner);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }

    }

    public void replacePlaylist(int id, String name, boolean owner) {

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("UPDATE playlists SET name =?, owner = ? WHERE id =? ");
            preparedStatement.setString(1,name);
            preparedStatement.setBoolean(2, owner);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
    }

    public void addTrackToPlaylist(int idOfPlaylist, int id) {

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("insert into tracksinplaylists(playlistID, trackID) values (?,?)");
            preparedStatement.setInt(1,idOfPlaylist);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new FailedQueryException();
        }
    }

    public void deleteTrackFromPlaylist(int playlistID, int trackID) {
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("delete from tracksInPlaylists where playlistID = ? and trackID = ?");
            preparedStatement.setInt(1,playlistID);
            preparedStatement.setInt(2, trackID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new FailedQueryException();
        }
    }
}
