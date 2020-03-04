package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by developtech on 1/25/18.
 */

public class VehicleDetailDialog extends android.support.v4.app.DialogFragment {

    private View view;
    private AlertDialog alertDialog;

    @BindView(R.id.tvVehicleMake)
    TextView tvVehicleMake;

    @BindView(R.id.tvVehicleModel)
    TextView tvVehicleModel;

    @BindView(R.id.tvVehicleNumber)
    TextView tvVehicleNumber;

    @BindView(R.id.tvVehicleColor)
    TextView tvVehicleColor;

    @BindView(R.id.tvFuelType)
    TextView tvFuelType;

    @BindView(R.id.tvfuelCapacity)
    TextView tvfuelCapacity;

    @BindView(R.id.tvName)
    TextView tvName;

    PaymentHistoryResponseModel paymentHistoryModel;

    AllVehicleResponseModel vehicleModel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_vehicle_details, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();

        if(getArguments()!=null)
        {
            Bundle bundle = getArguments();
            if (bundle.getSerializable("model")!=null)
            {
                paymentHistoryModel = (PaymentHistoryResponseModel) bundle.getSerializable("model");
                setData();
            }
            else if(bundle.getSerializable("online")!=null)
            {
                vehicleModel = (AllVehicleResponseModel) bundle.getSerializable("online");
                bindData();
            }



        }

        return alertDialog;
    }

    public void setData()
    {
        AllVehicleResponseModel model = paymentHistoryModel.getVehicle();
        tvName.setText(model.getAlias());
        tvVehicleMake.setText(model.getCompany());
        tvfuelCapacity.setText(model.getFuelCapacity());
        tvFuelType.setText(model.getFuelType());
        tvVehicleColor.setText(model.getColor());
        tvVehicleModel.setText(model.getModel());
        tvVehicleNumber.setText(model.getVehicleNumber());
    }

    public void bindData()
    {
        tvName.setText(vehicleModel.getAlias());
        tvVehicleMake.setText(vehicleModel.getCompany());
        tvfuelCapacity.setText(vehicleModel.getFuelCapacity());
        tvFuelType.setText(vehicleModel.getFuelType());
        tvVehicleColor.setText(vehicleModel.getColor());
        tvVehicleModel.setText(vehicleModel.getModel());
        tvVehicleNumber.setText(vehicleModel.getVehicleNumber());
    }
}
