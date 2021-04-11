
package com.newsorg.newsapp.android.ui.article_feed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newsorg.newsapp.android.R;
import com.newsorg.newsapp.android.databinding.ItemArticleEmptyViewBinding;
import com.newsorg.newsapp.android.databinding.ItemArticleViewBinding;
import com.newsorg.newsapp.android.data.model.api.NewsResponse;
import com.newsorg.newsapp.android.ui.article_details.ArticleDetailsActivity;
import com.newsorg.newsapp.android.ui.base.BaseViewHolder;
import com.newsorg.newsapp.android.utils.IntentKeys;

import java.util.List;

import static androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

/**
 * Created by satyajit on 09/04/21.
 */

public class DashBoardAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<NewsResponse.ArticleList.Article> newsResponseList;

    private DashBoardAdapterListener mListener;
    private Activity mContext;

    public DashBoardAdapter(Activity mContext, List<NewsResponse.ArticleList.Article> newsResponseList) {
        this.mContext = mContext;
        this.newsResponseList = newsResponseList;
    }

    @Override
    public int getItemCount() {
        if (newsResponseList != null && newsResponseList.size() > 0) {
            return newsResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (newsResponseList != null && !newsResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemArticleViewBinding articleViewBinding = ItemArticleViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new ArticleViewHolder(articleViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemArticleEmptyViewBinding emptyViewBinding = ItemArticleEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<NewsResponse.ArticleList.Article> articleList) {
        newsResponseList.addAll(articleList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        newsResponseList.clear();
    }

    public void setListener(DashBoardAdapterListener listener) {
        this.mListener = listener;
    }

    public interface DashBoardAdapterListener {

        void onRetryClick();


    }

    public class ArticleViewHolder extends BaseViewHolder implements DashBoardItemViewModel.ArticleItemViewModelListener {

        private ItemArticleViewBinding mBinding;

        private DashBoardItemViewModel mBlogItemViewModel;

        public ArticleViewHolder(ItemArticleViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final NewsResponse.ArticleList.Article article = newsResponseList.get(position);
            mBlogItemViewModel = new DashBoardItemViewModel(article, this);
            mBinding.setViewModel(mBlogItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
            Glide.with(mContext)
                    .load(article.getUrlToImage())
                    .centerCrop()
                    .placeholder(R.color.light_gray)
                    .error(R.drawable.ic_image_placeholder)
                    .into(mBinding.coverImageView);
        }

        @Override
        public void onItemClick(NewsResponse.ArticleList.Article article) {
            Intent intent = ArticleDetailsActivity.newIntent(mContext);
            intent.putExtra(IntentKeys.INTENT_KEY_ARTICLE, article);
            Pair<View, String> p1 = Pair.create(mBinding.coverImageView, "articleThumb");
            Pair<View, String> p2 = Pair.create(mBinding.titleTextView, "articleTitle");
            ActivityOptionsCompat options = makeSceneTransitionAnimation(mContext, p1, p2);
            mContext.startActivity(intent, options.toBundle());
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements DashBoardEmptyItemViewModel.EmptyItemViewModelListener {

        private ItemArticleEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemArticleEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            DashBoardEmptyItemViewModel emptyItemViewModel = new DashBoardEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}