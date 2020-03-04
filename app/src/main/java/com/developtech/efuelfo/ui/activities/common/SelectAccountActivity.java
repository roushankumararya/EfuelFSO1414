package com.developtech.efuelfo.ui.activities.common;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectAccountActivity extends MyActionBar {

    @BindView(R.id.btnContinue)
    Button btnContinue;
    @BindView(R.id.relFuelStaff)
    RelativeLayout relFuelStaff;
    @BindView(R.id.relFuelOwner)
    RelativeLayout relFuelOwner;

    @BindView(R.id.ivFuelOwner)
    ImageView ivFuelOwner;
    @BindView(R.id.ivFuelStaff)
    ImageView ivFuelStaff;

    @BindView(R.id.tvWarning)
    TextView tvWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);
        ButterKnife.bind(this);
        init();
        initComponents();
    }

    @Override
    public void initComponents() {
        requestPermission();
    }

    @Override
    public void retry() {

    }

    public void enableContinueButton(int pos) {
        btnContinue.setBackground(ContextCompat.getDrawable(this, R.drawable.corner_green_background));
        btnContinue.setClickable(true);
        btnContinue.setEnabled(true);
        switch (pos) {
            case 0: {
                ivFuelStaff.setBackground(ContextCompat.getDrawable(this, R.drawable.operator_unselected));
                ivFuelOwner.setBackground(ContextCompat.getDrawable(this, R.drawable.stationowner_unselected));
                break;
            }
            case 1: {
                ivFuelStaff.setBackground(ContextCompat.getDrawable(this, R.drawable.operator_unselected));
                ivFuelOwner.setBackground(ContextCompat.getDrawable(this, R.drawable.stationowner_unselected));
                break;
            }
            case 2: {
                ivFuelStaff.setBackground(ContextCompat.getDrawable(this, R.drawable.operator_selected));
                ivFuelOwner.setBackground(ContextCompat.getDrawable(this, R.drawable.stationowner_unselected));
                break;
            }
            case 3: {
                ivFuelStaff.setBackground(ContextCompat.getDrawable(this, R.drawable.operator_unselected));
                ivFuelOwner.setBackground(ContextCompat.getDrawable(this, R.drawable.stationowner_selected));
                break;
            }
        }
    }

    @OnClick({R.id.btnContinue, R.id.relFuelOwner, R.id.relFuelStaff})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnContinue: {
                newIntent(TutorialAcitvity.class, null, false);
                break;
            }
            case R.id.relFuelOwner: {
                appComponent.getSpUtils().saveAccountType(SPUtils.ACCOUNT_TYPES.FSO);
                enableContinueButton(3);
                break;
            }
            case R.id.relFuelStaff: {
                appComponent.getSpUtils().saveAccountType(SPUtils.ACCOUNT_TYPES.OPR);
                enableContinueButton(2);
                break;
            }
        }
    }

    void initWarningHandler()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWarning.setVisibility(View.GONE);
            }
        },5000);
    }

    @Override
    public void onBackPressed() {
        if (tvWarning.getVisibility()==View.VISIBLE)
        {
            tvWarning.setVisibility(View.GONE);
        }
        else
        {
            super.onBackPressed();
        }

    }

    void requestPermission()
    { initWarningHandler();
//        if (ContextCompat.checkSelfPermission(this,
//                android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
//                android.Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
//
//           return;
//        } else {
//            ActivityCompat.requestPermissions((Activity) this, arrayOf(android.Manifest.permission.READ_SMS, android.Manifest.permission.RECEIVE_SMS),
//                    999);
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==999)
        {
            initWarningHandler();
        }
    }

}
