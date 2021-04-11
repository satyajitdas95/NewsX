

package com.newsorg.newsapp.android.ui.article_feed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.newsorg.newsapp.android.BR;
import com.newsorg.newsapp.android.R;
import com.newsorg.newsapp.android.databinding.ActivityArticleFeedBinding;
import com.newsorg.newsapp.android.di.component.ActivityComponent;
import com.newsorg.newsapp.android.ui.base.BaseActivity;
import com.newsorg.newsapp.android.utils.CommonUtils;

import javax.inject.Inject;

/**
 * Created by satyajit on 09/04/21.
 */

public class DashBoardActivity extends BaseActivity<ActivityArticleFeedBinding, DashBoardViewModel> implements DashBoardNavigator, DashBoardAdapter.DashBoardAdapterListener {

    private ActivityArticleFeedBinding mBinding;

    @Inject
    DashBoardAdapter mDashBoardAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    public static Intent newIntent(Context context) {
        return new Intent(context, DashBoardActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_feed;
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
        initiateLoadingNews();
    }

    private void initiateLoadingNews() {
        fetchTopHeadLines();
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void setUpArticleListRecyclerView() {
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.rvNewsList.setLayoutManager(mLinearLayoutManager);
        mBinding.rvNewsList.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvNewsList.setAdapter(mDashBoardAdapter);
        mDashBoardAdapter.setListener(this);
    }

    @Override
    public void doNothing() {

    }

    @Override
    public void onRetryClick() {
        fetchTopHeadLines();
    }

    private void fetchTopHeadLines() {
        if (isNetworkConnected()) mViewModel.getTopHeadLines("us");
        else CommonUtils.showNoInternetDialogue(this);
    }
}
