package com.newsorg.newsapp.android.di.component;

import com.newsorg.newsapp.android.di.module.ActivityModule;
import com.newsorg.newsapp.android.di.scope.ActivityScope;
import com.newsorg.newsapp.android.ui.article_details.ArticleDetailsActivity;
import com.newsorg.newsapp.android.ui.article_feed.DashBoardActivity;

import dagger.Component;

/*
 * Author: rotbolt
 */

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(DashBoardActivity activity);

    void inject(ArticleDetailsActivity activity);


}
