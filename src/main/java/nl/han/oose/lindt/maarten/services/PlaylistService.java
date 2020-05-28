package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.services.dto.*;

public interface PlaylistService {
    PlaylistsDTO getAll(String token);

    void deletePlaylist(int id);

    void addPlaylist(String token, PlaylistWithBooleanOwnerDTO playlist);

    void replacePlaylist(String token, PlaylistWithBooleanOwnerDTO replacementPlaylist, int id);

    TracksDTO getAllTracksOfPlaylist(int idOfPlaylist);

    void addTrack(int idOfPlaylist, TrackDTO track);

    void deleteTrackFromPlaylist(int playlistID, int trackID);
}
