package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistReturnDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.exceptions.MultipleItemsForIDException;
import nl.han.oose.lindt.maarten.services.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaylistService {

    List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();

    public PlaylistService() {

        playlists.add(new PlaylistDTO(1, "Death metal", true, new ArrayList<TrackDTO>()));
        playlists.add(new PlaylistDTO(2, "Pop", false, new ArrayList<TrackDTO>()));
    }

    public PlaylistReturnDTO getAll() {
        return new PlaylistReturnDTO(playlists);
    }

    public void deletePlaylist(int id) {
        PlaylistDTO playlistToDelete = getPlaylistForID(id);
        playlists.remove(playlistToDelete);
    }


    public void addPlaylist(PlaylistDTO playlist) {
        playlist.setId(getID());
        playlist.setOwner(true);
        playlists.add(playlist);
    }

    private int getID() {
        return playlists.size() + 1;
    }

    public void replacePlaylist(int id, PlaylistDTO replacementPlaylist) {

        PlaylistDTO playlistToReplace = getPlaylistForID(id);

        playlists.set(playlists.indexOf(playlistToReplace), replacementPlaylist);
    }

    public List<TrackDTO> getAllTracksOfPlaylist(int idOfPlaylist) {
        PlaylistDTO playlist = getPlaylistForID(idOfPlaylist);
        return playlist.getTracks();
    }


    public PlaylistDTO getPlaylistForID(int id) {
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
       PlaylistDTO  playlist = getPlaylistForID(idOfPlaylist);
       playlist.addTrack(track);
    }
}
