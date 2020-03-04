package com.developtech.efuelfo.ui.activities.common;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.ChangePasswordRequestModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.util.SPUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends MyActionBar {

    @BindView(R.id.etConfirmPassocode)
    EditText etConfirmPassocode;
    @BindView(R.id.etNewPasscode)
    EditText etNewPasscode;
    @BindView(R.id.etOldPasscode)
    EditText etOldPasscode;

    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;
    NetworkListener listener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg("Your password has been changed");
                resetFields();
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

    String oldPass, newPass, confPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.OPR) {
            setContentView(R.layout.activity_passcode);
        }
        else if(appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.FSO)
        {
            setContentView(R.layout.activity_password);
        }
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
    public void onClick(View view) {
        hideKB();
        oldPass = etOldPasscode.getText().toString().trim();
        newPass = etNewPasscode.getText().toString().trim();
        confPass = etConfirmPassocode.getText().toString().trim();

        validate();
    }

    void validate() {

        if(appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.DRV) {

            if (oldPass.isEmpty()) {
                showMsg(rootLayout, getString(R.string.enter_old_passcode));
                etOldPasscode.requestFocus();
                return;
            }

            if (newPass.trim().isEmpty()) {
                showMsg(rootLayout, getString(R.string.enter_new_passw));
                etNewPasscode.requestFocus();
                return;
            }

            if (confPass.trim().isEmpty()) {
                showMsg(rootLayout, getString(R.string.enter_c_pwd));
                etConfirmPassocode.requestFocus();
                return;
            }

            if (oldPass.length() < 6) {
                showMsg(rootLayout, getString(R.string.old_password_length_check));
                etOldPasscode.requestFocus();
                return;
            }

            if (newPass.length() < 6) {
                showMsg(rootLayout, getString(R.string.new_password_length_check));
                etNewPasscode.requestFocus();
                return;
            }

//            String numberRegx = "(.)*(\\d)(.)*";
//
//            Pattern pattern = Pattern.compile(numberRegx);
//
//            Matcher matcher = pattern.matcher(newPass);
//
//            if (!matcher.matches())
//            {
//                showMsg(rootLayout, getString(R.string.password_number_check));
//                etNewPasscode.requestFocus();
//                return;
//            }
//
//            String alphabetRegx = ".*[a-zA-Z]+.*";
//
//            pattern = Pattern.compile(alphabetRegx);
//
//            matcher = pattern.matcher(newPass);
//
//            if (!matcher.matches())
//            {
//                showMsg(rootLayout, getString(R.string.password_alphabet_check));
//                etNewPasscode.requestFocus();
//                return;
//            }

            if (confPass.length() < 6) {
                showMsg(rootLayout, getString(R.string.cnf_password_length_check));
                etConfirmPassocode.requestFocus();
                return;
            }

        }

        if (oldPass.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_old_password));
            etOldPasscode.requestFocus();
            return;
        }

        if (newPass.trim().isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_new_passw));
            etNewPasscode.requestFocus();
            return;
        }

        if (confPass.trim().isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_c_pwd));
            etConfirmPassocode.requestFocus();
            return;
        }

        if (oldPass.length() < 6) {
            showMsg(rootLayout, getString(R.string.old_password_length_check));
            etOldPasscode.requestFocus();
            return;
        }

        if (newPass.length() < 6) {
            showMsg(rootLayout, getString(R.string.new_password_length_check));
            etNewPasscode.requestFocus();
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
//            etNewPasscode.requestFocus();
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
//            etNewPasscode.requestFocus();
//            return;
//        }

        if (confPass.length() < 6) {
            showMsg(rootLayout, getString(R.string.cnf_password_length_check));
            etConfirmPassocode.requestFocus();
            return;
        }


        if (!newPass.equalsIgnoreCase(confPass)) {
            showMsg(rootLayout, getString(R.string.new_confirm_password));
            return;
        }

        ChangePasswordRequestModel requestModel = new ChangePasswordRequestModel();
        requestModel.oldPassword = oldPass;
        requestModel.newPassword = newPass;
        requestModel.oldPasscode = oldPass;
        requestModel.newPasscode = newPass;
        appComponent.getServiceCaller().callService(appComponent.getAllApis().changePassword(requestModel), listener);
    }

    void resetFields()
    {
        etOldPasscode.setText("");
        etNewPasscode.setText("");
        etConfirmPassocode.setText("");
        finish();
    }
}
