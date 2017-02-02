package com.example.interactor;

/**
 * Created by inlab on 01/02/2017.
 */

public interface Interactor<ReturnType, Parameter> extends Runnable {
    void run();

    <R extends DefaultCallback<ReturnType>> void execute(R defaultCallback, Parameter args);
}
