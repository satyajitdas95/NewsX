

package com.newsorg.newsapp.android.ui.article_feed;

import androidx.databinding.ObservableField;

import com.newsorg.newsapp.android.data.model.api.NewsResponse;

/**
 * Created by satyajit on 09/04/21.
 */

public class DashBoardItemViewModel {

    public final ObservableField<String> articleHeadLine;

    public final ObservableField<String> articleDescription;

    public final ObservableField<String> articleAuthor;

    public final ObservableField<String> articlePublishedBy;

    public final NewsResponse.ArticleList.Article article;

    public final ArticleItemViewModelListener mListener;


    public DashBoardItemViewModel(NewsResponse.ArticleList.Article article, ArticleItemViewModelListener listener) {
        this.article = article;
        this.mListener = listener;
        articleHeadLine = new ObservableField<>(article.getTitle());
        articleDescription = new ObservableField<>(article.getDescription());
        String author = article.getAuthor() == null || article.getAuthor().equals("") || article.getAuthor().equals("null") ? "N/A" : article.getAuthor();
        String source = article.getSource().getName() == null || article.getSource().getName().equals("") || article.getSource().getName().equals("null") ? "N/A" : article.getSource().getName();
        articleAuthor = new ObservableField<>(" "+author);
        articlePublishedBy = new ObservableField<>(" "+source);
    }

    public void onItemClick() {
        mListener.onItemClick(article);
    }

    public interface ArticleItemViewModelListener {

        void onItemClick(NewsResponse.ArticleList.Article blogUrl);
    }
}
