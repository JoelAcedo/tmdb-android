package com.example.data.dependencyinjector;

import com.example.data.repository.CastDataRepository;
import com.example.data.repository.MovieDataRepository;
import com.example.data.repository.datasource.ApiCastDataSource;
import com.example.data.repository.datasource.ApiMovieDataSource;
import com.example.data.repository.datasource.CastDataSource;
import com.example.data.repository.datasource.MovieDataSource;
import com.example.entities.Actor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by inlab on 01/02/2017.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    public MovieDataRepository providesMovieDataRepository(MovieDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    public CastDataRepository providesCastDataRepository(CastDataRepository repository){
        return repository;
    }


    @Provides
    @Singleton
    public MovieDataSource providesMovieDataSource(ApiMovieDataSource apiDataSource){
        return apiDataSource;
    }

    @Provides
    @Singleton
    public CastDataSource providesMovieDataSource(ApiCastDataSource apiDataSource){
        return apiDataSource;
    }
}
