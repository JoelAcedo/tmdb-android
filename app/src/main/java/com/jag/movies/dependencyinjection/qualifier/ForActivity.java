package com.jag.movies.dependencyinjection.qualifier;

/**
 * Created by Albert.Ruiz on 26/01/2017.
 */

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForActivity {}