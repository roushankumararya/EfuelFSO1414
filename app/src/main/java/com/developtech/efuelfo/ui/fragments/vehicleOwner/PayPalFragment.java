package com.developtech.efuelfo.ui.fragments.vehicleOwner;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.requestModel.FindFuelStationManualRequestModel;
import com.developtech.efuelfo.model.requestModel.LocationRequestModel;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelTypeResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.QRScanActivity;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.ImageSpinnerAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayPalFragment extends BaseFragment implements VehicleOwnerItemClick , AdapterView.OnItemSelectedListener{

    View view;

    @BindView(R.id.btnPay)
    Button btnPay;
    @BindView(R.id.etVehicleNumber)
    EditText etVehicleNumber;
    @BindView(R.id.etMobileNumber)
    EditText etMobileNumber;
    @BindView(R.id.spinnerStationId)
    Spinner spinnerStationId;
    @BindView(R.id.spinnerFuelType)
    Spinner spinnerFuelType;
    @BindView(R.id.etQuantity)
    EditText etQuantity;
    @BindView(R.id.tvUnits)
    TextView tvUnits;
    @BindView(R.id.etTotalAmount)
    EditText etTotalAmount;
    @BindView(R.id.tvAmountCurrency)
    TextView tvAmountCurrency;
    @BindView(R.id.etMileage)
    EditText etMileage;
    @BindView(R.id.tvAmount)
    TextView tvAmount;

    @BindView(R.id.spinnerCode)
    Spinner spinnerCode;

    @BindView(R.id.spinnerImg)
    Spinner spinnerImg;

    @BindView(R.id.lytStationSpinner)
    LinearLayout lytStatoinSpinner;

    @BindView(R.id.etStationId)
    CustomEditText etStationId;

    private HomeActivity homeActivity;

    private int scanRequestCode = 100;

    ImageSpinnerAdapter imgSpinnerAdapter;

    android.widget.SpinnerAdapter spinnerAdapter;
    ArrayAdapter<String> spFuelTypeAdapter;

    int stationIdImages[] = {R.drawable.white_list, R.drawable.white_marker, R.drawable.white_qrcode, R.drawable.white_edit};
    private String[] stationIdList = {"List", "Map", "QR Code", "Manual"};

    List<AllVehicleResponseModel> vehicleResponseModels;
    List<FuelTypeResponseModel> fuelTypeResponseModels;
    AddRefuelRequestModel requestModel;
    LocationRequestModel locationRequestModel;

    boolean isEdit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity = (HomeActivity) context;
    }

    NetworkListener getAllVehicleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                vehicleResponseModels = (List<AllVehicleResponseModel>) responseBody.getResutData();
            } else {
                showMsg(view, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(view, msg);
        }

        @Override
        public void onComplete() {
            if(getActivity() == null)
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

    NetworkListener getFuelTypeListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelTypeResponseModels = (List<FuelTypeResponseModel>) responseBody.getResutData();

                spFuelTypeList.clear();
                for (FuelTypeResponseModel model : fuelTypeResponseModels) {
                    spFuelTypeList.add(model.getFuelType());
                }

                spFuelTypeAdapter.notifyDataSetChanged();

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

        }

        @Override
        public void onStart() {

        }
    };


    NetworkListener addRefuelReq = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(getResources().getString(R.string.pay4pal_request_added));
                etVehicleNumber.setText("");
                etQuantity.setText("");
                etTotalAmount.setText("");
                etMileage.setText("");
                tvAmount.setText("");
                etMobileNumber.setText("");
            }
        }

        @Override
        public void onError(String msg) {
            if(getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                }
            });
        }

        @Override
        public void onComplete() {
            if(getActivity() == null)
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



    NetworkListener findManualStationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelStationResponseModels.clear();
                FuelStationResponseModel model = (FuelStationResponseModel) responseBody.getResutData();
                fuelStationResponseModels.add(model);
                setData();
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
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


    private String mobile, mobileCode, fuelStation, fuelType, quantity, amount, mialage, customerVehicle;

    public PayPalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_paypal, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callApi();
        imgSpinnerAdapter = new ImageSpinnerAdapter(getActivity(), stationIdImages, stationIdList, this);
        spinnerImg.setAdapter(imgSpinnerAdapter);

        ArrayList<String> arrCode = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_code)));
        spinnerAdapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, arrCode);
        spinnerCode.setAdapter(spinnerAdapter);
        spStationIdAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spStationIdList);
        spFuelTypeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spFuelTypeList);
        spinnerStationId.setAdapter(spStationIdAdapter);
        spinnerFuelType.setAdapter(spFuelTypeAdapter);

        spinnerStationId.setOnItemSelectedListener(this);
        spinnerFuelType.setOnItemSelectedListener(this);
        spinnerCode.setOnItemSelectedListener(this);
        spinnerImg.setOnItemSelectedListener(this);

        TextView tvSpinner = (TextView) spinnerCode.getSelectedView();

        if(tvSpinner!=null)
        {
            tvSpinner.setTextColor(Color.BLACK);
        }

        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isEdit || spFuelTypeList.size()==0)
                    return;
                if (!etQuantity.getText().toString().trim().isEmpty()) {
                    isEdit = true;
                    float quantity = Float.parseFloat(etQuantity.getText().toString());
                    float ammount = quantity * price;
                    etTotalAmount.setText(ammount + "");
                    tvAmount.setText(ammount + "");
                } else {
                    isEdit = true;
                    etTotalAmount.setText("");
                    tvAmount.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isEdit = false;
            }
        });


        etTotalAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isEdit || spFuelTypeList.size()==0 || price==0.0)
                    return;
                if (!etTotalAmount.getText().toString().trim().isEmpty()) {
                    isEdit = true;
                    float amount = Float.parseFloat(etTotalAmount.getText().toString());
                    float quantity = amount / price;
                    etQuantity.setText(quantity + "");
                    tvAmount.setText(amount + "");
                } else {
                    isEdit = true;
                    etQuantity.setText("");
                    tvAmount.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isEdit = false;
            }
        });


        etStationId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==10)
                {
                    FindFuelStationManualRequestModel requestModel = new FindFuelStationManualRequestModel(charSequence.toString());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().findManualStation(requestModel), findManualStationListener);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    void callApi() {
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getVehicleList(), getAllVehicleListener);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelType(), getFuelTypeListener);
        locationRequestModel = new LocationRequestModel();
        locationRequestModel.setLatitude(appComponent.getSpUtils().getLat());
        locationRequestModel.setLongitude(appComponent.getSpUtils().getLng());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getNearByFuelStation(locationRequestModel), fuelStationListener);
    }


    @OnClick(R.id.btnPay)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPay: {
                checkValidation();
                break;
            }
        }
    }

    public void checkValidation() {
        hideKB();

        if(spFuelTypeList.size()==0)
        {
            showMsg(getResources().getString(R.string.fuel_type_not_available));
            spinnerFuelType.requestFocus();
            return;
        }

        customerVehicle = etVehicleNumber.getText().toString().trim();
        mobile = etMobileNumber.getText().toString().trim();
        if(spinnerFuelType.getSelectedItem()!=null)
        {
            fuelType = spinnerFuelType.getSelectedItem().toString();
        }
        quantity = etQuantity.getText().toString().trim();
        amount = etTotalAmount.getText().toString().trim();
        mialage = etMileage.getText().toString().trim();

        if (customerVehicle.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_vehicle_number));
            etVehicleNumber.requestFocus();
            return;
        }
        if (mobile.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_mobile_number));
            etMobileNumber.requestFocus();
            return;
        }

        if (quantity.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_fuel_quatity));
            etQuantity.requestFocus();
            return;
        }
        if (amount.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_total_amount));
            etTotalAmount.requestFocus();
            return;
        }
        if (mialage.isEmpty()) {
            showMsg(view, getString(R.string.enter_mileage));
            etMileage.requestFocus();
            return;
        }

        if (spinnerImg.getVisibility()==View.VISIBLE) {
            fuelStation = fuelStationResponseModels.get(spinnerStationId.getSelectedItemPosition()-1).getId();
        }

        requestModel = new AddRefuelRequestModel();
        requestModel.setAmount(amount);
      //  requestModel.setCreatedById(appComponent.getSpUtils().getUserId());
        requestModel.setFuelStation(fuelStation);
        requestModel.setFuelType(fuelType);
        requestModel.setMialage(mialage);
        requestModel.setCustomVehicle(customerVehicle);
        requestModel.setRequestType("PAY4PAL");
        requestModel.setQuantity(quantity);
        requestModel.setMobileNumber(spinnerCode.getSelectedItem().toString()+mobile);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().addRefuelRequest(requestModel), addRefuelReq);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onCLick(int position) {
        switch (position) {
            case 0: {
                break;
            }
            case 1: {
                homeActivity.pushFragment(new NearByFuelStationFragment());
                break;
            }
            case 2: {
                Intent intent = new Intent(getContext(), QRScanActivity.class);
                startActivityForResult(intent, scanRequestCode);
                break;
            }
            case 3: {
                break;
            }
        }

    }

    float price;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        switch (spinner.getId()) {
            case R.id.spinnerStationId: {
                spFuelTypeList.clear();
                fuelDetailList.clear();
                spFuelTypeAdapter.notifyDataSetChanged();
                if (fuelStationResponseModels.get(i).getFuelTypeResponseList().size() < 1) {
                    showMsg(getResources().getString(R.string.fuel_type_not_available));
                    price = 0;
                    return;
                }

                for (FuelTypeResponseModel model : fuelStationResponseModels.get(spinnerStationId.getSelectedItemPosition()).getFuelTypeResponseList()) {
                    fuelDetailList.addAll(model.getFuelDetail());
                }

                for(FuelDetailModel model : fuelDetailList)
                {
                    spFuelTypeList.add(model.getFuelType());
                }

                spFuelTypeAdapter.notifyDataSetChanged();
                break;
            }
            case R.id.spinnerFuelType: {
                if (fuelDetailList != null && fuelDetailList.size() > 0 && spinnerStationId.getSelectedItemPosition()>-1 && spinnerFuelType.getSelectedItemPosition()>-1) {

                    price = Integer.parseInt(fuelDetailList.get(spinnerFuelType.getSelectedItemPosition()).getPrice());
                }
                break;
            }
            case R.id.spinnerCode : {
                TextView textView = (TextView) view;
                textView.setTextColor(Color.BLACK);
                break;
            }
            case R.id.spinnerImg: {
                switch (spinnerImg.getSelectedItemPosition()) {
                    case 0: {
                        etStationId.setVisibility(View.GONE);
                        lytStatoinSpinner.setVisibility(View.VISIBLE);

                        locationRequestModel = new LocationRequestModel();
                        locationRequestModel.setLatitude(appComponent.getSpUtils().getLat());
                        locationRequestModel.setLongitude(appComponent.getSpUtils().getLng());
                        appComponent.getServiceCaller().callService(appComponent.getAllApis().getNearByFuelStation(locationRequestModel), fuelStationListener);

                        fuelTypeList.clear();
                        spFuelTypeList.clear();
                        spFuelTypeAdapter.notifyDataSetChanged();

                        break;
                    }
                    case 1: {
                        homeActivity.pushFragment(new NearByFuelStationFragment());
                        break;
                    }
                    case 2: {
                        Intent intent = new Intent(getContext(), QRScanActivity.class);
                        startActivityForResult(intent, scanRequestCode);
                        break;
                    }
                    case 3: {
                        lytStatoinSpinner.setVisibility(View.GONE);
                        etStationId.setVisibility(View.VISIBLE);

                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    ArrayAdapter<String> spStationIdAdapter;
    List<FuelStationResponseModel> fuelStationResponseModels;
    List<String> fuelTypeList = new ArrayList<>();
    ArrayList<String> spStationIdList = new ArrayList<>();

    NetworkListener fuelStationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelStationResponseModels = (List<FuelStationResponseModel>) responseBody.getResutData();
                spStationIdList.clear();
                for (FuelStationResponseModel model : fuelStationResponseModels) {
                    spStationIdList.add(model.getName() + " -- " + model.getDistance() + "KM");
                }
                spStationIdAdapter.notifyDataSetChanged();
                setData();
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if(getActivity() == null)
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

    ArrayList<String> spFuelTypeList = new ArrayList<>();
    List<FuelDetailModel> fuelDetailList = new ArrayList<>();

    public void setData() {

        if (fuelStationResponseModels.get(0).getFuelTypeResponseList().size() < 1) {
            showMsg(getResources().getString(R.string.fuel_type_not_available));
            return;
        }
        spFuelTypeList.clear();

        for (FuelTypeResponseModel model : fuelStationResponseModels.get(0).getFuelTypeResponseList()) {
            fuelDetailList.addAll(model.getFuelDetail());
        }

        for(FuelDetailModel model : fuelDetailList)
        {
            spFuelTypeList.add(model.getFuelType());
        }

        spFuelTypeAdapter.notifyDataSetChanged();
    }
}
