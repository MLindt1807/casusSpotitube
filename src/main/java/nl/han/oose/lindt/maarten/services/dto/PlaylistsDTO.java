package nl.han.oose.lindt.maarten.services.dto;

import java.util.List;

public class PlaylistsDTO {
    private List<IncomingPlaylistBooleanDTO> playlists;
    private int length;

    public PlaylistsDTO(List<IncomingPlaylistBooleanDTO> playlists){
        this.playlists = playlists;
        this.length = 1234;
    }

    public List<IncomingPlaylistBooleanDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<IncomingPlaylistBooleanDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
