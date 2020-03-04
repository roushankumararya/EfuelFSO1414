package com.developtech.efuelfo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.dialogFragments.AddFuelStationDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFuelStation extends BaseFragment {
    View view;

    @BindView(R.id.fabAddFuelStation)
    FloatingActionButton fabAddFuelStation;
    private NetworkListener listener;

    public AddFuelStation() {

    }

    public void setListener(NetworkListener listener) {
        this.listener = listener;
    }

    public static AddFuelStation newInstance(String param1, String param2) {
        AddFuelStation fragment = new AddFuelStation();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_fuel_station, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @OnClick(R.id.fabAddFuelStation)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabAddFuelStation: {
                AddFuelStationDialog dialog = new AddFuelStationDialog();
                dialog.setData(listener,null, appComponent,null, null);
                dialog.show(getFragmentManager(), "add_fuel_station");
                break;
            }
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }
}
