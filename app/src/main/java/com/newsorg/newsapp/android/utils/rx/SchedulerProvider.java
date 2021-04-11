

package com.newsorg.newsapp.android.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by satyajit on 09/04/21.
 */

public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
