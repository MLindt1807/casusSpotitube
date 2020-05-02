package nl.han.oose.lindt.maarten.datasource.mappers;

import nl.han.oose.lindt.maarten.datasource.dao.PlaylistDAO;
import nl.han.oose.lindt.maarten.datasource.translators.PlaylistArrayTranslator;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class PlaylistMapperJDBC implements PlaylistMapper {


    private PlaylistDAO playlistDAO;
    private PlaylistArrayTranslator playlistVertaler;

    public PlaylistMapperJDBC(){}

    @Inject
    public void setPlaylistVertaler(PlaylistArrayTranslator playlistVertaler) {
        this.playlistVertaler = playlistVertaler;
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO dao){
        this.playlistDAO = dao;
    }


    @Override
    public List<PlaylistDTO> getAll() {
       var playlists = playlistDAO.getAll();
       return playlistVertaler.resultSetToDTO(playlists);
    }

    @Override
    public void deletePlaylist(int id){
        playlistDAO.deletePlaylist(id);
    }

    @Override
    public void createPlaylist(PlaylistDTO playlist) {
        playlistDAO.createPlaylist(playlist.getName(), true);

    }
    @Override
    public void replacePlaylist(PlaylistDTO replacementPlaylist) {

            playlistDAO.replacePlaylist(replacementPlaylist.getId(), replacementPlaylist.getName(), replacementPlaylist.isOwner());

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
