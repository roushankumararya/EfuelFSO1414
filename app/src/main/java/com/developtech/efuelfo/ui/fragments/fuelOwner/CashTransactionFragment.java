package com.developtech.efuelfo.ui.fragments.fuelOwner;


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
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter4;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CashTransactionFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.etVehicleNumber)
    EditText etVehicleNumber;

    @BindView(R.id.etMobileNumber)
    CustomEditText etMobileNumber;

    @BindView(R.id.spinnerCode)
    Spinner spinnerCode;

    @BindView(R.id.spinnerFuelType)
    Spinner spinnerFuelType;

    @BindView(R.id.etQuantity)
    EditText etQuantity;

    @BindView(R.id.etAmount)
    EditText etAmount;

    @BindView(R.id.tvTotalAmount)
    TextView tvTotalAmount;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    @BindView(R.id.btnSave)
    Button btnSave;

    View view;

    SpinnerAdapter4 spFuelTypeAdapter, spinnerAdapter;
    List<String> spFuelTypeList = new ArrayList<>();

    boolean isEdit;
    float price;
    boolean isQuantityEdit;

    public CashTransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_cash_transactions, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        final String calendarDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        String calendarTime = date.getHours() + ":" + date.getMinutes();
        String strDate = calendarDate + " " + calendarTime;
        requestModel.setDateTime(getFormatedDate(strDate));
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());

        appComponent.getServiceCaller().callService(appComponent.getAllApis().searchSchedule(requestModel), scheduleListener);


        spFuelTypeAdapter = new SpinnerAdapter4(getContext(), R.layout.item_simple_spinner, spFuelTypeList);
        spinnerFuelType.setAdapter(spFuelTypeAdapter);
        spinnerFuelType.setOnItemSelectedListener(this);

        ArrayList<String> arrCode = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_code)));
        spinnerAdapter = new SpinnerAdapter4(getContext(), R.layout.item_simple_spinner, arrCode);
        spinnerCode.setAdapter(spinnerAdapter);
        spinnerCode.setOnItemSelectedListener(this);

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
                    tvTotalAmount.setText(ammount + "");
                    etAmount.setText(ammount + "");
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
                    etQuantity.setText(quantity + "");
                    tvTotalAmount.setText(amount + "");
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

        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    btnSave.setText(getResources().getString(R.string.saveAndSend));
                } else {
                    btnSave.setText(getResources().getString(R.string.save));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @OnClick(R.id.btnSave)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave: {
                validate();
            }
        }
    }

    private void validate() {

        if (spFuelTypeList.size() < 1) {
            showMsg(rootLayout, getResources().getString(R.string.fuel_type_not_available));
            return;
        }

        if (etVehicleNumber.getText().toString().trim().isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_vehical_number));
            return;
        }

//        if (etMobileNumber.getText().toString().trim().isEmpty()) {
//            showMsg(rootLayout, getResources().getString(R.string.enter_mobile_number));
//            return;
//        }
//        if (etMobileNumber.getText().toString().trim().length() > 0) {
//            showMsg(rootLayout, getResources().getString(R.string.enter_mobile_number));
//            return;
//        }
        if (etMobileNumber.getText().toString().trim().length() > 0 && etMobileNumber.getText().length() != 10) {
            showMsg(rootLayout, getResources().getString(R.string.mobile_number_length_check));
            return;
        }

        if (etQuantity.getText().toString().trim().isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_fuel_qty));
            return;
        }

        if (etAmount.getText().toString().trim().isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.please_enter_amount));
            return;
        }

        AddRefuelRequestModel cashRequestModel = new AddRefuelRequestModel();
        cashRequestModel.setVehicleNumber(etVehicleNumber.getText().toString().toUpperCase());
        cashRequestModel.setMobileNumber(spinnerCode.getSelectedItem().toString().trim() + etMobileNumber.getText().toString());
        cashRequestModel.setFuelType(spinnerFuelType.getSelectedItem().toString());
        cashRequestModel.setQuantity(etQuantity.getText().toString());
        cashRequestModel.setAmount(etAmount.getText().toString());
        cashRequestModel.setFuelStation(appComponent.getSpUtils().getFuelStationModel().getId());

        appComponent.getServiceCaller().callService(appComponent.getAllApis().createCashTransaction(cashRequestModel), createCashListener);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) view;
        textView.setTextColor(Color.BLACK);
        if (fuelDetailList.size() > 0) {
            price = Float.parseFloat(fuelDetailList.get(i).getPrice());


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

                    float amount = Float.parseFloat(str);
                    float quantity = amount / price;
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


    void resetFields() {
        etVehicleNumber.setText("");
        etMobileNumber.setText("");
        etQuantity.setText("");
        etAmount.setText("");
        etQuantity.setText("00.00");
    }


    List<FuelDetailModel> fuelDetailList = new ArrayList<>();

    NetworkListener scheduleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<SchedulesResponseModel> schedulesList = (List<SchedulesResponseModel>) responseBody.getResutData();
                fuelDetailList.clear();

                for (SchedulesResponseModel model : schedulesList) {
                    fuelDetailList.addAll(model.getFuelDetail());
                }

                for (FuelDetailModel model : fuelDetailList) {
                    spFuelTypeList.add(model.getFuelType());
                }

                spFuelTypeAdapter.notifyDataSetChanged();

                price = Float.parseFloat(fuelDetailList.get(0).getPrice());
            }

        }

        @Override
        public void onError(String msg) {

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


    NetworkListener createCashListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(rootLayout, getResources().getString(R.string.cash_transaction_created));
                resetFields();
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
}
