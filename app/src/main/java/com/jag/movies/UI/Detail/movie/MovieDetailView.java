package com.jag.movies.UI.Detail.movie;

import com.jag.movies.Models.ActorViewModel;
import com.jag.movies.UI.Detail.DetailView;

import java.util.List;

/**
 * Created by joela on 12/02/2017.
 */

public interface MovieDetailView extends DetailView {

    void showCast(List<ActorViewModel> castData);
}
