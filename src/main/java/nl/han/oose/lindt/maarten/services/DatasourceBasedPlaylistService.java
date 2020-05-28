package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.dao.LoginDAO;
import nl.han.oose.lindt.maarten.datasource.dao.PlaylistDAO;
import nl.han.oose.lindt.maarten.datasource.dao.TrackDAO;
import nl.han.oose.lindt.maarten.services.dto.*;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class DatasourceBasedPlaylistService implements PlaylistService {


    PlaylistDAO playlistDAO;
    TrackDAO trackDAO;
    LoginDAO loginDAO;

    public DatasourceBasedPlaylistService(){

    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO){this.loginDAO = loginDAO;}


    @Override
    public PlaylistsDTO getAll(String token) {
        List<IncomingPlaylistBooleanDTO> playlists = playlistDAO.getAll(token);
        List<IncomingPlaylistBooleanDTO> playlistsWithTracks = trackDAO.getTracksForPlaylists(playlists);
        PlaylistsDTO returnVar = new PlaylistsDTO( playlistsWithTracks);
        return returnVar;
    }

    @Override
    public void deletePlaylist(int id) {
        playlistDAO.deletePlaylist( id);
    }

    @Override
    public void addPlaylist(String token, IncomingPlaylistBooleanDTO playlist) {
        playlistDAO.createPlaylist(token, playlist.getName());
    }

    @Override
    public void replacePlaylist(String token, IncomingPlaylistBooleanDTO replacementPlaylist, int id) {
        if(id == replacementPlaylist.getId()) {
            playlistDAO.replacePlaylist(replacementPlaylist.getId(), replacementPlaylist.getName(), loginDAO.getGebruikerFromToken(token));
        }else{
            throw new NotConsistantDataException();
        }
    }

    @Override
    public TracksDTO getAllTracksOfPlaylist(int idOfPlaylist) {
        List<TrackDTO> tracks = trackDAO.getAllTracksForPlaylists(idOfPlaylist);
        return new TracksDTO(tracks);
    }



    @Override
    public void addTrack(int idOfPlaylist, TrackDTO track) {
        playlistDAO.addTrackToPlaylist(idOfPlaylist, track.getId());
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistID, int trackID) {
        playlistDAO.deleteTrackFromPlaylist(playlistID, trackID);
    }


}
