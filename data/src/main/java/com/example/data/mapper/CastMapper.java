package com.example.data.mapper;

import com.example.data.entities.ActorDTO;
import com.example.entities.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public class CastMapper {

    public static List<Actor> fromActorListDTO(List<ActorDTO> actorDTOList) {
        List<Actor> actors = new ArrayList<>();
        for (ActorDTO actorDTO : actorDTOList) {
            Actor actor = fromActorDTO(actorDTO);
            actors.add(actor);
        }
        return actors;
    }

    public static Actor fromActorDTO(ActorDTO actorDTO) {
        return new Actor(actorDTO.getName(), actorDTO.getCharacter(), actorDTO.getProfile_path());
    }

}
