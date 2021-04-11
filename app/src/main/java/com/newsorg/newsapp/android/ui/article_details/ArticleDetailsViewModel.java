

package com.newsorg.newsapp.android.ui.article_details;

import androidx.databinding.ObservableField;

import com.newsorg.newsapp.android.data.DataManager;
import com.newsorg.newsapp.android.ui.base.BaseViewModel;
import com.newsorg.newsapp.android.utils.rx.SchedulerProvider;

/**
 * Created by satyajit on 09/04/21.
 */

public class ArticleDetailsViewModel extends BaseViewModel<ArticleDetailsNavigator> {

    private ObservableField<String> articleTitle = new ObservableField<>("");
    private ObservableField<String> articleImage = new ObservableField<>("");
    private ObservableField<String> articleDescription = new ObservableField<>("");
    private ObservableField<String> articleContent = new ObservableField<>("");
    private ObservableField<String> articleAuthor = new ObservableField<>("");
    private ObservableField<String> articleSource = new ObservableField<>("");
    private ObservableField<String> articleLink = new ObservableField<>("");
    private ObservableField<String> articleLikes = new ObservableField<>("");
    private ObservableField<String> articleComments = new ObservableField<>("");


    public ArticleDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getAllLikes(String articleID) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getLikes(articleID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    articleLikes.set(response.getLikes());
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void getAllComments(String articleID) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getComments(articleID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    articleComments.set(response.getComments());
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }


    //getter setter


    public ObservableField<String> getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(ObservableField<String> articleTitle) {
        this.articleTitle = articleTitle;
    }

    public ObservableField<String> getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(ObservableField<String> articleImage) {
        this.articleImage = articleImage;
    }

    public ObservableField<String> getArticleLikes() {
        return articleLikes;
    }

    public void setArticleLikes(ObservableField<String> articleLikes) {
        this.articleLikes = articleLikes;
    }

    public ObservableField<String> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(ObservableField<String> articleComments) {
        this.articleComments = articleComments;
    }

    public ObservableField<String> getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(ObservableField<String> articleDescription) {
        this.articleDescription = articleDescription;
    }

    public ObservableField<String> getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(ObservableField<String> articleContent) {
        this.articleContent = articleContent;
    }

    public ObservableField<String> getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(ObservableField<String> articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public ObservableField<String> getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(ObservableField<String> articleSource) {
        this.articleSource = articleSource;
    }

    public ObservableField<String> getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(ObservableField<String> articleLink) {
        this.articleLink = articleLink;
    }
}
