package nl.han.oose.lindt.maarten.services.dto;

import java.util.List;

public class PlaylistWithBooleanOwnerDTO {
    public PlaylistWithBooleanOwnerDTO() {
    }

    public PlaylistWithBooleanOwnerDTO(int id, String name, boolean owner, List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    int id;
    String name;
    boolean owner;
    List<TrackDTO> tracks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(TrackDTO trackToAdd) {
        this.tracks.add(trackToAdd);
    }
}
