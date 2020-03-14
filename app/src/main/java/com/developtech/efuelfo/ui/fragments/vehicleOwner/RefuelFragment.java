package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.FindFuelStationManualRequestModel;
import com.developtech.efuelfo.model.requestModel.LocationRequestModel;
import com.developtech.efuelfo.model.responseModel.AddFuelRequestResponseModel;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.model.responseModel.FuelStationByQRResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelTypeResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.QRScanActivity;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.ImageSpinnerAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefuelFragment extends BaseFragment implements VehicleOwnerItemClick,
        AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinnerVehicle)
    Spinner spinnerVehicle;
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
    @BindView(R.id.btnPay)
    Button btnPay;

    @BindView(R.id.spinnerImg)
    Spinner spinnerImg;

    @BindView(R.id.lytStationSpinner)
    LinearLayout lytStatoinSpinner;

    @BindView(R.id.switchSelfDriven)
    Switch switchSelfDriven;

    FuelStationResponseModel fuelStationResponseModel;

    @BindView(R.id.etStationId)
    CustomEditText etStationId;

    List<AllVehicleResponseModel> vehicleResponseModels;
    List<FuelTypeResponseModel> fuelTypeResponseModels;
    List<FuelStationResponseModel> fuelStationResponseModels;
    AddRefuelRequestModel requestModel;
    LocationRequestModel locationRequestModel;
    ImageSpinnerAdapter imgSpinnerAdapter;
    int stationIdImages[] = {R.drawable.white_list, R.drawable.white_marker, R.drawable.white_qrcode, R.drawable.white_edit};
    ArrayAdapter<String> spVehicleAdapter, spFuelTypeAdapter, spStationIdAdapter;
    ArrayList<String> spVehicleList = new ArrayList<>();
    ArrayList<String> spFuelTypeList = new ArrayList<>();
    ArrayList<String> spStationIdList = new ArrayList<>();

    NetworkListener getAllVehicleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                vehicleResponseModels = (List<AllVehicleResponseModel>) responseBody.getResutData();

                spVehicleList.clear();
                spVehicleList.add("Select Vehicle");
                for (AllVehicleResponseModel model : vehicleResponseModels) {
                    spVehicleList.add(model.getVehicleNumber());
                }

                spVehicleAdapter.notifyDataSetChanged();

            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
        }

        @Override
        public void onComplete() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
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


    List<String> fuelTypeList = new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    NetworkListener addRefuelReq = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            Log.e("kkk","addsucc"+ responseBody.getResutData());
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                AddFuelRequestResponseModel model = (AddFuelRequestResponseModel)
                        responseBody.getResutData();
                showMsg(getResources().getString(R.string.request_sent_successfully));
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
        }

        @Override
        public void onComplete() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
        }
        @Override
        public void onStart() {
            showProgress();
        }
    };
    NetworkListener fuelStationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelStationResponseModels = (List<FuelStationResponseModel>) responseBody.getResutData();
                spStationIdList.clear();
                spStationIdList.add("Select Fuel Station");
                for (FuelStationResponseModel model : fuelStationResponseModels) {
                    spStationIdList.add(model.getName() + " -- " + model.getDistance() + "KM");
                }
                spStationIdAdapter.notifyDataSetChanged();

                if (fuelStationResponseModel!=null)
                {
                    for (int i = 0; i < fuelStationResponseModels.size(); i++) {
                        if (fuelStationResponseModel.getId().equals(fuelStationResponseModels.get(i).getId()))
                        {
                            spinnerStationId.setSelection(i+1);
                            break;
                        }
                    }
                }
                else
                {
                    setData();
                }

            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
        }

        @Override
        public void onComplete() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
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


    boolean isEdit;
    float price;
    private String[] stationIdList = {"List", "Map", "QR Code", "Manual"};
    private String vehicle, fuelStation, fuelType, quantity, amount, mialage, createdById;
    private View view;
    NetworkListener getFuelStation = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                FuelStationByQRResponseModel responseModel = (FuelStationByQRResponseModel) responseBody.getResutData();

                spFuelTypeList.clear();
                for (FuelTypeResponseModel model : responseModel.getSchedules()) {
                    spFuelTypeList.add(model.getFuelType());
                }

                spFuelTypeAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
        }

        @Override
        public void onComplete() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };
    private int scanRequestCode = 100;
    private HomeActivity homeActivity;

    public RefuelFragment() {

    }

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity = (HomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_refuel, container, false);
        ButterKnife.bind(this, view);
        init(view);
        callApi();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgSpinnerAdapter = new ImageSpinnerAdapter(getActivity(), stationIdImages, stationIdList, this);
        spinnerImg.setAdapter(imgSpinnerAdapter);
        spVehicleAdapter = new ArrayAdapter<String>(appComponent.getContext(), android.R.layout.simple_spinner_dropdown_item, spVehicleList);
        spFuelTypeAdapter = new ArrayAdapter<String>(appComponent.getContext(), android.R.layout.simple_spinner_dropdown_item, spFuelTypeList);
        spStationIdAdapter = new ArrayAdapter<String>(appComponent.getContext(), android.R.layout.simple_spinner_dropdown_item, spStationIdList);
        spinnerVehicle.setAdapter(spVehicleAdapter);
        spinnerFuelType.setAdapter(spFuelTypeAdapter);
        spinnerStationId.setAdapter(spStationIdAdapter);

        spinnerVehicle.setOnItemSelectedListener(this);
        spinnerStationId.setOnItemSelectedListener(this);
        spinnerFuelType.setOnItemSelectedListener(this);
        spinnerImg.setOnItemSelectedListener(this);

        if(getArguments()!=null)
        {
            fuelStationResponseModel = (FuelStationResponseModel) getArguments().getSerializable("model");
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
                if (isEdit || spFuelTypeList.size()==0)
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
                    FindFuelStationManualRequestModel requestModel = new FindFuelStationManualRequestModel
                            (charSequence.toString());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis()
                            .findManualStation(requestModel), findManualStationListener);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    void callApi() {
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getVehicleList(),
                getAllVehicleListener);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelType(), getFuelTypeListener);
        locationRequestModel = new LocationRequestModel();
        locationRequestModel.setLatitude(appComponent.getSpUtils().getLat());
        locationRequestModel.setLongitude(appComponent.getSpUtils().getLng());
        appComponent.getServiceCaller().callService(appComponent.getAllApis()
                .getNearByFuelStation(locationRequestModel), fuelStationListener);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @OnClick({R.id.btnPay})
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

        quantity = etQuantity.getText().toString().trim();
        amount = etTotalAmount.getText().toString().trim();
        mialage = etMileage.getText().toString().trim();

        if(spVehicleList.size()<2)
        {
            showMsg(getResources().getString(R.string.vehicle_not_available));
            spinnerVehicle.requestFocus();
            return;
        }

        if(spFuelTypeList.size()==0)
        {
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
            etTotalAmount.requestFocus();
            return;
        }
        if (mialage.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_mileage));
            etMileage.requestFocus();
            return;
        }

        if(spinnerVehicle.getSelectedItem()!=null)
        {
            vehicle = vehicleResponseModels.get(spinnerVehicle.getSelectedItemPosition()-1).getId();
        }
        if (spinnerImg.getVisibility()==View.VISIBLE) {
            fuelStation = fuelStationResponseModels.get(spinnerStationId.getSelectedItemPosition()-1).getId();
        }
        else
        {
            fuelStation = fuelStationResponseModels.get(0).getId();
        }
        if(spinnerFuelType.getSelectedItem()!=null)
        {
            fuelType = fuelTypeResponseModels.get(spinnerFuelType.getSelectedItemPosition()).getId();
        }

        requestModel = new AddRefuelRequestModel();
        requestModel.setAmount(amount);
     //   requestModel.setCreatedById(appComponent.getSpUtils().getUserId());
        requestModel.setFuelStation(fuelStation);
        requestModel.setFuelType(fuelType);
        requestModel.setMialage(mialage);
        requestModel.setVehicle(vehicle);
        requestModel.setQuantity(quantity);
        requestModel.setSelfDriven(switchSelfDriven.isChecked());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().addRefuelRequest(requestModel), addRefuelReq);
    }

    @Override
    public void onCLick(int position) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == scanRequestCode && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String id = bundle.getString("string");
                    if (id != null) {
                        getFuelStationDetail(id);
                    }
                }
            }
        }
    }

    public void getFuelStationDetail(String id) {

        DeleteDriverRequestModel requestModel = new DeleteDriverRequestModel(id);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().findStationByQr(requestModel), getFuelStation);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        switch (spinner.getId()) {
            case R.id.spinnerStationId: {
                TextView textView = (TextView) view;
                textView.setTextColor(Color.BLACK);

                spFuelTypeList.clear();
                fuelDetailList.clear();
                spFuelTypeAdapter.notifyDataSetChanged();

                if (spinnerStationId.getSelectedItemPosition()==0)
                {
                    return;
                }

                if (fuelStationResponseModels.get(i-1).getFuelTypeResponseList().size() < 1) {
                    showMsg(getResources().getString(R.string.fuel_type_not_available));
                    price = 0;
                    return;
                }

                for (FuelTypeResponseModel model : fuelStationResponseModels.get(spinnerStationId.getSelectedItemPosition()-1)
                        .getFuelTypeResponseList()) {
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
                TextView textView = (TextView) view;
                textView.setTextColor(Color.BLACK);

                if (fuelDetailList != null && fuelDetailList.size() > 0 && spinnerStationId.getSelectedItemPosition()>-1 && spinnerFuelType.getSelectedItemPosition()>-1) {

                    price = Integer.parseInt(fuelDetailList.get(spinnerFuelType.getSelectedItemPosition()).getPrice());
                }
                break;
            }
            case R.id.spinnerVehicle: {
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


}
