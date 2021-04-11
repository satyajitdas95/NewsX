

package com.newsorg.newsapp.android.ui.article_feed;

/**
 * Created by satyajit on 09/04/21.
 */

public interface DashBoardNavigator {

    void handleError(Throwable throwable);

    void setUpArticleListRecyclerView();
    void doNothing();

}
