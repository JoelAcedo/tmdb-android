package com.jag.movies.UI.renderes;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joela on 04/02/2017.
 */

public class RendererAdapterWithItemPosition<T> extends RVRendererAdapter<T> {


    public RendererAdapterWithItemPosition(RendererBuilder<T> rendererBuilder) {
        super(rendererBuilder);
    }

    public RendererAdapterWithItemPosition(RendererBuilder<T> rendererBuilder, AdapteeCollection<T> collection) {
        super(rendererBuilder, collection);
    }

    @Override
    protected void updateRendererExtraValues(T content, Renderer<T> renderer, int position) {
        ((RendererWithItemPosition) renderer).setItemPosition(position);
    }
}
