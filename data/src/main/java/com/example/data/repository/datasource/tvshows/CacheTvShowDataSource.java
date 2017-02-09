package com.example.data.repository.datasource.tvshows;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public interface CacheTvShowDataSource extends ReadableTvShowDataSource, WriteableTvShowDataSource {
    public long getTimeFromLastUpdateCheck(int page);
    public void setTimeFromLastUpdateCheck(int page);
}
