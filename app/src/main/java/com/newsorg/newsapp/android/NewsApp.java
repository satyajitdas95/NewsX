

package com.newsorg.newsapp.android;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.newsorg.newsapp.android.di.component.AppComponent;
import com.newsorg.newsapp.android.di.component.DaggerAppComponent;
import com.newsorg.newsapp.android.utils.AppLogger;

import javax.inject.Inject;

import io.github.inflationx.viewpump.ViewPump;

/**
 * Created by satyajit on 09/04/21.
 */

public class NewsApp extends Application {

    public AppComponent appComponent;

    @Inject
    ViewPump mCalligraphyConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

        appComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        ViewPump.init(mCalligraphyConfig);
    }
}
