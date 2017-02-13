package com.jag.movies.UI.renderes.tvShows;

import com.jag.movies.Models.TvShowViewModel;
import com.jag.movies.dependencyinjector.qualifier.ForTvShows;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by joela on 12/02/2017.
 */

public class TvShowRendererBuilder extends RendererBuilder<TvShowViewModel> {

    @Inject
    public TvShowRendererBuilder(@ForTvShows Set<Renderer> prototipes) {
        super(new ArrayList(prototipes));
    }

    @Override
    protected Class getPrototypeClass(TvShowViewModel content) {
        Class prototypeClass;

        if (content.isFavorited()) {
            prototypeClass = TvShowFavoriteRenderer.class;
        } else {
            prototypeClass = TvShowRenderer.class;
        }

        return prototypeClass;
    }
}
