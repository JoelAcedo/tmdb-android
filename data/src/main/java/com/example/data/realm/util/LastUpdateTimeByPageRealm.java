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

    @Ignore
    public static final String PAGE_TYPE = "pageType";

    @Ignore
    public static final int MOVIE_PAGE = 0;

    @Ignore
    public static final int TVSHOW_PAGE = 1;

    @PrimaryKey // format: PAGETYPE_PAGEID
    String pageId;

    Long timestampInMilis;

    public LastUpdateTimeByPageRealm() {
    }

    public LastUpdateTimeByPageRealm(Long timestampInMilis, int pageId, int pageType) {
        this.timestampInMilis = timestampInMilis;
        this.pageId = String.valueOf(pageType) + "_" + String.valueOf(pageId);
    }

    public Long getTimestampInMilis() {
        return timestampInMilis;
    }

    public void setTimestampInMilis(Long timestampInMilis) {
        this.timestampInMilis = timestampInMilis;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public static String getCompoundId(int pageId, int pageType) {
        return String.valueOf(pageType) + "_" + String.valueOf(pageId);
    }
}
