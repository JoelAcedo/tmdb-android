package com.jag.movies.Callbacks;

import com.jag.movies.Retrofit.ActorDTO;
import com.jag.movies.UI.MovieViewModel;

import java.util.ArrayList;

/**
 * Created by inlab on 31/01/2017.
 */

public interface ActorListCallback {

    void dataReady(ArrayList<ActorDTO> cast);
}
