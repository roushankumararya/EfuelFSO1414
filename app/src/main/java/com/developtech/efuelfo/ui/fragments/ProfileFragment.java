package com.developtech.efuelfo.ui.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.responseModel.CountriesResponseModel;
import com.developtech.efuelfo.model.responseModel.LanguageResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.fuelStation.StationRegistrationActivity;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter3;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter4;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.SelectImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {

    private View view;

    @BindView(R.id.relSelectImage)
    RelativeLayout relSelectImage;

    @BindView(R.id.etFirstName)
    EditText etFirstName;

    @BindView(R.id.etLastName)
    EditText etLastName;

    @BindView(R.id.spinnerCode)
    Spinner spinnerCode;

    @BindView(R.id.etMobileNumber)
    EditText etMobileNumber;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etLoginId)
    EditText etLoginId;

    @BindView(R.id.etAddress)
    EditText etAddress;

   // @BindView(R.id.etPincode)
    EditText etPincode;

   // @BindView(R.id.spinnerLanguage)
    Spinner spinnerLanguage;

  //  @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;

    @BindView(R.id.cbAdditionalInfo)
    CheckBox cbAdditionalInfo;

    @BindView(R.id.etAlternateMobile)
    EditText etAlternateMobile;

    @BindView(R.id.ivProfilePic)
    ImageView ivProfilePic;

  //  @BindView(R.id.lytAdditionalInfo)
    LinearLayout lytAdditionalInfo;

//    @BindView(R.id.lytMobileLoginId)
    FrameLayout lytMobileLoginId;


    String loginId, name, email, mobileNumber, address, country, pincode, image;
    NetworkListener listener;

    SelectImage selectImage;
    Uri resultUri = null;
    File imageFile;

    SpinnerAdapter3 spCountryAdapter, spLanguageAdapter;
    ArrayList<String> spCountryList = new ArrayList<>();
    ArrayList<String> spLanguageList = new ArrayList<>();
    ArrayList<CountriesResponseModel> countriesList;
    ArrayList<LanguageResponseModel> languagesList;

    SpinnerAdapter4 spinnerAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public void setListener(NetworkListener listener) {
        this.listener = listener;
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> arrCode = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_code)));
        spinnerAdapter = new SpinnerAdapter4(getContext(), android.R.layout.simple_spinner_dropdown_item, arrCode);
        spinnerCode.setAdapter(spinnerAdapter);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().getCountriesList(), getCountriesListener);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getLanguages(), getLanguagesListener);

        spCountryList.add("Select Country");
        spLanguageList.add("Select Language");

        spCountryAdapter = new SpinnerAdapter3(appComponent.getContext(), android.R.layout.simple_spinner_item, spCountryList);
        spLanguageAdapter = new SpinnerAdapter3(appComponent.getContext(), android.R.layout.simple_spinner_item, spLanguageList);
        spinnerCountry.setAdapter(spCountryAdapter);
        spinnerLanguage.setAdapter(spLanguageAdapter);

        spinnerCountry.setOnItemSelectedListener(this);
        spinnerLanguage.setOnItemSelectedListener(this);
        spCountryAdapter.setItemTextColor(Color.BLACK);
        spLanguageAdapter.setItemTextColor(Color.BLACK);

        if(spinnerCode.getSelectedView()!=null) {
            spinnerCode.getSelectedView().setEnabled(false);
        }
        spinnerCode.setEnabled(false);

        if (spinnerLanguage.getSelectedView()!=null)
        {
            TextView textView = (TextView) spinnerLanguage.getSelectedView();
            textView.setTextColor(Color.BLACK);
        }



        setData();

        cbAdditionalInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    lytAdditionalInfo.setVisibility(View.VISIBLE);
                }
                else
                {
                    lytAdditionalInfo.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setData() {

        lytMobileLoginId.setVisibility(View.VISIBLE);
        if (appComponent.getSpUtils()!=null &&appComponent.getSpUtils().getUserData()!=null){
            etFirstName.setText(appComponent.getSpUtils().getUserData().getFirstName());
            etLastName.setText(appComponent.getSpUtils().getUserData().getLastName());
            etEmail.setText(appComponent.getSpUtils().getEmail());
            etLoginId.setText(appComponent.getSpUtils().getMobileNo());
            etMobileNumber.setText(appComponent.getSpUtils().getMobileNo());
        }

    }

    @OnClick({R.id.relSelectImage})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.relSelectImage: {
                selectImage = new SelectImage(getContext(), ivProfilePic);
                ((StationRegistrationActivity)getActivity()).setSelectImage(selectImage);
                break;
            }
        }
    }

    public boolean checkValidation(Context context) {
        hideKB();
        loginId = etLoginId.getText().toString().trim();
        mobileNumber = etMobileNumber.getText().toString().trim();
        String fName = etFirstName.getText().toString().trim();
        String lName = etLastName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        address = etAddress.getText().toString().trim();
        pincode = etPincode.getText().toString().trim();

        if (loginId.isEmpty()) {
            showMsg(view, getString(R.string.enter_login_id));
            etLoginId.requestFocus();
            return false;
        }
        if (mobileNumber.isEmpty()) {
            showMsg(view, getString(R.string.enter_mobile_number));
            etMobileNumber.requestFocus();
            return false;
        }
        if (fName.isEmpty()) {
            showMsg(view, getString(R.string.first_name));
            etFirstName.requestFocus();
            return false;
        }

        if (lName.isEmpty()) {
            showMsg(view, getString(R.string.last_name));
            etLastName.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            showMsg(view, getString(R.string.enter_email));
            etEmail.requestFocus();
            return false;
        }

        String emailPatternStr = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";

        Pattern emailPattern = Pattern.compile(emailPatternStr);

        Matcher emailMatcher = emailPattern.matcher(email);

        if (!emailMatcher.matches()) {
            showMsg(rootLayout, getString(R.string.please_enter_valid_email_id));
            etEmail.requestFocus();
            return false;
        }

        if (address.isEmpty()) {
            showMsg(view, getString(R.string.enter_the_address));
            etAddress.requestFocus();
            return false;
        }
        if (pincode.isEmpty()) {
            showMsg(view, getString(R.string.please_enter_pincode));
            etPincode.requestFocus();
            return false;
        }

        if (pincode.length()!=6)
        {
            showMsg(view, getString(R.string.pincode_length));
            etPincode.requestFocus();
            return false;
        }

        if(spinnerCountry.getSelectedItemPosition()==0)
        {
            showMsg(view, getString(R.string.select_country));
            spinnerCountry.requestFocus();
            return false;
        }

        if(spinnerLanguage.getSelectedItemPosition()==0)
        {
            showMsg(view, getString(R.string.select_your_language));
            spinnerLanguage.requestFocus();
            return false;
        }

        if (selectImage != null) {
            resultUri = ((StationRegistrationActivity)getActivity()).getResultUri();
            if(resultUri!=null)
            {
                compressImage(((BitmapDrawable) ivProfilePic.getDrawable()).getBitmap());
            }
        }

        MultipartBody.Part part = null;

        if (resultUri != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
            part = MultipartBody.Part.createFormData("image", "image.png", requestFile);
        }

        final RequestBody LoginId = RequestBody.create(MediaType.parse("multipart/form-data"), loginId);
        final RequestBody firstName = RequestBody.create(MediaType.parse("multipart/form-data"), fName);
        final RequestBody lastName = RequestBody.create(MediaType.parse("multipart/form-data"), lName);
        final RequestBody Email = RequestBody.create(MediaType.parse("multipart/form-data"), email);
        final RequestBody MobileNumber = RequestBody.create(MediaType.parse("multipart/form-data"), mobileNumber);
        final RequestBody Address = RequestBody.create(MediaType.parse("multipart/form-data"), address);
        final RequestBody Country = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerCountry.getSelectedItem().toString());
        final RequestBody CountryCode = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerCode.getSelectedItem().toString());

        final RequestBody Language = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerLanguage.getSelectedItem().toString());
        final RequestBody Pincode = RequestBody.create(MediaType.parse("multipart/form-data"), pincode);
        final RequestBody AlterMobile = RequestBody.create(MediaType.parse("multipart/form-data"), cbAdditionalInfo.isChecked() ? etAlternateMobile.getText().toString() : "");

        appComponent.getServiceCaller().callService(appComponent.getAllApis().updateProfile( firstName, lastName, Email, MobileNumber, Address,
                Country, CountryCode, Language, Pincode, AlterMobile, part), listener);

        return true;

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

                spinnerCountry.setSelection(1);

                spCountryAdapter.setItemTextColor(Color.BLACK);
                spLanguageAdapter.setItemTextColor(Color.BLACK);

//                for (int i = 0; i < spCountryList.size(); i++) {
//                    if (model.getCountry().equalsIgnoreCase(spCountryList.get(i))) {
//                        spinnerCountry.setSelection(i);
//                        break;
//                    }
//                }
//
//                if (isEdit) {
//                    spCountryAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
//                } else {
//                    spCountryAdapter.setItemTextColor(getResources().getColor(R.color.white));
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

                spinnerLanguage.setSelection(1);

                spCountryAdapter.setItemTextColor(Color.BLACK);
                spLanguageAdapter.setItemTextColor(Color.BLACK);
            }

//            for (int i = 0; i < spLanguageList.size(); i++) {
//                if (model.getLanguage().equalsIgnoreCase(spLanguageList.get(i))) {
//                    spinnerLanguage.setSelection(i);
//                    break;
//                }
//            }
//
//            if (isEdit) {
//                spLanguageAdapter.setItemTextColor(getResources().getColor(R.color.blackDarker));
//            } else {
//                spLanguageAdapter.setItemTextColor(getResources().getColor(R.color.white));
//            }


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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tvItem = (TextView) view;
        tvItem.setTextColor(Color.BLACK);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
