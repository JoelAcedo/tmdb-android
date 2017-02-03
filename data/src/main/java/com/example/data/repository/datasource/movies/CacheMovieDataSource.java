package com.example.data.repository.datasource.movies;

/**
 * Created by inlab on 02/02/2017.
 */

public interface CacheMovieDataSource extends ReadableMovieDataSource, WriteableMovieDataSource {

    public long getTimeFromLastUpdateCheck();
    public void setTimeFromLastUpdateCheck();
}
