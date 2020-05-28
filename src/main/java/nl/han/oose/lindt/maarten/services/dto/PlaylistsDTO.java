package nl.han.oose.lindt.maarten.services.dto;

import java.util.List;

public class PlaylistsDTO {
    private List<PlaylistWithBooleanOwnerDTO> playlists;
    private int length;

    public PlaylistsDTO(List<PlaylistWithBooleanOwnerDTO> playlists){
        this.playlists = playlists;
        this.length = 1234;
    }

    public List<PlaylistWithBooleanOwnerDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistWithBooleanOwnerDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
