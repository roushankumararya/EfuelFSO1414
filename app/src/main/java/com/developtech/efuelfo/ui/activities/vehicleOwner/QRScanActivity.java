package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.developtech.Scanner.ZXingScannerView;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.google.zxing.Result;

import static butterknife.internal.Utils.arrayOf;

public class QRScanActivity extends MyActionBar implements View.OnClickListener, ZXingScannerView.ResultHandler {
    int PERMISSION_REQUEST_CAMERA = 100;
    private ZXingScannerView scanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
//        initComponents();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            initComponents();
            scanView.startCamera();

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override
    public void initComponents() {
        scanView = (ZXingScannerView) findViewById(R.id.scanView);
        scanView.setAutoFocus(true);
        scanView.setResultHandler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            if (scanView!=null) {
                scanView.startCamera();
            }
        else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (scanView!=null)
        {
            scanView.stopCamera();
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void handleResult(Result rawResult) {
        String str = rawResult.getText().toString();
        Log.e("string", str);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("string", str);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setContentView(R.layout.activity_qrscan);
                    initComponents();
                    scanView.startCamera();
                }
                else {
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
