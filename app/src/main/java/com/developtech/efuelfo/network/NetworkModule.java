package com.developtech.efuelfo.network;

import android.content.Context;

import com.developtech.efuelfo.util.PermissionUtils;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.UtilFunctions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by android on 9/1/17.
 */
//

@Module
public class NetworkModule {


    @Provides
    @Singleton
    public OkHttpClient getClient(Interceptor interceptor) {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logger)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        return okClient;
    }

    @Provides
    @Singleton
    public Interceptor getInterceptor(final SPUtils spUtils) {
        String tokenTmp = "";
        /*if(spUtils.getUserData()!=null && spUtils.getUserData().getToken()!=null)
        {
            tokenTmp = spUtils.getUserData().getToken();
        }*/
        final String token = tokenTmp;

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                String fuelStationid = "";
                if (spUtils.getAccountType()== SPUtils.ACCOUNT_TYPES.OPR && spUtils.getFuelStationModel()!=null)
                {
                    fuelStationid = spUtils.getFuelStationModel().getId();
                }
                Request requestBuiler = original.newBuilder()
                        .addHeader("Authorization", token)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("fuelStationId", fuelStationid)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(requestBuiler);
            }
        };
        return interceptor;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofitBuilder(AllUrls allUrls, OkHttpClient okHttpClient) {
        Gson gson = new Gson();

        return new Retrofit.Builder()
                .baseUrl(allUrls.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient);
    }

    @Provides
    @Singleton
    public AllUrls provideAllUrls() {
        return new AllUrls();
    }

    @Provides
    @Singleton
    public Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    public AllApis provideAllApi(Api api) {
        return new AllApis(api);
    }

    @Provides
    @Singleton
    public UtilFunctions provideUtilFunction(Context context, AllUrls allUrls) {
        return new UtilFunctions(context, allUrls);
    }

    @Provides
    @Singleton
    public CompositeSubscription provideCompositeSubs() {
        return new CompositeSubscription();
    }

    public PermissionUtils providePermissionUtil() {
        return new PermissionUtils();
    }

    @Provides
    @Singleton
    public ServiceCaller getServiceCaller(CompositeSubscription compositeSubscription, UtilFunctions utilFunctions, SPUtils spUtils, Context context) {
        return new ServiceCaller(compositeSubscription, utilFunctions, spUtils, context);
    }

    public ServiceCaller getServiceCaller(UtilFunctions utilFunctions, SPUtils spUtils, Context context) {
        return new ServiceCaller(new CompositeSubscription(), utilFunctions, spUtils, context);

    }
}
