package nl.han.oose.lindt.maarten.datasource.dao;

import nl.han.oose.lindt.maarten.datasource.*;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


        ResultSet playlists = null;
        PreparedStatement preparedStatement = null;
        //opvragen
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM playlists");
            playlists = preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
        return playlists;
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

            throw new FailedQueryException();
        }
    }
}
