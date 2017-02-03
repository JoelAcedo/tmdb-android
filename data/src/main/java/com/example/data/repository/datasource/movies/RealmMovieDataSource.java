package com.example.data.repository.datasource.movies;

import com.example.data.mapper.CastMapper;
import com.example.data.mapper.MovieMapper;
import com.example.data.realm.entities.MovieRealm;
import com.example.data.realm.util.RealmString;
import com.example.entities.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by inlab on 02/02/2017.
 */

public class RealmMovieDataSource implements CacheMovieDataSource {
    
    @Inject
    public RealmMovieDataSource() {
    }

    @Override
    public List<Movie> getMovies() throws IOException {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        RealmResults<MovieRealm> moviesRealm = realm.where(MovieRealm.class).findAll();

        List<Movie> movies = new ArrayList<>();
        for (MovieRealm movieRealm : moviesRealm) {
            movies.add(MovieMapper.fromMovieRealm(movieRealm));
        }

        realm.commitTransaction();
        return movies;
    }

    @Override
    public Movie getMovieById(int movieId) throws IOException {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MovieRealm movieRealm = realm.where(MovieRealm.class).equalTo(MovieRealm.MOVIE_ID_REALM, movieId)
                .findFirst();

        if (movieRealm == null) return null;

        Movie movie = MovieMapper.fromMovieRealm(movieRealm);
        realm.commitTransaction();
        return movie;
    }

    @Override
    public void saveMovies(List<Movie> movies) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for (Movie movie : movies) {
            RealmList<RealmString> realmStrings = new RealmList<>();
            for (String genre : movie.getGenresList()) {
                realmStrings.add(new RealmString(genre));
            }

            MovieRealm movieRealm = new MovieRealm(movie.getMovieId(), movie.getTitle(),
                    movie.getOverview(), movie.getVoteAverage(), movie.getReleaseDate(),
                    realmStrings, movie.getCoverUrl(), movie.isFavorited());
            realm.copyToRealm(movieRealm);
        }
        realm.commitTransaction();
    }

    @Override
    public void updateMovieFavorited(int movieId) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        MovieRealm movieRealm = realm.where(MovieRealm.class).equalTo(MovieRealm.MOVIE_ID_REALM, movieId)
                .findFirst();
        movieRealm.setFavorited(!movieRealm.isFavorited());
        realm.copyToRealm(movieRealm);
        realm.commitTransaction();

    }
}
