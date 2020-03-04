package com.developtech.efuelfo.app;

import android.app.Application;

import com.developtech.efuelfo.network.NetworkModule;

/**
 * Created by dt on 12/21/17.
 */

public class MyApplication extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    public void initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .networkModule(new NetworkModule()).build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
