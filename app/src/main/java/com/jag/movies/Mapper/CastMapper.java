package com.jag.movies.Mapper;

import com.example.entities.Actor;
import com.jag.movies.Models.ActorViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert.Ruiz on 01/02/2017.
 */

public class CastMapper {
    public static List<ActorViewModel> toListActorViewModel(List<Actor> actorList) {
        List<ActorViewModel> actors = new ArrayList<>();
        for (Actor actor : actorList) {
            ActorViewModel actorViewModel = toActorViewModel(actor);
            actors.add(actorViewModel);
        }
        return actors;
    }

    public static ActorViewModel toActorViewModel(Actor actor) {
        return new ActorViewModel(actor.getName(), actor.getCharacter(), actor.getProfileUrl());
    }
}
