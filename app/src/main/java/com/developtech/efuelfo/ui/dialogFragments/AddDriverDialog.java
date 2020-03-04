package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.customs.CustomButton;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.UpdateDriverRequestModel;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter2;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.SelectImage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by dt on 12/26/17.
 */

public class AddDriverDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.ivProfilePic)
    ImageView ivProfilePic;
    @BindView(R.id.etAlternateMobile)
    EditText etAlternateMobile;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etDriverMobile)
    EditText etDriverMobile;
    @BindView(R.id.spinnerCode)
    Spinner spinnerCode;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.lytSaveChanges)
    LinearLayout lytSaveChanges;
    @BindView(R.id.btnDelete)
    CustomButton btnDelete;
    @BindView(R.id.btnAddDriver)
    CustomButton btnAddDriver;
    @BindView(R.id.spinnerGender)
    Spinner spinnerGender;
    @BindView(R.id.spinnerVehicleNo)
    Spinner spinnerVehicleNumber;

    SpinnerAdapter spGenderAdapter;
    SpinnerAdapter2 spVehicleNumerAdapter;
    AddDriverRequestModel addDriverRequestModel;
    String mobileNumber, email;
    boolean isEdit;
    GetDriverResponseModel driverResponseModel;
    ArrayList<String> spGenderList = new ArrayList<>();
    ArrayList<String> spVehicleNumberList = new ArrayList<>();
    SelectImage selectImage;
    HomeActivity homeActivity;
    File imageFile;
    Uri resultUri = null;
    private View view;
    private AlertDialog alertDialog;
    private AppComponent appComponent;
    private NetworkListener listener;
    private NetworkListener deleteDriverListener;
    private List<AllVehicleResponseModel> vehicleResponseModels = new ArrayList<>();

    ArrayAdapter<String> spinnerAdapter;

    NetworkListener getVehicleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                vehicleResponseModels = (List<AllVehicleResponseModel>) responseBody.getResutData();
                if (vehicleResponseModels.size() > 0) {
                    for (AllVehicleResponseModel model : vehicleResponseModels) {
                        spVehicleNumberList.add(model.getVehicleNumber());
                    }
                    spVehicleNumerAdapter.notifyDataSetChanged();
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_drivers, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        init();
        return alertDialog;
    }

    public void setData(AppComponent component, NetworkListener networkListener, NetworkListener deleteDriverListener, boolean isEdit, GetDriverResponseModel model) {
        this.appComponent = component;
        this.listener = networkListener;
        this.isEdit = isEdit;
        driverResponseModel = model;
        this.deleteDriverListener = deleteDriverListener;
    }

    void init() {

        appComponent.getServiceCaller().callService(appComponent.getAllApis().getVehicles(), getVehicleListener);

        spGenderList.add("Select Gender");
        spGenderList.add("Male");
        spGenderList.add("Female");
        spVehicleNumberList.add("No vehicle");

        spGenderAdapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, spGenderList);
        spVehicleNumerAdapter = new SpinnerAdapter2(getContext(), android.R.layout.simple_spinner_item, spVehicleNumberList);
        spinnerGender.setAdapter(spGenderAdapter);
        spinnerVehicleNumber.setAdapter(spVehicleNumerAdapter);

        ArrayList<String> arrCode = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_code)));
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrCode);
        spinnerCode.setAdapter(spinnerAdapter);
        spinnerCode.setOnItemSelectedListener(this);
        spinnerGender.setOnItemSelectedListener(this);

        if (isEdit) {
            btnAddDriver.setVisibility(View.GONE);
            lytSaveChanges.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
        }

        if (driverResponseModel != null) {
            etFirstName.setText(driverResponseModel.getFirstName());
            etLastName.setText(driverResponseModel.getLastName());
            etDriverMobile.setText(driverResponseModel.getMobileNumber());
            etEmail.setText(driverResponseModel.getEmail());

            if (driverResponseModel.getAltMobileNumber()!=null && !driverResponseModel.getAltMobileNumber().isEmpty())
            {
                etAlternateMobile.setText(driverResponseModel.getAltMobileNumber());
            }

            for (int i = 0; i < spGenderList.size(); i++) {
                if(spGenderList.get(i).equalsIgnoreCase(driverResponseModel.getGender()))
                {
                    spinnerGender.setSelection(i);
                    break;
                }
            }

            if (driverResponseModel.getCountryCode()!=null && !driverResponseModel.getCountryCode().isEmpty())
            {
                for (int i = 0; i < Arrays.asList(getResources().getStringArray(R.array.country_code)).size(); i++) {
                    if(driverResponseModel.getCountryCode().equals(Arrays.asList(getResources().getStringArray(R.array.country_code)).get(i)))
                    {
                        spinnerCode.setSelection(i);
                        break;
                    }
                }
            }

            if(driverResponseModel.getImage()!=null && !driverResponseModel.getImage().isEmpty())
            {
                Picasso.with(getContext()).load(appComponent.getAllUrls().BASE_IMAGE_URL + driverResponseModel.getImage()).placeholder(R.drawable.place_holder).into(ivProfilePic);
            }
        }
    }

    @OnClick({R.id.btnAddDriver, R.id.btnSaveChanges, R.id.btnDelete, R.id.btnCancel, R.id.ivProfilePic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivProfilePic: {
                Log.e("parent", getParentFragment().getActivity().toString());
                selectImage = new SelectImage(getContext(), ivProfilePic);
                homeActivity = (HomeActivity) getParentFragment().getActivity();
                homeActivity.setSelectImage(selectImage);
//                selectImage.showDialog();
                break;
            }
            case R.id.btnAddDriver: {
                if (selectImage != null) {
                    resultUri = homeActivity.getResultUri();
                    if (resultUri != null) {
                        compressImage(((BitmapDrawable) ivProfilePic.getDrawable()).getBitmap());
                    }
                }
                checkValidation(false);
                break;
            }
            case R.id.btnSaveChanges: {
                checkValidation(true);
                break;
            }
            case R.id.btnDelete: {
                DeleteDriverRequestModel requestModel = new DeleteDriverRequestModel(driverResponseModel.getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().deleteDriver(requestModel), deleteDriverListener);
                dismissDialog();
                break;
            }
            case R.id.btnCancel: {
                dismissDialog();
                break;
            }
        }
    }

    public void checkValidation(boolean isEdit) {
        String fname = etFirstName.getText().toString().trim();
        String lname = etLastName.getText().toString().trim();
        mobileNumber = etDriverMobile.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        if (fname.isEmpty()) {
            showMsg(view, getString(R.string.enter_first_name));
            etFirstName.requestFocus();
            return;
        }
        if (lname.isEmpty()) {
            showMsg(view, getString(R.string.enter_last_name));
            etLastName.requestFocus();
            return;
        }
        if (isEdit && mobileNumber.isEmpty()) {
            showMsg(view, getString(R.string.enter_driver_number));
            etDriverMobile.requestFocus();
            return;
        }
//        if (email.isEmpty()) {
//            showMsg(view, getString(R.string.enter_email_address));
//            etEmail.requestFocus();
//            return;
//        }
        if (!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMsg(view, getString(R.string.please_enter_valid_email_id));
            etEmail.requestFocus();
            return;
        }

        if(spinnerGender.getSelectedItemPosition()==0)
        {
            showMsg(view, "Please Select Gender");
            spinnerGender.requestFocus();
            return;
        }

        dismissDialog();

        addDriverRequestModel = new AddDriverRequestModel();
        addDriverRequestModel.setMobileNumber(mobileNumber);
        addDriverRequestModel.setLastName(lname);
        addDriverRequestModel.setFirstName(fname);
        addDriverRequestModel.setEmail(email);

        MultipartBody.Part body = null;
        if (imageFile != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
            body = MultipartBody.Part.createFormData("image", "image.png", requestFile);
        }

        final RequestBody FirstName = RequestBody.create(MediaType.parse("multipart/form-data"), fname);
        final RequestBody LastName = RequestBody.create(MediaType.parse("multipart/form-data"), lname);
        final RequestBody Email = RequestBody.create(MediaType.parse("multipart/form-data"), email==null ? "" : email);
        final RequestBody MobileNumber = RequestBody.create(MediaType.parse("multipart/form-data"), mobileNumber);
        final RequestBody AltMobile = RequestBody.create(MediaType.parse("multipart/form-data"), etAlternateMobile.getText().toString());
        final RequestBody gender = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerGender.getSelectedItem().toString());

        final RequestBody vehicle = RequestBody.create(MediaType.parse("multipart/form-data"), vehicleResponseModels.size()<1 || spinnerVehicleNumber.getSelectedItem()==null || spinnerVehicleNumber.getSelectedItemPosition()<1 ? "No Vehicle" : vehicleResponseModels.get(spinnerVehicleNumber.getSelectedItemPosition()-1).getId());
        final RequestBody countryCode = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerCode.getSelectedItem().toString());

        if (isEdit) {
            final RequestBody DriverId = RequestBody.create(MediaType.parse("multipart/form-data"), driverResponseModel.getId());
            UpdateDriverRequestModel updateRequest = new UpdateDriverRequestModel();
//            updateRequest.email = email;
//            updateRequest.firstName = fname;
//            updateRequest.lastName = lname;
            updateRequest.driverId = driverResponseModel.getId();
            updateRequest.vehicle = spinnerVehicleNumber.getSelectedItem()==null ? "NO VEHICLE" : spinnerVehicleNumber.getSelectedItem().toString();
            appComponent.getServiceCaller().callService(appComponent.getAllApis().updateDriver(FirstName, LastName, MobileNumber, Email, gender, vehicle,DriverId, body), listener);
        } else {
            appComponent.getServiceCaller().callService(appComponent.getAllApis().addDriver(FirstName, LastName, MobileNumber, Email, gender, vehicle, AltMobile, countryCode, body), listener);
        }
    }

    public void dismissDialog() {
        Fragment fragment = getFragmentManager().findFragmentByTag("add_driver");
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

    public void compressImage(Bitmap bitmap) {
        imageFile = new File(resultUri.getPath());
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.e("exception", e + "");
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        TextView tvView = (TextView) view;
        tvView.setTextColor(Color.WHITE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
