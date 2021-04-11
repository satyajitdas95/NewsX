

package com.newsorg.newsapp.android.ui.article_feed;

/**
 * Created by satyajit on 09/04/21.
 */

public class DashBoardEmptyItemViewModel {

    private EmptyItemViewModelListener mListener;

    public DashBoardEmptyItemViewModel(EmptyItemViewModelListener listener) {
        this.mListener = listener;
    }
    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface EmptyItemViewModelListener {

        void onRetryClick();
    }
}
