package com.example.data.mapper;

import com.example.data.realm.entities.ActorRealm;
import com.example.data.realm.entities.MovieRealm;
import com.example.data.realm.util.RealmString;
import com.example.data.retrofit.entities.ActorDTO;
import com.example.entities.Actor;
import com.example.entities.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public class CastMapper {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w600";

    public static List<Actor> fromActorListDTO(List<ActorDTO> actorDTOList) {
        List<Actor> actors = new ArrayList<>();
        for (ActorDTO actorDTO : actorDTOList) {
            Actor actor = fromActorDTO(actorDTO);
            actors.add(actor);
        }
        return actors;
    }

    public static Actor fromActorDTO(ActorDTO actorDTO) {
        return new Actor(actorDTO.getName(), actorDTO.getCharacter(), BASE_IMAGE_URL + actorDTO.getProfile_path());
    }

    public static Actor fromActorRealm(ActorRealm actorRealm) {
        return new Actor(actorRealm.getName(), actorRealm.getCharacter(), actorRealm.getProfile_path());
    }

    public static Movie fromMovieRealm(MovieRealm movieRealm) {
        List<String> genres = new ArrayList<>();
        for (RealmString realmString : movieRealm.getGenreIds()) {
            genres.add(realmString.getString());
        }

        return new Movie(movieRealm.getId(), movieRealm.getTitle(), movieRealm.getOverview(),
                movieRealm.getVoteAverage(), movieRealm.getReleaseDate(), genres,
                movieRealm.getPosterPath());
    }
}
