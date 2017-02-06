package com.jag.movies.UI.renderes;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

/**
 * Created by joela on 04/02/2017.
 */

public abstract class RendererWithItemPosition<T> extends Renderer<T> {

    private int position;

    public void setItemPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
