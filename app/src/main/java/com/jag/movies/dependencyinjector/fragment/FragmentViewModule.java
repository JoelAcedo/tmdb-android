package com.jag.movies.dependencyinjector.fragment;

import com.jag.movies.UI.Discover.fragments.FragmentDiscoverView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joela on 12/02/2017.
 */

@Module
public class FragmentViewModule {

    private FragmentDiscoverView fragmentDiscoverView;

    public FragmentViewModule(FragmentDiscoverView fragmentDiscoverView) {
        this.fragmentDiscoverView = fragmentDiscoverView;
    }

    @Provides
    FragmentDiscoverView providesFragmentDiscoverView() {
        return fragmentDiscoverView;
    }
}
