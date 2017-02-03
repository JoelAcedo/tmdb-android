package com.jag.movies.UI.Discover;

import android.util.Log;
import android.widget.ImageView;

import com.example.entities.Movie;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetMovieListInteractor;
import com.example.repositories.MovieRepository;
import com.jag.movies.Mapper.MovieMapper;
import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class DiscoverPresenter {

    private static final String TAG = "DiscoverPresenter";
    private final DiscoverView discoverView;
    private final GetMovieListInteractor getMovieListInteractor;

    @Inject
    public DiscoverPresenter(DiscoverView discoverView, GetMovieListInteractor getMovieListInteractor) {
        this.discoverView = discoverView;
        this.getMovieListInteractor = getMovieListInteractor;
    }

    public void movieClicked(int movieId, ImageView movieCover) {
        discoverView.startDetailActivity(movieId, movieCover);
    }


    public void onStart() {
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
            }, null);
    }
}
