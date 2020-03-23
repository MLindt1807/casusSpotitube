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
            throw new NotFoundException();
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
            throw new NotFoundException();
        }
        playlists.set(playlists.indexOf(playlistToReplace),replacementPlaylist);
    }

    public List<TrackDTO> getAllOfPlaylist(int idOfPlaylist) {
        List<PlaylistDTO> gekozenPlaylist = playlists.stream().filter(playlist -> playlist.getId() == idOfPlaylist).collect(Collectors.toList());
        if(gekozenPlaylist.size() < 1){
            throw new NotFoundException();
        }
        if(gekozenPlaylist.size() > 1){
            throw new MultipleItemsForIDException();
        }
        return gekozenPlaylist.get(0).getTracks();
    }

    public List<Integer> getAllIDOfPlaylist(int idOfPlaylist) {
        List<TrackDTO> tracks = getAllOfPlaylist(idOfPlaylist);
        List<Integer> trackIDs = new ArrayList<Integer>();
        for(TrackDTO track: tracks){
            trackIDs.add(track.getId());
        }
        return trackIDs;
    }
}
