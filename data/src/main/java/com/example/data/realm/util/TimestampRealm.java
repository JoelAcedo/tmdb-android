package com.example.data.realm.util;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by joela on 03/02/2017.
 */

public class TimestampRealm extends RealmObject {

    @Ignore
    private final static int ID = 1;

    @PrimaryKey
    int uniqueId;

    Long timestampInMilis;

    public TimestampRealm() {
    }

    public TimestampRealm(Long timestampInMilis) {
        this.timestampInMilis = timestampInMilis;
        uniqueId = ID;
    }

    public Long getTimestampInMilis() {
        return timestampInMilis;
    }

    public void setTimestampInMilis(Long timestampInMilis) {
        this.timestampInMilis = timestampInMilis;
    }
}
