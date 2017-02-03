package com.example.data.realm.util;

import io.realm.RealmObject;

/**
 * Created by inlab on 02/02/2017.
 */

public class RealmString extends RealmObject {
    private String string;

    public RealmString() {
    }

    public RealmString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
