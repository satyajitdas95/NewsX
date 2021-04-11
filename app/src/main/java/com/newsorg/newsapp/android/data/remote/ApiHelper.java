

package com.newsorg.newsapp.android.data.remote;

import com.newsorg.newsapp.android.data.model.api.NewsResponse;

import io.reactivex.Single;

/**
 * Created by satyajit on 09/04/21.
 */

public interface ApiHelper {

    Single<NewsResponse.ArticleList> getTopHeadLines(String countryCode);

    Single<NewsResponse.LikesResponse> getLikes(String articleID);

    Single<NewsResponse.CommentsResponse> getComments(String articleID);
}
