

package com.newsorg.newsapp.android.utils;

import android.content.Context;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.newsorg.newsapp.android.R;
import com.newsorg.newsapp.android.data.model.api.NewsResponse;
import com.newsorg.newsapp.android.ui.article_feed.DashBoardAdapter;

import java.util.List;



public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapter"})
    public static void addArticles(RecyclerView recyclerView, List<NewsResponse.ArticleList.Article> articleList) {
        DashBoardAdapter adapter = (DashBoardAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(articleList);
        }
    }


    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.color.light_gray)
                .error(R.drawable.ic_image_placeholder)
                .into(imageView);
    }
}
