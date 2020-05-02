package nl.han.oose.lindt.maarten.datasource.mappers;

import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.exceptions.MultipleItemsForIDException;
import nl.han.oose.lindt.maarten.services.exceptions.NotFoundException;

import java.util.List;

public interface PlaylistMapper {


    List<PlaylistDTO> getAll();

    void deletePlaylist(int id);

    void createPlaylist(PlaylistDTO playlist);

    void replacePlaylist(PlaylistDTO replacementPlaylist);

    default PlaylistDTO getPlaylistForID(int id, List<PlaylistDTO> playlists) {
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

    void addTrack(int idOfPlaylist, TrackDTO track);

    void deleteTrackFromPlaylist(int playlistID, int trackID);
}
