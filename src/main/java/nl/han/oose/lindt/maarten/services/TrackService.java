package nl.han.oose.lindt.maarten.services;

import nl.han.oose.lindt.maarten.services.dto.PlaylistDTO;
import nl.han.oose.lindt.maarten.services.dto.TrackDTO;
import nl.han.oose.lindt.maarten.services.exceptions.MultipleItemsForIDException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrackService {

    List<TrackDTO> tracks = new ArrayList<TrackDTO>();
    TrackDTO trackTest;
    public TrackService(){
        tracks.add(new TrackDTO(3,"Ocean and a rock","Lisa Hannigan", 337,"Sea sew", null,null,null,false));
        tracks.add(new TrackDTO(4,"So Long, Marianne","Leonard Cohen",546,"Songs of Leonard Cohen", null,null,null,false));
        tracks.add(new TrackDTO(5,"One","Metallica",423, null, 37,"18-03-2001","Long version",true));
    }


    public List<TrackDTO> getAll(){
        return tracks;
    }

    //TODO hij get niet alles wat niet in de playlist staat maar wat niet dat id heeft.
    public List<TrackDTO> getAll(int id){
                return tracks.stream()
                .filter(track -> track.getId() != id)
                .collect(Collectors.toList());
    }

    public List<TrackDTO> getAllExcept(List<Integer> tracksInPlaylist) {
        List<TrackDTO> tracksToReturn = new ArrayList<TrackDTO>();
        for(TrackDTO trackToCheck: tracks){
          for(Integer trackToNotInclude: tracksInPlaylist){
                if(!tracksToReturn.contains(trackToCheck) && !(trackToCheck.equals(getTrack(trackToNotInclude)))){
                    tracksToReturn.add(trackToCheck);
                }

            }
        }
        for(TrackDTO track: tracksToReturn){
            System.out.println(track.getId());
        }
        return tracksToReturn;

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
}
