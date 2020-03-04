package com.developtech.efuelfo.ui.activities.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.developtech.efuelfo.HomePage;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.util.SPUtils;

public class SplashActivity extends MyActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();

        final Bundle bundle = getIntent().getExtras();
        System.out.println("here in falut==>"+bundle);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                intent=new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
//                if (appComponent.getSpUtils().getUserData()==null || appComponent.getSpUtils().getUserId()==null ||
//                        appComponent.getSpUtils().getUserId().isEmpty() || !appComponent.getSpUtils().isKeepMeLogin()) {
//                    intent = new Intent(SplashActivity.this, SelectAccountActivity.class);
//                    startActivity(intent);
//                }
//                else if (appComponent.getSpUtils().getAccountType()!=null &&
//                        appComponent.getSpUtils().getAccountType()==SPUtils.ACCOUNT_TYPES.FSO &&
//                        appComponent.getSpUtils().getAllFuelStationsList()==null && appComponent.getSpUtils().getUserData().getId()!=null )
//                {
//                    intent = new Intent(SplashActivity.this, StationRegistrationActivity.class);
//                    startActivity(intent);
//                }
//                else if (appComponent.getSpUtils().getAccountType()!=null &&
//                        appComponent.getSpUtils().getAccountType()==SPUtils.ACCOUNT_TYPES.FSO &&
//                        appComponent.getSpUtils().getFuelStationModel()==null )
//                {
//                    intent = new Intent(SplashActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                }
//                else {
//                    intent = new Intent(SplashActivity.this, HomeActivity.class);
//                    if (bundle!=null && bundle.getString("type")!=null)
//                        intent.putExtras(bundle);
//                    startActivity(intent);
//                }

                SPUtils spUtils = appComponent.getSpUtils();
                SignInResponseModel userData = spUtils.getUserData();

                /*if (userData !=null && userData.getId()!=null &&
                        !userData.getId().isEmpty() && spUtils.isKeepMeLogin())
                {

                    if(spUtils.getFuelStationModel()!=null)
                    {
                        intent = new Intent(SplashActivity.this, HomePage.class);
                        if (bundle!=null && bundle.getString("type")!=null)
                            intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(spUtils.getAccountType()!=null &&
                            spUtils.getAccountType()==SPUtils.ACCOUNT_TYPES.FSO &&
                            spUtils.getAllFuelStationsList()!=null &&
                            spUtils.getAllFuelStationsList().size()>0)
                    {
                        if (userData.getAddress()!=null &&
                            !userData.getAddress().isEmpty())
                        {
                            spUtils.clearUserData();
                            intent = new Intent(SplashActivity.this, SelectAccountActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            intent = new Intent(SplashActivity.this, StationRegistrationActivity.class);
                            startActivity(intent);
                        }

                    }
                    else
                    {
                        intent = new Intent(SplashActivity.this, SelectAccountActivity.class);
                        startActivity(intent);
                    }

                }
                else
                {
                    intent = new Intent(SplashActivity.this, SelectAccountActivity.class);
                    startActivity(intent);
                }*/

                finish();
            }
        }, 2000);
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void retry() {

    }
}
