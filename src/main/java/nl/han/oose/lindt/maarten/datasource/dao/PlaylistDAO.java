package nl.han.oose.lindt.maarten.datasource.dao;

import nl.han.oose.lindt.maarten.datasource.*;
import nl.han.oose.lindt.maarten.datasource.databaseExceptions.FailedQueryException;
import nl.han.oose.lindt.maarten.datasource.translators.PlaylistArrayTranslator;
import nl.han.oose.lindt.maarten.services.dto.IncomingPlaylistBooleanDTO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaylistDAO {
    private DatabaseConnection databaseConnection;
    private Connection connection;
    private PlaylistArrayTranslator playlistArrayTranslator;
    public PlaylistDAO(){

    }

    @Inject
    public void setPlaylistArrayTranslator(PlaylistArrayTranslator playlistArrayTranslator) {
        this.playlistArrayTranslator = playlistArrayTranslator;
    }

    @Inject
    private void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

    }

    @Inject
    private void setConnection(){
       this.connection = databaseConnection.getConnection();
    }

    public List<IncomingPlaylistBooleanDTO> getAll(String token)  {


        ResultSet playlists = null;
        PreparedStatement preparedStatement = null;
        //opvragen
        try {
            preparedStatement = connection.prepareStatement("SELECT id, name, (owner = (select gebruikersnaam from gebruiker where verbindingtoken = ?)) as owner FROM playlists;");
            preparedStatement.setString(1,token);
            playlists = preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }
        return playlistArrayTranslator.resultSetToDTO(playlists);
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

    public void createPlaylist(String Token, String name) {

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("insert into playlists(name,owner) values (?, (select gebruikersnaam from gebruiker where verbindingtoken = ?))");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, Token);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new FailedQueryException();
        }

    }

    public void replacePlaylist(int id, String name, String owner) {

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("UPDATE playlists SET name =?, owner = ? WHERE id =? ");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, owner);
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
