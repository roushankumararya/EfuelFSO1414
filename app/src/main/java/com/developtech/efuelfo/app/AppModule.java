package com.developtech.efuelfo.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.developtech.efuelfo.util.SPUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by android on 9/1/17.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public SharedPreferences getSharedPref(Context context) {
        return context.getSharedPreferences("efuel", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public SPUtils getSpUtils(SharedPreferences sharedPreferences) {
        return new SPUtils(sharedPreferences);
    }

}
