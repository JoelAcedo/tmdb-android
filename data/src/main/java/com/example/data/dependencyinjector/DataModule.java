package com.example.data.dependencyinjector;

import com.example.data.repository.CastDataRepository;
import com.example.data.repository.EpisodeDataRepository;
import com.example.data.repository.MovieDataRepository;
import com.example.data.repository.TvShowDataRepository;
import com.example.data.repository.datasource.actors.ApiCastDataSource;
import com.example.data.repository.datasource.actors.CacheCastDataSource;
import com.example.data.repository.datasource.actors.ReadableCastDataSource;
import com.example.data.repository.datasource.actors.RealmCastDataSource;
import com.example.data.repository.datasource.episodes.ApiEpisodeDataSource;
import com.example.data.repository.datasource.episodes.ReadableEpisodeDataSource;
import com.example.data.repository.datasource.movies.ApiMovieDataSource;
import com.example.data.repository.datasource.movies.CacheMovieDataSource;
import com.example.data.repository.datasource.movies.ReadableMovieDataSource;
import com.example.data.repository.datasource.movies.RealmMovieDataSource;
import com.example.data.repository.datasource.tvshows.ApiTvShowDataSource;
import com.example.data.repository.datasource.tvshows.CacheTvShowDataSource;
import com.example.data.repository.datasource.tvshows.ReadableTvShowDataSource;
import com.example.data.repository.datasource.tvshows.RealmTvShowDataSource;
import com.example.repositories.CastRepository;
import com.example.repositories.EpisodeRepository;
import com.example.repositories.MovieRepository;
import com.example.repositories.TvShowRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by inlab on 01/02/2017.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    public MovieRepository providesMovieDataRepository(MovieDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    public CastRepository providesCastDataRepository(CastDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    public TvShowRepository providesTvShowDataRepository(TvShowDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    public EpisodeRepository providesEpisodeDataRepository(EpisodeDataRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    public ReadableMovieDataSource providesMovieDataSource(ApiMovieDataSource apiDataSource){
        return apiDataSource;
    }

    @Provides
    @Singleton
    public CacheMovieDataSource providesCacheMovieDataSource(RealmMovieDataSource realmDataSource){
        return realmDataSource;
    }


    @Provides
    @Singleton
    public ReadableCastDataSource providesReadableCastDataSource(ApiCastDataSource apiDataSource){
        return apiDataSource;
    }

    @Provides
    @Singleton
    public CacheCastDataSource providesCacheCastDataSource(RealmCastDataSource realmDataSource){
        return realmDataSource;
    }


    @Provides
    @Singleton
    public ReadableTvShowDataSource providesTvShowDataSource(ApiTvShowDataSource apiDataSource){
        return apiDataSource;
    }

    @Provides
    @Singleton
    public CacheTvShowDataSource providesCacheTvShowDataSource(RealmTvShowDataSource realmDataSource){
        return realmDataSource;
    }

    @Provides
    @Singleton
    public ReadableEpisodeDataSource providesEpisodeDataSource(ApiEpisodeDataSource apiDataSource) {
        return apiDataSource;
    }

//    @Provides
//    @Singleton
//    public Realm providesRealm(@ForApp Context context){
//        Realm.init(context);
//        return Realm.getDefaultInstance();
//    }

    @Provides
    @Singleton
    public Realm providesRealm(){
        return Realm.getDefaultInstance();
    }
}
