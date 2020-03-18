package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistReturnDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.exceptions.notFoundException;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {

    List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();

    public PlaylistService(){
        playlists.add(new PlaylistDTO(1, "Death metal", true, new ArrayList<TrackDTO>()));
        playlists.add(new PlaylistDTO(2, "Pop", false , new ArrayList<TrackDTO>()));
    }

    public PlaylistReturnDTO getAll(){
        return new PlaylistReturnDTO(playlists);
    }

    public void deletePlaylist(int id) {
        PlaylistDTO playlistToDelete = null;
        for(PlaylistDTO playlist: playlists){
            if(playlist.getId() == id){
                playlistToDelete = playlist;
            }
        }
        if(playlistToDelete == null){
            throw new notFoundException();
        }
        playlists.remove(playlistToDelete);
    }


    public void addPlaylist(PlaylistDTO playlist) {
        playlist.setId(getID());
        playlist.setOwner(true);
        playlists.add(playlist);
    }

    private int getID(){
        return playlists.size()+1;
    }

    public void replacePlaylist(int id, PlaylistDTO replacementPlaylist) {
        PlaylistDTO playlistToReplace = null;
        for(PlaylistDTO playlist: playlists){
            if(playlist.getId() == id){
                playlistToReplace = playlist;
            }
        }
        if(playlistToReplace == null){
            throw new notFoundException();
        }
        playlists.set(playlists.indexOf(playlistToReplace),replacementPlaylist);
    }
}
