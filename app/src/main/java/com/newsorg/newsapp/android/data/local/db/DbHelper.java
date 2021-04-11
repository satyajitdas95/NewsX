

package com.newsorg.newsapp.android.data.local.db;

import com.newsorg.newsapp.android.data.model.db.User;

import io.reactivex.Observable;

/**
 * Created by satyajit on 09/04/21.
 */

public interface DbHelper {
    Observable<Boolean> insertUser(final User user);

}
