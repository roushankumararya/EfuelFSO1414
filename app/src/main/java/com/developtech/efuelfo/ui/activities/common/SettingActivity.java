package com.developtech.efuelfo.ui.activities.common;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SettingsRequestModel;
import com.developtech.efuelfo.model.responseModel.SettingsResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends MyActionBar {
    @BindView(R.id.tvPrivacyPolicy)
    TextView tvPrivacyPolicy;
    @BindView(R.id.tvTermsCond)
    TextView tvTermsCond;
    @BindView(R.id.tvPaymentAgreement)
    TextView tvPaymentAgreement;
    @BindView(R.id.tvLicenceAgreement)
    TextView tvLicenceAgreement;
    @BindView(R.id.tvDisclaimer)
    TextView tvDisclaimer;
    @BindView(R.id.tvFaq)
    TextView tvFaq;
    @BindView(R.id.tvUserGuide)
    TextView tvUserGuide;
    @BindView(R.id.tvAppWalkThrough)
    TextView tvAppWalkThrough;
    @BindView(R.id.tvThemeSub)
    TextView tvThemeSub;
    @BindView(R.id.tvChangePass)
    TextView tvChangePass;
    @BindView(R.id.tvLanguageSub)
    TextView tvLanguageSub;
    @BindView(R.id.layLang)
    LinearLayout layLang;
    @BindView(R.id.layTheme)
    LinearLayout layTheme;

    SPUtils.ACCOUNT_TYPES account_types;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        init();
        initComponents();
        setHomeTitle(getString(R.string.settings));
        setHomeImage(true);
        showNotification(false);
    }

    @Override
    public void initComponents() {
        setData();
    }

    public void setData() {
        if (appComponent.getSpUtils().getAccountType() != null) {
            account_types = appComponent.getSpUtils().getAccountType();

            if (account_types != null) {
                if (account_types == SPUtils.ACCOUNT_TYPES.VCO) {


                } else if (account_types == SPUtils.ACCOUNT_TYPES.DRV) {

                } else if (account_types == SPUtils.ACCOUNT_TYPES.FSO) {

                } else if (account_types == SPUtils.ACCOUNT_TYPES.OPR) {

                }
            }
        }
    }

    @Override
    public void retry() {

    }

    @OnClick({R.id.tvChangePass, R.id.layLang, R.id.layTheme, R.id.tvLanguageSub, R.id.tvThemeSub, R.id.tvAppWalkThrough, R.id.tvUserGuide, R.id.tvFaq
            , R.id.tvDisclaimer, R.id.tvLicenceAgreement, R.id.tvPaymentAgreement, R.id.tvTermsCond, R.id.tvPrivacyPolicy})
    public void onClick(View view) {

        List<String> required = new ArrayList(Arrays.asList("faqs", "disclaimer", "licenseAgreement", "paymentAgreement", "creditAgreement", "termAndCondition", "privacyPolicy"));
        SettingsRequestModel requestModel = new SettingsRequestModel();
        requestModel.setRequired(required);

        switch (view.getId()) {
            case R.id.tvChangePass: {
                newIntent(ChangePasswordActivity.class, null, false);
                break;
            }
            case R.id.layLang: {
                break;
            }
            case R.id.layTheme: {
                break;
            }
            case R.id.tvLanguageSub: {
                break;
            }
            case R.id.tvThemeSub: {
                break;
            }
            case R.id.tvAppWalkThrough: {
                Bundle bundle = new Bundle();
                bundle.putBoolean("settings", true);
                newIntent(TutorialAcitvity.class, bundle, false);
                break;
            }
            case R.id.tvUserGuide: {
                break;
            }
            case R.id.tvFaq: {
                CLICKED = FAQ;
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getSettings(requestModel), settingsListener);
                break;
            }
            case R.id.tvDisclaimer: {
                CLICKED = DESCLAIMER;
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getSettings(requestModel), settingsListener);
                break;
            }
            case R.id.tvLicenceAgreement: {
                CLICKED = LA;
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getSettings(requestModel), settingsListener);
                break;
            }
            case R.id.tvPaymentAgreement: {
                CLICKED = PA;
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getSettings(requestModel), settingsListener);
                break;
            }
            case R.id.tvTermsCond: {
                CLICKED = TC;
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getSettings(requestModel), settingsListener);
                break;
            }
            case R.id.tvPrivacyPolicy: {
                CLICKED = PP;
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getSettings(requestModel), settingsListener);
                break;
            }
        }
    }

    int CLICKED;
    final int FAQ=1, DESCLAIMER=2, LA=3, PA=4, TC=5, PP=6;

    void showSettingsDialog(SettingsResponseModel model)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_settings);

        TextView tvContent = dialog.findViewById(R.id.tvSettings);
        Button btnOk = dialog.findViewById(R.id.btnOk);

        switch (CLICKED)
        {
            case FAQ:{
                tvContent.setText(Html.fromHtml(model.getFaqs()).toString());
                break;
            }
            case DESCLAIMER:{
                tvContent.setText(Html.fromHtml(model.getDisclaimer()).toString());
                break;
            }
            case LA:{
                tvContent.setText(Html.fromHtml(model.getLicenseAgreement()).toString());
                break;
            }
            case PA:{
                tvContent.setText(Html.fromHtml(model.getPaymentAgreement()).toString());
                break;
            }
            case TC:{
                tvContent.setText(Html.fromHtml(model.getTermAndCondition()).toString());
                break;
            }
            case PP:{
                tvContent.setText(Html.fromHtml(model.getPrivacyPolicy()).toString());
                break;
            }
        }

        CLICKED = 0;

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    NetworkListener settingsListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SettingsResponseModel responseModel = (SettingsResponseModel) responseBody.getResutData();
                showSettingsDialog(responseModel);
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
