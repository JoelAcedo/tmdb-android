package com.jag.movies.UI.Discover;

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

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class DiscoverPresenter {

    //TODO Seria buena idea guardar la posicion y la movieId junto con el lifecycle de el activity?

    private static final String TAG = "DiscoverPresenter";
    private final DiscoverView discoverView;
    private final GetMovieListInteractor getMovieListInteractor;
    private final GetMovieByIdInteractor getMovieByIdInteractor;
    private int page;
    private int position;
    private int movieId;

    @Inject
    public DiscoverPresenter(DiscoverView discoverView, GetMovieListInteractor getMovieListInteractor, GetMovieByIdInteractor getMovieByIdInteractor) {
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
//        Log.d(TAG, "OnClick (position): " + String.valueOf(this.position));
//        Log.d(TAG, "OnClick (movieId): " + String.valueOf(this.movieId));
    }

    public void onResume() {
//        Log.d(TAG, "OnResume (position): " + String.valueOf(this.position));
//        Log.d(TAG, "OnResume (movieId): " + String.valueOf(this.movieId));
        if (position > -1) {
            getMovieByIdInteractor.execute(new MovieRepository.GetMovieByIdCallback() {
                @Override
                public void onError(ErrorBundle errorBundle) {
                    Log.e(TAG, errorBundle.getErrorMessage());
                }

                @Override
                public void onSuccess(Movie returnParam) {
                    MovieViewModel movie = MovieMapper.toMovieViewModel(returnParam);
                    discoverView.updateMovieFavoritedState(movie, position);
                }
            }, movieId);
        }
    }


    public void onCreate() {
            getMovieListInteractor.execute(new MovieRepository.GetMoviesCallback() {
                @Override
                public void onError(ErrorBundle errorBundle) {
                    Log.e(TAG, errorBundle.getErrorMessage());
                    // TODO pasar lista con un elemento de error;
        //            discoverView.showMovies(new ArrayList<MovieViewModel>());
                }

                @Override
                public void onSuccess(List<Movie> returnParam) {
                    List<MovieViewModel> movieList = MovieMapper.toListMovieViewModel(returnParam);
                    discoverView.showMovies(movieList);
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
                discoverView.addMovies(movieList);
            }
        }, page);

    }
}
