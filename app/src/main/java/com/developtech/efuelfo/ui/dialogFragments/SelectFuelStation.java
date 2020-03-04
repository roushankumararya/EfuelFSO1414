package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.StationOwnerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 2/8/18.
 */

public class SelectFuelStation extends DialogFragment implements VehicleOwnerItemClick {

    @BindView(R.id.recycleStationList)
    RecyclerView recycleStationList;

    View view;
    private AlertDialog alertDialog;
    StationOwnerAdapter stationsAdapter;
    List<GetFuelStationResponseModel> fuelStationList = new ArrayList<>();
    AppComponent appComponent;
    Context context;

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

    public void setData(AppComponent appComponent, List<GetFuelStationResponseModel> fuelStationList, Context context)
    {
        this.appComponent = appComponent;
        this.context = context;
        this.fuelStationList = fuelStationList;
    }

    void initComponents()
    {

        stationsAdapter = new StationOwnerAdapter(fuelStationList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recycleStationList.setLayoutManager(layoutManager);
        recycleStationList.setAdapter(stationsAdapter);
    }

    @Override
    public void onCLick(int position) {
        appComponent.getSpUtils().saveFuelStation(fuelStationList.get(position));
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }


}
