package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistsDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import nl.han.oose.lindt.maarten.services.exceptions.MultipleItemsForIDException;
import nl.han.oose.lindt.maarten.services.exceptions.NotFoundException;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class HardcodedPlaylistService implements PlaylistService {

    List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();

    public HardcodedPlaylistService() {

        playlists.add(new PlaylistDTO(1, "Death metal", true, new ArrayList<TrackDTO>()));
        playlists.add(new PlaylistDTO(2, "Pop", false, new ArrayList<TrackDTO>()));
    }

    @Override
    public PlaylistsDTO getAll() {
        return new PlaylistsDTO(playlists);
    }

    @Override
    public void deletePlaylist(int id) {
        PlaylistDTO playlistToDelete = getPlaylistForID(id);
        playlists.remove(playlistToDelete);
    }


    @Override
    public void addPlaylist(PlaylistDTO playlist) {
        playlist.setId(getID());
        playlist.setOwner(true);
        playlists.add(playlist);
    }

    private int getID() {
        return playlists.size() + 1;
    }

    @Override
    public void replacePlaylist(int id, PlaylistDTO replacementPlaylist) {

        PlaylistDTO playlistToReplace = getPlaylistForID(id);

        playlists.set(playlists.indexOf(playlistToReplace), replacementPlaylist);
    }

    @Override
    public TracksDTO getAllTracksOfPlaylist(int idOfPlaylist) {
        PlaylistDTO playlist = getPlaylistForID(idOfPlaylist);
        return new TracksDTO( playlist.getTracks());
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


    @Override
    public void addTrack(int idOfPlaylist, TrackDTO track) {
       PlaylistDTO  playlist = getPlaylistForID(idOfPlaylist);
       playlist.addTrack(track);
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistID, int trackID) {
        //todo implement
    }
}
