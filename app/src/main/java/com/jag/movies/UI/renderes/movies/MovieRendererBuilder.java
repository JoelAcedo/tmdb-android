package com.jag.movies.UI.renderes.movies;

import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.dependencyinjector.qualifier.ForMovies;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by inlab on 03/02/2017.
 */

public class MovieRendererBuilder extends RendererBuilder<MovieViewModel> {

    @Inject
    public MovieRendererBuilder(@ForMovies Set<Renderer> prototipes) {
        super(new ArrayList(prototipes));
    }

    @Override
    protected Class getPrototypeClass(MovieViewModel content) {
        Class prototypeClass;

        if (content.isFavorited()) {
            prototypeClass = MovieFavoriteRenderer.class;
        } else {
            prototypeClass = MovieRenderer.class;
        }

        return prototypeClass;
    }
}
