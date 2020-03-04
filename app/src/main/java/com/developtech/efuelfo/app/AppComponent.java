package com.developtech.efuelfo.app;

import android.content.Context;

import com.developtech.efuelfo.network.AllApis;
import com.developtech.efuelfo.network.AllUrls;
import com.developtech.efuelfo.network.Api;
import com.developtech.efuelfo.network.NetworkModule;
import com.developtech.efuelfo.network.ServiceCaller;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.UtilFunctions;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})

public interface AppComponent {
    void inject(MyApplication myApplication);

    SPUtils getSpUtils();

    Api getApi();

    AllApis getAllApis();

    AllUrls getAllUrls();

    ServiceCaller getServiceCaller();

    Context getContext();

    UtilFunctions getUtilFunctions();

//    PermissionUtils getPermissionUtils();
}
