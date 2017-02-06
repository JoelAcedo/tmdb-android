package com.example.data.realm.util;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by joela on 03/02/2017.
 */

public class LastUpdateTimeByPageRealm extends RealmObject {
    @Ignore
    public static final String PAGE_ID_REALM = "pageId";

    @PrimaryKey
    int pageId;

    Long timestampInMilis;

    public LastUpdateTimeByPageRealm() {
    }

    public LastUpdateTimeByPageRealm(Long timestampInMilis, int pageId) {
        this.timestampInMilis = timestampInMilis;
        this.pageId = pageId;
    }

    public Long getTimestampInMilis() {
        return timestampInMilis;
    }

    public void setTimestampInMilis(Long timestampInMilis) {
        this.timestampInMilis = timestampInMilis;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
