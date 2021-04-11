

package com.newsorg.newsapp.android.data.remote;

import com.newsorg.newsapp.android.data.model.api.NewsResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by satyajit on 09/04/21.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public Single<NewsResponse.ArticleList> getTopHeadLines(String countryCode) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_TOP_HEAD_LINES)
                .addQueryParameter("country", countryCode)
                .addQueryParameter("apiKey", mApiHeader.getProtectedApiHeader().getApiKey())
                .build()
                .getObjectSingle(NewsResponse.ArticleList.class);
    }

    @Override
    public Single<NewsResponse.LikesResponse> getLikes(String articleID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXTRA_DATA_LIKES + articleID)
                .build()
                .getObjectSingle(NewsResponse.LikesResponse.class);
    }

    @Override
    public Single<NewsResponse.CommentsResponse> getComments(String articleID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXTRA_DATA_COMMENTS + articleID)
                .build()
                .getObjectSingle(NewsResponse.CommentsResponse.class);
    }
}
