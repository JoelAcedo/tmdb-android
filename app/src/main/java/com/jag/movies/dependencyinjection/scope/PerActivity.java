package com.jag.movies.dependencyinjection.scope;

/**
 * Created by Albert.Ruiz on 26/01/2017.
 */

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {}
