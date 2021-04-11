package com.newsorg.newsapp.android.di.module;


import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.newsorg.newsapp.android.ViewModelProviderFactory;
import com.newsorg.newsapp.android.data.DataManager;
import com.newsorg.newsapp.android.ui.article_details.ArticleDetailsViewModel;
import com.newsorg.newsapp.android.ui.base.BaseActivity;
import com.newsorg.newsapp.android.ui.article_feed.DashBoardAdapter;
import com.newsorg.newsapp.android.ui.article_feed.DashBoardViewModel;
import com.newsorg.newsapp.android.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/*
 * Author: rotbolt
 */

@Module
public class ActivityModule {
    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Provides
    DashBoardAdapter provideDashBoardAdapter() {
        return new DashBoardAdapter(activity, new ArrayList<>());
    }

    @Provides
    DashBoardViewModel provideDashBoardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<DashBoardViewModel> supplier = () -> new DashBoardViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<DashBoardViewModel> factory = new ViewModelProviderFactory<>(DashBoardViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(DashBoardViewModel.class);
    }


    @Provides
    ArticleDetailsViewModel provideArticleDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<ArticleDetailsViewModel> supplier = () -> new ArticleDetailsViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<ArticleDetailsViewModel> factory = new ViewModelProviderFactory<>(ArticleDetailsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ArticleDetailsViewModel.class);
    }

}
