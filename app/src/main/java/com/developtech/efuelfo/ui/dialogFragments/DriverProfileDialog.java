package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;

import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.responseModel.DriverResponseModel;

import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.model.responseModel.OnlineTransactionsResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 12/26/17.
 */

public class DriverProfileDialog extends DialogFragment {

    @BindView(R.id.tvMobile)
    TextView tvMobile;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvVehicleNumber)
    TextView tvVehicleNumber;
    @BindView(R.id.tvLimit)
    TextView tvApprovedLimit;

    @BindView(R.id.tvName)
    TextView tvName;

    private View view;
    private AlertDialog alertDialog;
    private AppComponent appComponent;
    private DriverResponseModel driverResponseModel;
    private OnlineTransactionsResponseModel onlineModel;

    public void setData(AppComponent appComponent, DriverResponseModel driverResponseModel, OnlineTransactionsResponseModel onlineModel) {
        this.appComponent = appComponent;
        this.driverResponseModel = driverResponseModel;
        this.onlineModel = onlineModel;
    }

    DriverResponseModel driverModel;
    PaymentHistoryResponseModel paymentHistoryModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_driver_info, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        setData();
        alertDialog = builder.create();

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            paymentHistoryModel = (PaymentHistoryResponseModel) bundle.getSerializable("model");

            setData();
        }

        return alertDialog;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setData() {

        if (paymentHistoryModel != null && paymentHistoryModel.getDriver() != null) {
            driverModel = paymentHistoryModel.getDriver();
            tvName.setText(driverModel.getFirstName() + " " + driverModel.getLastName());
            tvMobile.setText(driverModel.getMobileNumber());
            tvEmail.setText(driverModel.getEmail());
            tvVehicleNumber.setText(paymentHistoryModel.getVehicle().getVehicleNumber());
        } else if (onlineModel != null) {
            GetDriverResponseModel driverModel = onlineModel.getVehicle().getDriver();
            tvName.setText(driverModel.getFirstName() + " " + driverModel.getLastName());
            tvMobile.setText(driverModel.getMobileNumber());
            tvEmail.setText(driverModel.getEmail());
            tvVehicleNumber.setText(onlineModel.getVehicle().getVehicleNumber());
        } else if (paymentHistoryModel != null && paymentHistoryModel.getVehicle().getDriver() != null) {
            GetDriverResponseModel driverModel = paymentHistoryModel.getVehicle().getDriver();
            tvName.setText(driverModel.getFirstName() + " " + driverModel.getLastName());
            tvMobile.setText(driverModel.getMobileNumber());
            tvEmail.setText(driverModel.getEmail());
            tvVehicleNumber.setText(paymentHistoryModel.getVehicle().getVehicleNumber());
        }
        String mobile = tvMobile.getText().toString().trim();
        if (mobile.length() >= 10) {
            String mobileWithStar = "xxxxxx" + mobile.substring(6);
            tvMobile.setText(mobileWithStar);
        }

    }

    public void dismissDialog() {
        Fragment fragment = getFragmentManager().findFragmentByTag("view_owner");
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
}
