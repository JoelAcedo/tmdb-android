package com.jag.movies.UI.Detail.tvshow;

import android.content.Intent;
import android.util.Log;

import com.example.entities.Episode;
import com.example.entities.TvShow;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetEpisodeInteractor;
import com.example.interactor.GetTvShowByIdInteractor;
import com.example.interactor.UpdateTvShowFavoritedInteractor;
import com.example.repositories.EpisodeRepository;
import com.example.repositories.TvShowRepository;
import com.jag.movies.Mapper.EpisodeMapper;
import com.jag.movies.Mapper.TvShowMapper;
import com.jag.movies.Models.EpisodeViewModel;
import com.jag.movies.Models.TvShowViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joela on 13/02/2017.
 */

@PerActivity
public class TvShowDetailPresenter {

    private static final String TAG = "TvShowDetailPresenter";
    private final TvShowDetailView tvShowDetailView;
    private final GetTvShowByIdInteractor getTvShowByIdInteractor;
    private final UpdateTvShowFavoritedInteractor updateTvShowFavoritedInteractor;
    private final GetEpisodeInteractor getEpisodeInteractor;
    private TvShowViewModel tvShowViewModel;
    private int tvShowId;

    @Inject
    public TvShowDetailPresenter(TvShowDetailView tvShowDetailView, GetTvShowByIdInteractor getTvShowByIdInteractor,
                                 UpdateTvShowFavoritedInteractor updateTvShowFavoritedInteractor,
                                 GetEpisodeInteractor getEpisodeInteractor) {
        this.tvShowDetailView = tvShowDetailView;
        this.getTvShowByIdInteractor = getTvShowByIdInteractor;
        this.updateTvShowFavoritedInteractor = updateTvShowFavoritedInteractor;
        this.getEpisodeInteractor = getEpisodeInteractor;
    }

    public void onStart(Intent intent) {
        getExtrasFromIntent(intent);
        getTvShowDataByID();
    }

    private void getExtrasFromIntent(Intent intent) {
        if (intent != null) {
            tvShowId = intent.getIntExtra(TvShowDetailActivity.ID_TVSHOW, 0);
        }
        else {
            tvShowId = 0;
        }
    }

    public void getTvShowDataByID() {
        getTvShowByIdInteractor.execute(new TvShowRepository.GetTvShowByIdCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(TvShow returnParam) {
                tvShowViewModel = TvShowMapper.toTvShowViewModel(returnParam);
                tvShowDataReady();
            }
        }, tvShowId);
    }

    private void tvShowDataReady() {
        tvShowDetailView.renderCover(tvShowViewModel.getCoverUrl());
        tvShowDetailView.renderTitle(tvShowViewModel.getName());
        tvShowDetailView.renderOverview(tvShowViewModel.getOverview());
        tvShowDetailView.renderGenres(tvShowViewModel.getGenresList());
        tvShowDetailView.renderScore(tvShowViewModel.getVoteAverage());
        tvShowDetailView.renderReleaseDate(tvShowViewModel.getReleaseDate());
        tvShowDetailView.showSeasons(tvShowViewModel.getSeasons());

        if (tvShowViewModel.isFavorited()) {
            tvShowDetailView.setFloatingButtonFavorited();
        } else {
            tvShowDetailView.setFloatingButtonNotFavorited();
        }
    }

    public void floatingButtonClicked() {
        if (tvShowViewModel.isFavorited()) {
            tvShowViewModel.setFavorite(false);
            tvShowDetailView.setFloatingButtonNotFavorited();
        } else {
            tvShowViewModel.setFavorite(true);
            tvShowDetailView.setFloatingButtonFavorited();
        }
        updateTvShowFavoritedInteractor.execute(null, tvShowId);
    }

    public void updateVibrantColor(int vibrantColor) {
        tvShowDetailView.renderColors(vibrantColor);
    }

    public void seasonSelected(int seasonNumber) {

        /* args = { tvShowId, seasonNumber} */
        Integer[] args = {tvShowId, seasonNumber};
        getEpisodeInteractor.execute(new EpisodeRepository.GetEpisodesCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<Episode> returnParam) {
                List<EpisodeViewModel> episodes = EpisodeMapper.toListEpisodeViewModel(returnParam);
                tvShowDetailView.showEpisodes(episodes);
            }
        }, args);
    }
}
