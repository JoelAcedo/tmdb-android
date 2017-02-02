package com.example.data.dependencyinjector;

import android.content.Context;

import com.example.data.repository.CastDataRepository;
import com.example.data.repository.MovieDataRepository;
import com.example.data.repository.datasource.ApiCastDataSource;
import com.example.data.repository.datasource.ApiMovieDataSource;
import com.example.data.repository.datasource.CacheCastDataSource;
import com.example.data.repository.datasource.ReadableCastDataSource;
import com.example.data.repository.datasource.MovieDataSource;
import com.example.data.repository.datasource.RealmCastDataSource;
import com.example.data.repository.datasource.WriteableCastDataSource;
import com.example.repositories.CastRepository;
import com.example.repositories.MovieRepository;

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
    public MovieDataSource providesMovieDataSource(ApiMovieDataSource apiDataSource){
        return apiDataSource;
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
