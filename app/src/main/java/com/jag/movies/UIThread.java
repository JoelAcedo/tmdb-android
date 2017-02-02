package com.jag.movies;

import android.os.Handler;
import android.os.Looper;

import com.example.executor.PostExecutionThread;

/**
 * Created by inlab on 02/02/2017.
 */

public class UIThread implements PostExecutionThread {

    private final Handler handler;

    public UIThread() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
