package com.example.data.expception;

import com.example.exception.ErrorBundle;

/**
 * Created by inlab on 03/02/2017.
 */

public class DataErrorBundle implements ErrorBundle {

    private Exception exception;

    public DataErrorBundle(Exception e) {
        exception = e;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        return exception.getMessage();
    }
}
