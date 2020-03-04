package com.developtech.efuelfo.ui.activities.stationOwner;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomButton;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AgreementRequestModel;
import com.developtech.efuelfo.model.requestModel.TerminateAgreementRequestModel;
import com.developtech.efuelfo.model.responseModel.CreditAgreementResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.dialogFragments.VehicleOwnerProfileDialog;
import com.developtech.efuelfo.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestPendingActivity extends MyActionBar {

    @BindView(R.id.btnTerminate)
    CustomButton btnTerminate;
    @BindView(R.id.cbTerms)
    CheckBox cbTerms;
    @BindView(R.id.lytSaveChanges)
    LinearLayout lytSaveChanges;
    @BindView(R.id.layAmount)
    LinearLayout layAmount;
    @BindView(R.id.tvFuelStationName)
    TextView tvFuelStationName;
    @BindView(R.id.tvCountry)
    TextView tvCountry;
    @BindView(R.id.tvPaymentStatus)
    TextView tvPaymentStatus;
    @BindView(R.id.tvDays)
    TextView tvDays;
    @BindView(R.id.tvAmount)
    TextView tvAmount;
    @BindView(R.id.tvProfileInfo)
    TextView tvProfileInfo;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    Bundle bundle;
    String page = "", agreementId = "";
    boolean isApproved;
    CreditAgreementResponseModel creditAgreementResponseModel;
    NetworkListener listener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                setResult(200);
                finish();
            } else
                showMsg(rootLayout, responseBody.getMessage());
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
        setContentView(R.layout.activity_request_pending);
        ButterKnife.bind(this);
        init();
        initComponents();
        setMenuItem();
        setHomeTitle(getString(R.string.request));
        setHomeImage(true);
        showNotification(false);

    }

    @Override
    public void initComponents() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            page = bundle.getString("page");
            isApproved = bundle.getBoolean("isApproved");
            if (page!=null) {
                if (page.equalsIgnoreCase("creditAgreement")) {
                    creditAgreementResponseModel = (CreditAgreementResponseModel) bundle.getSerializable("model");
                    setData(creditAgreementResponseModel);

                } else if (page.equalsIgnoreCase("paymentHistory")) {
                    PaymentHistoryResponseModel model = (PaymentHistoryResponseModel) bundle.getSerializable("model");
                    setData(model);
                    btnTerminate.setVisibility(View.GONE);
                }

            }
            else if (bundle.getString("id")!=null)
            {
                AgreementRequestModel requestModel = new AgreementRequestModel();
                requestModel.setRequestId(bundle.getString("id"));
                appComponent.getServiceCaller().callService(appComponent.getAllApis().agreementDetails(requestModel), agreementDetailListener);
            }
        }
    }

    public void setData(CreditAgreementResponseModel model) {
        agreementId = model.getId();
        cbTerms.setVisibility(View.VISIBLE);
        tvFuelStationName.setText(model.getVehicleOwner().getFirstName()+" "+model.getVehicleOwner().getLastName());

        tvPaymentStatus.setText(model.getStatus());
        if (model.getVehicleOwner().getCompanyName()!=null)
        {
            tvCountry.setText(model.getVehicleOwner().getCompanyName());
        }

        handleStatus(model.getStatus());


        tvDays.setText(model.getDuration() + " Days");

        if (isApproved){
            tvAmount.setText(getResources().getString(R.string.rupee_symbol) + " " + model.getRemainingCredits()+ "/" + model.getCreditLimit());
        }
        else
        {
            tvAmount.setText(getResources().getString(R.string.rupee_symbol) + " " + model.getCreditLimit() );
        }
    }

    public void setData(PaymentHistoryResponseModel model) {
        agreementId = model.getId();
        if(model.getFuelStation()!=null)
        {
            tvFuelStationName.setText(model.getFuelStation().getName());
        }

        handleStatus(model.getStatus());

        tvDays.setText("20 Days");
        tvAmount.setText(model.getAmount() + "");
    }

    void handleStatus(String status)
    {
        if (status.trim().equalsIgnoreCase("NEW")) {
            tvPaymentStatus.setText("PENDING");
        }
        else if(status.equalsIgnoreCase("TERMINATED"))
        {
            cbTerms.setVisibility(View.GONE);
            lytSaveChanges.setVisibility(View.GONE);
        }
        else if (status.equalsIgnoreCase("APPROVED"))
        {
            isApproved = true;
            btnTerminate.setVisibility(View.VISIBLE);
            lytSaveChanges.setVisibility(View.GONE);
        }
        else if(status.equalsIgnoreCase("COMPLETED"))
        {
            cbTerms.setVisibility(View.GONE);
            lytSaveChanges.setVisibility(View.GONE);
        }
    }


    @Override
    public void retry() {

    }

    @OnClick({R.id.btnTerminate, R.id.tvProfileInfo, R.id.btnAccept, R.id.btnDecline})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTerminate: {
                if (cbTerms.isChecked()) {
                    terminateConfirmDialog();
                }
                else
                {
                    showMsg(rootLayout, getResources().getString(R.string.please_accept));
                }
                break;
            }
            case R.id.tvProfileInfo: {
                VehicleOwnerProfileDialog dialogFragment = new VehicleOwnerProfileDialog();
                dialogFragment.setData(creditAgreementResponseModel,null, appComponent);
                dialogFragment.show(getSupportFragmentManager(), "view_owner");
                break;
            }
            case R.id.btnAccept: {
                isAccepted = true;
                callAgreementRequest("APPROVED");
                break;
            }
            case R.id.btnDecline: {
                isAccepted = false;
                confirmDialog("REJECTED");
                break;
            }
        }
    }

    boolean isAccepted;

    void callAgreementRequest(String status)
    {
        if (!cbTerms.isChecked() && isAccepted)
        {
            showMsg(rootLayout, getResources().getString(R.string.please_accept));
            return;
        }
        AgreementRequestModel requestModel = new AgreementRequestModel();
        requestModel.setRequestId(creditAgreementResponseModel.getId());
        requestModel.setStatus(status);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().agreementRequest(requestModel), agreementRequestListener);
    }


    NetworkListener agreementRequestListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                if (isAccepted) {
                    showMsg(getResources().getString(R.string.request_accepted));
                }
                else
                {
                    showMsg(getResources().getString(R.string.request_declined));
                }
                setResult(200);
                finish();
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


    void confirmDialog(final String status)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_general);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvDesc = dialog.findViewById(R.id.tvDesc);
        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        tvTitle.setText(getResources().getString(R.string.confirm));
        tvDesc.setText(getResources().getString(R.string.desc_decline_request));

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAgreementRequest(status);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    void terminateConfirmDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_general);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvDesc = dialog.findViewById(R.id.tvDesc);
        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        tvTitle.setText(getResources().getString(R.string.confirm));
        tvDesc.setText(getResources().getString(R.string.desc_terminate));

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TerminateAgreementRequestModel requestModel = new TerminateAgreementRequestModel();
                requestModel.setAgreementId(agreementId);
                appComponent.getServiceCaller().callService(appComponent.getAllApis().terminateAgreement(requestModel), listener);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }


    NetworkListener agreementDetailListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                creditAgreementResponseModel = (CreditAgreementResponseModel) responseBody.getResutData();
                setData(creditAgreementResponseModel);
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
