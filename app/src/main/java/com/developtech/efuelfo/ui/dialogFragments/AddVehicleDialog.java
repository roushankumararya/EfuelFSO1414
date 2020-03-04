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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.customs.RoundedImageView;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddVehicleRequestModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.GetCarModelsListRequest;
import com.developtech.efuelfo.model.requestModel.VehicleCompanyListRequestModel;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.model.responseModel.CompanyListResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelTypeResponseModel;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.model.responseModel.GetVehicleCategoryResponse;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter2;
import com.developtech.efuelfo.ui.fragments.AddVehicleFragment;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.SelectImage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddVehicleDialog extends DialogFragment implements AdapterView.OnItemSelectedListener
{

    @BindView(R.id.spinnerVehicleMake)
    Spinner spinnerVehicleMake;
    @BindView(R.id.tvVehicleModel)
    TextView tvVehicleModel;
    @BindView(R.id.etVehicleColor)
    EditText etVehicleColor;
    @BindView(R.id.etAliasName)
    EditText etAliasName;
    @BindView(R.id.etVehicleNumber)
    EditText etVehicleNumber;
    @BindView(R.id.btnAddVehicle)
    Button btnAddVehicle;

    @BindView(R.id.etFuelCapacity)
    EditText etFuelCapacity;

    @BindView(R.id.lytSaveChanges)
    LinearLayout lytSaveChanges;

    @BindView(R.id.lytDeleteBuy)
    LinearLayout lytDeleteBuy;

    @BindView(R.id.ivVehicle)
    RoundedImageView ivVehicle;

    @BindView(R.id.spinnerDriver)
    Spinner spinnerDriver;

    @BindView(R.id.spinnerVehicleCategory)
    Spinner spinnerVehicleCategory;

    @BindView(R.id.rg)
    RadioGroup radioGroup;

    @BindView(R.id.rbPrivate)
    RadioButton rbPrivate;

    @BindView(R.id.rbCommercial)
    RadioButton rbCommercial;

    @BindView(R.id.switchSelfDriven)
    Switch switchSelfDriven;

    @BindView(R.id.lytDriverName)
    LinearLayout lytDriverName;

    @BindView(R.id.tvFuelType)
    CustomTextView tvFuelType;

    AddVehicleRequestModel requestModel;
    String company, model, color, alias, vehicleNumber, fuelType, fuelCapacity;
    boolean isEdit;
    AllVehicleResponseModel vehicleModel;
    ArrayList<String> spCompanyList = new ArrayList<>();
    ArrayList<String> spModelsList = new ArrayList<>();
    ArrayList<String> spVehicleCategoryList = new ArrayList<>();
    SpinnerAdapter spCompanyAdapter, spVehicleCategoryAdapter;
    SpinnerAdapter2 spDriverAdaper;
    List<CompanyListResponseModel> companyList = new ArrayList<>();
    List<CompanyListResponseModel> modelsList = new ArrayList<>();
    List<FuelTypeResponseModel> fuelTypeList = new ArrayList<>();

    SelectImage selectImage;
    HomeActivity homeActivity;

    NetworkListener getModelsListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {

            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                modelsList = (List<CompanyListResponseModel>) responseBody.getResutData();
                spModelsList.clear();

                for (CompanyListResponseModel model : modelsList)
                {
                    spModelsList.add(model.getName());
                }
//                spModelsList.add("Select Vehicle Model");
//
//                for (CompanyListResponseModel model : modelsList) {
//                    spModelsList.add(model.getName());
//                }
//
//                spModelsAdapter.notifyDataSetChanged();
//
//                if (isEdit) {
//                    for (int i = 0; i < spModelsList.size(); i++) {
//                        if (vehicleModel.getModel().equals(spModelsList.get(i))) {
//                            spinnerVehicleModel.setSelection(i);
//                        }
//                    }
//                }

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

    private View view;
    private AlertDialog alertDialog;
    private NetworkListener listener, deleteListener;
    private AppComponent appComponent;
    NetworkListener getCompanyListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                    companyList = (List<CompanyListResponseModel>) responseBody.getResutData();
                    spCompanyList.clear();
                    spCompanyList.add("Select Vehicle Make");
                    for (CompanyListResponseModel model : companyList) {
                        spCompanyList.add(model.getName());
                    }
                    spCompanyAdapter.notifyDataSetChanged();

                    if (isEdit) {
                        for (int i = 0; i < companyList.size(); i++) {
                            if (vehicleModel.getCompany().equals(companyList.get(i).getName())) {
                                spinnerVehicleMake.setSelection(i+1);
                            }
                        }

                        GetCarModelsListRequest request = new GetCarModelsListRequest();
                        request.setCompany(companyList.get(spinnerVehicleMake.getSelectedItemPosition() - 1).getId());
                        appComponent.getServiceCaller().callService(appComponent.getAllApis().getModelsList(request), getModelsListener);
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

    private List<GetDriverResponseModel> driverResponseModels;
    List<String> spDriverList = new ArrayList<>();

    NetworkListener getDriversListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                driverResponseModels = (List<GetDriverResponseModel>) responseBody.getResutData();
                if(isEdit && vehicleModel!=null && vehicleModel.getDriver()!=null)
                {
                    driverResponseModels.add(vehicleModel.getDriver());
                }

                if (driverResponseModels.size() > 0){
                    for(GetDriverResponseModel model : driverResponseModels)
                    {
                        spDriverList.add(model.getFirstName()+" "+model.getLastName());
                    }
                    spDriverAdaper.notifyDataSetChanged();

                    if(isEdit && vehicleModel!=null && vehicleModel.getDriver()!=null)
                    {
                        for (int i = 0; i < driverResponseModels.size(); i++) {
                            if(vehicleModel.getDriver().getId().equals(driverResponseModels.get(i).getId()))
                            {
                                spinnerDriver.setSelection(i+1);
                                break;
                            }
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

    List<GetVehicleCategoryResponse> vehicleCategoryList;

    NetworkListener getVehicleCategoryListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                vehicleCategoryList = (List<GetVehicleCategoryResponse>) responseBody.getResutData();
                if(vehicleCategoryList.size()>0)
                {
                    for (GetVehicleCategoryResponse model : vehicleCategoryList)
                    {
                        spVehicleCategoryList.add(model.getCategory());
                    }
                    spVehicleCategoryAdapter.notifyDataSetChanged();

                    if (isEdit && vehicleModel!=null && vehicleModel.getVehicleCategory()!=null && !vehicleModel.getVehicleCategory().isEmpty())
                    {
                        for (int i = 0; i < vehicleCategoryList.size(); i++) {
                            if(vehicleModel.getVehicleCategory().equals(vehicleCategoryList.get(i).getCategory()))
                            {
                                spinnerVehicleCategory.setSelection(i+1);
                                break;
                            }

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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_vehicles, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        init();
        return alertDialog;
    }

    void init() {
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getVehicleCategories(), getVehicleCategoryListener);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getDrivers(), getDriversListener);
        spCompanyList.add("Select Vehicle Make");
        spModelsList.add("Select Vehicle Model");
        spVehicleCategoryList.add("Select Vehicle Category");
        spDriverList.add("No Driver");
        spCompanyAdapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, spCompanyList);
//        spModelsAdapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, spModelsList);
        spVehicleCategoryAdapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, spVehicleCategoryList);
        spDriverAdaper = new SpinnerAdapter2(getContext(), android.R.layout.simple_spinner_item, spDriverList);
        spinnerVehicleMake.setAdapter(spCompanyAdapter);
//        spinnerVehicleModel.setAdapter(spModelsAdapter);
        spinnerDriver.setAdapter(spDriverAdaper);
        spinnerVehicleCategory.setAdapter(spVehicleCategoryAdapter);
        spinnerVehicleCategory.setOnItemSelectedListener(this);
//        spinnerVehicleModel.setOnItemSelectedListener(this);

        spinnerVehicleMake.setOnItemSelectedListener(this);

        if (isEdit) {
            btnAddVehicle.setVisibility(View.GONE);
            lytDeleteBuy.setVisibility(View.VISIBLE);
            lytSaveChanges.setVisibility(View.VISIBLE);

            if (vehicleModel != null) {
                etAliasName.setText(vehicleModel.getAlias());
                etFuelCapacity.setText(vehicleModel.getFuelCapacity());
                etVehicleColor.setText(vehicleModel.getColor());
                etVehicleNumber.setText(vehicleModel.getVehicleNumber());

                tvVehicleModel.setText(vehicleModel.getModel());

                if(vehicleModel.getVehicleType()!=null && vehicleModel.getVehicleType().equals("PVT"))
                {
                    rbCommercial.setChecked(false);
                    rbPrivate.setChecked(true);
                }

                if(vehicleModel.getVehicleType()!=null && vehicleModel.getVehicleType().equals("COM"))
                {
                    rbPrivate.setChecked(false);
                    rbCommercial.setChecked(true);
                }

                if(vehicleModel.isSelfDriven())
                {
                    switchSelfDriven.setChecked(true);
                }
                else
                {
                    switchSelfDriven.setChecked(false);
                }


                if(vehicleModel.getImage()!=null && !vehicleModel.getImage().isEmpty())
                {
                    Picasso.with(getContext()).load(appComponent.getAllUrls().BASE_IMAGE_URL + vehicleModel.getImage()).placeholder(R.drawable.place_holder).into(ivVehicle);
                }
            }

        }
        else
        {
            rbPrivate.setChecked(true);
        }


    }

    public void setData(AppComponent component, NetworkListener networkListener, NetworkListener deleteListener, boolean isEdit, AllVehicleResponseModel vehicleModel) {
        this.appComponent = component;
        this.listener = networkListener;
        this.isEdit = isEdit;
        this.vehicleModel = vehicleModel;
        this.deleteListener = deleteListener;
    }

    @OnClick({R.id.btnAddVehicle, R.id.btnSaveChanges, R.id.btnCancel, R.id.btnDelete, R.id.ivVehicle, R.id.tvVehicleModel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddVehicle: {
                validate();
                break;
            }
            case R.id.btnSaveChanges: {
                validate();
                break;
            }
            case R.id.btnCancel: {
                dismissDialog();
                break;
            }
            case R.id.btnDelete: {
                DeleteDriverRequestModel requestModel = new DeleteDriverRequestModel(vehicleModel.getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().deleteVehicle(requestModel), deleteListener);
                dismissDialog();
                break;
            }
            case R.id.ivVehicle: {
                selectImage = new SelectImage(getContext(), ivVehicle);
                homeActivity = (HomeActivity) getParentFragment().getActivity();
                homeActivity.setSelectImage(selectImage);
                break;
            }
            case R.id.tvVehicleModel : {
                VehicleModelSearchDialog modelDialog = new VehicleModelSearchDialog();
                modelDialog.setData(spModelsList, this);
                modelDialog.show(homeActivity.getFragmentManager(), "add_vehicle");
                break;
            }
        }
    }

    File imageFile;
    Uri resultUri = null;


    void validate() {
        company = "A";
        model = "B";
        color = etVehicleColor.getText().toString().trim();
        alias = etAliasName.getText().toString().trim();
        vehicleNumber = etVehicleNumber.getText().toString().trim();
        fuelType = "Diesel";
        fuelCapacity = etFuelCapacity.getText().toString().trim();

        if (spinnerVehicleCategory.getSelectedItemPosition() < 1) {
            showMsg(view, "Please select vehicle category");
            spinnerVehicleCategory.requestFocus();
            return;
        }

        if (spinnerVehicleMake.getSelectedItemPosition() < 1) {
            showMsg(view, "Please select vehicle make");
            spinnerVehicleMake.requestFocus();
            return;
        }

        if (model.trim().isEmpty()) {
            showMsg(view, "Please select vehicle model");
            tvVehicleModel.requestFocus();
            return;
        }

        if (color.isEmpty()) {
            showMsg(view, getString(R.string.enter_vehiclecolor));
            etVehicleColor.requestFocus();
            return;
        }
        if (alias.isEmpty()) {
            showMsg(view, getString(R.string.enter_aliasname));
            etAliasName.requestFocus();
            return;
        }
        if (vehicleNumber.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_vehicle_number));
            etVehicleNumber.requestFocus();
            return;
        }
        if (fuelCapacity.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_fuel_capacity));
            etFuelCapacity.requestFocus();
            return;
        }

        if (selectImage != null) {
            resultUri = homeActivity.getResultUri();
            if (resultUri != null) {
                compressImage(((BitmapDrawable) ivVehicle.getDrawable()).getBitmap());
            }
        }

        String strVehicleType = "";

        if (rbPrivate.isChecked())
        {
            strVehicleType = "PVT";
        }
        else
        {
            strVehicleType = "COM";
        }

        company = companyList.get(spinnerVehicleMake.getSelectedItemPosition() - 1).getName();
//        model = modelsList.get(spinnerVehicleModel.getSelectedItemPosition() - 1).getName();
        fuelType = tvFuelType.getText().toString();

        MultipartBody.Part body = null;
        if (resultUri != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
            body = MultipartBody.Part.createFormData("image", "image.png", requestFile);
        }


        String strDriverId ;
        if(spinnerDriver.getSelectedItemPosition()==0)
        {
            strDriverId = "No Driver";
        }
        else
        {
            strDriverId = driverResponseModels.get(spinnerDriver.getSelectedItemPosition()-1).getId();
        }

        final RequestBody Company = RequestBody.create(MediaType.parse("multipart/form-data"), company);
        final RequestBody Model = RequestBody.create(MediaType.parse("multipart/form-data"), model);
        final RequestBody Color = RequestBody.create(MediaType.parse("multipart/form-data"), color);
        final RequestBody Alias = RequestBody.create(MediaType.parse("multipart/form-data"), alias);
        final RequestBody VehicleNumber = RequestBody.create(MediaType.parse("multipart/form-data"), vehicleNumber);
        final RequestBody FuelType = RequestBody.create(MediaType.parse("multipart/form-data"), fuelType);
        final RequestBody FuelCapacity = RequestBody.create(MediaType.parse("multipart/form-data"), fuelCapacity);
        final RequestBody DriverId = RequestBody.create(MediaType.parse("multipart/form-data"), strDriverId);
        final RequestBody vehicleCategory = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerVehicleCategory.getSelectedItem().toString());
        final RequestBody vehicleType = RequestBody.create(MediaType.parse("multipart/form-data"), strVehicleType);
        final RequestBody selfDriven = RequestBody.create(MediaType.parse("multipart/form-data"), switchSelfDriven.isChecked() ? "true" : "false");

        if (isEdit) {
            final RequestBody RequestId = RequestBody.create(MediaType.parse("multipart/form-data"), vehicleModel.getId());

            appComponent.getServiceCaller().callService(appComponent.getAllApis().updateVehicle(RequestId, Company, Model, Color, Alias, VehicleNumber, FuelType, FuelCapacity, DriverId,vehicleCategory, vehicleType, selfDriven, body), listener);
        } else {
            appComponent.getServiceCaller().callService(appComponent.getAllApis().addVehicle(Company, Model, Color, Alias, VehicleNumber, FuelType, FuelCapacity,DriverId, vehicleCategory, vehicleType, selfDriven, body), listener);
        }
        dismissDialog();
    }

    public void dismissDialog() {
        Fragment fragment = getFragmentManager().findFragmentByTag("add_vehicle");
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinnerVehicleMake: {

                TextView textView = (TextView) view;
                if(spinnerVehicleMake.getSelectedItemPosition()<1)
                {
                    textView.setTextColor(getResources().getColor(R.color.hint_color));
                }
                else
                {
                    textView.setTextColor(Color.WHITE);
                }

                if (spinnerVehicleMake.getSelectedItemPosition() > 0) {
                    GetCarModelsListRequest request = new GetCarModelsListRequest();
                    request.setCompany(companyList.get(spinnerVehicleMake.getSelectedItemPosition() - 1).getId());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().getModelsList(request), getModelsListener);
                }
                break;
            }
            case R.id.spinnerVehicleCategory: {

                TextView textView = (TextView) view;
                if(spinnerVehicleCategory.getSelectedItemPosition()<1)
                {
                    textView.setTextColor(getResources().getColor(R.color.hint_color));
                }
                else
                {
                    textView.setTextColor(Color.WHITE);
                }


                if (spinnerVehicleCategory.getSelectedItemPosition()>0)
                {
                    VehicleCompanyListRequestModel model = new VehicleCompanyListRequestModel();
                    model.setCategory(vehicleCategoryList.get(spinnerVehicleCategory.getSelectedItemPosition()-1).getId());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().getCompanyList(model), getCompanyListener);
                }
                break;
            }
//            case R.id.spinnerVehicleModel : {
//
//                TextView textView = (TextView) view;
//                if(spinnerVehicleModel.getSelectedItemPosition()<1)
//                {
//                    textView.setTextColor(getResources().getColor(R.color.hint_color));
//                }
//                else
//                {
//                    textView.setTextColor(Color.WHITE);
//                }
//
//
//                if (spinnerVehicleModel.getSelectedItemPosition()>0) {
//
//
//
//                }
//            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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



}
