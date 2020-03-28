package nl.han.oose.lindt.maarten.datasource.Mappers;

import nl.han.oose.lindt.maarten.datasource.dao.PlaylistDAO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class PlaylistMapperJDBC implements PlaylistMapper {


    private PlaylistDAO playlistDAO;

    public PlaylistMapperJDBC(){}


    @Inject
    public void setPlaylistDAO(PlaylistDAO dao){
        this.playlistDAO = dao;
    }


    @Override
    public List<PlaylistDTO> getAll() {
       var playlists = playlistDAO.getAll();



        return playlists;
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
//    public List<TrackDTO> getAllTracksForPlaylist(int id) {
//        ResultSet result = playlistDAO.getAllTracksForPlaylist(id);
//        List<TrackDTO> tracks = new ArrayList<>();
//        try {
//            while(result.next()) {
//                tracks.add(new TrackDTO(
//                        result.getInt("id"),
//                        result.getString("title"),
//                        result.getString("performer"),
//                        result.getInt("duration"),
//                        result.getString("album"),
//                        result.getInt("playcount"),
//                        result.getString("publicationDate"),
//                        result.getString("description"),
//                        result.getBoolean("offlineAvailable")));
//            }
//        } catch (SQLException e) {
//
//            throw new FailedResultsetReadingException();
//        }
//
//        return tracks;
//    }


    @Override
    public void addTrack(int idOfPlaylist, TrackDTO track) {
        playlistDAO.addTrackToPlaylist(idOfPlaylist, track.getId());
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistID, int trackID) {
        playlistDAO.deleteTrackFromPlaylist(playlistID, trackID);
    }
}
