package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.Mappers.PlaylistMapper;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistsDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class datasourceBasedPlaylistService implements PlaylistService {

    PlaylistMapper playlistMapper;

    public datasourceBasedPlaylistService(){

    }

    @Inject
    public void setPlaylistMapper(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    @Override
    public PlaylistsDTO getAll() {
        PlaylistsDTO returnVar = new PlaylistsDTO( playlistMapper.getAll());
        return returnVar;
    }

    @Override
    public void deletePlaylist(int id) {
        playlistMapper.deletePlaylist( id);
    }

    @Override
    public void addPlaylist(PlaylistDTO playlist) {
        playlistMapper.createPlaylist(playlist);
    }

    @Override
    public void replacePlaylist(int id, PlaylistDTO replacementPlaylist) {
        if(id == replacementPlaylist.getId()) {
            playlistMapper.replacePlaylist(replacementPlaylist);
        }else{
            throw new NotConsistantDataException();
        }
    }

    @Override
    public TracksDTO getAllTracksOfPlaylist(int idOfPlaylist) {
        List<TrackDTO> tracks = playlistMapper.getAllTracksForPlaylist(idOfPlaylist);
        return new TracksDTO(tracks);
    }



    @Override
    public void addTrack(int idOfPlaylist, TrackDTO track) {
        playlistMapper.addTrack(idOfPlaylist, track);
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistID, int trackID) {
        playlistMapper.deleteTrackFromPlaylist(playlistID, trackID);
    }


}
