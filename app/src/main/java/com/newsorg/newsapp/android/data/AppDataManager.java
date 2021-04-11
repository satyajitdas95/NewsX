

package com.newsorg.newsapp.android.data;

import android.content.Context;

import com.google.gson.Gson;
import com.newsorg.newsapp.android.data.local.db.DbHelper;
import com.newsorg.newsapp.android.data.local.prefs.PreferencesHelper;
import com.newsorg.newsapp.android.data.model.api.NewsResponse;
import com.newsorg.newsapp.android.data.model.db.User;
import com.newsorg.newsapp.android.data.remote.ApiHelper;

import io.reactivex.Observable;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by satyajit on 09/04/21.
 */
@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }



    @Override
    public Single<NewsResponse.ArticleList> getTopHeadLines(String countryCode) {
        return mApiHelper.getTopHeadLines(countryCode);
    }

    @Override
    public Single<NewsResponse.LikesResponse> getLikes(String articleID) {
        return mApiHelper.getLikes(articleID);
    }

    @Override
    public Single<NewsResponse.CommentsResponse> getComments(String articleID) {
        return mApiHelper.getComments(articleID);
    }


    @Override
    public Observable<Boolean> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }


    @Override
    public void updateApiHeader(Long userId, String accessToken) {
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath) {

        setAccessToken(accessToken);
        updateApiHeader(userId, accessToken);
    }
}
