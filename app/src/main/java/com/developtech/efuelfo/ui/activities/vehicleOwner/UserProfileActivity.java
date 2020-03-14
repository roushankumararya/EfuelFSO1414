package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.UserPreference;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.FindOperatorRequestModel;
import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.developtech.efuelfo.model.responseModel.CountriesResponseModel;
import com.developtech.efuelfo.model.responseModel.LanguageResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.SelectImage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserProfileActivity extends MyActionBar implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.tvFirstNameLabel)
    CustomTextView tvFirstNameLabel;

    @BindView(R.id.tvLastNameLabel)
    CustomTextView tvLastNameLabel;

    @BindView(R.id.tvMobileLabel)
    CustomTextView tvMobileLabel;

    @BindView(R.id.tvEmailLabel)
    CustomTextView tvEmailLabel;

    @BindView(R.id.tvCountryLabel)
    CustomTextView tvCountryLabel;

    @BindView(R.id.tvLanguageLabel)
    CustomTextView tvLanguageLabel;

    @BindView(R.id.etFirstName)
    CustomEditText etFirstName;

    @BindView(R.id.etLastName)
    CustomEditText etLastName;

    @BindView(R.id.etPhone)
    CustomEditText etPhone;

    @BindView(R.id.etEmail)
    CustomEditText etEmail;

    @BindView(R.id.spCountryCode)
    EditText spCountryCode;

    @BindView(R.id.spCountry)
    EditText spinnerCountry;

    @BindView(R.id.spLanguage)
    EditText spinnerLanguage;

    @BindView(R.id.cbAdditionalInfo)
    CheckBox cbAdditionalInfo;

    @BindView(R.id.ivProfilePic)
    ImageView ivProfilePic;

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    @BindView(R.id.tvAlternateMobile)
    CustomTextView tvAlternateMobile;

    @BindView(R.id.etAlternateMobile)
    CustomEditText etAlternateMobile;

    @BindView(R.id.viewToDiable)
    View viewToDiable;

    @BindView(R.id.ivEdit)
    ImageView ivEdit;

    @BindView(R.id.tvLabel)
    TextView tvLabel;

    @BindView(R.id.tvValue)
    TextView tvValue;

    @BindView(R.id.tvFuelStationIdLabel)
    TextView tvFuelStationIdLabel;

    @BindView(R.id.etFuelStationId)
    EditText etFuelStationId;

    @BindView(R.id.tvAddressLabel)
    TextView tvAddressLabel;

    @BindView(R.id.etAddress)
    EditText etAddress;

    @BindView(R.id.lytPinCode)
    LinearLayout lytPinCode;

    @BindView(R.id.tvPinCodeLabel)
    TextView tvPinCodeLabel;

    @BindView(R.id.etPinCode)
    EditText etPinCode;

    @BindView(R.id.tvLoginId)
    TextView tvLoginId;

    @BindView(R.id.etLoginId)
    EditText etLoginId;

    boolean isEdit;
    SelectImage selectImage;
    Uri resultUri = null;
    Bundle bundle;
    SignInResponseModel model;
   // UserPreference modelone;
   // String fnm,lnm,nmm,nmm1,fns,eem,adddddd,cont,lng,pinn;

    SpinnerAdapter spCountryAdapter, spLanguageAdapter;
    ArrayList<String> spCountryList = new ArrayList<>();
    ArrayList<String> spLanguageList = new ArrayList<>();
    ArrayList<CountriesResponseModel> countriesList;
    ArrayList<LanguageResponseModel> languagesList;
    SpinnerAdapter spinnerAdapter;
    UserPreference userPreference;
    EditText editText;


    File imageFile;
    NetworkListener listener = new NetworkListener() {
       // Intent intent=getIntent();
        @Override
        public void onSuccess(ResultModel<?> responseBody) {


            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {

                SignInResponseModel responseModel = (SignInResponseModel) responseBody.getResutData();

                String token = appComponent.getSpUtils().getUserData().getToken();
                responseModel.setToken(token);
                appComponent.getSpUtils().saveUserData(responseModel);

                showMsg("Profile has been updated");
//                SignInResponseModel model = appComponent.getSpUtils().getUserData();
//                model.setFirstName(firstName);
//                model.setLastName(lastName);
//                model.setEmail(email);
//                model.setImage(responseModel.getImage());
//                model.setCountry(country);
//                model.setLanguage(language);
//                model.setCountryCode(spCountryCode.getSelectedItem().toString());
//                model.setPincode(pinCode);
//                if (appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.FSO)
//                {
//                    model.setAddress(address);
//                }
//                if(cbAdditionalInfo.isChecked())
//                {
//                    model.setAltMobile(etAlternateMobile.getText().toString());
//                }
//                else
//                {
//                    model.setAltMobile("");
//                }
//                appComponent.getSpUtils().saveUserData(model);

                isEdit = false;
                changeView();
                setResult(RESULT_OK);
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
    NetworkListener getCountriesListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                countriesList = (ArrayList<CountriesResponseModel>) responseBody.getResutData();
                if (countriesList.size() > 0) {
                    for (CountriesResponseModel model : countriesList) {
                        spCountryList.add(model.getCountryName());
                    }
                }
                spCountryAdapter.notifyDataSetChanged();

                if(model.getCountry()!=null && !model.getCountry().isEmpty())
                {
                    for (int i = 0; i < spCountryList.size(); i++) {
                        if (model.getCountry().equalsIgnoreCase(spCountryList.get(i))) {
                     //       spinnerCountry.setSelection(i);
                            break;
                        }
                    }
                }
                else
                {
                //    spinnerCountry.setSelection(1);
                }

                if (isEdit) {
                    spCountryAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
                } else {
                    spCountryAdapter.setItemTextColor(getResources().getColor(R.color.white));
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
    NetworkListener getLanguagesListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                languagesList = (ArrayList<LanguageResponseModel>) responseBody.getResutData();
                if (languagesList.size() > 0) {
                    for (LanguageResponseModel model : languagesList) {
                        spLanguageList.add(model.getLanguage());
                    }
                }
                spLanguageAdapter.notifyDataSetChanged();
            }

            if(model.getLanguage()!=null && !model.getLanguage().isEmpty()) {
                for (int i = 0; i < spLanguageList.size(); i++) {
                    if (model.getLanguage().equalsIgnoreCase(spLanguageList.get(i))) {
                 //       spinnerLanguage.setSelection(i);
                        break;
                    }
                }
            }
            else
            {
       //         spinnerLanguage.setSelection(1);
            }

            if (isEdit) {
                spLanguageAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
            } else {
                spLanguageAdapter.setItemTextColor(getResources().getColor(R.color.white));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        init();
        initComponents();
        userPreference=new UserPreference(getApplicationContext()).getInstance(getApplicationContext());

        etFirstName.setText(""+userPreference.getFirsName());
        etLastName.setText(""+userPreference.getLastName());
      //  tvMobileLabel.setText(""+userPreference.getNumber());
        etAddress.setText(/*""+userPreference.getAddress()+*/""+userPreference.getAddresstwo());
        etLoginId.setText(""+userPreference.getNumber());
        tvCountryLabel.setText("India");
        etPinCode.setText(""+userPreference.getAddress());
        etPhone.setText(""+userPreference.getNumber());
        etEmail.setText(""+userPreference.getEmailId());
        etFuelStationId.setText(""+userPreference.getNumber());

       // etPinCode.setText(""+userPreference.get);
        spinnerCountry.setText("India");
        spCountryCode.setText("+91");
        spinnerLanguage.setText("English");

        tvEmailLabel.setTextColor(getResources().getColor(android.R.color.white));
        tvCountryLabel.setTextColor(getResources().getColor(android.R.color.white));
        tvLanguageLabel.setTextColor(getResources().getColor(android.R.color.white));
        tvAlternateMobile.setTextColor(getResources().getColor(android.R.color.white));
        tvFuelStationIdLabel.setTextColor(getResources().getColor(android.R.color.white));
        tvAddressLabel.setTextColor(getResources().getColor(android.R.color.white));
        tvPinCodeLabel.setTextColor(getResources().getColor(android.R.color.white));
        tvLoginId.setTextColor(getResources().getColor(android.R.color.white));

        etFirstName.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etLastName.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        //spCountryCode.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etPhone.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etEmail.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
      //  spinnerCountry.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
      //  spinnerLanguage.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etAlternateMobile.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etFuelStationId.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etAddress.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etPinCode.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etLoginId.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
        etFirstName.setTextColor(getResources().getColor(R.color.blackDarker));
        etLastName.setTextColor(getResources().getColor(R.color.blackDarker));
        etPhone.setTextColor(getResources().getColor(R.color.blackDarker));
        etEmail.setTextColor(getResources().getColor(R.color.blackDarker));
        spCountryAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
        spLanguageAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
        spinnerAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
        etAlternateMobile.setTextColor(getResources().getColor(R.color.blackDarker));
        etFuelStationId.setTextColor(getResources().getColor(R.color.blackDarker));
        etAddress.setTextColor(getResources().getColor(R.color.blackDarker));
        etPinCode.setTextColor(getResources().getColor(R.color.blackDarker));
        etLoginId.setTextColor(getResources().getColor(R.color.blackDarker));
    }

    @Override
    public void initComponents() {

        ArrayList<String> arrCode = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_code)));
        spinnerAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrCode);
      //  spCountryCode.setAdapter(spinnerAdapter);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().getCountriesList(), getCountriesListener);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getLanguages(), getLanguagesListener);

        if(appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.FSO)
        {
            FindOperatorRequestModel requestModel = new FindOperatorRequestModel();
            requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
            appComponent.getServiceCaller().callService(appComponent.getAllApis().getOperators(requestModel),
                    getOperatorsListener);
            tvLabel.setText("Operators");
        }

        spCountryList.add("Select Country");
        spLanguageList.add("Select Language");
        spCountryAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item,
                spCountryList);
        spLanguageAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item,
                spLanguageList);
    //    spinnerCountry.setAdapter(spCountryAdapter);
      //  spinnerLanguage.setAdapter(spLanguageAdapter);
       // spinnerLanguage.setOnItemSelectedListener(this);
     //   spinnerCountry.setOnItemSelectedListener(this);
     //   spCountryCode.setOnItemSelectedListener(this);

       /* UserPreference userPreference=new UserPreference(getApplicationContext())
                .getInstance(getApplicationContext());
        //shareprefer save boolean set tru, number
        userPreference.setNumber(etMobileNumber.getText().toString());
        userPreference.setIsLogin(true);*/

       /* if (spCountryCode.getSelectedView()!=null) {
            spCountryCode.getSelectedView().setEnabled(false);
        }*/
     //   spCountryCode.setEnabled(false);

        viewToDiable.setVisibility(View.VISIBLE);
        cbAdditionalInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvAlternateMobile.setVisibility(View.VISIBLE);
                    etAlternateMobile.setVisibility(View.VISIBLE);
                } else {
                    tvAlternateMobile.setVisibility(View.GONE);
                    etAlternateMobile.setVisibility(View.INVISIBLE);
                }
            }
        });

        model = appComponent.getSpUtils().getUserData();

        if (model != null) {
            if (model.getImage() != null && !model.getImage().isEmpty()) {

                Picasso.with(this).load(appComponent.getAllUrls().BASE_IMAGE_URL + model.getImage())
                        .placeholder(R.drawable.place_holder).into(ivProfilePic);
            }
            etFirstName.setText(model.getFirstName());
            etLastName.setText(model.getLastName());

            etEmail.setText(model.getEmail());
            etPhone.setText(model.getMobileNumber());
            if (model.getCountryCode()!=null && !model.getCountryCode().isEmpty())
            {
                for (int i = 0; i < Arrays.asList(getResources().getStringArray(R.array.country_code)).size(); i++) {
                    if(model.getCountryCode().equals(Arrays.asList(getResources().getStringArray(R.array.country_code)).get(i)))
                    {
                   //     spCountryCode.setSelection(i);
                        break;
                    }
                }
            }

            etFuelStationId.setText(appComponent.getSpUtils().getFuelStationModel().getFuelStationId());
            etAddress.setText(model.getAddress());
            etPinCode.setText(model.getPincode());
            etLoginId.setText(model.getMobileNumber());

            if(model.getAltMobile()!=null && !model.getAltMobile().isEmpty())
            {
                cbAdditionalInfo.setVisibility(View.VISIBLE);

                tvAlternateMobile.setVisibility(View.VISIBLE);
                etAlternateMobile.setVisibility(View.VISIBLE);
                cbAdditionalInfo.setChecked(true);
                etAlternateMobile.setText(model.getAltMobile());

            }
            else
            {
                cbAdditionalInfo.setVisibility(View.GONE);
                tvAlternateMobile.setVisibility(View.GONE);
                etAlternateMobile.setVisibility(View.INVISIBLE);

            }

        }


        switch (/*appComponent.getSpUtils().getAccountType()*/
                SPUtils.ACCOUNT_TYPES.FSO
        ) {

            case DRV: {
                cbAdditionalInfo.setVisibility(View.VISIBLE);
                break;
            }
            case FSO: {

                tvFuelStationIdLabel.setVisibility(View.VISIBLE);
                etFuelStationId.setVisibility(View.VISIBLE);
                tvAddressLabel.setVisibility(View.VISIBLE);
                etAddress.setVisibility(View.VISIBLE);
                lytPinCode.setVisibility(View.VISIBLE);
                tvLoginId.setVisibility(View.VISIBLE);
                etLoginId.setVisibility(View.VISIBLE);

                break;
            }
            case OPR: {
                break;
            }
            case VCO: {

            }
        }

        changeView();
    }

    @OnClick({R.id.ivEdit, R.id.ivProfilePic})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivEdit: {
                if (isEdit) {
                    if (selectImage != null) {
                        resultUri = selectImage.getResultUri();
                        compressImage(((BitmapDrawable) ivProfilePic.getDrawable()).getBitmap());
                    }
                    validate();
                } else {
                    isEdit = true;
                    changeView();
                }
                break;
            }
            case R.id.ivProfilePic: {
                selectImage = new SelectImage(this, ivProfilePic);
                selectImage.showDialog();
                break;
            }
        }
    }

    public void compressImage(Bitmap bitmap) {
        if(resultUri==null || resultUri.getPath()==null)
            return;

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

    void changeView() {
        if (isEdit) {
            ivProfilePic.setClickable(true);


           /* if(model.getId()==null){
                final RequestBody LoginId = RequestBody.create(MediaType.parse("multipart/form-data"), model.getId());
            }else {
                 // final RequestBody LoginId = RequestBody.create(MediaType.parse("multipart/form-data"), model.getId());
            }*/

           /* Log.e("jjj","succ"+tvFirstNameLabel);
            tvFirstNameLabel.setText(userPreference.getFirsName());
            tvLastNameLabel.setText(userPreference.getLastName());
            tvAddressLabel.setText(userPreference.getAddress());
            tvEmailLabel.setText(userPreference.getEmailId());
            tvMobileLabel.setText(userPreference.getNumber());*/


            tvFirstNameLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvLastNameLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvMobileLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvEmailLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvCountryLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvLanguageLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvAlternateMobile.setTextColor(getResources().getColor(android.R.color.white));
            tvFuelStationIdLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvAddressLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvPinCodeLabel.setTextColor(getResources().getColor(android.R.color.white));
            tvLoginId.setTextColor(getResources().getColor(android.R.color.white));

            etFirstName.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etLastName.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
       //     spCountryCode.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etPhone.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etEmail.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
         //   spinnerCountry.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
         //   spinnerLanguage.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etAlternateMobile.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etFuelStationId.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etAddress.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etPinCode.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etLoginId.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
            etFirstName.setTextColor(getResources().getColor(R.color.blackDarker));
            etLastName.setTextColor(getResources().getColor(R.color.blackDarker));
            etPhone.setTextColor(getResources().getColor(R.color.blackDarker));
            etEmail.setTextColor(getResources().getColor(R.color.blackDarker));
            spCountryAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
            spLanguageAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
            spinnerAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
            etAlternateMobile.setTextColor(getResources().getColor(R.color.blackDarker));
            etFuelStationId.setTextColor(getResources().getColor(R.color.blackDarker));
            etAddress.setTextColor(getResources().getColor(R.color.blackDarker));
            etPinCode.setTextColor(getResources().getColor(R.color.blackDarker));
            etLoginId.setTextColor(getResources().getColor(R.color.blackDarker));

           // TextView view = (TextView) spCountryCode.getSelectedView();
          //  view.setTextColor(getResources().getColor(R.color.blackDarker));

           // TextView viewCountry = (TextView) spinnerCountry.getSelectedView();
           /* if(viewCountry!=null)
            {
                viewCountry.setTextColor(getResources().getColor(R.color.blackDarker));
            }*/

           /* TextView viewLanguage = (TextView) spinnerLanguage.getSelectedView();
            if(viewLanguage!=null)
            {
                viewLanguage.setTextColor(getResources().getColor(R.color.blackDarker));
            }*/
            cbAdditionalInfo.setVisibility(View.VISIBLE);
            viewToDiable.setVisibility(View.GONE);
            etFirstName.setCursorVisible(true);
            etEmail.setCursorVisible(true);
            etPinCode.setCursorVisible(true);
            etAlternateMobile.setCursorVisible(true);
            ivEdit.setImageResource(R.drawable.tick);
        } else {
            ivProfilePic.setClickable(false);
            tvFirstNameLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvLastNameLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvMobileLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvEmailLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvCountryLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvLanguageLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvAlternateMobile.setTextColor(getResources().getColor(R.color.blackLessText));
            tvAddressLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvFuelStationIdLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvPinCodeLabel.setTextColor(getResources().getColor(R.color.blackLessText));

            etFirstName.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
            etLastName.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
          //  spCountryCode.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
            etPhone.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
            etEmail.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
            etAlternateMobile.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
         //   spinnerCountry.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
         //   spinnerLanguage.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
            etFuelStationId.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
            etAddress.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
            etPinCode.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
            etLoginId.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));

            etFirstName.setTextColor(getResources().getColor(R.color.white));
            etLastName.setTextColor(getResources().getColor(R.color.white));
            etPhone.setTextColor(getResources().getColor(R.color.white));
            etEmail.setTextColor(getResources().getColor(R.color.white));
            etAlternateMobile.setTextColor(getResources().getColor(R.color.white));
            spCountryAdapter.setItemTextColor(getResources().getColor(R.color.white));
            spLanguageAdapter.setItemTextColor(getResources().getColor(R.color.white));
            spinnerAdapter.setItemTextColor(getResources().getColor(R.color.white));
            etFuelStationId.setTextColor(getResources().getColor(R.color.white));
            etAddress.setTextColor(getResources().getColor(R.color.white));
            etPinCode.setTextColor(getResources().getColor(R.color.white));
            etLoginId.setTextColor(getResources().getColor(R.color.white));

           /* TextView viewCountryCode = (TextView) spCountryCode.getSelectedView();
            if(viewCountryCode!=null)
            {
                viewCountryCode.setTextColor(Color.WHITE);
            }*/

            /*TextView viewCountry = (TextView) spinnerCountry.getSelectedView();
            if(viewCountry!=null)
            {
                viewCountry.setTextColor(Color.WHITE);
            }*/

           /* TextView viewLanguage = (TextView) spinnerLanguage.getSelectedView();
            if(viewLanguage!=null)
            {
                viewLanguage.setTextColor(Color.WHITE);
            }*/

            if (!cbAdditionalInfo.isChecked())
            {
                cbAdditionalInfo.setVisibility(View.GONE);
            }
            viewToDiable.setVisibility(View.VISIBLE);
            etFirstName.setCursorVisible(false);
            etEmail.setCursorVisible(false);
            etPinCode.setCursorVisible(false);
            etAlternateMobile.setCursorVisible(false);
            ivEdit.setImageResource(R.drawable.edit);



        }
    }

    void validate() {
        hideKB();
        String fname = etFirstName.getText().toString().trim();
        String lname = etLastName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
       // String fname;
        etFirstName.setText(userPreference.getFirsName());
        etLastName.setText(userPreference.getLastName());
        etPhone.setText(userPreference.getNumber());
        etEmail.setText(userPreference.getEmailId());



        if (fname.isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_first_name));
            return;
        }

        if (lname.isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_last_name));
            return;
        }


        if (phone.isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_mobile_number));
            return;
        }

        if (appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.OPR)
        {
            if (email.isEmpty()) {
                email = "";
            }
        }
        else
        {
            if (email.isEmpty()) {
                showMsg(rootLayout, getResources().getString(R.string.enter_email_address));
                return;
            }
        }

        String emailPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";

        Pattern pattern = Pattern.compile(emailPattern);

        Matcher matcher = pattern.matcher(email);

        if (!email.isEmpty() && !matcher.matches()) {
            showMsg(rootLayout, getString(R.string.please_enter_valid_email_id));
            etEmail.requestFocus();
            return;
        }

       /* if (spinnerCountry.getSelectedItemPosition() == 0) {
            showMsg(rootLayout, "Please select country");
            return;
        }*/

        MultipartBody.Part body = null;
        if (resultUri != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
            body = MultipartBody.Part.createFormData("image", "image.png", requestFile);
        }

//        final RequestBody img = RequestBody.create(MediaType.parse("image/*"), "");
//        MultipartBody.Part part = MultipartBody.Part.createFormData("image", "image.png", img);

        RequestBody Address = null;
        RequestBody Pincode = null;
        if (appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.FSO)
        {
            if (etAddress.getText().toString().trim().isEmpty())
            {
                showMsg(rootLayout, getResources().getString(R.string.please_enter_address));
                return;
            }

            if (etPinCode.getText().toString().trim().isEmpty())
            {
                showMsg(rootLayout, getResources().getString(R.string.please_enter_pincode));
                return;
            }

            if (etPinCode.getText().length()<6)
            {
                showMsg(rootLayout, getResources().getString(R.string.pincode_length));
                return;
            }
            if(Address!=null && Pincode!=null){
                Address = RequestBody.create(MediaType.parse("multipart/form-data"), etAddress.getText().toString());
                Pincode = RequestBody.create(MediaType.parse("multipart/form-data"), etPinCode.getText().toString());
            }
          //  Address = RequestBody.create(MediaType.parse("multipart/form-data"), etAddress.getText().toString());
          //  Pincode = RequestBody.create(MediaType.parse("multipart/form-data"), etPinCode.getText().toString());
        }
        else
        {
            if(Address!=null && Pincode!=null ){
                Address = RequestBody.create(MediaType.parse("multipart/form-data"), model.getAddress() == null ? "" : model.getAddress());
                Pincode = RequestBody.create(MediaType.parse("multipart/form-data"), model.getPincode()==null ? "" : model.getPincode());
            }
           // Address = RequestBody.create(MediaType.parse("multipart/form-data"), model.getAddress() == null ? "" : model.getAddress());
          //  Pincode = RequestBody.create(MediaType.parse("multipart/form-data"), model.getPincode()==null ? "" : model.getPincode());
        }

       if(model.getId()!=null){
           final RequestBody LoginId = RequestBody.create(MediaType.parse("multipart/form-data"), model.getId());
       }else {
         //  final RequestBody LoginId = RequestBody.create(MediaType.parse("multipart/form-data"), model.getId());
       }
     //   Log.e("kk","jjhjh"+ model.getId());

//        final RequestBody LoginId = RequestBody.create(MediaType.parse("multipart/form-data"), model.getId());
        final RequestBody firstName = RequestBody.create(MediaType.parse("multipart/form-data"), fname);
        final RequestBody lastName = RequestBody.create(MediaType.parse("multipart/form-data"), lname);
        final RequestBody Email = RequestBody.create(MediaType.parse("multipart/form-data"), email);
        final RequestBody MobileNumber = RequestBody.create(MediaType.parse("multipart/form-data"), phone);

     //   final RequestBody Country = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerCountry.getSelectedItem().toString());
     //   final RequestBody Language = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerLanguage.getSelectedItem().toString());

     //   final RequestBody CountryCode = RequestBody.create(MediaType.parse("multipart/form-data"), spCountryCode.getSelectedItem().toString());

        final RequestBody AlterMobile = RequestBody.create(MediaType.parse("multipart/form-data"), cbAdditionalInfo.isChecked() ? etAlternateMobile.getText().toString() : "");

     /*   appComponent.getServiceCaller().callService(appComponent.getAllApis().updateProfile(firstName,lastName, Email, MobileNumber, Address,
                Country,CountryCode, Language, Pincode, AlterMobile, body), listener);*/

        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
      //  this.country = spinnerCountry.getSelectedItem().toString();
    //    this.language = spinnerLanguage.getSelectedItem().toString();
        this.pinCode = etPinCode.getText().toString().trim();
        this.address = etAddress.getText().toString().trim();

    }



    String firstName, lastName, email, country, language, pinCode, address;

    @Override
    public void retry() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectImage.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        selectImage.onRequestPermissionChange(requestCode, permissions, grantResults);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView spinner = (TextView) view;
        if (spinner!=null) {
            if (isEdit) {
                spinner.setTextColor(getResources().getColor(R.color.blackDarker));
            } else {
                spinner.setTextColor(Color.WHITE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    NetworkListener getOperatorsListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<OperatorsResponseModel> operatorsList = (List<OperatorsResponseModel>) responseBody.getResutData();
                tvValue.setText(operatorsList.size()+"");
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
