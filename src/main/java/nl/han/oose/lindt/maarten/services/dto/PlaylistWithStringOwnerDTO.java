package nl.han.oose.lindt.maarten.services.dto;

import java.util.List;

public class PlaylistWithStringOwnerDTO {
    private int id;
    private String name;
    private String owner;
    private List<TrackDTO> tracks;

    public PlaylistWithStringOwnerDTO(){}
    public PlaylistWithStringOwnerDTO(int id, String name, String owner, List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<TrackDTO> getTracks() {

        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(TrackDTO track) {
        tracks.add(track);

    }
}
