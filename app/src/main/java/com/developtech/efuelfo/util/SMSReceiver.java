package com.developtech.efuelfo.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();
    String verifCode;
    String TAG="messageDetail";

    public void onReceive(final Context context, Intent intent) {
        System.out.println(".............................123........................");
        final Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive: ");
        SmsMessage[] msgs = null;
        String body = "";
        try {
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    body += msgs[i].getMessageBody().toString();
                    String otp=body.substring(body.indexOf("is ") + 3 , body.length());

                    otp = otp.substring(0, 6);
                    Log.d("messagedetail","is==> "+otp);
                    MessageDetail messageDetail=new MessageDetail();
                    messageDetail.setOtp(otp);

                    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
                    Intent local = new Intent("OTP_MSG");
                    Bundle localBundle = new Bundle();
                    localBundle.putSerializable("msgDetail", messageDetail);
                    local.putExtras(localBundle);
                    localBroadcastManager.sendBroadcast(local);
                }
            }

        } catch (Exception e) {
            System.out.println("XX " + e);
        }
    }
}