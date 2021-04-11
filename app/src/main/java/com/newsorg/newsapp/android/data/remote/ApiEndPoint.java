

package com.newsorg.newsapp.android.data.remote;


import com.newsorg.newsapp.android.BuildConfig;

/**
 * Created by satyajit on 09/04/21.
 */

public final class ApiEndPoint {

    public static final String ENDPOINT_TOP_HEAD_LINES = BuildConfig.BASE_URL + "/v2/top-headlines";

    public static final String ENDPOINT_EXTRA_DATA_LIKES = "https://cn-news-info-api.herokuapp.com/likes/";

    public static final String ENDPOINT_EXTRA_DATA_COMMENTS = "https://cn-news-info-api.herokuapp.com/comments/";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
