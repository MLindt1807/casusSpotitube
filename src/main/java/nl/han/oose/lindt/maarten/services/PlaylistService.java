package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.PlaylistsDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;

public interface PlaylistService {
    PlaylistsDTO getAll();

    void deletePlaylist(int id);

    void addPlaylist(PlaylistDTO playlist);

    void replacePlaylist(int id, PlaylistDTO replacementPlaylist);

    TracksDTO getAllTracksOfPlaylist(int idOfPlaylist);



    void addTrack(int idOfPlaylist, TrackDTO track);

    void deleteTrackFromPlaylist(int playlistID, int trackID);
}
