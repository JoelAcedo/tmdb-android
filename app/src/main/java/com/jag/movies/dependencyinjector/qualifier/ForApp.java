package com.jag.movies.dependencyinjector.qualifier;

/**
 * Created by inlab on 26/01/2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApp {
}
