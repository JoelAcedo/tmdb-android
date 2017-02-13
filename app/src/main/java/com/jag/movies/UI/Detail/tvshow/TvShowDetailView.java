package com.jag.movies.UI.Detail.tvshow;

import com.jag.movies.Models.EpisodeViewModel;
import com.jag.movies.Models.SeasonViewModel;
import com.jag.movies.UI.Detail.DetailView;

import java.util.List;

/**
 * Created by joela on 12/02/2017.
 */

public interface TvShowDetailView extends DetailView {

    void showSeasons(List<SeasonViewModel> seasonData);
    void showEpisodes(List<EpisodeViewModel> episodesData);
}
