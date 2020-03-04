package com.developtech.efuelfo.ui.fragments.vehicleOwner;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.DriverVehiclePagerAdapter;
import com.developtech.efuelfo.ui.fragments.AddDriverFragment;
import com.developtech.efuelfo.ui.fragments.AddVehicleFragment;
import com.developtech.efuelfo.ui.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverVehicleFragment extends BaseFragment {

    View view;

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;

    @BindView(R.id.view_pager)
    ViewPager view_pager;

    DriverVehiclePagerAdapter adapter;
    AddVehicleFragment addVehicleFragment;


    public DriverVehicleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_driver_vehicle, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new AddVehicleFragment());
        fragmentList.add(new AddDriverFragment());
        adapter = new DriverVehiclePagerAdapter(getChildFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        view_pager.setOffscreenPageLimit(2);
        tab_layout.setupWithViewPager(view_pager);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }
}
