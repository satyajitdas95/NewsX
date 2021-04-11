

package com.newsorg.newsapp.android.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.newsorg.newsapp.android.data.local.db.dao.UserDao;
import com.newsorg.newsapp.android.data.model.db.User;

/**
 * Created by satyajit on 09/04/21.
 */

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
