package com.example.data.repository.datasource.actors;

import com.example.data.mapper.CastMapper;
import com.example.data.realm.entities.ActorRealm;
import com.example.entities.Actor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by inlab on 02/02/2017.
 */

public class RealmCastDataSource implements CacheCastDataSource {

    @Inject
    public RealmCastDataSource() {}

    @Override
    public List<Actor> getCastByMovieId(int movieId) throws IOException {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<ActorRealm> castRealm = realm.where(ActorRealm.class)
                .equalTo(ActorRealm.MOVIE_ID_REALM, movieId).findAll();

        List<Actor> actors = new ArrayList<>();
        for (ActorRealm actorRealm : castRealm) {
            actors.add(CastMapper.fromActorRealm(actorRealm));
        }

        return actors;
    }

    @Override
    public void saveCast(final List<Actor> actors, int movieId) {
        //Realm realm = RealmClient.getDefaultInstance();
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for (Actor actor : actors) {
            ActorRealm actorRealm = new ActorRealm(actor.getName(), actor.getCharacter(),
                    actor.getProfileUrl(), movieId);
            realm.copyToRealm(actorRealm);
        }
        realm.commitTransaction();
    }
}
