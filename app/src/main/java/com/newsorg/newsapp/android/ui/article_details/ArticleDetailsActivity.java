

package com.newsorg.newsapp.android.ui.article_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.newsorg.newsapp.android.BR;
import com.newsorg.newsapp.android.R;
import com.newsorg.newsapp.android.data.model.api.NewsResponse;
import com.newsorg.newsapp.android.databinding.ActivityArticleDetailsBinding;
import com.newsorg.newsapp.android.di.component.ActivityComponent;
import com.newsorg.newsapp.android.ui.base.BaseActivity;
import com.newsorg.newsapp.android.utils.AppLogger;
import com.newsorg.newsapp.android.utils.IntentKeys;

/**
 * Created by satyajit on 09/04/21.
 */

public class ArticleDetailsActivity extends BaseActivity<ActivityArticleDetailsBinding, ArticleDetailsViewModel> implements ArticleDetailsNavigator {

    private ActivityArticleDetailsBinding mBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, ArticleDetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_details;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        retrieveArticle();
        setUpViews();
    }

    private void retrieveArticle() {
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            NewsResponse.ArticleList.Article article = extra.getParcelable(IntentKeys.INTENT_KEY_ARTICLE);
            setupArticle(article);
        }
    }

    private void setupArticle(NewsResponse.ArticleList.Article article) {
        String description = article.getDescription() == null || article.getDescription().equals("") || article.getDescription().equals("null") ? "N/A" : article.getDescription();
        String content = article.getContent() == null || article.getContent().equals("") || article.getContent().equals("null") ? "N/A" : article.getContent();
        String author = article.getAuthor() == null || article.getAuthor().equals("") || article.getAuthor().equals("null") ? "N/A" : article.getAuthor();
        String source = article.getSource().getName() == null || article.getSource().getName().equals("") || article.getSource().getName().equals("null") ? "N/A" : article.getSource().getName();

        mViewModel.getArticleTitle().set(article.getTitle());
        mViewModel.getArticleImage().set(article.getUrlToImage());
        mViewModel.getArticleDescription().set(description);
        mViewModel.getArticleContent().set(content);
        mViewModel.getArticleAuthor().set(" " + author);
        mViewModel.getArticleSource().set(" " + source);
        mViewModel.getArticleLink().set(article.getUrl());

        //fetch likes and comments
        String articleID = article.getUrl().replace("/", "-");
        mViewModel.getAllLikes(articleID);
        mViewModel.getAllComments(articleID);
    }

    private void setUpViews() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
