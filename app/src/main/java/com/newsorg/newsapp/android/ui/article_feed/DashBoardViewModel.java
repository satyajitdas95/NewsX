

package com.newsorg.newsapp.android.ui.article_feed;

import androidx.lifecycle.MutableLiveData;

import com.newsorg.newsapp.android.data.DataManager;
import com.newsorg.newsapp.android.data.model.api.NewsResponse;
import com.newsorg.newsapp.android.ui.base.BaseViewModel;
import com.newsorg.newsapp.android.utils.rx.SchedulerProvider;

import java.util.List;

/**
 * Created by satyajit on 09/04/21.
 */

public class DashBoardViewModel extends BaseViewModel<DashBoardNavigator> {

    private final MutableLiveData<List<NewsResponse.ArticleList.Article>> articleLiveData;

    public DashBoardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        articleLiveData = new MutableLiveData<>();
    }

    public void getTopHeadLines(String countryCode) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getTopHeadLines(countryCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().setUpArticleListRecyclerView();
                    getNavigator().doNothing();
                    articleLiveData.setValue(response.getArticles());
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    //getter setter
    public MutableLiveData<List<NewsResponse.ArticleList.Article>> getArticleLiveData() {
        return articleLiveData;
    }


}
