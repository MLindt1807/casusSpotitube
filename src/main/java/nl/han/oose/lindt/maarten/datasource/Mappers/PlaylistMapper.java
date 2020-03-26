package nl.han.oose.lindt.maarten.datasource.Mappers;

import nl.han.oose.lindt.maarten.datasource.DAO.FailedResultsetReadingException;
import nl.han.oose.lindt.maarten.datasource.DAO.PlaylistDAO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.exceptions.MultipleItemsForIDException;
import nl.han.oose.lindt.maarten.services.exceptions.NotFoundException;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistMapper {


    private PlaylistDAO playlistDAO;

    public PlaylistMapper(){}

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    //TODO geeft alleen playlists met tracks
    public List<PlaylistDTO> getAll() {
       ResultSet playlists = playlistDAO.getAll();

       List<PlaylistDTO> playlistsToReturn = new ArrayList<>();
       try {
            while(playlists.next()) {
                playlistsToReturn.add(new PlaylistDTO(
                        playlists.getInt("id"),
                        playlists.getString("name"),
                        playlists.getBoolean("owner"),
                        new ArrayList<>()));

                ResultSet tracks = playlistDAO.getAllTracksForPlaylist(playlists.getInt("id"));
                PlaylistDTO playlist = getPlaylistForID(playlists.getInt("id"),playlistsToReturn);
                while(tracks.next()){

                    playlist.addTrack(new TrackDTO(
                            tracks.getInt("id"),
                            tracks.getString("title")      ,
                            tracks.getString("performer"),
                            tracks.getInt("duration"),
                            tracks.getString("album"),
                            tracks.getInt("playcount"),
                            tracks.getString("publicationDate"),
                            tracks.getString( "description"),
                            tracks.getBoolean("offlineAvailable")));
                }

            }
       } catch (SQLException e) {
               throw new FailedResultsetReadingException();
       }

        return playlistsToReturn;
    }

    public void deletePlaylist(int id){
        playlistDAO.deletePlaylist(id);
    }

    public void createPlaylist(PlaylistDTO playlist) {
        playlistDAO.createPlaylist(playlist.getName(), true);

    }
    public void replacePlaylist( PlaylistDTO replacementPlaylist) {

            playlistDAO.replacePlaylist(replacementPlaylist.getId(), replacementPlaylist.getName(), replacementPlaylist.isOwner());

    }
    public List<TrackDTO> getAllTracksForPlaylist(int id) {
        ResultSet result = playlistDAO.getAllTracksForPlaylist(id);
        List<TrackDTO> tracks = new ArrayList<>();
        try {
            while(result.next()) {
                tracks.add(new TrackDTO(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("performer"),
                        result.getInt("duration"),
                        result.getString("album"),
                        result.getInt("playcount"),
                        result.getString("publicationDate"),
                        result.getString("description"),
                        result.getBoolean("offlineAvailable")));
            }
        } catch (SQLException e) {

            throw new FailedResultsetReadingException();
        }

        return tracks;
    }

    private PlaylistDTO getPlaylistForID(int id,List<PlaylistDTO> playlists) {
        PlaylistDTO playlistToReturn = null;


        for(PlaylistDTO playlist:playlists) {
            if (playlist.getId() == id) {
                if(playlistToReturn == null) {
                    playlistToReturn = playlist;
                }else{
                    throw new MultipleItemsForIDException();
                }
            }
        }
        if(playlistToReturn == null){
            throw new NotFoundException();
        }

        return playlistToReturn;
    }


    public void addTrack(int idOfPlaylist, TrackDTO track) {
        playlistDAO.addTrackToPlaylist(idOfPlaylist, track.getId());
    }

    public void deleteTrackFromPlaylist(int playlistID, int trackID) {
        playlistDAO.deleteTrackFromPlaylist(playlistID, trackID);
    }
}
