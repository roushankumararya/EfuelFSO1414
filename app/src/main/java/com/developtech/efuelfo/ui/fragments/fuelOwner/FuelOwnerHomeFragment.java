package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.developtech.efuelfo.ui.adapters.vehicleOwner.HomeAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.CreditAgreementFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuelOwnerHomeFragment extends BaseFragment implements VehicleOwnerItemClick {

    @BindView(R.id.recycleItems)
    RecyclerView recycleItems;

    private HomeAdapter homeAdapter;
    private View view;
    private List<VehicleOwnerHomeModel> list;

    private HomeActivity homeActivity;
    public FuelOwnerHomeFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();

        homeActivity.showNotification(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        homeActivity.showNotification(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity = (HomeActivity) context;
    }

    public static FuelOwnerHomeFragment newInstance(String param1, String param2) {
        FuelOwnerHomeFragment fragment = new FuelOwnerHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeActivity.handleNoti();

        recycleItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        list = new ArrayList<>();
        setData();
        homeAdapter = new HomeAdapter(getContext(), list, this);
        recycleItems.setAdapter(homeAdapter);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    public void setData() {
        VehicleOwnerHomeModel model = new VehicleOwnerHomeModel();
        model.setTitle(getString(R.string.Fuelprices));
        model.setDrawable(R.drawable.fuel_price_home);
        list.add(model);

        VehicleOwnerHomeModel model1 = new VehicleOwnerHomeModel();
        model1.setTitle(getString(R.string.saleinitiation));
        model1.setDrawable(R.drawable.sale_initiation_home);
        list.add(model1);

        VehicleOwnerHomeModel model2 = new VehicleOwnerHomeModel();
        model2.setTitle(getString(R.string.operators));
        model2.setDrawable(R.drawable.myoperators_home);
        list.add(model2);

        VehicleOwnerHomeModel model3 = new VehicleOwnerHomeModel();
        model3.setTitle(getString(R.string.verifytransaction));
        model3.setDrawable(R.drawable.verify_trasaction);
        list.add(model3);

        VehicleOwnerHomeModel model4 = new VehicleOwnerHomeModel();
        model4.setTitle(getString(R.string.creditagreement));
        model4.setDrawable(R.drawable.total_sales_home);
        list.add(model4);

        VehicleOwnerHomeModel model5 = new VehicleOwnerHomeModel();
        model5.setTitle(getString(R.string.park_transaction));
        model5.setDrawable(R.drawable.mydrivers_vehicles_home);
        list.add(model5);
    }

    @Override
    public void onCLick(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: {
                fragment = new FuelPricesFragment();
                homeActivity.setHomeTitle(getString(R.string.fuel_prices_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_station_ow_fuel_prices);
                break;
            }
            case 1: {


              /* if (!appComponent.getSpUtils().getFuelStationModel().getFuelStationVerified()) {
                    showMsg(getString(R.string.station_not_verified));
                    return;
                }*/
                fragment = new SaleInitiationFragment();
                homeActivity.setHomeTitle(getString(R.string.sale_initiation_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_station_ow_sale_init);
                break;
            }
            case 2: {
                fragment = new OperatorsFragment();
                homeActivity.setHomeTitle(getString(R.string.operators_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_station_ow_operator);
                break;
            }
            case 3: {
              /*  if (!appComponent.getSpUtils().getFuelStationModel().getFuelStationVerified()) {
                    showMsg(getString(R.string.station_not_verified));
                    return;
                }*/
                fragment = new VerifyTransactionFragment();
                homeActivity.setHomeTitle(getString(R.string.verifytransaction_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_station_ow_verify_transaction);
                break;
            }
            case 4: {
                fragment = new CreditAgreementFragment();
                homeActivity.setHomeTitle(getString(R.string.creditagreement));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_station_ow_credit_agreement_list);
                break;
            }
            case 5: {
                fragment = new ParkTransactionFragment();
                homeActivity.setHomeTitle(getString(R.string.park_transaction_caps));
                homeActivity.setHomeImage(true);
                homeActivity.navigationView.setCheckedItem(R.id.nav_station_ow_park_trans);
            }
        }

        homeActivity.pushFragment(fragment);
    }
}
