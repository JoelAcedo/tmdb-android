package com.example.data.dependencyinjector;

import com.example.data.repository.CastDataRepository;
import com.example.data.repository.MovieDataRepository;
import com.example.data.repository.datasource.ApiCastDataSource;
import com.example.data.repository.datasource.ApiMovieDataSource;
import com.example.data.repository.datasource.CastDataSource;
import com.example.data.repository.datasource.MovieDataSource;
import com.example.entities.Actor;
import com.example.repositories.CastRepository;
import com.example.repositories.MovieRepository;

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
    public MovieDataSource providesMovieDataSource(ApiMovieDataSource apiDataSource){
        return apiDataSource;
    }

    @Provides
    @Singleton
    public CastDataSource providesCastDataSource(ApiCastDataSource apiDataSource){
        return apiDataSource;
    }
}
