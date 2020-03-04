package com.developtech.efuelfo.network;

import android.content.Context;
import android.content.Intent;

import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;



import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.UtilFunctions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by developtech on 1/9/18.
 */

public class ServiceCaller<T> {
    CompositeSubscription subscriptions;
    UtilFunctions utilFunctions;
    SPUtils spUtils;
    Context context;

    @Inject
    public ServiceCaller(CompositeSubscription subscriptions, UtilFunctions utilFunctions, SPUtils spUtils, Context context) {
        this.subscriptions = subscriptions;
        this.utilFunctions = utilFunctions;
        this.spUtils = spUtils;
        this.context = context;
    }

    public void removeSubscription() {
        this.subscriptions.unsubscribe();
    }

    public void addSubscription() {
        this.subscriptions.add(subscriptions);
    }

    public void callService(Observable<ResultModel<T>> observable) {
        callService(Schedulers.newThread(), AndroidSchedulers.mainThread(), 30L, observable);
    }

    public void callService(Observable<ResultModel<T>> observable, NetworkListener listner, Long time) {
        callService(Schedulers.newThread(), AndroidSchedulers.mainThread(), time, listner, observable);
    }

    public void callService(Observable<ResultModel<T>> observable, NetworkListener listner) {
        callService(Schedulers.newThread(), AndroidSchedulers.mainThread(), 30L, listner, observable);
    }

    public void callService(Scheduler subscribeThread, Scheduler observeThread, Long timeOut, final NetworkListener listner,
                            Observable<ResultModel<T>> observable) {
        Log.e("kkk222","jjj"+observable.toString());
        if (!utilFunctions.isInternetOn()) {
            listner.onError(context.getResources().getString(R.string.internet_not_available));
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
            Intent local = new Intent("NO_INTERNET");
            localBroadcastManager.sendBroadcast(local);
            return;
        }
        listner.onStart();
        subscriptions.add(observable.subscribeOn(subscribeThread).observeOn(observeThread).timeout(timeOut, TimeUnit.SECONDS)
                .subscribe(new Subscriber<ResultModel<T>>() {
            @Override
            public void onCompleted() {
                listner.onComplete();
            }

            @Override
            public void onNext(ResultModel<T> t) {
                listner.onSuccess(t);
                listner.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                try {
                    if (e instanceof HttpException) {
                        HttpException httpException = (HttpException) e;
                        int code = httpException.code();
                        String message = httpException.message();
                        switch (code) {
                            case 403: {

                                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
                                Intent local = new Intent("SESSION_EXPIRED");
                                localBroadcastManager.sendBroadcast(local);

                                break;
                            }
                        }
                    }
                    listner.onError(e.getMessage());
                    listner.onComplete();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }));
    }


    public void callService(Scheduler subscribeThread, Scheduler observeThread, Long timeOut, Observable<ResultModel<T>> observable) {
        if (!utilFunctions.isInternetOn()) {
            return;
        }
        subscriptions.add(observable.subscribeOn(subscribeThread).observeOn(observeThread).timeout(timeOut, TimeUnit.SECONDS).subscribe(new Subscriber<ResultModel<T>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onNext(ResultModel<T> t) {
            }

            @Override
            public void onError(Throwable e) {
            }
        }));
    }

}
