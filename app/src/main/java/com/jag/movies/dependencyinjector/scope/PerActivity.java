package com.jag.movies.dependencyinjector.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by inlab on 26/01/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
