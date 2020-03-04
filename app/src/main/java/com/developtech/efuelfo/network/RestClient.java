package com.developtech.efuelfo.network;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BRAR on 8/11/15.
 */
public class RestClient {
    public static final String AUTHENTICATION_HEADER = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_URl_FORM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_MULTIPART = "application/json";


//    public static String AUTHENTICATION_KEY = "Basic ";

    static {


        // AUTHENTICATION_KEY += " Y29tLmR0LndhdmU=";
    }

//    public static void setAuthKey(String authKey) {
//        AUTHENTICATION_KEY += authKey;
//    }

    public static Retrofit build(String url) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient().build()).build();

        return retrofit;

    }

    public static Retrofit buildToken(Context context, String url) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClientToken(context))
                .build();

        return retrofit;

    }

    static OkHttpClient.Builder getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging).connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).retryOnConnectionFailure(true);

        return httpClient;
    }


    @NonNull
    static OkHttpClient getClientToken(final Context context) {
        final String token, email;
//        final String deviceId = UtilFunctions.getDeviceId(context);
////        if (SPUtils.getLoginData(context) != null) {
////
////            if(SPUtils.getLoginData(context).getToken()!=null)
////            {
////                token = SPUtils.getLoginData(context).getToken();
////            }
////            else
////            {
////                token = "";
////            }
////
////
////            email = SPUtils.getLoginData(context).getEmail();
////        }
//        else
//        {
//            token = "";
//            email = "";
//        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()

                .addInterceptor(logging)
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                // Request customization: add request headers
                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader(CONTENT_TYPE, CONTENT_TYPE_JSON).
//                                                header("Authorization",token).
//                                                header("Email",email).
//                                                header("Device%20Id",deviceId).
                                                method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        })
                .build();
    }
}
//
//class SessionRequestInterceptor implements RequestInterceptor {
//    private final String TAG = SessionRequestInterceptor.class
//            .getSimpleName();
//
//    @Override
//    public void intercept(RequestFacade request) {
//        request.addHeader("Content-Type", "application/json");
//    }
//}.


//    OkHttpClient httpClient = new OkHttpClient();
//httpClient.networkInterceptors().add(new Interceptor() {
//@Override
//public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request().newBuilder().
//        // addHeader(CONTENT_TYPE, CONTENT_TYPE_JSON).
//        // addHeader(AUTHENTICATION_HEADER, AUTHENTICATION_KEY).
//        build();
//        return chain.proceed(request);
//        }
//        });
