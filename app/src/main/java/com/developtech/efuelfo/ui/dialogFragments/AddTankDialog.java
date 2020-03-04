package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.requestModel.CreateTankRequestModel;
import com.developtech.efuelfo.model.responseModel.ExtraNotificationModel;
import com.developtech.efuelfo.model.responseModel.TankResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter4;
import com.developtech.efuelfo.ui.fragments.fuelOwner.TankTypeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by dt on 1/4/18.
 */

public class AddTankDialog extends DialogFragment implements AdapterView.OnItemSelectedListener{

    @BindView(R.id.spinnerFuelType)
    Spinner spinnerFuelType;

    @BindView(R.id.etTankName)
    EditText etTankName;

    @BindView(R.id.etFuelCapacity)
    EditText etFuelCapacity;

    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;

    @BindView(R.id.etDensity)
    EditText etDensity;

    @BindView(R.id.etTemperature)
    EditText etTemperature;

    @BindView(R.id.btnAddTank)
    Button btnAddTank;

    @BindView(R.id.btnDeleteTank)
    Button btnDeleteTank;

    @BindView(R.id.lytSaveChanges)
    LinearLayout lytSaveChanges;

    private View view;
    private AlertDialog alertDialog;

    AppComponent appComponent;

    SpinnerAdapter4 spFuelTypeAdapter;

    NetworkListener createTankListener, deleteListener, updateListener;

    TankTypeFragment parent;

    TankResponseModel tankResponseModel;

    public void setData(AppComponent appComponent, NetworkListener createTankListener, NetworkListener updateListener, TankTypeFragment parent, TankResponseModel tankResponseModel, NetworkListener deleteListener)
    {
        this.appComponent = appComponent;
        this.createTankListener = createTankListener;
        this.parent = parent;
        this.tankResponseModel = tankResponseModel;
        this.deleteListener = deleteListener;
        this.updateListener = updateListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_tank, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        init(view);
        return alertDialog;
    }

    void init(View view)
    {
        List<String> spFuelType = appComponent.getSpUtils().getFuelStationModel().getFuelCompany().getFuelType();
        spFuelTypeAdapter = new SpinnerAdapter4(appComponent.getContext(), R.layout.item_simple_spinner, spFuelType);
        spinnerFuelType.setAdapter(spFuelTypeAdapter);
        spinnerFuelType.setOnItemSelectedListener(this);

        if (tankResponseModel!=null)
        {
            for (int i = 0; i < spFuelType.size(); i++) {
                if (tankResponseModel.getFuelType().equalsIgnoreCase(spFuelType.get(i)))
                {
                    spinnerFuelType.setSelection(i);
                    break;
                }
            }

            etTankName.setText(tankResponseModel.getTankName());
            etFuelCapacity.setText(tankResponseModel.getFuelCapacity());
            etDensity.setText(tankResponseModel.getDensity());
            etTemperature.setText(tankResponseModel.getTemperature());

            btnAddTank.setVisibility(View.GONE);
            btnDeleteTank.setVisibility(View.VISIBLE);
            lytSaveChanges.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.btnAddTank, R.id.btnSaveChanges, R.id.btnDeleteTank, R.id.btnCancel})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnAddTank : {
                validate();
                break;
            }
            case R.id.btnSaveChanges : {
                validate();
                break;
            }
            case R.id.btnDeleteTank :
            {
                ExtraNotificationModel requestModel = new ExtraNotificationModel();
                requestModel.setRequestId(tankResponseModel.getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().deleteTank(requestModel), deleteListener);
                dismiss();
                break;
            }
            case R.id.btnCancel:
            {
                dismiss();
                break;
            }
        }
    }

    void validate() {
        parent.hideKB();
        if (etTankName.getText().toString().trim().isEmpty()) {
            parent.showMsg(getContext().getResources().getString(R.string.please_enter_tank_name));
            return;
        }

        if (etFuelCapacity.getText().toString().trim().isEmpty()) {
            parent.showMsg( getContext().getResources().getString(R.string.please_enter_fuel_capacity));
            return;
        }

        if (etDensity.getText().toString().trim().isEmpty()) {
            parent.showMsg(getContext().getResources().getString(R.string.please_enter_fuel_density));
            return;
        }

        String temperature = etTemperature.getText().toString().trim();

        if (tankResponseModel == null) {
            CreateTankRequestModel tankRequestModel = new CreateTankRequestModel();
            tankRequestModel.setFuelType(spinnerFuelType.getSelectedItem().toString());
            tankRequestModel.setTankName(etTankName.getText().toString());
            tankRequestModel.setFuelCapacity(etFuelCapacity.getText().toString());
            tankRequestModel.setDensity(etDensity.getText().toString());
            tankRequestModel.setTemperature(temperature.isEmpty() ? "0" : temperature);
            tankRequestModel.setFuelStation(appComponent.getSpUtils().getFuelStationModel().getId());

            appComponent.getServiceCaller().callService(appComponent.getAllApis().createTank(tankRequestModel), createTankListener);
        }
        else
        {
            CreateTankRequestModel tankRequestModel = new CreateTankRequestModel();
            tankRequestModel.setFuelType(spinnerFuelType.getSelectedItem().toString());
            tankRequestModel.setTankName(etTankName.getText().toString());
            tankRequestModel.setFuelCapacity(etFuelCapacity.getText().toString());
            tankRequestModel.setDensity(etDensity.getText().toString());
            tankRequestModel.setTemperature(temperature.isEmpty() ? "0" : temperature);
            tankRequestModel.setRequestId(tankResponseModel.getId());

            appComponent.getServiceCaller().callService(appComponent.getAllApis().updateTank(tankRequestModel), updateListener);
        }
        dismiss();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) view;
        textView.setTextColor(Color.WHITE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
