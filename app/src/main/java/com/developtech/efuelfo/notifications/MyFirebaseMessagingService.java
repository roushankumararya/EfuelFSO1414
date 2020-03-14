/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.developtech.efuelfo.notifications;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.app.MyApplication;
import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.developtech.efuelfo.model.responseModel.ExtraNotificationModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.ui.activities.common.TransactionDetailsActivity;
import com.developtech.efuelfo.ui.activities.stationOwner.RequestPendingActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.List;

//import com.developtech.efuelfo.ui.activities.stationOwner.TransactionDetailsActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "Notification";

    String title, msg, requestId, type, action;
    public  static String TRANS = "TRANS", CREDIT = "CREDIT", DELETED="DELETED", MANAGER_TRUE = "MANAGER_TRUE", MANAGER_FALSE = "MANAGER_FALSE";
    private AppComponent graph;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        graph = ((MyApplication) getApplication()).appComponent;

        title = remoteMessage.getData().get("title");
        msg = remoteMessage.getData().get("message");
        ExtraNotificationModel model = new Gson().fromJson(remoteMessage.getData().get("extra"), ExtraNotificationModel.class);
        requestId = model.getRequestId();
        type = remoteMessage.getData().get("type");
        action = remoteMessage.getData().get("action_noti");


        if (type.equalsIgnoreCase(graph.getSpUtils().getAccountType().toString())) {
            if (action.equalsIgnoreCase(TRANS)) {
                sendNotification(TransactionDetailsActivity.class);
            } else if (action.equalsIgnoreCase(CREDIT)) {
                sendNotification(RequestPendingActivity.class);
            }
            else if (action.equalsIgnoreCase(DELETED))
            {
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
                Intent local = new Intent("DELETED");
                Bundle bundle = new Bundle();
                bundle.putString("msg", msg);
                local.putExtras(bundle);
                localBroadcastManager.sendBroadcast(local);

                graph.getSpUtils().saveIsDeleted(true);
                graph.getSpUtils().saveNotiMsg(msg);
            }
            else if(action.equalsIgnoreCase(MANAGER_TRUE) || action.equalsIgnoreCase(MANAGER_FALSE))
            {
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
                Intent local = new Intent("MANAGER");
                Bundle bundle = new Bundle();
                bundle.putString("msg", msg);
                if (action.equalsIgnoreCase(MANAGER_TRUE)) {
                    bundle.getString("MANAGER", MANAGER_TRUE);
                    GetFuelStationResponseModel fsModel = graph.getSpUtils().getFuelStationModel();
                    List<OperatorsResponseModel> oprList = fsModel.getOperator();
                    for (OperatorsResponseModel oprModel : oprList) {
                        if (graph.getSpUtils().getUserData().getId().equals(oprModel.getId())) {
                            oprModel.setManager(true);
                        }
                    }
                    graph.getSpUtils().saveFuelStation(fsModel);
                }
                else if (action.equalsIgnoreCase(MANAGER_FALSE))
                {
                    bundle.getString("MANAGER", MANAGER_FALSE);
                    GetFuelStationResponseModel fsModel = graph.getSpUtils().getFuelStationModel();
                    List<OperatorsResponseModel> oprList = fsModel.getOperator();
                    for (OperatorsResponseModel oprModel : oprList) {
                        if (graph.getSpUtils().getUserData().getId().equals(oprModel.getId())) {
                            oprModel.setManager(false);
                        }
                    }
                    graph.getSpUtils().saveFuelStation(fsModel);
                }
                local.putExtras(bundle);
                localBroadcastManager.sendBroadcast(local);

                graph.getSpUtils().saveManagerChanged(true);
                graph.getSpUtils().saveNotiMsg(msg);
            }
        }


        Log.d(TAG, "onMessageReceived title: " + title + ", msg: " + msg + ", requestId: " + requestId);
        if (remoteMessage.getNotification()!=null) {
            System.out.println("notificaton body................ " + remoteMessage.getNotification().getBody());
        }

    }


    private void sendNotification(Class value) {
        Intent intent = null;
        Bundle bundle = new Bundle();
        bundle.putString("id", requestId);
        intent = new Intent(this, value);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, iUniqueId /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_white_app_icon)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
