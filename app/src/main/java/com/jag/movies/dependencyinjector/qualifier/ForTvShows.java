package com.jag.movies.dependencyinjector.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by joela on 12/02/2017.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForTvShows {
}
