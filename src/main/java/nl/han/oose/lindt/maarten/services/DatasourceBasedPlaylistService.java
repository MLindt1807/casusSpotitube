package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.datasource.mappers.PlaylistMapper;
import nl.han.oose.lindt.maarten.datasource.mappers.TrackMapper;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistsDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class DatasourceBasedPlaylistService implements PlaylistService {

    PlaylistMapper playlistMapper;



    TrackMapper trackMapper;

    public DatasourceBasedPlaylistService(){

    }

    @Inject
    public void setPlaylistMapper(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    @Inject
    public void setTrackMapper(TrackMapper trackMapper) {
        this.trackMapper = trackMapper;
    }

    @Override
    public PlaylistsDTO getAll() {
        List<PlaylistDTO> playlists = playlistMapper.getAll();
        List<PlaylistDTO> playlistsWithTracks = trackMapper.getTracksForPlaylists(playlists);
        PlaylistsDTO returnVar = new PlaylistsDTO( playlistsWithTracks);
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
        List<TrackDTO> tracks = trackMapper.getAllTracksForPlaylist(idOfPlaylist);
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
