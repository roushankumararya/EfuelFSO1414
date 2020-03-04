package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.VehicleByNumberRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.model.responseModel.ParkTransactionResponseModel;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.model.responseModel.VehicleByNumberResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter4;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleInitiationFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.etVehicleNumber)
    CustomEditText etVehicleNumber;

    @BindView(R.id.spinnerFuelType)
    Spinner spinnerFuelType;

    @BindView(R.id.etQuantity)
    CustomEditText etQuantity;

    @BindView(R.id.etAmount)
    CustomEditText etAmount;

    @BindView(R.id.etMileage)
    CustomEditText etMileage;

    @BindView(R.id.tvTotalAmount)
    CustomTextView tvTotalAmount;

    @BindView(R.id.tvDriverNameMobile)
    CustomTextView tvDriverNameMobile;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    @BindView(R.id.btnSend)
    Button btnSend;

    @BindView(R.id.btnPark)
    Button btnPark;

    View view;
    boolean isEdit;
    float price;
    boolean isQuantityEdit, isCreditAgreement = true;

    SpinnerAdapter4 spFuelTypeAdapter;

    VehicleByNumberResponseModel responseModel;

    public SaleInitiationFragment() {
        // Required empty public constructor
    }

    ParkTransactionResponseModel parkTransModel;

    DecimalFormat formatter = new DecimalFormat("#.##");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_sale_initiation, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spFuelTypeAdapter = new SpinnerAdapter4(getContext(), R.layout.item_simple_spinner, spFuelTypeList);
        spinnerFuelType.setAdapter(spFuelTypeAdapter);
        spinnerFuelType.setOnItemSelectedListener(this);


        if (getArguments() != null && getArguments().getSerializable("model") != null) {
            parkTransModel = (ParkTransactionResponseModel) getArguments().getSerializable("model");

            if (parkTransModel != null) {
                etVehicleNumber.setText(parkTransModel.getVehicle().getVehicleNumber());
                String mobileWithStar = "";
                if (parkTransModel.getVehicle().getDriver().getMobileNumber().length() >= 10) {
                    mobileWithStar = "xxxxxx" + parkTransModel.getVehicle().getDriver().getMobileNumber().substring(6);
                }
                tvDriverNameMobile.setText(parkTransModel.getVehicle().getDriver().getFirstName() + " " + parkTransModel.getVehicle().getDriver().getLastName() + " - " + mobileWithStar);
                etQuantity.setText(parkTransModel.getQuantity());
                etAmount.setText(parkTransModel.getAmount());
                tvTotalAmount.setText(parkTransModel.getAmount());
                if (parkTransModel.getMialage() != null) {
                    etMileage.setText(parkTransModel.getMialage());
                }

                btnPark.setText(getResources().getString(R.string.delete));


                VehicleByNumberRequestModel requestModel = new VehicleByNumberRequestModel();
                requestModel.setVehicleNumber(etVehicleNumber.getText().toString().trim().toUpperCase());
                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().vehicleByNumber(requestModel), vehicleByNumberListener);
            }
        }

        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isEdit || spFuelTypeList.size() == 0)
                    return;
                if (!etQuantity.getText().toString().trim().isEmpty()) {
                    isEdit = true;
                    isQuantityEdit = true;

                    String str = etQuantity.getText().toString().trim();
                    if (str.length() == 1 && str.equals(".")) {
                        str = 0 + str + 0;
                    } else if (str.length() > 1 && str.charAt(0) == '.') {
                        str = 0 + str;
                    }

                    float quantity = Float.parseFloat(str);
                    float ammount = quantity * price;
                    tvTotalAmount.setText(formatter.format(ammount));
                    etAmount.setText(formatter.format(ammount));
                } else {
                    isEdit = true;
                    etAmount.setText("");
                    tvTotalAmount.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isEdit = false;
            }
        });


        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isEdit || spFuelTypeList.size() == 0)
                    return;
                if (!etAmount.getText().toString().trim().isEmpty()) {
                    isEdit = true;
                    isQuantityEdit = false;

                    String str = etAmount.getText().toString().trim();
                    if (str.length() == 1 && str.equals(".")) {
                        str = 0 + str + 0;
                    } else if (str.length() > 1 && str.charAt(0) == '.') {
                        str = 0 + str;
                    }

                    float amount = Float.parseFloat(str);
                    float quantity = amount / price;
                    etQuantity.setText(formatter.format(quantity));
                    tvTotalAmount.setText(formatter.format(amount));
                } else {
                    isEdit = true;
                    etQuantity.setText("");
                    tvTotalAmount.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isEdit = false;
            }
        });


        etVehicleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (!s.equals(s.toUpperCase())) {
                    s = s.toUpperCase();
                    etVehicleNumber.setText(s);
                    etVehicleNumber.setSelection(etVehicleNumber.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @OnClick({R.id.btnSend, R.id.btnPark, R.id.ivSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend: {
                send(true);
                break;
            }
            case R.id.btnPark: {
                if (parkTransModel != null) {
                    confirmDialog();
                } else {
                    send(false);
                }
                break;
            }
            case R.id.ivSearch: {
                searchVehicle();
                break;
            }
        }
    }

    void searchVehicle() {
        hideKB();

        tvDriverNameMobile.setText("");
        etQuantity.setText("");
        etAmount.setText("");

        if (etVehicleNumber.getText().toString().trim().isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_vehical_number));
            return;
        }

        VehicleByNumberRequestModel requestModel = new VehicleByNumberRequestModel();
        requestModel.setVehicleNumber(etVehicleNumber.getText().toString().trim().toUpperCase());
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().vehicleByNumber(requestModel), vehicleByNumberListener);
    }

    boolean isSend;

    void send(boolean isSend) {

        hideKB();

        String quantity = etQuantity.getText().toString().trim();
        String amount = etAmount.getText().toString().trim();
        String mialage = etMileage.getText().toString().trim();
        String fuelType = "";

        if (etVehicleNumber.getText().toString().isEmpty()) {
            showMsg(getResources().getString(R.string.enter_vehical_number));
            etVehicleNumber.requestFocus();
            return;
        }

        if (spFuelTypeList.size() == 0) {
            showMsg(getResources().getString(R.string.fuel_type_not_available));
            spinnerFuelType.requestFocus();
            return;
        }

        if (quantity.isEmpty()) {
            showMsg(rootLayout, getString(R.string.please_enter_fuel_quatity));
            etQuantity.requestFocus();
            return;
        }
        if (amount.isEmpty()) {
            showMsg(rootLayout, getString(R.string.please_enter_total_amount));
            etAmount.requestFocus();
            return;
        }

        if (!vehicleNumber.equals(etVehicleNumber.getText().toString())) {
            showMsg(rootLayout, getResources().getString(R.string.vehicle_not_available));
            return;
        }

        //if()
        if (mialage.isEmpty()) {
            mialage = "";
        }

        String vehicle = "";
        float capacity = 999999;
        float qty = 0;

        if (parkTransModel != null) {
            vehicle = parkTransModel.getVehicle().getId();
            try {
                capacity = Float.parseFloat(parkTransModel.getVehicle().getFuelCapacity());
                qty = Float.parseFloat(quantity);
            } catch (Exception e) {
            }
        } else {
            vehicle = responseModel.getId();
            try {
                capacity = Float.parseFloat(responseModel.getFuelCapacity());
                qty = Float.parseFloat(quantity);
            } catch (Exception e) {
            }
        }

        if (qty > capacity) {
            showMsg(rootLayout, getResources().getString(R.string.vehicle_capacity));
            return;
        }

        String fuelStation = appComponent.getSpUtils().getFuelStationModel().getId();

        if (parkTransModel == null && spinnerFuelType.getSelectedItem() != null) {
            fuelType = sortedFuelDetailList.get(spinnerFuelType.getSelectedItemPosition()).getFuelType();
        } else if (parkTransModel != null) {
            fuelType = parkTransModel.getFuelType();
        }


        AddRefuelRequestModel requestModel = new AddRefuelRequestModel();
        requestModel.setAmount(amount);
        requestModel.setFuelStation(fuelStation);

        if (responseModel!=null && responseModel.getDriver() != null && responseModel.getDriver().getFirstName() != null && responseModel.getDriver().getFirstName() != "") {
            requestModel.setByOwner(false);
            requestModel.setSelfDriven(false);
        }else{
            requestModel.setByOwner(true);
            requestModel.setSelfDriven(true);
        }
        if (tvDriverNameMobile.getText().length() < 10) {

        }

        requestModel.setFuelType(fuelType);
        requestModel.setMialage(mialage);
        requestModel.setVehicle(vehicle);
        requestModel.setQuantity(quantity);
        requestModel.setCreditAgreement(isCreditAgreement);
//-- dd -- cc -- bb


        if (parkTransModel != null) {
            requestModel.setId(parkTransModel.getId());
            requestModel.setParked(true);
            requestModel.setMobileNumber(parkTransModel.getVehicle().getDriver().getMobileNumber());
        } else {
            requestModel.setMobileNumber(responseModel.getDriver().getMobileNumber());
        }

        this.isSend = isSend;

        if (isSend) {

            appComponent.getServiceCaller().callService(appComponent.getAllApis().addRefuelRequest(requestModel), sendListener);
        } else {
            appComponent.getServiceCaller().callService(appComponent.getAllApis().requestParkTransaction(requestModel), sendListener);
        }

    }


    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    List<SchedulesResponseModel> schedulesList = new ArrayList<>();
    List<FuelDetailModel> sortedFuelDetailList = new ArrayList<>();
    List<String> spFuelTypeList = new ArrayList<>();

    String vehicleNumber;

    NetworkListener vehicleByNumberListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                responseModel = (VehicleByNumberResponseModel) responseBody.getResutData();
                schedulesList.clear();
                schedulesList.addAll(responseModel.getSchedules());
                List<FuelDetailModel> fuelDetailsList = new ArrayList<>();
                for (SchedulesResponseModel scheduleModel : schedulesList) {
                    fuelDetailsList.addAll(scheduleModel.getFuelDetail());
                }

                spFuelTypeList.clear();
                sortedFuelDetailList.clear();
                for (FuelDetailModel model : fuelDetailsList) {
                    if (model.getFuelType() != null) {
                        if (model.getFuelType().contains(responseModel.getFuelType())) {
                            sortedFuelDetailList.add(model);
                            spFuelTypeList.add(model.getFuelType() + " - ₹" + model.getPrice());
                        }
                    }
                }

                spFuelTypeAdapter.notifyDataSetChanged();
                if (sortedFuelDetailList.size() > 0) {
                    price = Float.parseFloat(sortedFuelDetailList.get(0).getPrice());
                } else {
                    showMsg(getResources().getString(R.string.fuel_type_not_available));
                }

                if (responseModel.getDriver() != null && responseModel.getDriver().getFirstName() != null && responseModel.getDriver().getLastName() != null) {

                    //String mobileWithStar = "xxxxxx" + responseModel.getDriver().getMobileNumber().substring(6);

                    String mobileWithStar = "";
                    if (responseModel.getDriver().getMobileNumber().length() >= 10) {
                        mobileWithStar = "xxxxxx" + responseModel.getDriver().getMobileNumber().substring(6);
                    }
                    tvDriverNameMobile.setText(responseModel.getDriver().getFirstName() + " " + responseModel.getDriver().getLastName() + " -  " + mobileWithStar);
                }

                if (responseModel.getDriver() != null && responseModel.getDriver().getFirstName() != null && responseModel.getDriver().getFirstName() != "") {
//                    String mobileWithStar = "xxxxxx" + responseModel.getDriver().getMobileNumber().substring(6);
                    String mobileWithStar = "";
                    if (responseModel.getDriver().getMobileNumber().length() >= 10) {
                        mobileWithStar = "xxxxxx" + responseModel.getDriver().getMobileNumber().substring(6);
                    }
                    tvDriverNameMobile.setText(responseModel.getDriver().getFirstName() + " " + responseModel.getDriver().getLastName() + " -  " + mobileWithStar);
                    btnSend.setEnabled(true);
                    btnPark.setEnabled(true);
                } else {

                    if (responseModel.getVehicleOwner() != null && responseModel.getVehicleOwner().getFirstName() != "" && responseModel.getVehicleOwner().getFirstName() != null && responseModel.getVehicleOwner().getMobileNumber() != null) {
                        String mobileWithStar = "";
                        if (responseModel.getVehicleOwner().getMobileNumber().length() >= 10) {
                            mobileWithStar = "xxxxxx" + responseModel.getVehicleOwner().getMobileNumber().substring(6);
                        }
                        tvDriverNameMobile.setText(responseModel.getVehicleOwner().getFirstName() + " " + responseModel.getVehicleOwner().getLastName() + " -  " + mobileWithStar);
                        btnSend.setEnabled(true);
                        btnPark.setEnabled(true);
                    } else {
                        showMsg(getResources().getString(R.string.driver_not_assigned));
                        btnSend.setEnabled(false);
                        btnPark.setEnabled(false);
                    }

                }

                if (parkTransModel != null) {
                    for (int i = 0; i < spFuelTypeList.size(); i++) {
                        if (parkTransModel.getFuelType().equals(spFuelTypeList.get(i))) {
                            spinnerFuelType.setSelection(i);
                            break;
                        }
                    }
                }

                vehicleNumber = etVehicleNumber.getText().toString();
            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if (getActivity() == null)
                return;

            getActivity().runOnUiThread(new Runnable() {
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (sortedFuelDetailList.size() > 0) {
            price = Float.parseFloat(sortedFuelDetailList.get(i).getPrice());

            if (isQuantityEdit) {
                if (!etQuantity.getText().toString().trim().isEmpty()) {
                    isEdit = true;
                    String str = etQuantity.getText().toString().trim();
                    if (str.length() == 1 && str.equals(".")) {
                        str = 0 + str + 0;
                    } else if (str.length() > 1 && str.charAt(0) == '.') {
                        str = 0 + str;
                    }

                    float quantity = Float.parseFloat(str);
                    float ammount = quantity * price;
                    tvTotalAmount.setText(ammount + "");
                    etAmount.setText(ammount + "");

                    isEdit = false;
                }
            } else {
                if (!etAmount.getText().toString().trim().isEmpty()) {
                    isEdit = true;
                    String str = etAmount.getText().toString().trim();
                    if (str.length() == 1 && str.equals(".")) {
                        str = 0 + str + 0;
                    } else if (str.length() > 1 && str.charAt(0) == '.') {
                        str = 0 + str;
                    }

                    double amount = Double.parseDouble(str);

                    double quantity = amount / price;
                    etQuantity.setText(quantity + "");
                    tvTotalAmount.setText(amount + "");
                    isEdit = false;
                }
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    NetworkListener sendListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                if (isSend) {
                    showMsg(getResources().getString(R.string.request_sent_successfully));
                } else {
                    showMsg(getResources().getString(R.string.request_parked));
                }
                resetFields();
                if (getActivity() != null)
                    getActivity().onBackPressed();
            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if (getActivity() == null)
                return;

            getActivity().runOnUiThread(new Runnable() {
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

    NetworkListener requestDeleteListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(getResources().getString(R.string.request_deleted));
                HomeActivity homeActivity = (HomeActivity) getActivity();
                if (homeActivity != null) {
                    homeActivity.onBackPressed();
                }
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if (getActivity() == null)
                return;

            getActivity().runOnUiThread(new Runnable() {
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

    void resetFields() {
        etVehicleNumber.setText("");
        tvDriverNameMobile.setText("");
        spFuelTypeList.clear();
        spFuelTypeAdapter.notifyDataSetChanged();
        etQuantity.setText("");
        etAmount.setText("");
        etMileage.setText("");
        tvTotalAmount.setText("00.00");
    }


    void confirmDialog() {
        final Dialog dialog = new Dialog(getContext());
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
                dialog.dismiss();
                DeleteDriverRequestModel requestModel = new DeleteDriverRequestModel(parkTransModel.getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().deletePendingRefuel(requestModel), requestDeleteListener);
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
}
