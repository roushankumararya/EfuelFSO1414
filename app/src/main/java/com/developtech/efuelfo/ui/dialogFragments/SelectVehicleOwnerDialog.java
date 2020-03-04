package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.VehicleOwnerResponseModel;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.SelectFuelOwnerDialogAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 12/26/17.
 */

public class SelectVehicleOwnerDialog extends DialogFragment implements VehicleOwnerItemClick {

    public AppComponent appComponent;
    @BindView(R.id.recycleStationList)
    RecyclerView recycleStationList;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private SelectFuelOwnerDialogAdapter adapter;
    private View view;
    private AlertDialog alertDialog;
    private String page;
    private Bundle bundle;
    private List<VehicleOwnerResponseModel> vehicleOwnerResponseModels;
    private List<GetFuelStationResponseModel> allFuelStationList;

    public static SelectVehicleOwnerDialog newInstance(String page, List<VehicleOwnerResponseModel> modelList) {
        SelectVehicleOwnerDialog owner = new SelectVehicleOwnerDialog();
        Bundle bundle = new Bundle();
        bundle.putString("page", page);
        bundle.putSerializable("model", (Serializable) modelList);
        owner.setArguments(bundle);
        return owner;
    }

    public void setData(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_fuel_station_owner, null);
        ButterKnife.bind(this, view);
        initComponents();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        return alertDialog;
    }


    public void initComponents() {
        bundle = getArguments();
        if (bundle != null) {
            page = bundle.getString("page");
        }

        recycleStationList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (page != null && page.equalsIgnoreCase("driver"))
        {
            tvTitle.setText(getString(R.string.please_select_vehicle_owner));
            vehicleOwnerResponseModels = (List<VehicleOwnerResponseModel>) bundle.getSerializable("model");
            adapter = new SelectFuelOwnerDialogAdapter(getContext(), this, vehicleOwnerResponseModels);
            recycleStationList.setAdapter(adapter);
        }
        else if(page != null && page.equalsIgnoreCase("fso"))
        {
            tvTitle.setText(getString(R.string.please_select_fuelstation));
        }

    }

    @Override
    public void onCLick(int position) {
        appComponent.getSpUtils().saveVehicleOwnerResponse(vehicleOwnerResponseModels.get(position));
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
