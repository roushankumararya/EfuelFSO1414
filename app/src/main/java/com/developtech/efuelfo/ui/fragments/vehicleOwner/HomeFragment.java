package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.VehicleOwnerHomeModel;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.HomeAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements VehicleOwnerItemClick {

    @BindView(R.id.recycleItems)
    RecyclerView recycleItems;

    private HomeAdapter homeAdapter;
    private View view;
    private List<VehicleOwnerHomeModel> list;
    private HomeActivity homeActivity;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    public void initComponents() {
        recycleItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        list = new ArrayList<>();
        setData();
        homeAdapter = new HomeAdapter(getContext(), list, this);
        recycleItems.setAdapter(homeAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity = (HomeActivity) context;
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onPause() {
        super.onPause();
        homeActivity.showNotification(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (homeAdapter != null) {
            homeActivity.showNotification(true);
        }
    }

    public void setData() {
        VehicleOwnerHomeModel model = new VehicleOwnerHomeModel();
        model.setTitle(getString(R.string.refuel));
        model.setDrawable(R.drawable.refuel_home);
        list.add(model);

        VehicleOwnerHomeModel model1 = new VehicleOwnerHomeModel();
        model1.setTitle(getString(R.string.MyDriversandVehicles));
        model1.setDrawable(R.drawable.mydrivers_vehicles_home);
        list.add(model1);

        VehicleOwnerHomeModel model2 = new VehicleOwnerHomeModel();
        model2.setTitle(getString(R.string.TrackDriver));
        model2.setDrawable(R.drawable.track_driver_home);
        list.add(model2);

        VehicleOwnerHomeModel model3 = new VehicleOwnerHomeModel();
        model3.setTitle(getString(R.string.Nearbyfuelpump));
        model3.setDrawable(R.drawable.nearbyfuelpump_home);
        list.add(model3);

        VehicleOwnerHomeModel model4 = new VehicleOwnerHomeModel();
        model4.setTitle(getString(R.string.PaymentHistoryandInvoices));
        model4.setDrawable(R.drawable.paymenthistory_home);
        list.add(model4);

        VehicleOwnerHomeModel model5 = new VehicleOwnerHomeModel();
        model5.setTitle(getString(R.string.MyCalist));
        model5.setDrawable(R.drawable.total_sales_home);
        list.add(model5);
    }

    @Override
    public void onCLick(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: {
                fragment = new RefuelFragment();
                homeActivity.setHomeTitle(getString(R.string.refuel_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_refuel);
                break;
            }
            case 1: {
                fragment = new DriverVehicleFragment();
                homeActivity.setHomeTitle(getString(R.string.myvehicleanddrivers));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_driver_vehicle);
                break;
            }
            case 2: {
                fragment = new TrackDriverFragment();
                homeActivity.setHomeTitle(getString(R.string.track_driver));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_track_driver);
                break;
            }
            case 3: {
                fragment = new NearByFuelStationFragment();
                homeActivity.setHomeTitle(getString(R.string.near_by_st));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_near_by_fuel_station);
                break;
            }
            case 4: {
                fragment = new PaymentHistoryFragment();
                homeActivity.setHomeTitle(getString(R.string.transactions_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_payment_history);
                break;
            }
            case 5: {
                fragment = new CreditAgreementFragment();
                homeActivity.setHomeTitle(getString(R.string.creditagreement));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_credit_agreements);
                break;
            }
        }

        homeActivity.pushFragment(fragment);
    }
}
