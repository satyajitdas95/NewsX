

package com.newsorg.newsapp.android.di.module;

import android.app.Application;

import androidx.room.Room;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newsorg.newsapp.android.BuildConfig;
import com.newsorg.newsapp.android.R;
import com.newsorg.newsapp.android.data.AppDataManager;
import com.newsorg.newsapp.android.data.DataManager;
import com.newsorg.newsapp.android.data.local.db.AppDatabase;
import com.newsorg.newsapp.android.data.local.db.AppDbHelper;
import com.newsorg.newsapp.android.data.local.db.DbHelper;
import com.newsorg.newsapp.android.data.local.prefs.AppPreferencesHelper;
import com.newsorg.newsapp.android.data.local.prefs.PreferencesHelper;
import com.newsorg.newsapp.android.data.remote.ApiHeader;
import com.newsorg.newsapp.android.data.remote.ApiHelper;
import com.newsorg.newsapp.android.data.remote.AppApiHelper;
import com.newsorg.newsapp.android.di.ApiInfo;
import com.newsorg.newsapp.android.di.DatabaseInfo;
import com.newsorg.newsapp.android.di.PreferenceInfo;
import com.newsorg.newsapp.android.utils.AppConstants;
import com.newsorg.newsapp.android.utils.rx.AppSchedulerProvider;
import com.newsorg.newsapp.android.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

import javax.inject.Singleton;

/**
 * Created by satyajit on 09/04/21.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    ViewPump provideCalligraphyDefaultConfig() {
        return new ViewPump.Builder().addInterceptor(new CalligraphyInterceptor(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()))
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey
    ) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey);
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
