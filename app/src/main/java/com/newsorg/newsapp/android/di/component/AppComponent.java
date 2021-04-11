

package com.newsorg.newsapp.android.di.component;

import android.app.Application;
import com.newsorg.newsapp.android.NewsApp;
import com.newsorg.newsapp.android.data.DataManager;

import com.newsorg.newsapp.android.di.module.AppModule;
import com.newsorg.newsapp.android.utils.rx.SchedulerProvider;

import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by satyajit on 09/04/21.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(NewsApp app);

    DataManager getDataManager();

    SchedulerProvider getSchedulerProvider();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
