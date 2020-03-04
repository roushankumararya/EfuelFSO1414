package com.developtech.efuelfo.ui.activities.stationOwner;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.DispenseTransRequestModel;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.responseModel.VerifyTransResponseModel;
import com.developtech.efuelfo.model.responseModel.VerifyVehicleModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyTransactionDetailActivity extends MyActionBar {

    @BindView(R.id.rootlayout)
    CoordinatorLayout rootLayout;

    @BindView(R.id.tvFuelType)
    TextView tvFuelType;

    @BindView(R.id.tvQty)
    TextView tvQty;

    @BindView(R.id.tvAmount)
    TextView tvAmount;

    @BindView(R.id.tvTransId)
    TextView tvTransId;

    @BindView(R.id.tvOwnerName)
    TextView tvOwnerName;

    @BindView(R.id.tvDriverName)
    TextView tvDriverName;

    @BindView(R.id.tvDateTime)
    TextView tvDateTime;

    @BindView(R.id.tvPaymentStatus)
    TextView tvPaymentStatus;

    @BindView(R.id.etDispenseCode)
    EditText etDispenseCode;

    @BindView(R.id.btnDispense)
    Button btnDispense;

    @BindView(R.id.lytCode)
    LinearLayout lytCode;

    @BindView(R.id.lytDriver)
    LinearLayout lytDriver;

    @BindView(R.id.tvVehicleNumber)
    TextView tvVehicleNumber;


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

    VerifyVehicleModel verifyModel;
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_transaction_detail);
        ButterKnife.bind(this);
        init();
        initComponents();
        setHomeTitle(getString(R.string.verifytransaction_caps));
        showNotification(false);
        setHomeImage(true);

        if (getIntent().getExtras()!=null)
        {
            verifyModel = (VerifyVehicleModel) getIntent().getExtras().getSerializable("model");

            bindData();
        }


        etDispenseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = etDispenseCode.getText().toString().trim();
                System.out.println("onTextChanged");
                if (code.length()==4)
                {
                    dispenseTrans();
                }

                switch (code.length())
                {
                    case 0: {
                        setViewVisibility(0);
                        setTextViewVisibility(0);
                        break;
                    }
                    case 1: {
                        setViewVisibility(1);
                        setTextViewVisibility(1);
                        tv.setText(code.charAt(0) + "");
                        break;
                    }
                    case 2: {
                        setViewVisibility(2);
                        setTextViewVisibility(2);
                        tv1.setText(code.charAt(1) + "");
                        break;
                    }
                    case 3: {
                        setViewVisibility(3);
                        setTextViewVisibility(3);
                        tv2.setText(code.charAt(2) + "");
                        break;
                    }
                    case 4: {
                        setViewVisibility(4);
                        setTextViewVisibility(4);
                        tv3.setText(code.charAt(3) + "");
                        break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("afterTextChanged");
            }
        });
    }

    @Override
    public void initComponents() {

    }

    void bindData()
    {
        if (verifyModel==null)
            return;


        tvVehicleNumber.setText(verifyModel.getVehicleNumber());

        tvFuelType.setText(verifyModel.getFuelType());
        tvQty.setText(verifyModel.getQuantity());
        tvAmount.setText(getResources().getString(R.string.rupee_symbol)+" "+verifyModel.getAmount());
        tvDateTime.setText(appComponent.getUtilFunctions().toLocal(verifyModel.getCreatedAt()));
        tvPaymentStatus.setText(verifyModel.getStatus());
        tvTransId.setText(verifyModel.getTransactionId());
        tvPaymentStatus.setText(verifyModel.getStatus());
        tvOwnerName.setText(verifyModel.getVehicleOwnerName());


        if (verifyModel.getDriverName()!=null && !verifyModel.getDriverName().trim().isEmpty())
        {
            tvDriverName.setText(verifyModel.getDriverName());
        }
        else
        {
            lytDriver.setVisibility(View.GONE);
        }
    }

    @Override
    public void retry() {

    }

    @OnClick({R.id.btnDispense, R.id.lytCode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDispense: {

                if (etDispenseCode.getText().toString().trim().isEmpty())
                {
                    showMsg(rootLayout, getResources().getString(R.string.enter_dispense_code));
                    return;
                }


                dispenseTrans();
                break;
            }
            case R.id.lytCode : {
                showKey();
                etDispenseCode.requestFocus();
                break;
            }
        }
    }

    void dispenseTrans()
    {
        DispenseTransRequestModel requestModel = new DispenseTransRequestModel(verifyModel.getId(), etDispenseCode.getText().toString().trim());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().dispenseVerifyTrans(requestModel), dispenseListener);
    }

    NetworkListener dispenseListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg( getResources().getString(R.string.transaction_dispensed));
                setResult(RESULT_OK);
                finish();
            }
            else
            {
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


    void setViewVisibility(int i) {
        switch (i) {
            case 0: {
                view.setVisibility(View.VISIBLE);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                break;
            }
            case 1: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                break;
            }
            case 2: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.VISIBLE);
                break;
            }
            case 3: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.VISIBLE);
                break;
            }
            case 4: {
                view.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
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
                break;
            }
            case 1: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                break;
            }
            case 2: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                break;
            }
            case 3: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.GONE);
                break;
            }
            case 4: {
                tv.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

}
