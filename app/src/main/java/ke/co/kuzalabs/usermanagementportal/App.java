package ke.co.kuzalabs.usermanagementportal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import ke.co.kuzalabs.usermanagementportal.di.DaggerAppComponent;

public class App extends Application implements HasActivityInjector {
    private static App instance;

    public static Context getContext() {
        return instance;
    }

    public static App getInstance() {
        return instance;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        /* dagger init */
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
