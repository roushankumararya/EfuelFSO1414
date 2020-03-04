package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.ui.activities.stationOwner.FuelPriceActivity;
import com.developtech.efuelfo.ui.fragments.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewScheduleFragment extends BaseFragment {


    View view;

    public ViewScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_view_schedule, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }


    @OnClick(R.id.btnCreateSchdule)
    public void onCLick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateSchdule: {
                newIntent(FuelPriceActivity.class, null, true);
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
