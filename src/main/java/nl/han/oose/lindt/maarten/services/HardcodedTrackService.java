package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.dto.TracksDTO;
import nl.han.oose.lindt.maarten.services.exceptions.MultipleItemsForIDException;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class HardcodedTrackService implements TrackService {

    List<TrackDTO> tracks = new ArrayList<TrackDTO>();
    TrackDTO trackTest;
    public HardcodedTrackService(){
        tracks.add(new TrackDTO(3,"Ocean and a rock","Lisa Hannigan", 337,"Sea sew", null,null,null,false));
        tracks.add(new TrackDTO(4,"So Long, Marianne","Leonard Cohen",546,"Songs of Leonard Cohen", null,null,null,false));
        tracks.add(new TrackDTO(5,"One","Metallica",423, null, 37,"18-03-2001","Long version",true));
    }


    @Override
    public TracksDTO getAllTracks(){
        return new TracksDTO(tracks);
    }

    @Override
    public TracksDTO getAllTracksNotInCurrentPlaylist(int id) {
       // todo implementeren
        return null;
    }



    public TrackDTO getTrack(int id){
        TrackDTO returnTrack = null;
        for(TrackDTO track: tracks){
            if(track.getId() == id){
                if(returnTrack == null){
                    returnTrack = track;
                } else{
                    throw new MultipleItemsForIDException();
                }
            }
        }

        return returnTrack;
    }

    @Override
    public void checkTrack(TrackDTO incomingTrack, int idOfPlaylist) {
        boolean checkOKE = false;
        for(TrackDTO checkingTrack: tracks){
            boolean isGelijk = checkingTrack.checkTrack(incomingTrack);
            if(isGelijk ){
                if(!checkOKE) {
                    checkOKE = true;
                }else{
                    throw new MultipleItemsForIDException();
                }
            }
        }

    }
}
