package com.developtech.efuelfo.ui.dialogFragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.CallbackListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelCompanyRespnseModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter4;
import com.developtech.efuelfo.ui.adapters.stationOwner.FuelCompanySpinnerAdapter;
import com.developtech.efuelfo.util.SPUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dt on 12/26/17.
 */

public class AddFuelStationDialog extends DialogFragment implements AdapterView.OnItemSelectedListener, CallbackListener {
    @BindView(R.id.cbIsAvailable)
    CheckBox cbIsAvailable;
    @BindView(R.id.etStationName)
    EditText etStationName;
    @BindView(R.id.etDealerCode)
    EditText etDealerCode;
    @BindView(R.id.etStationId)
    EditText etStationId;
    @BindView(R.id.etStartTime)
    EditText etStartTime;
    @BindView(R.id.etEndTime)
    EditText etEndTime;
    @BindView(R.id.spinnerCode)
    Spinner spinnerCode;
    @BindView(R.id.etMobileNumber)
    EditText etMobileNumber;
    @BindView(R.id.spinnerFuelCompany)
    Spinner spinnerFuelCompany;
    @BindView(R.id.etAccountName)
    EditText etAccountName;
    @BindView(R.id.etAccountNumber)
    EditText etAccountNumber;
    @BindView(R.id.etIfsc)
    EditText etIfsc;
    @BindView(R.id.etLandline)
    EditText etLandline;
    @BindView(R.id.btnAddFuelStation)
    Button btnAddFuelStation;
    @BindView(R.id.rootLayout)
    LinearLayout rootlayout;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.btnSendQr)
    Button btnSendQr;
    @BindView(R.id.lytFuelStatinId)
    LinearLayout lytFuelStationId;

    @BindView(R.id.lnrQR)
    LinearLayout lnrQR;
    @BindView(R.id.imgQR)
    ImageView imgQR;

    public String name, dealerCode, startTime, endTime, mobileNumber, address, accountName, accountNumber, ifscCode, landlineNumber, latitude, longitude;
    private AddFuelStationRequestModel stationRequestModel;
    private NetworkListener listener, sendQrListener;
    private View view;
    private AlertDialog alertDialog;
    private AppComponent appComponent;

    int START_TIME = 1, END_TIME = 0;

    SpinnerAdapter4 spinnerAdapter;

    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    FuelCompanySpinnerAdapter fuelCompanyAdapter;
    List<FuelCompanyRespnseModel> fuelCompaniesList = new ArrayList<>();
    Activity activity;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.content_add_fuel_station, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        init();
        alertDialog = builder.create();
        return alertDialog;
    }

    GetFuelStationResponseModel fuelStationResponseModel;

    public void setData(NetworkListener listener, NetworkListener sendQrListener, AppComponent appComponent, GetFuelStationResponseModel fuelStationResponseModel, Activity activity) {
        this.listener = listener;
        this.appComponent = appComponent;
        this.fuelStationResponseModel = fuelStationResponseModel;
        this.sendQrListener = sendQrListener;
        this.activity = activity;
    }

    public final static int WHITE = 0xFFFFFFFF;
    public final static int BLACK = 0xFF000000;
    public final static int WIDTH = 300;
    public final static int HEIGHT = 300;

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

    void init() {
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelCompanies(), fuelCompanyListener);
        ArrayList<String> arrCode = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_code)));
        spinnerAdapter = new SpinnerAdapter4(getContext(), android.R.layout.simple_spinner_dropdown_item, arrCode);
        spinnerCode.setAdapter(spinnerAdapter);
        fuelCompanyAdapter = new FuelCompanySpinnerAdapter(fuelCompaniesList, appComponent);
        spinnerFuelCompany.setAdapter(fuelCompanyAdapter);
        spinnerCode.setOnItemSelectedListener(this);
        spinnerFuelCompany.setOnItemSelectedListener(this);

        if (fuelStationResponseModel != null) {
            etStationName.setText(fuelStationResponseModel.getName());
            etDealerCode.setText(fuelStationResponseModel.getDealerCode());
            etStationId.setText(fuelStationResponseModel.getFuelStationId());
            etStartTime.setText(fuelStationResponseModel.getStartTime());
            etEndTime.setText(fuelStationResponseModel.getEndTime());

            for (int i = 0; i < arrCode.size(); i++) {
                if (fuelStationResponseModel.getCountryCode().equals(arrCode.get(i))) {
                    spinnerCode.setSelection(i);
                    break;
                }
            }

            etMobileNumber.setText(fuelStationResponseModel.getMobileNumber());

            tvAddress.setText(fuelStationResponseModel.getAddress());
            etAccountName.setText(fuelStationResponseModel.getAccountName());
            etAccountNumber.setText(fuelStationResponseModel.getAccountNumber());
            etIfsc.setText(fuelStationResponseModel.getIfscCode());
            etLandline.setText(fuelStationResponseModel.getLandlineNumber());
            cbIsAvailable.setChecked(fuelStationResponseModel.getAvailability());

            btnSendQr.setVisibility(View.VISIBLE);

            try {
                imgQR.setImageBitmap(encodeAsBitmap(fuelStationResponseModel.getId()));
                lnrQR.setVisibility(View.VISIBLE);
                imgQR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_qr_code);
                        ImageView ivQrCode = dialog.findViewById(R.id.ivQrCode);
                        try {
                            ivQrCode.setImageBitmap(encodeAsBitmap(fuelStationResponseModel
                                    .getId()));
                        } catch (WriterException e) {

                        }

                        dialog.show();
                    }
                });
            } catch (WriterException e) {
                e.printStackTrace();
            }
            btnAddFuelStation.setText(getResources().getString(R.string.save));

            etDealerCode.setEnabled(false);
            etStationId.setEnabled(false);
            if (spinnerFuelCompany.getSelectedView() != null) {
                spinnerFuelCompany.getSelectedView().setEnabled(false);
            }
            spinnerFuelCompany.setEnabled(false);
        } else {
            lytFuelStationId.setVisibility(View.GONE);
        }


    }


    @OnClick({R.id.btnAddFuelStation, R.id.etStartTime, R.id.etEndTime, R.id.lytAddress, R.id.btnSendQr})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddFuelStation: {
                checkValidation();
                break;
            }
            case R.id.etStartTime: {
                showTimePicker(START_TIME);
                break;
            }
            case R.id.etEndTime: {
                showTimePicker(END_TIME);
                break;
            }
            case R.id.lytAddress: {

                if (!isLocationEnabled())
                    showAlert();

                checkLocationPermission(getActivity());

                break;
            }
            case R.id.btnSendQr: {
                showMsg(null, getResources().getString(R.string.sending_qr));
                stationRequestModel = new AddFuelStationRequestModel();
                stationRequestModel.setFuelStationId(fuelStationResponseModel.getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().sendQr(stationRequestModel), sendQrListener);
                break;
            }
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Enable Location").setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        paramDialogInterface.dismiss();
                    }
                });
        dialog.show();
    }

    public void checkLocationPermission(Activity activity) {
        int hasLocationPerm = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasLocationPerm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            showLocationDialog();
        }
    }

    void showLocationDialog() {
        AddLocationDialog addLocationDialog = new AddLocationDialog();
        addLocationDialog.setData(appComponent, this, getActivity());
        addLocationDialog.show(getChildFragmentManager(), AddLocationDialog.class.getName());
    }

    public void checkValidation() {
        name = etStationName.getText().toString().trim();
        dealerCode = etDealerCode.getText().toString().trim();
        startTime = etStartTime.getText().toString().trim();
        endTime = etEndTime.getText().toString().trim();
        mobileNumber = etMobileNumber.getText().toString().trim();
        accountName = etAccountName.getText().toString().trim();
        accountNumber = etAccountNumber.getText().toString().trim();
        ifscCode = etIfsc.getText().toString().trim();
        landlineNumber = etLandline.getText().toString().trim();
        address = tvAddress.getText().toString().trim();

        if (name.isEmpty()) {
            showMsg(view, getString(R.string.please_fuel_station_name));
            return;
        }
        if (dealerCode.isEmpty()) {
            showMsg(view, getString(R.string.enter_dealer_code));
            return;
        }

        boolean allZeros = true;

        for (int i = 0; i < dealerCode.length(); i++) {
            if (dealerCode.charAt(i) != '0') {
                allZeros = false;
            }
        }

        if (allZeros) {
            showMsg(view, getString(R.string.enter_valid_dealer_code));
            return;
        }

        if (startTime.isEmpty()) {
            showMsg(view, "Enter start time");
            return;
        }
        if (endTime.isEmpty()) {
            showMsg(view, "Enter end time");
            return;
        }
        if (mobileNumber.isEmpty()) {
            showMsg(view, getString(R.string.enter_mobile_number));
            return;
        }

        if (address.isEmpty()) {
            showMsg(view, getString(R.string.enter_the_address));
            return;
        }

        if (accountName.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_account_name));
            return;
        }

        if (accountNumber.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_account_number));
            return;
        }

        if (accountNumber.length() < 14) {
            showMsg(view, getString(R.string.validation_acc_number));
            return;
        }

        allZeros = true;

        for (int i = 0; i < accountName.length(); i++) {
            if (accountName.charAt(i) != '0') {
                allZeros = false;
            }
        }

        if (allZeros) {
            showMsg(view, getString(R.string.enter_valid_acc_number));
            return;
        }

        if (ifscCode.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_ifsc_code));
            return;
        }

        if (ifscCode.length() < 11) {
            showMsg(view, getString(R.string.validation_ifsc));
            return;
        }
        allZeros = true;

        for (int i = 0; i < ifscCode.length(); i++) {
            if (ifscCode.charAt(i) != '0') {
                allZeros = false;
            }
        }

        if (allZeros) {
            showMsg(view, getString(R.string.enter_valid_ifsc));
            return;
        }

        if (landlineNumber.isEmpty()) {
            landlineNumber = "";
        }

        allZeros = true;

        for (int i = 0; i < ifscCode.length(); i++) {
            if (ifscCode.charAt(i) != '0') {
                allZeros = false;
            }
        }

        if (allZeros && !landlineNumber.isEmpty()) {
            showMsg(view, getString(R.string.enter_valid_landline));
            return;
        }

        stationRequestModel = new AddFuelStationRequestModel();
        stationRequestModel.setAccountName(accountName);
        stationRequestModel.setAccountNumber(accountNumber);
        stationRequestModel.setAddress(address);
        stationRequestModel.setEndTime(endTime);
        stationRequestModel.setIfscCode(ifscCode);
        stationRequestModel.setLandlineNumber(landlineNumber);
        if (fuelStationResponseModel != null) {
            stationRequestModel.setFuelStationId(fuelStationResponseModel.getFuelStationId());
            stationRequestModel.setLatitude(fuelStationResponseModel.getLatitude());
            stationRequestModel.setLongitude(fuelStationResponseModel.getLongitude());
            stationRequestModel.setCity((fuelStationResponseModel.getCity() != null && !fuelStationResponseModel.getCity().isEmpty()) ? fuelStationResponseModel.getCity() : fuelStatinAddress.getCity());
            stationRequestModel.setState((fuelStationResponseModel.getState() != null && !fuelStationResponseModel.getState().isEmpty()) ? fuelStationResponseModel.getState() : fuelStatinAddress.getState());
            stationRequestModel.setCountry((fuelStationResponseModel.getCountry() != null && !fuelStationResponseModel.getCountry().isEmpty()) ? fuelStationResponseModel.getCountry() : fuelStatinAddress.getCountry());
        } else {
            stationRequestModel.setDealerCode(dealerCode);
            stationRequestModel.setLatitude(fuelStatinAddress.getLatitude());
            stationRequestModel.setLongitude(fuelStatinAddress.getLongitude());
            stationRequestModel.setCity(fuelStatinAddress.getCity());
            stationRequestModel.setState(fuelStatinAddress.getState());
            stationRequestModel.setCountry(fuelStatinAddress.getCountry());
        }

        stationRequestModel.setMobileNumber(mobileNumber);
        stationRequestModel.setStartTime(startTime);
        stationRequestModel.setName(name.toUpperCase());
        stationRequestModel.setCountryCode(spinnerCode.getSelectedItem().toString());
        stationRequestModel.setFuelCompany(fuelCompaniesList.get(spinnerFuelCompany.getSelectedItemPosition()).getId());
        stationRequestModel.setAvailability(cbIsAvailable.isChecked());

        if (fuelStationResponseModel == null) {

            appComponent.getServiceCaller().callService(appComponent.getAllApis().addFuelStation(stationRequestModel), listener);
        } else {
            appComponent.getServiceCaller().callService(appComponent.getAllApis().updateFuelStation(stationRequestModel), listener);
        }


        dismissDialog();
    }

    public void dismissDialog() {
        Fragment fragment = getFragmentManager().findFragmentByTag("add_fuel_station");
        if (fragment != null) {
            DialogFragment dialogFragment = (DialogFragment) fragment;
            dialogFragment.dismiss();
        }
    }

    public void showMsg(View v, String msg) {
        if (v == null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
            return;
        }
        Snackbar s = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
        View view = s.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" + getResources().getString(R.string.font_light)));
        tv.setTextColor(Color.WHITE);
        s.show();
    }


    void showTimePicker(final int check) {
        int hourTemp = 0, minuteTemp = 0;
        final Calendar mcurrentTime = Calendar.getInstance();
        if (fuelStationResponseModel == null) {
            hourTemp = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            minuteTemp = mcurrentTime.get(Calendar.MINUTE);
            ;
        } else {
            if (check == START_TIME) {
                hourTemp = getDate(fuelStationResponseModel.getStartTime()).getHours();
                minuteTemp = getDate(fuelStationResponseModel.getStartTime()).getMinutes();
            } else {
                hourTemp = getDate(fuelStationResponseModel.getEndTime()).getHours();
                minuteTemp = getDate(fuelStationResponseModel.getEndTime()).getMinutes();
            }
        }

        final int hour = hourTemp;
        final int minute = minuteTemp;
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                //@param
                //hour, minute, second
                Time time = new Time(selectedHour, selectedMinute, 0);

                int mHour = selectedHour;

                int mMinute = selectedMinute;

                Calendar c2 = Calendar.getInstance();
                Log.d("DateIssue", "TimeSet: " + mHour + ":" + mMinute + ", current: " + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE);

                updateTime(check, time);


            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    Time start_time, end_time;

    void updateTime(int check, Time time) {
        String txttime = time.getHours() + ":" + time.getMinutes();

        txttime = get12HourTime(txttime);

        if (check == START_TIME) {
            etStartTime.setText(txttime);
            start_time = time;
        } else if (check == END_TIME) {
            etEndTime.setText(txttime);
            end_time = time;
        }
    }


    public String get12HourTime(String time) {
        String convertedTime = "";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final Date dateObj = sdf.parse(time);
            convertedTime = new SimpleDateFormat("K:mm a").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
    }

    public Date getDate(String time) {
        Date dateObj = null;
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
            dateObj = sdf.parse(time);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return dateObj;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.spinnerFuelCompany: {
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            }
            case R.id.spinnerCode: {
                TextView textView = (TextView) view;
                textView.setTextColor(Color.WHITE);
                break;
            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    AddFuelStationRequestModel fuelStatinAddress;

    @Override
    public void onAddLocation(AddFuelStationRequestModel address) {
        this.fuelStatinAddress = address;
        tvAddress.setText(address.getAddress());
    }

    NetworkListener fuelCompanyListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelCompaniesList = (List<FuelCompanyRespnseModel>) responseBody.getResutData();
                fuelCompanyAdapter.updateList(fuelCompaniesList);

                if (fuelStationResponseModel != null) {
                    for (int i = 0; i < fuelCompaniesList.size(); i++) {
                        if (fuelStationResponseModel.getFuelCompany().getId().equals(fuelCompaniesList.get(i).getId())) {
                            spinnerFuelCompany.setSelection(i);
                            break;
                        }
                    }
                }
            }
        }

        @Override
        public void onError(String msg) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onStart() {

        }
    };
}
