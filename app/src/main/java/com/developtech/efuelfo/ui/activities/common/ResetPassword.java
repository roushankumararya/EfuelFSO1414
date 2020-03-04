package com.developtech.efuelfo.ui.activities.common;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.ResetPasswordRequestModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.util.SPUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPassword extends MyActionBar {

    @BindView(R.id.etNewPass)
    EditText etNewPass;
    @BindView(R.id.etConfirmPass)
    EditText etConfirmPass;
    @BindView(R.id.btnContinue)
    Button btnContinue;
    String newPass, confirmPass;
    NetworkListener listener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                newIntentClear(LoginActivity.class, null, true);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        init();
        initComponents();
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void retry() {

    }

    @OnClick(R.id.btnContinue)
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btnContinue: {
                checkValidation();
                break;
            }
        }
    }

    public void checkValidation() {
        newPass = etNewPass.getText().toString().trim();
        confirmPass = etConfirmPass.getText().toString().trim();
        if (newPass.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_new_passw));
            return;
        }

        if (newPass.length()<6)
        {
            showMsg(rootLayout, getString(R.string.new_password_length_check));
            return;
        }

        if (confirmPass.length()<6)
        {
            showMsg(rootLayout, getString(R.string.cnf_password_length_check));
            return;
        }

//        String numberRegx = "(.)*(\\d)(.)*";
//
//        Pattern pattern = Pattern.compile(numberRegx);
//
//        Matcher matcher = pattern.matcher(newPass);
//
//        if (!matcher.matches())
//        {
//            showMsg(rootLayout, getString(R.string.password_number_check));
//            etNewPass.requestFocus();
//            return;
//        }
//
//        String alphabetRegx = ".*[a-zA-Z]+.*";
//
//        pattern = Pattern.compile(alphabetRegx);
//
//        matcher = pattern.matcher(newPass);
//
//        if (!matcher.matches())
//        {
//            showMsg(rootLayout, getString(R.string.password_alphabet_check));
//            etConfirmPass.requestFocus();
//            return;
//        }

        if (confirmPass.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_c_pwd));
            return;
        }

        if (!newPass.equalsIgnoreCase(confirmPass)) {
            showMsg(rootLayout, getString(R.string.password_mismatch));
            return;
        }
        ResetPasswordRequestModel requestModel = new ResetPasswordRequestModel();
        requestModel.setPassword(newPass);
        requestModel.setPasscode(newPass);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().resetPassword(requestModel), listener);

    }
}
