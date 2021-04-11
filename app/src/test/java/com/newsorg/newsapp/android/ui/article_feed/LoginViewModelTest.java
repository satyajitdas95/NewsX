package com.newsorg.newsapp.android.ui.article_feed;

import static org.mockito.Mockito.doReturn;

import com.newsorg.newsapp.android.data.DataManager;
import com.newsorg.newsapp.android.data.model.api.NewsResponse;
import com.newsorg.newsapp.android.utils.rx.TestSchedulerProvider;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LoginViewModelTest {

    @Mock
    DashBoardNavigator mLoginCallback;

    @Mock
    DataManager mMockDataManager;

    private DashBoardViewModel mLoginViewModel;
    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mLoginViewModel = new DashBoardViewModel(mMockDataManager, testSchedulerProvider);
        mLoginViewModel.setNavigator(mLoginCallback);
    }

    @After
    public void tearDown() throws Exception {
        mTestScheduler = null;
        mLoginViewModel = null;
        mLoginCallback = null;
    }

    @Test
    public void testServerLoginSuccess() {
        String countryCode = "us";

        NewsResponse.ArticleList loginResponse = new  NewsResponse.ArticleList();

        doReturn(Single.just(loginResponse))
                .when(mMockDataManager)
                .getTopHeadLines(countryCode);

        mLoginViewModel.getTopHeadLines(countryCode);
        mLoginViewModel.getArticleLiveData().getValue();
//       mTestScheduler.triggerActions();
//
       //verify(mMockDataManager).getAccessToken();
    }
}
