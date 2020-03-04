package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.requestModel.CreditRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.network.NetworkListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dt on 12/26/17.
 */

public class RequestCreditDialog extends DialogFragment {

    @BindView(R.id.etCreditLimit)
    EditText etCreditLimit;
    @BindView(R.id.etDuration)
    EditText etDuration;
    @BindView(R.id.cbTerms)
    CheckBox cbTerms;
    @BindView(R.id.btnSendRequest)
    Button btnSendRequest;
    String creditLimit, creditDuration = "";
    CreditRequestModel creditRequestModel;
    private View view;
    private AlertDialog alertDialog;
    private AppComponent appComponent;
    private FuelStationResponseModel fuelStationResponseModel;
    private NetworkListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_request_credit, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        return alertDialog;
    }

    public void setData(AppComponent component, NetworkListener networkListener, FuelStationResponseModel fuelStationResponseModel) {
        this.appComponent = component;
        this.fuelStationResponseModel = fuelStationResponseModel;
        this.listener = networkListener;
    }

    @OnClick({R.id.btnSendRequest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendRequest: {
                checkValidation();
                break;
            }
        }
    }

    public void checkValidation() {
        creditLimit = etCreditLimit.getText().toString().trim();
        creditDuration = etDuration.getText().toString().trim();

        if (creditLimit.isEmpty()) {
            showMsg(view, getString(R.string.enter_credit_limit));
            etCreditLimit.requestFocus();
            return;
        }
        if (creditDuration.isEmpty()) {
            showMsg(view, getString(R.string.enter_credit_dur));
            etDuration.requestFocus();
            return;
        }

        if(!cbTerms.isChecked())
        {
            showMsg(view, getString(R.string.please_accept));
            return;
        }

        creditRequestModel = new CreditRequestModel();
        creditRequestModel.setCreditLimit(creditLimit);
        creditRequestModel.setDuration(creditDuration);
        creditRequestModel.setFuelStation(fuelStationResponseModel.getId());
        creditRequestModel.setRemainingCredits("50");
        appComponent.getServiceCaller().callService(appComponent.getAllApis().requestCredit(creditRequestModel), listener);
        dismissDialog();
    }

    public void dismissDialog() {
        Fragment fragment = getFragmentManager().findFragmentByTag("request_credit");
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
