package com.jag.movies.UI.Discover.fragments;

import android.util.Log;
import android.widget.ImageView;

import com.example.entities.Movie;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetMovieByIdInteractor;
import com.example.interactor.GetMovieListInteractor;
import com.example.repositories.MovieRepository;
import com.jag.movies.Mapper.MovieMapper;
import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;
import com.jag.movies.dependencyinjector.scope.PerFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joela on 12/02/2017.
 */
@PerFragment
public class MoviesPresenter {

    private static final String TAG = "MoviePresenter";
    private final FragmentDiscoverView discoverView;
    private final GetMovieListInteractor getMovieListInteractor;
    private final GetMovieByIdInteractor getMovieByIdInteractor;
    private int page;
    private int position;
    private int movieId;

    @Inject
    public MoviesPresenter(FragmentDiscoverView discoverView, GetMovieListInteractor getMovieListInteractor, GetMovieByIdInteractor getMovieByIdInteractor) {
        this.discoverView = discoverView;
        this.getMovieListInteractor = getMovieListInteractor;
        this.getMovieByIdInteractor = getMovieByIdInteractor;
        this.page = 1;
        this.position = -1;
    }

    public void movieClicked(int movieId, int position, ImageView movieCover) {
        discoverView.startDetailActivity(movieId, movieCover);
        this.movieId = movieId;
        this.position = position;
    }

    public void onResume() {
//        Log.e(TAG, "onResume");
        if (position > -1) {
            getMovieByIdInteractor.execute(new MovieRepository.GetMovieByIdCallback() {
                @Override
                public void onError(ErrorBundle errorBundle) {
                    Log.e(TAG, errorBundle.getErrorMessage());
                }

                @Override
                public void onSuccess(Movie returnParam) {
//                    Log.e(TAG, "onResumeSucces");
                    MovieViewModel movie = MovieMapper.toMovieViewModel(returnParam);
                    discoverView.updateItemFavoritedState(movie, position);
                }
            }, movieId);
        } else {
            onCreate();
        }
    }


    public void onCreate() {
        getMovieListInteractor.execute(new MovieRepository.GetMoviesCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<Movie> returnParam) {
                List<MovieViewModel> movieList = MovieMapper.toListMovieViewModel(returnParam);
                discoverView.showData(movieList);
            }
        }, page);
    }

    public void onLoadMore(int page) {
        this.page = page;
        getMovieListInteractor.execute(new MovieRepository.GetMoviesCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<Movie> returnParam) {
                List<MovieViewModel> movieList = MovieMapper.toListMovieViewModel(returnParam);
                discoverView.addData(movieList);
            }
        }, page);

    }
}
