package com.example.data.repository.datasource.movies;

import com.example.data.mapper.MovieMapper;
import com.example.data.realm.entities.MovieRealm;
import com.example.data.realm.util.RealmString;
import com.example.data.realm.util.LastUpdateTimeByPageRealm;
import com.example.data.repository.MovieDataRepository;
import com.example.entities.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.example.data.realm.util.LastUpdateTimeByPageRealm.PAGE_ID_REALM;

/**
 * Created by inlab on 02/02/2017.
 */

public class RealmMovieDataSource implements CacheMovieDataSource {
    
    @Inject
    public RealmMovieDataSource() {
    }

    @Override
    public List<Movie> getMoviesByPage(int page) throws IOException {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        RealmResults<MovieRealm> moviesRealm = realm.where(MovieRealm.class)
                .findAllSorted(MovieRealm.MOVIE_POPULARITY_REALM, Sort.DESCENDING);

        List<Movie> movies = new ArrayList<>();
        int maxSize = Math.min(moviesRealm.size(), MovieDataRepository.RESULTS_FOR_PAGE * page);
        for (int i = (MovieDataRepository.RESULTS_FOR_PAGE * (page-1)); i < maxSize; i++) {
            MovieRealm movieRealm = moviesRealm.get(i);
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
                    realmStrings, movie.getCoverUrl(), movie.isFavorited(), movie.getPopularity());

            realm.copyToRealmOrUpdate(movieRealm);
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


    @Override
    public long getTimeFromLastUpdateCheck(int page) {
        Realm realm = Realm.getDefaultInstance();

        Long timestamp;
        realm.beginTransaction();
        LastUpdateTimeByPageRealm lastUpdateTimeByPageRealm = realm.where(LastUpdateTimeByPageRealm.class).equalTo(PAGE_ID_REALM, page).findFirst();
        if (lastUpdateTimeByPageRealm == null) {
            timestamp = 0l;
        }
        else {
            timestamp = lastUpdateTimeByPageRealm.getTimestampInMilis();
        }
        realm.commitTransaction();

        return timestamp;
    }

    @Override
    public void setTimeFromLastUpdateCheck(int page) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        LastUpdateTimeByPageRealm lastUpdateTimeByPageRealm = new LastUpdateTimeByPageRealm(System.currentTimeMillis(), page);
        realm.copyToRealmOrUpdate(lastUpdateTimeByPageRealm);
        realm.commitTransaction();
    }
}
