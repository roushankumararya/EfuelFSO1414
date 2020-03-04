package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.content.Context;
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
import com.developtech.efuelfo.listeners.FilterUpdateListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.responseModel.CreditAgreementResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.CreditAgreementPagerAdapter;
import com.developtech.efuelfo.ui.dialogFragments.FilterStationDialogFragment;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditAgreementFragment extends BaseFragment implements ViewPager.OnPageChangeListener , FilterUpdateListener {

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    CreditAgreementPagerAdapter adapter;
    List<CreditAgreementResponseModel> pendingModelList = new ArrayList<>();
    List<CreditAgreementResponseModel> historyModelList = new ArrayList<>();
    PendingFragment pendingFragment;
    HistoryFragment historyFragment;
    private View view;

    HomeActivity homeActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        homeActivity = (HomeActivity) context;
    }

    public CreditAgreementFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_credit_agreement, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents();
    }

    public void initComponents() {
        List<Fragment> fragmentList = new ArrayList<>();
        pendingFragment = new PendingFragment();
        historyFragment = new HistoryFragment();
        fragmentList.add(pendingFragment);
        fragmentList.add(historyFragment);
        adapter = new CreditAgreementPagerAdapter(getChildFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        view_pager.setOffscreenPageLimit(2);
        tab_layout.setupWithViewPager(view_pager);
        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();

        if (appComponent.getSpUtils().getFuelStationModel()!=null && appComponent.getSpUtils().getFuelStationModel().getId()!=null)
        {
            requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        }

        view_pager.addOnPageChangeListener(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        homeActivity.showFilter(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (adapter!=null)
        {
            SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
            if (appComponent.getSpUtils().getFuelStationModel()!=null && appComponent.getSpUtils().getFuelStationModel().getId()!=null)
            {
                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
            }



        }

        if(view_pager.getCurrentItem()==1)
        {
            if (appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.VCO) {
                homeActivity.showFilter(true);
            }
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {
        FilterStationDialogFragment dialogFragment = new FilterStationDialogFragment();
        dialogFragment.setData(getContext(), historyModelList, CreditAgreementFragment.this);
        dialogFragment.show(getChildFragmentManager(), null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if (appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.VCO) {
            
            if (position == 1) {
                homeActivity.showFilter(true);
            } else {
                homeActivity.showFilter(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void updateList(List<PaymentHistoryResponseModel> filteredList) {

    }

    @Override
    public void updateData(List<CreditAgreementResponseModel> filterList) {
        historyFragment.setData(filterList);
    }

    @Override
    public void clearFilter() {
        historyFragment.setData(historyModelList);
    }

    public void refreshHistoryFrag()
    {
        historyFragment.page = 0;
        historyFragment.historyModelList.clear();
        historyFragment.callApi();
    }
}
