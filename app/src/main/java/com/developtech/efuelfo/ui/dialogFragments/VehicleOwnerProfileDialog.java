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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.responseModel.CreditAgreementResponseModel;
import com.developtech.efuelfo.model.responseModel.VehicleOwnerResponseModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 12/26/17.
 */

public class VehicleOwnerProfileDialog extends DialogFragment {

    @BindView(R.id.tvMobile)
    TextView tvMobile;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvCompanyNameLabel)
    TextView tvCompanyNameLabel;

    @BindView(R.id.tvCompanyName)
    TextView tvCompanyName;

    @BindView(R.id.viewCompany)
    View viewCompany;

    private View view;
    private AlertDialog alertDialog;
    private CreditAgreementResponseModel caModel;
    private AppComponent appComponent;

    VehicleOwnerResponseModel ownerModel;

    public void setData(CreditAgreementResponseModel creditAgreementResponseModel,VehicleOwnerResponseModel ownerModel, AppComponent appComponent) {
        this.caModel = creditAgreementResponseModel;
        this.appComponent = appComponent;
        this.ownerModel = ownerModel;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_vehicle_owner_detail, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        setData();
        alertDialog = builder.create();
        return alertDialog;
    }

    void setData() {
        if (ownerModel!=null) {
            tvName.setText(ownerModel.getFirstName() + " " + ownerModel.getLastName());
            tvMobile.setText(ownerModel.getMobileNumber());
            tvEmail.setText(ownerModel.getEmail());
            if (ownerModel.getImage() != null && !ownerModel.getImage().isEmpty()) {
                Picasso.with(getContext()).load(appComponent.getUtilFunctions().getImageFullUrl(ownerModel.getImage())).into(ivImg);
            }

            tvAddress.setText(ownerModel.getAddress());
            if (ownerModel.getCompanyName()!=null && !ownerModel.getCompanyName().isEmpty())
            {
                tvCompanyNameLabel.setVisibility(View.VISIBLE);
                tvCompanyName.setVisibility(View.VISIBLE);
                viewCompany.setVisibility(View.VISIBLE);
                tvCompanyName.setText(ownerModel.getCompanyName());
            }
        }
        else if (caModel!=null)
        {
            tvName.setText(caModel.getVehicleOwner().getFirstName() + " " + caModel.getVehicleOwner().getLastName());
            tvMobile.setText(caModel.getVehicleOwner().getMobileNumber());
            tvEmail.setText(caModel.getVehicleOwner().getEmail());
            if (caModel.getVehicleOwner().getImage() != null && !caModel.getVehicleOwner().getImage().isEmpty()) {
                Picasso.with(getContext()).load(appComponent.getUtilFunctions().getImageFullUrl(caModel.getVehicleOwner().getImage())).into(ivImg);
            }

            tvAddress.setText(caModel.getVehicleOwner().getAddress());
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
