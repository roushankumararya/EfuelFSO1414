package com.developtech.efuelfo.ui.fragments.fuelStaff;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.developtech.efuelfo.ui.activities.vehicleOwner.UserProfileActivity;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.HomeAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.ParkTransactionFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.SaleHistoryInvoicesFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.SaleInitiationFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.VerifyTransactionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaffHomeFragment extends BaseFragment implements VehicleOwnerItemClick {

    @BindView(R.id.recycleItems)
    RecyclerView recycleItems;

    private HomeAdapter homeAdapter;
    private View view;
    private List<VehicleOwnerHomeModel> list;

    private HomeActivity homeActivity;

    public StaffHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity = (HomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeActivity.handleNoti();
        homeActivity.showOperatorManager();

        recycleItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        list = new ArrayList<>();
        setData();
        homeAdapter = new HomeAdapter(getContext(), list, this);
        recycleItems.setAdapter(homeAdapter);

    }

    public void setData() {
        VehicleOwnerHomeModel model = new VehicleOwnerHomeModel();
        model.setTitle(getString(R.string.fuel_price));
        model.setDrawable(R.drawable.fuel_price_home);
        list.add(model);

        VehicleOwnerHomeModel model1 = new VehicleOwnerHomeModel();
        model1.setTitle(getString(R.string.verifytransaction));
        model1.setDrawable(R.drawable.verify_trasaction);
        list.add(model1);

        VehicleOwnerHomeModel model2 = new VehicleOwnerHomeModel();
        model2.setTitle(getString(R.string.saleHistoryandInvoices));
        model2.setDrawable(R.drawable.paymenthistory_home);
        list.add(model2);

        VehicleOwnerHomeModel model4 = new VehicleOwnerHomeModel();
        model4.setTitle(getString(R.string.saleinitiation));
        model4.setDrawable(R.drawable.refuel_home);
        list.add(model4);

        VehicleOwnerHomeModel model5 = new VehicleOwnerHomeModel();
        model5.setTitle("My Profile");
        model5.setDrawable(R.drawable.myprofile_home);
        list.add(model5);

        VehicleOwnerHomeModel model6 = new VehicleOwnerHomeModel();
        model6.setTitle(getString(R.string.park_transaction));
        model6.setDrawable(R.drawable.paymenthistory_home);
        list.add(model6);
    }


    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onCLick(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: {
                if (homeActivity.isOpratorManager) {
                    fragment = new com.developtech.efuelfo.ui.fragments.fuelOwner.FuelPricesFragment();
                } else {
                    fragment = new com.developtech.efuelfo.ui.fragments.fuelStaff.FuelPricesFragment();
                }

                homeActivity.setHomeTitle(getString(R.string.fuel_price));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_operator_fuelprice);
                break;
            }
            case 1: {
                if (!appComponent.getSpUtils().getFuelStationModel().getFuelStationVerified()) {
                    showMsg(getString(R.string.station_not_verified));
                    return;
                }
                fragment = new VerifyTransactionFragment();
                homeActivity.setHomeTitle(getString(R.string.verifytransaction));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_operator_verify_trans);

                break;
            }
            case 2: {

                fragment = new SaleHistoryInvoicesFragment();
                homeActivity.setHomeTitle(getString(R.string.transaction_log));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_operator_saleshistory);
                break;
            }
            case 3: {
                if (!appComponent.getSpUtils().getFuelStationModel().getFuelStationVerified()) {
                    showMsg(getString(R.string.station_not_verified));
                    return;
                }
                fragment = new SaleInitiationFragment();
                homeActivity.setHomeTitle(getString(R.string.sale_initiation_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_operator_initiatesale);
                break;
            }
            case 4: {
                newIntent(UserProfileActivity.class, null, false);
                break;
            }
            case 5: {
                fragment = new ParkTransactionFragment();
                homeActivity.setHomeTitle(getString(R.string.park_transaction_caps));
                homeActivity.setHomeImage(true);
                break;
            }
        }

        homeActivity.pushFragment(fragment);
    }

    @Override
    public void onPause() {
        super.onPause();

        homeActivity.showNotification(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        homeActivity.showNotification(true);
    }
}
