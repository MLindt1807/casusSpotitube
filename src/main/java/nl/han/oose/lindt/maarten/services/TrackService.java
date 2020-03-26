package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;

public interface TrackService {

    TracksDTO getAllTracks();


    TracksDTO getAllTracksNotInCurrentPlaylist(int id);



    void checkTrack(TrackDTO incomingTrack, int idOfPlaylist);
}
