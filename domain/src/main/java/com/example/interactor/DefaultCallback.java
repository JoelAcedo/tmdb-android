package com.example.interactor;

import com.example.exception.ErrorBundle;

/**
 * Created by inlab on 01/02/2017.
 */

public interface DefaultCallback<T> {

    void onError(ErrorBundle errorBundle);
    void onSuccess(T returnParam);
}

