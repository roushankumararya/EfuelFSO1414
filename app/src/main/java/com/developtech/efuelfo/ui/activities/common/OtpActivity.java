package com.developtech.efuelfo.ui.activities.common;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.MyApplication;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.ResendOtpRequestModel;
import com.developtech.efuelfo.model.requestModel.SignUpRequestModel;
import com.developtech.efuelfo.model.requestModel.VerifyForgetPassRequestModel;
import com.developtech.efuelfo.model.requestModel.VerifyOtpRequestModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.model.responseModel.VerifyForgetPassResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.fuelStation.StationRegistrationActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.RegistrationActivity;
import com.developtech.efuelfo.ui.dialogFragments.SelectVehicleOwnerDialog;
import com.developtech.efuelfo.util.MessageDetail;
import com.developtech.efuelfo.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtpActivity extends MyActionBar {

    @BindView(R.id.btnVerify)
    Button btnVerify;
    @BindView(R.id.etOtp)
    EditText etOtp;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.layOtp)
    LinearLayout layOtp;

    @BindView(R.id.tvMobileNumber)
    CustomTextView tvMobileNumber;

    @BindView(R.id.rootlayout)
    RelativeLayout rootLayout;


    NetworkListener otpListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SignInResponseModel responseModel = (SignInResponseModel) responseBody.getResutData();
                appComponent.getSpUtils().saveUserData(responseModel);
                MyApplication application = (MyApplication) getApplication();
                application.initDagger();
                goToNextPage();
            } else if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.FAIL.toString())) {
                showMsg(rootLayout, responseBody.getMessage());
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

    NetworkListener verifyForgetPass = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                VerifyForgetPassResponseModel responseModel = (VerifyForgetPassResponseModel) responseBody.getResutData();
                Log.e("tokenn", responseModel.getToken());
                appComponent.getSpUtils().saveToken(responseModel.getToken());
                MyApplication application = (MyApplication) getApplication();
                application.initDagger();
                appComponent.getSpUtils().setKeepMeLogin(true);
                newIntent(ResetPassword.class, null, true);
            } else if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.FAIL.toString())) {
                showMsg(rootLayout, responseBody.getMessage());
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
    private String otp_id, password;
    private SignInResponseModel signInResponseModel;
    private SignUpRequestModel signUpRequestModel;
    private String otp = "";
    private boolean isForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        init();
        initComponents();
    }

    @Override
    public void initComponents() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            isForget = bundle.getBoolean("is_forget");
            otp_id = bundle.getString("otp_id");
            if (!isForget) {
                if (otp_id != null) {
                    signUpRequestModel = (SignUpRequestModel) bundle.getSerializable("signUpData");
                    tvMobileNumber.setText(signUpRequestModel.getMobileNumber());
                } else {
                    signInResponseModel = (SignInResponseModel) bundle.getSerializable("sign_in");
                    password = bundle.getString("password");
                    tvMobileNumber.setText(signInResponseModel.getMobileNumber());
                }
            }
            else
            {
                String loginId = bundle.getString("loginId");
                tvMobileNumber.setText(loginId);
            }
        }

        etOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp = etOtp.getText().toString().trim();
//                if (otp.length() == 6) {
//                    hideKB();
//                    if (!isForget)
//                        verifyOtp(otp);
//                    else
//                        verifyForgetOtp(otp);
//                }
                switch (otp.length()) {
                    case 0: {
//                        setViewVisibility(0);
//                        setTextViewVisibility(0);
                        tv.setText("");
                        break;
                    }
                    case 1: {
//                        setViewVisibility(1);
//                        setTextViewVisibility(1);
                        tv.setText(otp.charAt(0) + "");
                        tv1.setText("");
                        break;
                    }
                    case 2: {
//                        setViewVisibility(2);
//                        setTextViewVisibility(2);
                        tv1.setText(otp.charAt(1) + "");
                        tv2.setText("");
                        break;
                    }
                    case 3: {
//                        setViewVisibility(3);
//                        setTextViewVisibility(3);
                        tv2.setText(otp.charAt(2) + "");
                        tv3.setText("");
                        break;
                    }
                    case 4: {
//                        setViewVisibility(4);
//                        setTextViewVisibility(4);

                        tv3.setText(otp.charAt(3) + "");
                        tv4.setText("");
                        break;
                    }
                    case 5: {
//                        setViewVisibility(5);
//                        setTextViewVisibility(5);

                        tv4.setText(otp.charAt(4) + "");
                        tv5.setText("");
                        break;
                    }
                    case 6: {
//                        setViewVisibility(6);
//                        setTextViewVisibility(6);
                        tv.setText(otp.charAt(0) + "");
                        tv1.setText(otp.charAt(1) + "");
                        tv2.setText(otp.charAt(2) + "");
                        tv3.setText(otp.charAt(3) + "");
                        tv4.setText(otp.charAt(4) + "");
                        tv5.setText(otp.charAt(5) + "");
                        break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void verifyOtp(String otp) {

        if (otp.trim().isEmpty())
        {
            showMsg(rootLayout, getResources().getString(R.string.please_enter_otp));
            return;
        }

        VerifyOtpRequestModel requestModel = new VerifyOtpRequestModel();
        requestModel.setOtp(otp);
        requestModel.setUserType(appComponent.getSpUtils().getAccountType().toString());

        if (signInResponseModel != null) {
            requestModel.setEmail(signInResponseModel.getEmail());
            requestModel.setFirstName(signInResponseModel.getFirstName());
            requestModel.setLastName(signInResponseModel.getLastName());
            requestModel.setMobileNumber(signInResponseModel.getMobileNumber());
            requestModel.setOtpId(signInResponseModel.getId());
            requestModel.setPassword(password);
        } else if (signUpRequestModel !=null)
        {
            requestModel.setEmail(signUpRequestModel.getEmail());
            requestModel.setFirstName(signUpRequestModel.getFirstName());
            requestModel.setLastName(signUpRequestModel.getLastName());
            requestModel.setMobileNumber(signUpRequestModel.getMobileNumber());
            requestModel.setOtpId(otp_id);
            requestModel.setPassword(signUpRequestModel.getPassword());
        }

        appComponent.getServiceCaller().callService(appComponent.getAllApis().verifyOtp(requestModel), otpListener);
    }

    void setViewVisibility(int i) {
        switch (i) {
            case 0: {
                view.setVisibility(View.VISIBLE);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.VISIBLE);
                view5.setVisibility(View.VISIBLE);
                break;
            }
            case 1: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.VISIBLE);
                view5.setVisibility(View.VISIBLE);
                break;
            }
            case 2: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.VISIBLE);
                view5.setVisibility(View.VISIBLE);
                break;
            }
            case 3: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.VISIBLE);
                view5.setVisibility(View.VISIBLE);
                break;
            }
            case 4: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.VISIBLE);
                view5.setVisibility(View.VISIBLE);
                break;
            }
            case 5: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                view5.setVisibility(View.VISIBLE);
                break;
            }
            case 6: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                view5.setVisibility(View.GONE);
                break;
            }
        }
    }

    void setTextViewVisibility(int i) {
        switch (i) {
            case 0: {
                tv.setVisibility(View.GONE);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                break;
            }
            case 1: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                break;
            }
            case 2: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);

                break;
            }
            case 3: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                break;
            }
            case 4: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                break;
            }
            case 5: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.VISIBLE);
                tv5.setVisibility(View.GONE);
                break;
            }
            case 6: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.VISIBLE);
                tv5.setVisibility(View.VISIBLE);

                break;
            }
        }
    }

    @Override
    public void retry() {

    }

    public void goToNextPage() {
        switch (appComponent.getSpUtils().getAccountType()) {
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
                newIntent(StationRegistrationActivity.class, null, false);

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

    @OnClick({R.id.btnVerify, R.id.layOtp, R.id.tvResend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVerify: {
                if (!isForget)
                    verifyOtp(otp);
                else
                    verifyForgetOtp(otp);
                break;
            }
            case R.id.layOtp: {
                showKey();
                etOtp.requestFocus();
                break;
            }
            case R.id.tvResend: {
                ResendOtpRequestModel model = new ResendOtpRequestModel();
                if(otp_id!=null)
                {
                    model.setOtpId(otp_id);
                    model.setResendCase("SIGNUP");
                    hideKB();
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().resendOtp(model), resendListener);
                }

            }
        }
    }

    private void verifyForgetOtp(String otp) {

        if (otp.trim().isEmpty())
        {
            showMsg(rootLayout, getResources().getString(R.string.please_enter_otp));
            return;
        }

        VerifyForgetPassRequestModel requestModel = new VerifyForgetPassRequestModel();
        requestModel.setOtp(otp);
        requestModel.setUserType(appComponent.getSpUtils().getAccountType().toString());
        requestModel.setOtpId(otp_id);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().verifyForgotPassword(requestModel), verifyForgetPass);
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
                etOtp.setText(mesgDetail.getOtp());
                if (!isForget)
                    verifyOtp(mesgDetail.getOtp());
                else
                    verifyForgetOtp(mesgDetail.getOtp());
            }

        }
    };

}
