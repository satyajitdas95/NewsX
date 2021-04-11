package com.newsorg.newsapp.android.data;

import com.newsorg.newsapp.android.data.local.db.DbHelper;
import com.newsorg.newsapp.android.data.local.prefs.PreferencesHelper;
import com.newsorg.newsapp.android.data.remote.ApiHelper;

/**
 * Created by satyajit on 09/04/21.
 */

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void updateApiHeader(Long userId, String accessToken);

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
