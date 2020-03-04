package com.developtech.efuelfo.ui.dialogFragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.customs.CustomButton;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.RoundedImageView;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.FindOperatorRequestModel;
import com.developtech.efuelfo.model.requestModel.OperatorBlockRequestModel;
import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.developtech.efuelfo.model.requestModel.UpdateOperatorRequestModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter4;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.SelectImage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by developtech on 1/4/18.
 */

public class AddOperatorDialog extends android.support.v4.app.DialogFragment implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.btnAddOperator)
    CustomButton btnAddOperator;

    @BindView(R.id.btnDeleteOperator)
    CustomButton btnDeleteOperator;

    @BindView(R.id.lytSaveChanges)
    LinearLayout lytSaveChanges;

    @BindView(R.id.etFirstName)
    CustomEditText etFirstName;

    @BindView(R.id.etLastName)
    CustomEditText etLastName;

    @BindView(R.id.etMobileNumber)
    CustomEditText etMobileNumber;

    @BindView(R.id.etEmail)
    CustomEditText etEmail;

    @BindView(R.id.addoperatormenu)
    LinearLayout rootLayout;

    @BindView(R.id.ivOperatorProfile)
    RoundedImageView ivOperatorProfile;

    @BindView(R.id.spinnerCode)
    Spinner spinnerCode;

    @BindView(R.id.lytOverlayName)
    View lytOverlayName;

    @BindView(R.id.lytOverlayMobile)
    View lytOverlayMobile;

    @BindView(R.id.lytOverlayBottom)
    View lytOverlayBottom;

    @BindView(R.id.progress_bar)
    FrameLayout progress;

    @BindView(R.id.switchManager)
    Switch switchManager;

    @BindView(R.id.lytManager)
    LinearLayout lytManager;

    @BindView(R.id.ivDelete)
    ImageView ivDelete;

    @BindView(R.id.lnrBlockSection)
    LinearLayout lnrBlockSection;

    @BindView(R.id.btnBlock)
    Button btnBlock;

    private AppComponent appComponent;
    private NetworkListener listener, updateListener, blockOprListener;

    boolean isAdd;

    private View view;
    private AlertDialog alertDialog;
    Bundle bundle;

    SelectImage selectImage;
    File imageFile;
    Uri resultUri = null;
    HomeActivity homeActivity;

    OperatorsResponseModel operatorModel;

    SpinnerAdapter4 spinnerAdapter;

    ArrayList<String> arrCode;
    boolean isMax;

    public AddOperatorDialog() {

    }

    public void setData(AppComponent component, NetworkListener networkListener, NetworkListener updateListener, NetworkListener blockOperatorListener, OperatorsResponseModel operatorModel, boolean isAdd, Activity activity) {
        this.appComponent = component;
        this.listener = networkListener;
        this.blockOprListener = blockOperatorListener;
        this.isAdd = isAdd;
        homeActivity = (HomeActivity) activity;
        this.operatorModel = operatorModel;
        this.updateListener = updateListener;
//        if (operatorModel != null && operatorModel.getBlockedFuelStation().indexOf(appComponent.getSpUtils().getFuelStationModel().getId()) > -1) {
//            operatorModel.setBlocked(true);
//        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_operator, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        initComponents(view);
        return alertDialog;
    }

    void initComponents(View view) {
        arrCode = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_code)));
        spinnerAdapter = new SpinnerAdapter4(appComponent.getContext(), android.R.layout.simple_spinner_dropdown_item, arrCode);
        spinnerCode.setAdapter(spinnerAdapter);
        spinnerCode.setOnItemSelectedListener(this);

        if (isAdd) {
            btnAddOperator.setVisibility(View.VISIBLE);
            ivDelete.setVisibility(View.GONE);
            lnrBlockSection.setVisibility(View.GONE);
        } else {
            btnAddOperator.setVisibility(View.GONE);
            if (operatorModel != null && appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.OPR) {
                boolean isManager = false;
                for (OperatorsResponseModel model : appComponent.getSpUtils().getFuelStationModel().getOperator()) {
                    if (model.id.equals(operatorModel.id)) {
                        isManager = model.isManager();
                    }
                }
                if (operatorModel.isManager() && isManager) {
                    ivDelete.setVisibility(View.GONE);
                    lytSaveChanges.setVisibility(View.GONE);
                }
            } else {
                ivDelete.setVisibility(View.VISIBLE);
                lytSaveChanges.setVisibility(View.VISIBLE);
            }

        }

        if (operatorModel != null) {
            bindData(operatorModel, false);
        }

        if (appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.OPR) {
            lytManager.setVisibility(View.GONE);
        }


        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!isMax && charSequence.toString().trim().length() == 10) {
                    isMax = true;
                    FindOperatorRequestModel requestModel = new FindOperatorRequestModel(charSequence.toString());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().findOperatorByNumber(requestModel), findOperatorListener);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void bindData(OperatorsResponseModel operatorModel, boolean search) {
        etFirstName.setText(operatorModel.getFirstName());
        etLastName.setText(operatorModel.getLastName());

        etMobileNumber.setText(operatorModel.getMobileNumber());
        if (operatorModel.getEmail() != null && !operatorModel.getEmail().isEmpty()) {
            etEmail.setText(operatorModel.getEmail());
        }

        if (operatorModel.getImage() != null && !operatorModel.getImage().isEmpty()) {
            Picasso.with(homeActivity).load(appComponent.getAllUrls().BASE_IMAGE_URL + operatorModel.getImage()).placeholder(R.drawable.place_holder).into(ivOperatorProfile);
        }

        if (operatorModel.getCountryCode() != null) {
            for (int i = 0; i < arrCode.size(); i++) {
                if (operatorModel.getCountryCode().equals(arrCode.get(i))) {
                    spinnerCode.setSelection(i);
                    break;
                }
            }
        }

        lytOverlayName.setVisibility(View.VISIBLE);
        lytOverlayBottom.setVisibility(View.VISIBLE);

        if (search) {
            lytOverlayMobile.setVisibility(View.GONE);
        } else {
            lytOverlayMobile.setVisibility(View.VISIBLE);
        }

        switchManager.setChecked(operatorModel.isManager());

        ivOperatorProfile.setClickable(false);
        etFirstName.setCursorVisible(false);
        if (operatorModel.isBlocked()) {
            btnBlock.setText("Unblock");
        } else {
            btnBlock.setText("block");
        }

        isMax = false;
    }


    @OnClick({R.id.btnAddOperator, R.id.ivOperatorProfile, R.id.ivDelete, R.id.btnSaveChanges, R.id.btnCancel, R.id.btnBlock})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddOperator: {
                if (selectImage != null) {
                    resultUri = homeActivity.getResultUri();
                    if (resultUri != null) {
                        compressImage(((BitmapDrawable) ivOperatorProfile.getDrawable()).getBitmap());
                    }
                }
                vaidate(etFirstName.getText().toString(), etLastName.getText().toString(), etMobileNumber.getText().toString(), etEmail.getText().toString());
                break;
            }
            case R.id.ivOperatorProfile: {
                if (homeActivity == null)
                    break;

                selectImage = new SelectImage(getContext(), ivOperatorProfile);
                homeActivity.setSelectImage(selectImage);
                break;
            }
            case R.id.ivDelete: {
                showDeleteOperatorDialog();
                break;
            }
            case R.id.btnBlock: {
                showBlockOperatorDialog();
                break;
            }
            case R.id.btnSaveChanges: {

                updateOperator();
                dismiss();
                break;
            }
            case R.id.btnCancel: {
                dismiss();
                break;
            }
        }
    }

    private void updateOperator() {
        UpdateOperatorRequestModel requestModel = new UpdateOperatorRequestModel();
        requestModel.setId(operatorModel.getId());
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        requestModel.setManager(switchManager.isChecked());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().updateOperator(requestModel), updateListener);
    }


    void vaidate(String fname, String lname, String mobile, String email) {
        if (fname.trim().isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_first_name));
            return;
        }

        if (lname.trim().isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_last_name));
            return;
        }

        if (mobile.trim().isEmpty()) {
            showMsg(rootLayout, getResources().getString(R.string.enter_mobile_number));
            etMobileNumber.requestFocus();
            return;
        }

        if (mobile.trim().length() < 10) {
            showMsg(rootLayout, getResources().getString(R.string.please_enter_10_digits_mobile_number));
            etMobileNumber.requestFocus();
            return;
        }

        if (!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMsg(view, getString(R.string.please_enter_valid_email_id));
            etEmail.requestFocus();
            return;
        }

        MultipartBody.Part body = null;
        if (imageFile != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
            body = MultipartBody.Part.createFormData("image", "image.png", requestFile);
        }

        RequestBody requestEmail = RequestBody.create(MediaType.parse("multipart/form-data"), email);


        final RequestBody requestFirstName = RequestBody.create(MediaType.parse("multipart/form-data"), fname);
        final RequestBody requestLastName = RequestBody.create(MediaType.parse("multipart/form-data"), lname);
        final RequestBody requestMobile = RequestBody.create(MediaType.parse("multipart/form-data"), mobile);
        final RequestBody requestCountryCode = RequestBody.create(MediaType.parse("multipart/form-data"), spinnerCode.getSelectedItem().toString());
        final RequestBody fuelStationId = RequestBody.create(MediaType.parse("multipart/form-data"), appComponent.getSpUtils().getFuelStationModel().getId());
        final RequestBody isManager = RequestBody.create(MediaType.parse("multipart/form-data"), switchManager.isChecked() + "");


        appComponent.getServiceCaller().callService(appComponent.getAllApis().addOperator(requestFirstName, requestLastName, requestEmail, requestMobile, requestCountryCode, fuelStationId, isManager, body), listener);
        dismiss();
    }


    @SuppressLint("NewApi")
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


    void showBlockOperatorDialog() {
        final Dialog dialog = new Dialog(homeActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_general);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvDesc = dialog.findViewById(R.id.tvDesc);
        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);
        int title = R.string.block_operator;
        String txt = "Block";
        String status = "block";

        if (this.operatorModel != null && this.operatorModel.isBlocked()) {
            txt = "UnBlock";
            status = "unblock";
            title = R.string.un_block_operator;
        }

        tvTitle.setText(getResources().getString(title));

        tvDesc.setText("Are you sure you want to " + txt + " this operator?");

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = "block";

                if (operatorModel != null && operatorModel.isBlocked()) {

                    status = "unblock";
                }
                OperatorBlockRequestModel requestModel = new OperatorBlockRequestModel();

                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                requestModel.setOperatorId(operatorModel.id);
                requestModel.setStatus(status);
                appComponent.getServiceCaller().callService(appComponent.getAllApis().blockUnblockOPRFuelStation(requestModel), blockOprListener);
                dialog.dismiss();
                dismiss();
            }
        });

        dialog.show();
    }


    void showDeleteOperatorDialog() {
        final Dialog dialog = new Dialog(homeActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_general);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvDesc = dialog.findViewById(R.id.tvDesc);
        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        tvTitle.setText(getResources().getString(R.string.delete_operator));
        tvDesc.setText("Are you sure you want to delete this operator?");

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDriverRequestModel requestModel = new DeleteDriverRequestModel(operatorModel.getId());
                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().deleteOperator(requestModel), listener);
                dialog.dismiss();
                dismiss();
            }
        });

        dialog.show();
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

    NetworkListener findOperatorListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                OperatorsResponseModel operatorModel = (OperatorsResponseModel) responseBody.getResutData();
                if (operatorModel.getId() != null) {
                    bindData(operatorModel, true);
                }
                etMobileNumber.setSelection(etMobileNumber.getText().length());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            progress.setVisibility(View.GONE);
        }

        @Override
        public void onStart() {
            progress.setVisibility(View.VISIBLE);
        }
    };

    public void setBlockListener(NetworkListener blockOperatorListener) {
        this.blockOprListener = blockOperatorListener;
    }
}
