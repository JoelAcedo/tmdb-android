package com.jag.movies.UI;


import android.content.Intent;

import java.util.List;

public interface IDetailView {

    void setFloatingButtonNotFavorited();

    void setFloatingButtonFavorited();

    void renderCover(String coverUrl);

    void renderTitle(String title);

    void renderOverview(String overview);

    void renderGenres(List<String> genresList);

    void renderScore(float voteAverage);

    void renderReleaseDate(String releaseDate);

    void renderToolbarColor();
}
