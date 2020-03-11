package com.developtech.efuelfo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.developtech.efuelfo.app.MyApplication;
import com.developtech.efuelfo.app.UserPreference;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.ResendOtpRequestModel;
import com.developtech.efuelfo.model.requestModel.SignUpRequestModel;
import com.developtech.efuelfo.model.requestModel.VerifyOtpRequestModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.activities.vehicleOwner.RegistrationActivity;
import com.developtech.efuelfo.ui.dialogFragments.SelectVehicleOwnerDialog;
import com.developtech.efuelfo.util.MessageDetail;
import com.developtech.efuelfo.util.SPUtils;

public class OtpVerifyNumber extends MyActionBar implements TextWatcher,View.OnClickListener {

    Button btnotpverifypage;
    UserPreference userPreference;
    EditText etotp1,etotp2,etotp3,etotp4,etotp5,etotp6;
    TextView resendotp,tvnumber,mobileNo;
    String contactnumber;
    NetworkListener otpListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            userPreference.setNumber(nuber);
            userPreference.setIsLogin(true);
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SignInResponseModel responseModel = (SignInResponseModel) responseBody.getResutData();
              //  Log.e("kkk","succ" + responseModel);
                appComponent.getSpUtils().saveUserData(responseModel);
                MyApplication application = (MyApplication) getApplication();
                application.initDagger();
                appComponent.getSpUtils().setKeepMeLogin(true);
                goToNextPage();

                //shareprefer save boolean set tru, number

            } else if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.FAIL.toString())) {
              //  showMsg(rootLayout, responseBody.getMessage());
                appComponent.getSpUtils().setKeepMeLogin(true);
                goToNextPage();
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                }
            });
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };






    private Bundle bundle;
    private String str;
    private String otp_id /*password*/;
    private SignInResponseModel signInResponseModel;
    private SignUpRequestModel signUpRequestModel;
    private String otp = "";
    private boolean isForget;
  String nuber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        etotp1=(EditText)findViewById(R.id.otp1);
        etotp2=(EditText)findViewById(R.id.otp2);
        etotp3=(EditText)findViewById(R.id.otp3);
        etotp4=(EditText)findViewById(R.id.otp4);
        etotp5=(EditText)findViewById(R.id.otp5);
        etotp6=(EditText)findViewById(R.id.otp6);
       // tvnumber=(TextView)findViewById(R.id.getnumber);
        mobileNo=(TextView) findViewById(R.id.hashvie);
        resendotp=(TextView) findViewById(R.id.resendotp);
        etotp1.addTextChangedListener(this);
        etotp2.addTextChangedListener(this);
        etotp3.addTextChangedListener(this);
        etotp4.addTextChangedListener(this);
        etotp5.addTextChangedListener(this);
        etotp6.addTextChangedListener(this);
   resendotp.setOnClickListener(this);
         userPreference=new UserPreference(getApplicationContext()).getInstance(getApplicationContext());
       // init();
        initComponents();
       /* Intent intent1=getIntent();
        String str=intent1.getStringExtra("number");
        Bundle bundle=intent1.getBundleExtra("bundle");*/
       // bundle.getString("MobileNo");

       // Intent intent = getIntent();
       // String str = intent.getStringExtra("getnumber");
        //tvnumber.setText(""+str);
        // init();
      //  initComponents();
        btnotpverifypage=(Button)findViewById(R.id.otpsendnumber);
        btnotpverifypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp=etotp1.getText().toString()+etotp2.getText().toString()+etotp3.getText().toString()+etotp4.getText().toString()+etotp5.getText().toString()+etotp6.getText().toString();
                verifyOtp(otp);


                //tvnumber.setText(getIntent().getExtras().getString("number"));
              /*  Intent intent=new Intent(getApplicationContext(), UserPersonalDeatil.class);
                startActivity(intent);
                finish();*/
            }
        });
    }

    @Override
    public void initComponents() {
        Intent intent=getIntent();
         bundle=intent.getExtras().getBundle("bundle");

        if (bundle != null) {
            //isForget = bundle.getBoolean("is_forget");
            otp_id = bundle.getString("otp_id");
            /*str=bundle.getString("MobileNo");
            mobileNo.setText("#"+str+"#");*/
            nuber=bundle.getString("MobileNo");
            mobileNo.setText(""+bundle.getString("MobileNo")+"");
           // bundle.getString()
            //  if (!isForget) {
            if (otp_id != null) {
               // signUpRequestModel = (SignUpRequestModel) bundle.getSerializable("signUpData");
                //    contactnumber.setText(signUpRequestModel.getMobileNumber());
            } else {
                signInResponseModel = (SignInResponseModel) bundle.getSerializable("sign_in");
                //  password = bundle.getString("password");
                // contactnumber.setText(signInResponseModel.getMobileNumber());*//*
            }
            //}
            /*else
            {
                String loginId = bundle.getString("loginId");
                //  contactnumber.setText(loginId);
            }*/
        }

  }





    private void verifyOtp(String otp) {

        if (otp.trim().isEmpty())
        {
            showMsg(rootLayout, getResources().getString(R.string.please_enter_otp));
            return;
        }

        VerifyOtpRequestModel requestModel = new VerifyOtpRequestModel();
        requestModel.setOtp(otp);
        requestModel.setUserType(appComponent.getSpUtils().getAccountType().FSO.toString());

        if (signInResponseModel != null) {
            requestModel.setMobileNumber(signInResponseModel.getMobileNumber());
            requestModel.setOtpId(signInResponseModel.getId());
        } else if (signUpRequestModel !=null) {
            requestModel.setMobileNumber(bundle.getString("MobileNo"));
            requestModel.setOtpId(otp_id);
        }
        appComponent.getServiceCaller().callService(appComponent.getAllApis().verifyOtp(requestModel),
                otpListener);
        /*Intent intent=new Intent(getApplicationContext(), UserPersonalDeatil.class);
        startActivity(intent);
        finish();*/
    }


    @Override
    public void retry() {

    }

    public void goToNextPage() {
        switch (SPUtils.ACCOUNT_TYPES.FSO) {
            case DRV: {
                DialogFragment dialogFragment = new SelectVehicleOwnerDialog();
                dialogFragment.show(getSupportFragmentManager(), "select_owner");
//                newIntent(ChangePasswordActivity.class, null, false);
                break;
            }
            case VCO: {
                newIntent(RegistrationActivity.class, null, false);
//                newIntent(StationRegistrationActivity.class, null, false);
                break;
            }
            case FSO: {
                newIntent(UserPersonalDeatil.class, null, false);
//                DialogFragment dialogFragment = new SelectVehicleOwnerDialog();
//                dialogFragment.show(getSupportFragmentManager(), "select_owner");
                break;
            }
            case OPR: {
//                newIntent(StationRegistrationActivity.class,null,false);
//                newIntent(StationRegistrationActivity.class,null,false);
                DialogFragment dialogFragment = new SelectVehicleOwnerDialog();
                dialogFragment.show(getSupportFragmentManager(), "select_owner");
                break;
            }
        }
    }

    //@OnClick({R.id.btnVerify, R.id.layOtp, R.id.resendotp})

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVerify: {
                if (!isForget)
                    verifyOtp(otp);
                else
                    //verifyForgetOtp(otp);
                break;
            }
            case R.id.layOtp: {
                showKey();
                etotp1.requestFocus();
                break;
            }
            case R.id.resendotp: {
                ResendOtpRequestModel model = new ResendOtpRequestModel();
              //  Log.e("kkk","succ"+appComponent.getServiceCaller());
                if(otp_id!=null)
                {
                    model.setOtpId(otp_id);
                    model.setResendCase("SIGNUP");
                    hideKB();
                    Log.e("hhh","success"+ appComponent.getServiceCaller());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().resendOtp(model),
                            resendListener);
                }
            }
        }
    }

    NetworkListener resendListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(getResources().getString(R.string.otp_confirm));

            }
        }

        @Override
        public void onError(String msg) {
            hideProgress();
        }

        @Override
        public void onComplete() {
            hideProgress();
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter fitler = new IntentFilter();
        fitler.addAction("OTP_MSG");
        LocalBroadcastManager.getInstance(this).registerReceiver(listener, fitler);
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(listener);
    }

    BroadcastReceiver listener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras()!=null)
            {
                MessageDetail mesgDetail = (MessageDetail) intent.getExtras().getSerializable("msgDetail");
                MessageDetail getotpdigits=mesgDetail;
                //etOtp.setText(mesgDetail.getOtp());
                if (!isForget)
                  //  verifyOtp(mesgDetail.getOtp());
                    verifyOtp(mesgDetail.getOtp());
                else{

                }
                   // verifyForgetOtp(mesgDetail.getOtp());
            }

        }
    };

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(s.length()==1){
            if(etotp1.length()==1){
                etotp2.requestFocus();
            }
            if(etotp2.length()==1){
                etotp3.requestFocus();
            }
            if(etotp3.length()==1){
                etotp4.requestFocus();
            }
            if(etotp4.length()==1){
                etotp5.requestFocus();
            }
            if (etotp5.length()==1){
                etotp6.requestFocus();
            }
        }else if(s.length()==0){
            if(etotp6.length()==0){
                etotp5.requestFocus();
            }
            if(etotp5.length()==0){
                etotp4.requestFocus();
            }
            if(etotp4.length()==0){
                etotp3.requestFocus();
            }
            if(etotp3.length()==0){
                etotp2.requestFocus();
            }
            if(etotp2.length()==0){
                etotp1.requestFocus();
            }
        }
    }
}
