package nl.han.oose.lindt.maarten.services.dto;

import java.util.List;

public class TracksDTO {
    public List<TrackDTO> tracks;

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }



    public TracksDTO(List<TrackDTO> tracks){
        this.tracks = tracks;
    }

}
