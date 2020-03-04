package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.app.Dialog;
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
import android.view.Window;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.listeners.FilterUpdateListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.responseModel.CreditAgreementResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.CreditAgreementPagerAdapter;
import com.developtech.efuelfo.ui.dialogFragments.FilterDriverDialogFragment;
import com.developtech.efuelfo.ui.dialogFragments.FilterVehicleDialogFragment;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentHistoryFragment extends BaseFragment implements ViewPager.OnPageChangeListener, FilterUpdateListener{

    View view;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    PendingTransactionFragment pendingTransactionFragment;
    HistoryTransactionFragment historyTransactionFragment;
    CreditAgreementPagerAdapter adapter;
    private List<PaymentHistoryResponseModel> pendingList = new ArrayList<>();
    private List<PaymentHistoryResponseModel> completeList = new ArrayList<>();

    HomeActivity homeActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        homeActivity = (HomeActivity) context;
    }

    NetworkListener getPendngTransaction = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                pendingList = (List<PaymentHistoryResponseModel>) responseBody.getResutData();
                pendingTransactionFragment.setData(pendingList);
            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            hideProgress();
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };
    NetworkListener getCompleteTransaction = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                completeList = (List<PaymentHistoryResponseModel>) responseBody.getResutData();
                historyTransactionFragment.setData(completeList);
            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            hideProgress();
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };

    public PaymentHistoryFragment() {
        // Required empty public constructor
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
        pendingTransactionFragment = new PendingTransactionFragment();
        historyTransactionFragment = new HistoryTransactionFragment();
        fragmentList.add(pendingTransactionFragment);
        fragmentList.add(historyTransactionFragment);

        adapter = new CreditAgreementPagerAdapter(getChildFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        view_pager.setOffscreenPageLimit(2);
        tab_layout.setupWithViewPager(view_pager);
        view_pager.addOnPageChangeListener(this);

        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
        if(appComponent.getSpUtils().getFuelStationModel()!=null) {
            requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
            appComponent.getServiceCaller().callService(appComponent.getAllApis().vcoCompleteTransaction(requestModel),
                    getCompleteTransaction);
        }

        appComponent.getServiceCaller().callService(appComponent.getAllApis().vcoPendingTransaction(),
                getPendngTransaction);

    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {
        if(getContext()!=null)
        {
            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_filter_options);

            CustomTextView tvFilterVehicle = dialog.findViewById(R.id.tvFilterVehicle);
            CustomTextView tvFilterDriver = dialog.findViewById(R.id.tvFilterDriver);

            tvFilterVehicle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FilterVehicleDialogFragment dialogFragment = new FilterVehicleDialogFragment();
                    dialogFragment.setData(getContext(), completeList, PaymentHistoryFragment.this);
                    dialogFragment.show(getChildFragmentManager(), null);
                    dialog.dismiss();
                }
            });

            tvFilterDriver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FilterDriverDialogFragment dialogFragment = new FilterDriverDialogFragment();
                    dialogFragment.setData(getContext(), completeList, PaymentHistoryFragment.this);
                    dialogFragment.show(getChildFragmentManager(), null);
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==1)
        {
            homeActivity.showFilter(true);
        }
        else
        {
            homeActivity.showFilter(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPause() {
        super.onPause();
        homeActivity.showFilter(false);
    }

    @Override
    public void updateList(List<PaymentHistoryResponseModel> filteredList) {
        historyTransactionFragment.setData(filteredList);
    }

    @Override
    public void updateData(List<CreditAgreementResponseModel> filterList) {

    }

    @Override
    public void clearFilter() {
        historyTransactionFragment.setData(completeList);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(view_pager.getCurrentItem()==1)
        {
            homeActivity.showFilter(true);
        }
    }
}
