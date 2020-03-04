package com.developtech.efuelfo.ui.activities.common;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.UserForgetPassRequestModel;
import com.developtech.efuelfo.model.responseModel.SignUpResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgotPassword extends MyActionBar {

    @BindView(R.id.btnSend)
    Button btnSend;
    @BindView(R.id.etLoginId)
    EditText etLoginId;
    @BindView(R.id.spinnerId)
    Spinner spinnerId;
    private String loginId, otpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        init();
        initComponents();
    }

    @OnClick(R.id.btnSend)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend: {
                checkValidation();
                break;
            }
        }
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void retry() {

    }

    public void checkValidation() {
        loginId = etLoginId.getText().toString().trim();
        hideKB();
        if (loginId.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_login_id));
            return;
        }

        if (loginId.length()!=10)
        {
            showMsg(rootLayout, getString(R.string.login_id_number_length_check));
            return;
        }
        UserForgetPassRequestModel requestModel = new UserForgetPassRequestModel();
        requestModel.setLoginId(loginId);
        requestModel.setUserType(appComponent.getSpUtils().getAccountType().toString());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().userForgotPassword(requestModel), listener);
    }

    NetworkListener listener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SignUpResponseModel responseModel = (SignUpResponseModel) responseBody.getResutData();
                otpId = responseModel.getId();
                Bundle bundle = new Bundle();
                bundle.putString("otp_id", otpId);
                bundle.putString("loginId", etLoginId.getText().toString());
                bundle.putBoolean("is_forget", true);
                newIntent(OtpActivity.class, bundle, false);
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
            hideProgress();
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };
}
