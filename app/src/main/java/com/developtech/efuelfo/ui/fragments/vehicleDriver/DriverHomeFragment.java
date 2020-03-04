package com.developtech.efuelfo.ui.fragments.vehicleDriver;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.VehicleOwnerHomeModel;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.UserProfileActivity;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.HomeAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.NearByFuelStationFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.RefuelFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DriverHomeFragment extends BaseFragment implements VehicleOwnerItemClick {


    @BindView(R.id.recycleItems)
    RecyclerView recycleItems;

    private HomeAdapter homeAdapter;
    private View view;
    private List<VehicleOwnerHomeModel> list;
    private HomeActivity homeActivity;

    public DriverHomeFragment() {

    }

    public static DriverHomeFragment newInstance(String param1, String param2) {
        DriverHomeFragment fragment = new DriverHomeFragment();
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

    public void setData() {
        VehicleOwnerHomeModel model = new VehicleOwnerHomeModel();
        model.setTitle(getString(R.string.refuel));
        model.setDrawable(R.drawable.refuel_home);
        list.add(model);

        VehicleOwnerHomeModel model1 = new VehicleOwnerHomeModel();
        model1.setTitle(getString(R.string.PaymentHistoryandInvoicesCaps));
        model1.setDrawable(R.drawable.paymenthistory_home);
        list.add(model1);

        VehicleOwnerHomeModel model3 = new VehicleOwnerHomeModel();
        model3.setTitle(getString(R.string.Nearbyfuelpump));
        model3.setDrawable(R.drawable.nearbyfuelpump_home);
        list.add(model3);

        VehicleOwnerHomeModel model4 = new VehicleOwnerHomeModel();
        model4.setTitle(getString(R.string.MyProfile));
        model4.setDrawable(R.drawable.myprofile_home);
        list.add(model4);
    }

    @Override
    public void onCLick(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: {
                fragment = new RefuelFragment();
                homeActivity.setHomeTitle(getString(R.string.refuel_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_driver_refuel);
                break;
            }
            case 1: {
                fragment = new DriverTransactionFragment();
                homeActivity.setHomeTitle(getString(R.string.transaction_log));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_driver_payment_history);
                break;
            }
            case 2: {
                fragment = new NearByFuelStationFragment();
                homeActivity.setHomeTitle(getString(R.string.near_by_st));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_driver_nearbyfuel);
                break;
            }
            case 3: {
                newIntent(UserProfileActivity.class, null, false);
                break;
            }
        }
        if (fragment == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }
}

