package com.developtech.efuelfo.ui.activities.stationOwner;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.EmailInvoiceRequestModel;
import com.developtech.efuelfo.model.requestModel.TotalRequest;
import com.developtech.efuelfo.model.requestModel.TransDetailRequestModel;
import com.developtech.efuelfo.model.requestModel.TransRecievedRequestModel;
import com.developtech.efuelfo.model.responseModel.OnlineTransactionsResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.model.responseModel.TotalResponse;
import com.developtech.efuelfo.model.responseModel.ViewCashTransactionResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.dialogFragments.DriverProfileDialog;
import com.developtech.efuelfo.ui.dialogFragments.FuelStationDetailDialog;
import com.developtech.efuelfo.ui.dialogFragments.VehicleDetailDialog;
import com.developtech.efuelfo.ui.dialogFragments.VehicleOwnerProfileDialog;
import com.developtech.efuelfo.util.SPUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransactionDetailsActivity extends MyActionBar {

    public final static int WHITE = 0xFFFFFFFF;
    public final static int BLACK = 0xFF000000;
    public final static int WIDTH = 400;
    public final static int HEIGHT = 400;
    @BindView(R.id.tvTransId)
    TextView tvTransId;
    @BindView(R.id.tvTransDate)
    TextView tvTransDate;
    @BindView(R.id.tvOwnerName)
    TextView tvOwnerName;
    @BindView(R.id.frameOwnerInfo)
    FrameLayout frameOwnerInfo;
    @BindView(R.id.tvVehicleNumber)
    TextView tvVehicleNumber;
    @BindView(R.id.frameVehicleInfo)
    FrameLayout frameVehicleInfo;
    @BindView(R.id.tvFuelStation)
    TextView tvFuelStation;
    @BindView(R.id.frameStationInfo)
    FrameLayout frameStationInfo;
    @BindView(R.id.tvPaymentType)
    TextView tvPaymentType;
    @BindView(R.id.tvFuelType)
    TextView tvFuelType;
    @BindView(R.id.ivQrCode)
    ImageView ivQrCode;
    @BindView(R.id.tvQty)
    TextView tvQty;
    @BindView(R.id.tvTotalAmount)
    TextView tvTotalAmount;
    @BindView(R.id.tvPaymentStatus)
    TextView tvPaymentStatus;
    @BindView(R.id.btnEmailInvoice)
    Button btnEmailInvoice;
    @BindView(R.id.btnReceive)
    Button btnReceive;
    @BindView(R.id.tvDriverName)
    TextView tvDriverName;
    @BindView(R.id.frameDriverInfo)
    FrameLayout frameDriverInfo;
    @BindView(R.id.lytDriver)
    LinearLayout lytDriver;
    @BindView(R.id.lytPaymentType)
    LinearLayout lytPaymentType;

    @BindView(R.id.lytPaymentStatus)
    LinearLayout lytPaymentStatus;

    @BindView(R.id.tvOwnerNameLabel)
    TextView tvOwnerNameLabel;

    @BindView(R.id.lytOwner)
    LinearLayout lytOwner;

    @BindView(R.id.ivInfoWindow)
    ImageView ivInfoWindow;

    private PaymentHistoryResponseModel responseModel;
    private ViewCashTransactionResponseModel cashTransModel;
    private OnlineTransactionsResponseModel onlineTransModel;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        ButterKnife.bind(this);
        init();
        initComponents();
        setHomeImage(true);
        setHomeTitle(getString(R.string.transaction_details));
        showNotification(false);
    }

    @Override
    public void initComponents() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getSerializable("model")!=null)
            {
                responseModel = (PaymentHistoryResponseModel) bundle.getSerializable("model");
                setData();
            }
            else if(bundle.getSerializable("cash")!=null)
            {
                cashTransModel = (ViewCashTransactionResponseModel) bundle.getSerializable("cash");
                bindData();
            }
            else if(bundle.getSerializable("online")!=null)
            {
                onlineTransModel = (OnlineTransactionsResponseModel) bundle.getSerializable("online");
                bindView();
            }
            else if (bundle.getString("id")!=null)
            {
                String requestId = bundle.getString("id");
                TransDetailRequestModel requestModel = new TransDetailRequestModel(requestId);
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getTransDetail(requestModel), transDetailListener);
            }
        }

        if (appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.OPR)
        {
            btnEmailInvoice.setVisibility(View.GONE);
            btnReceive.setVisibility(View.GONE);
        }
    }

    private void bindData()
    {
        ivInfoWindow.setVisibility(View.GONE);
        tvOwnerNameLabel.setText(getResources().getString(R.string.mobile_number).toUpperCase());
        lytDriver.setVisibility(View.GONE);
        lytPaymentType.setVisibility(View.GONE);
        frameOwnerInfo.setVisibility(View.GONE);
        frameVehicleInfo.setVisibility(View.GONE);
        frameStationInfo.setVisibility(View.GONE);
        tvOwnerName.setText(cashTransModel.getMobileNumber());
        tvVehicleNumber.setText(cashTransModel.getVehicleNumber());
        tvFuelStation.setText(appComponent.getSpUtils().getFuelStationModel().getName());
        tvFuelType.setText(cashTransModel.getFuelType());
        tvQty.setText(cashTransModel.getQuantity());
        tvTotalAmount.setText(getResources().getString(R.string.rupee_symbol)+" "+cashTransModel.getAmount());
        tvTransDate.setText(appComponent.getUtilFunctions().toLocal(cashTransModel.getCreatedAt()));

        frameStationInfo.setVisibility(View.VISIBLE);

        btnEmailInvoice.setVisibility(View.GONE);
        btnReceive.setVisibility(View.GONE);
//        TotalRequest totalRequest = new TotalRequest();
//        totalRequest.setId(cashTransModel.getId());
//        appComponent.getServiceCaller().callService(appComponent.getAllApis().getTotal(totalRequest), getTotalListener);


        tvPaymentStatus.setText("DONE");

        tvFuelType.setText(cashTransModel.getFuelType());

        try {
            ivQrCode.setImageBitmap(encodeAsBitmap(cashTransModel.getId()));
        } catch (WriterException e) {

        }

//        convenienceFee = Float.parseFloat(cashTransModel.getConvenienceFee());
//        GST = Float.parseFloat(cashTransModel.getGst());
//        totalAmount = Float.parseFloat(cashTransModel.getTotalAmount());
//        amount = Float.parseFloat(cashTransModel.getAmount());
//        quantity = Float.parseFloat(cashTransModel.getAmount());
//        fuelPrice = amount / quantity;
    }

    private void setData() {
        tvTransId.setText(getResources().getString(R.string.trans_id) + responseModel.getTransactionId());
        tvTransDate.setText(appComponent.getUtilFunctions().toLocal(responseModel.getCreatedAt()));
        tvOwnerName.setText(appComponent.getSpUtils().getName());
        tvVehicleNumber.setText(responseModel.getVehicle().getVehicleNumber());
        tvFuelType.setText(responseModel.getFuelType());
        tvDriverName.setText(responseModel.getDriver().getFirstName() + " " + responseModel.getDriver().getLastName());
        tvFuelStation.setText(appComponent.getSpUtils().getFuelStationModel().getName());
        tvQty.setText(responseModel.getQuantity() + "");
        tvTotalAmount.setText(getResources().getString(R.string.rupee_symbol)+" "+responseModel.getAmount());
        tvPaymentStatus.setText(responseModel.getStatus());
        try {
            ivQrCode.setImageBitmap(encodeAsBitmap(responseModel.getId()));
        } catch (WriterException e) {

        }
        if (responseModel.getStatus()!=null && !responseModel.getStatus().isEmpty())
        {
            handleStatus(responseModel.getStatus(), responseModel.getInvoiceStatus());
        }

        convenienceFee = Float.parseFloat(responseModel.getConvenienceFee());
        GST = Float.parseFloat(responseModel.getGst());
        totalAmount = Float.parseFloat(responseModel.getTotalAmount());
        fuelPrice = responseModel.getAmount() / responseModel.getQuantity();
        amount = responseModel.getAmount();
        quantity = responseModel.getQuantity();
    }

    private void bindView()
    {
        tvTransId.setText(getResources().getString(R.string.trans_id) + onlineTransModel.getTransactionId());
        tvTransDate.setText(appComponent.getUtilFunctions().toLocal(onlineTransModel.getCreatedAt()));
        tvOwnerName.setText(onlineTransModel.getVehicleOwner().getFirstName()+" "+onlineTransModel.getVehicleOwner().getLastName());
        tvVehicleNumber.setText(onlineTransModel.getVehicle().getVehicleNumber());
        tvFuelType.setText(onlineTransModel.getFuelType());

        if (onlineTransModel.getVehicle().getDriver()!=null && onlineTransModel.getVehicle().getDriver().getFirstName()!=null)
        {
            tvDriverName.setText(onlineTransModel.getVehicle().getDriver().getFirstName() + " " + onlineTransModel.getVehicle().getDriver().getLastName());
        }
        else
        {
            lytDriver.setVisibility(View.GONE);
        }

        tvFuelStation.setText(appComponent.getSpUtils().getFuelStationModel().getName());
        tvQty.setText(onlineTransModel.getQuantity() + "");
        tvTotalAmount.setText(getResources().getString(R.string.rupee_symbol)+" "+onlineTransModel.getAmount());
        tvPaymentStatus.setText(onlineTransModel.getStatus());
        try {
            ivQrCode.setImageBitmap(encodeAsBitmap(onlineTransModel.getVehicleOwner().getId()));
        } catch (WriterException e) {

        }

        if (onlineTransModel.isPay4pal())
        {
            frameVehicleInfo.setVisibility(View.GONE);
        }

        if (onlineTransModel.getStatus()!=null && !onlineTransModel.getStatus().isEmpty())
        {
            if(onlineTransModel.getStatus().trim().equals("DONE") || onlineTransModel.getStatus().trim().equals("APPROVED"))
            {
                lytPaymentType.setVisibility(View.VISIBLE);
                tvPaymentType.setText(onlineTransModel.getPaymentType());
            }
            else
            {
                lytPaymentType.setVisibility(View.GONE);
            }
            handleStatus(onlineTransModel.getStatus(), onlineTransModel.getInvoiceStatus());
        }

        convenienceFee = Float.parseFloat(onlineTransModel.getConvenienceFee());
        GST = Float.parseFloat(onlineTransModel.getGst());
        totalAmount = Float.parseFloat(onlineTransModel.getTotalAmount());
        fuelPrice = onlineTransModel.getAmount() / onlineTransModel.getQuantity();
        amount = onlineTransModel.getAmount();
        quantity = onlineTransModel.getQuantity();
    }


    private void setTransData() {
        lytPaymentType.setVisibility(View.GONE);

        tvTransId.setText(getResources().getString(R.string.trans_id) + responseModel.getTransactionId());
        tvTransDate.setText(appComponent.getUtilFunctions().toLocal(responseModel.getCreatedAt()));
        tvOwnerName.setText(appComponent.getSpUtils().getName());
        tvVehicleNumber.setText(responseModel.getVehicle().getVehicleNumber());
        tvFuelType.setText(responseModel.getFuelType());

        tvFuelStation.setText(appComponent.getSpUtils().getFuelStationModel().getName());
        tvQty.setText(responseModel.getQuantity() + "");
        tvTotalAmount.setText(getResources().getString(R.string.rupee_symbol) + " " + responseModel.getAmount());

        tvOwnerName.setText(responseModel.getVehicleOwner().getFirstName()+" "+responseModel.getVehicleOwner().getLastName());

        try {
            ivQrCode.setImageBitmap(encodeAsBitmap(responseModel.getId()));
        } catch (WriterException e) {

        }

        if (responseModel.getVehicle().getDriver()!=null && responseModel.getVehicle().getDriver().getFirstName()!=null)
        {
            tvDriverName.setText(responseModel.getVehicle().getDriver().getFirstName() + " " + responseModel.getVehicle().getDriver().getLastName());
        }
        else
        {
            lytDriver.setVisibility(View.GONE);
        }

        if (responseModel.isPay4pal())
        {
            frameVehicleInfo.setVisibility(View.GONE);
        }

        if (responseModel.getStatus()!=null && !responseModel.getStatus().isEmpty())
        {
            handleStatus(responseModel.getStatus(), responseModel.getInvoiceStatus());
        }

        convenienceFee = Float.parseFloat(responseModel.getConvenienceFee());
        GST = Float.parseFloat(responseModel.getGst());
        totalAmount = Float.parseFloat(responseModel.getTotalAmount());
        amount = responseModel.getAmount();
        quantity = responseModel.getQuantity();
        fuelPrice = amount / quantity;

    }

    void handleStatus(String status, String invoiceStatus)
    {
        if (status.equals("DONE"))
        {
            btnEmailInvoice.setVisibility(View.VISIBLE);
            if (invoiceStatus.equals("PENDING"))
            {
                btnReceive.setVisibility(View.VISIBLE);
            }
            else if (invoiceStatus.equals("DONE"))
            {
                btnReceive.setVisibility(View.GONE);
            }
        }
        else if(status.equals("APPROVED") || status.equals("REJECTED"))
        {
            btnEmailInvoice.setVisibility(View.GONE);
            btnReceive.setVisibility(View.GONE);
        }
        else if(status.equalsIgnoreCase("EXPIRED"))
        {
            btnEmailInvoice.setVisibility(View.GONE);
            btnReceive.setVisibility(View.GONE);
        }

        lytPaymentStatus.setVisibility(View.VISIBLE);

        tvPaymentStatus.setText(status);

        if (status.equalsIgnoreCase("REJECTED"))
        {
            tvPaymentStatus.setText("DECLINED");
        }


    }

    @Override
    public void retry() {

    }


    @OnClick({R.id.frameOwnerInfo, R.id.frameDriverInfo, R.id.frameVehicleInfo, R.id.frameStationInfo, R.id.btnEmailInvoice, R.id.ivInfoWindow, R.id.btnReceive})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frameOwnerInfo: {
                VehicleOwnerProfileDialog dialogFragment = new VehicleOwnerProfileDialog();
                if (onlineTransModel!=null && onlineTransModel.getVehicleOwner()!=null) {
                    dialogFragment.setData(null, onlineTransModel.getVehicleOwner(), appComponent);
                }
                else if(responseModel!=null && responseModel.getVehicleOwner()!=null)
                {
                    dialogFragment.setData(null, responseModel.getVehicleOwner(), appComponent);

                }
                dialogFragment.show(getSupportFragmentManager(), "view_owner");
                break;
            }
            case R.id.frameDriverInfo: {
                DriverProfileDialog dialogFragment = new DriverProfileDialog();
                if (responseModel!=null)
                {
                    dialogFragment.setData(appComponent,responseModel.getDriver(),null);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("model", responseModel);
                    dialogFragment.setArguments(bundle);

                }
                else if (onlineTransModel!=null)
                {
                    dialogFragment.setData(appComponent,null, onlineTransModel);
                }

                dialogFragment.show(getSupportFragmentManager(), "view_driver");
                break;
            }
            case R.id.frameVehicleInfo: {
                VehicleDetailDialog dialogFragment = new VehicleDetailDialog();
                Bundle bundle = new Bundle();
                if (responseModel!=null)
                {
                    bundle.putSerializable("model", responseModel);
                }
                else if (onlineTransModel!=null)
                {
                    bundle.putSerializable("online", onlineTransModel.getVehicle());
                }

                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(), "view_owner");
                break;
            }
            case R.id.frameStationInfo: {
                FuelStationDetailDialog dialogFragment = new FuelStationDetailDialog();
                Bundle bundle = new Bundle();
                if (responseModel!=null)
                {
                    bundle.putSerializable("model", appComponent.getSpUtils().getFuelStationModel());
                }
                else if (cashTransModel!=null || onlineTransModel!=null)
                {
                    bundle.putSerializable("cash", appComponent.getSpUtils().getFuelStationModel());
                }

                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(), "view_owner");
                break;
            }
            case R.id.btnEmailInvoice: {

                final String requestId;
                if (responseModel!=null)
                {
                    requestId = responseModel.getId();
                }
                else if(onlineTransModel!=null)
                {
                    requestId = onlineTransModel.getId();
                }
                else
                {
                    requestId = appComponent.getSpUtils().getFuelStationModel().getId();
                }

                final List<String> checkList = new ArrayList<>();
                checkList.add(requestId);

                final Dialog dialog = new Dialog(TransactionDetailsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_general);
                TextView tvTitle = dialog.findViewById(R.id.tvTitle);
                TextView tvDesc = dialog.findViewById(R.id.tvDesc);
                final EditText etEmail = dialog.findViewById(R.id.etEmail);
                Button btnCancel = dialog.findViewById(R.id.btnNo);
                Button btnSend = dialog.findViewById(R.id.btnYes);

                btnCancel.setText(getResources().getString(R.string.cancel));
                btnSend.setText(getResources().getString(R.string.send));

                tvDesc.setVisibility(View.GONE);
                etEmail.setVisibility(View.VISIBLE);

                tvTitle.setText(getResources().getString(R.string.enter_email_address));
                etEmail.setText(appComponent.getSpUtils().getUserData().getEmail());
                etEmail.setSelection(appComponent.getSpUtils().getUserData().getEmail().length());

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (etEmail.getText().toString().trim().isEmpty())
                        {
                            etEmail.setError(getResources().getString(R.string.enter_email_address));
                            return;
                        }

                        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                            etEmail.setError(getString(R.string.please_enter_valid_email_id));
                            return;
                        }

                        EmailInvoiceRequestModel requestModel = new EmailInvoiceRequestModel(checkList);
                        requestModel.setEmail(etEmail.getText().toString());

                        appComponent.getServiceCaller().callService(appComponent.getAllApis().sendInvoice(requestModel), emailInvoiceListener);
                        dialog.dismiss();
                    }
                });

                dialog.show();

                break;
            }
            case R.id.ivInfoWindow : {
                showAmountInfoWindow();
                break;
            }
            case R.id.btnReceive : {

                TransRecievedRequestModel requestModel = new TransRecievedRequestModel();
                List<String> foList = new ArrayList<>();
                foList.add(onlineTransModel.getId());
                requestModel.setVehicleOwner(foList);
                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().transRecieved(requestModel), recievedListener);
                break;
            }
            case R.id.ivQrCode : {
                if (cashTransModel!=null && cashTransModel.getId()!=null & !cashTransModel.getId().isEmpty())
                {
                    showQrCodeDialog();
                }
            }
        }
    }

    void showQrCodeDialog()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_qr_code);
        ImageView ivQrCode = dialog.findViewById(R.id.ivQrCode);
        try {
            ivQrCode.setImageBitmap(encodeAsBitmap(cashTransModel.getId()));
        } catch (WriterException e) {

        }

        dialog.show();
    }

    float convenienceFee , GST, totalAmount, amount, fuelPrice, quantity;

    void showAmountInfoWindow() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pay_now);

        TextView tvAmount, tvFuelQty, tvFuelPrice, tvConvenienceFee, tvGST, tvTotalAmount;

        tvAmount = dialog.findViewById(R.id.tvAmount);
        tvFuelQty = dialog.findViewById(R.id.tvFuelQty);
        tvFuelPrice = dialog.findViewById(R.id.tvFuelPrice);
        tvConvenienceFee = dialog.findViewById(R.id.tvConvenienceFee);
        tvGST = dialog.findViewById(R.id.tvGST);
        tvTotalAmount = dialog.findViewById(R.id.tvTotalAmount);

        tvAmount.setText(amount+"");
        tvFuelQty.setText(quantity + " "+getResources().getString(R.string.ltrs_notation));
        tvFuelPrice.setText(getResources().getString(R.string.rupee_symbol) + " " + fuelPrice);

        tvConvenienceFee.setText(getResources().getString(R.string.rupee_symbol) + " " + convenienceFee);
        tvGST.setText(getResources().getString(R.string.rupee_symbol) + " " + GST);

        tvTotalAmount.setText(getResources().getString(R.string.rupee_symbol) + " " + totalAmount);

        dialog.show();

    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (IllegalArgumentException iae) {
            return null;
        }

        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    NetworkListener emailInvoiceListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(rootLayout, getResources().getString(R.string.invoice_sent_successfully));
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


    NetworkListener transDetailListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                responseModel = (PaymentHistoryResponseModel) responseBody.getResutData();
                setTransData();
            }
        }

        @Override
        public void onError(String msg) {
            System.out.println(msg);
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

    NetworkListener recievedListener = new NetworkListener() {
            @Override
            public void onSuccess(ResultModel<?> responseBody) {
                if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                    showMsg(rootLayout, getResources().getString(R.string.trans_recieved));
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


    NetworkListener getTotalListener = new NetworkListener() {
            @Override
            public void onSuccess(ResultModel<?> responseBody) {
                if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                    TotalResponse totalResponse = (TotalResponse) responseBody.getResutData();

                    convenienceFee = totalResponse.getConvenienceFee();
                    GST = totalResponse.getGst();
                    amount = totalResponse.getAmount();
                    totalAmount = totalResponse.getTotalAmount();
                    fuelPrice = totalResponse.getFuelPrice();
                    quantity = totalResponse.getQuantity();

                }
            }

            @Override
            public void onError(String msg) {

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
