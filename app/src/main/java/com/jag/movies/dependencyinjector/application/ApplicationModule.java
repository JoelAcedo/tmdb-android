package com.jag.movies.dependencyinjector.application;

import android.content.Context;

import com.example.data.dependencyinjector.DataModule;
import com.example.data.executor.JobExecutor;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.jag.movies.App;
import com.jag.movies.UIThread;
import com.jag.movies.Utils.PicassoLoader;
import com.jag.movies.dependencyinjector.qualifier.ForApp;
import com.jag.movies.Utils.GlideLoader;
import com.jag.movies.Utils.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                DataModule.class,
        }
)
public class ApplicationModule {
    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ForApp
    public Context providesContext() {
        return app;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(GlideLoader imageLoader) {
        return imageLoader;
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }
}
