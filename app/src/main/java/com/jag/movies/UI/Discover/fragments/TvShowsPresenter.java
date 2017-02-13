package com.jag.movies.UI.Discover.fragments;

import android.util.Log;
import android.widget.ImageView;

import com.example.entities.TvShow;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetTvShowByIdInteractor;
import com.example.interactor.GetTvShowListInteractor;
import com.example.repositories.TvShowRepository;
import com.jag.movies.Mapper.TvShowMapper;
import com.jag.movies.Models.TvShowViewModel;
import com.jag.movies.dependencyinjector.scope.PerFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joela on 12/02/2017.
 */
@PerFragment
public class TvShowsPresenter {

    private static final String TAG = "MoviePresenter";
    private final FragmentDiscoverView discoverView;
    private final GetTvShowListInteractor getTvShowListInteractor;
    private final GetTvShowByIdInteractor getTvShowByIdInteractor;
    private int page;
    private int position;
    private int tvShowId;

    @Inject
    public TvShowsPresenter(FragmentDiscoverView discoverView, GetTvShowListInteractor getTvShowListInteractor,
                            GetTvShowByIdInteractor getTvShowByIdInteractor) {
        this.discoverView = discoverView;
        this.getTvShowListInteractor = getTvShowListInteractor;
        this.getTvShowByIdInteractor = getTvShowByIdInteractor;
        this.page = 1;
        this.position = -1;
    }

    public void tvShowClicked(int tvShowId, int position, ImageView cover) {
        discoverView.startDetailActivity(tvShowId, cover);
        this.tvShowId = tvShowId;
        this.position = position;
    }

    public void onResume() {
        if (position > -1) {
            getTvShowByIdInteractor.execute(new TvShowRepository.GetTvShowByIdCallback() {
                @Override
                public void onError(ErrorBundle errorBundle) {
                    Log.e(TAG, errorBundle.getErrorMessage());
                }

                @Override
                public void onSuccess(TvShow returnParam) {
//                    Log.e(TAG, "onResumeSucces");
                    TvShowViewModel tvShow = TvShowMapper.toTvShowViewModel(returnParam);
                    discoverView.updateItemFavoritedState(tvShow, position);
                }
            }, tvShowId);
        } else {
            onCreate();
        }
    }

    public void onCreate() {
        getTvShowListInteractor.execute(new TvShowRepository.GetTvShowsCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<TvShow> returnParam) {
                List<TvShowViewModel> tvShowList = TvShowMapper.toListTvShowViewModel(returnParam);
                discoverView.showData(tvShowList);
            }
        }, page);
    }

    public void onLoadMore(int page) {
        this.page = page;
        getTvShowListInteractor.execute(new TvShowRepository.GetTvShowsCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<TvShow> returnParam) {
                List<TvShowViewModel> tvShowList = TvShowMapper.toListTvShowViewModel(returnParam);
                discoverView.addData(tvShowList);
            }
        }, page);
    }
}
