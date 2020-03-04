package com.developtech.efuelfo.ui.fragments.vehicleDriver;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.stationOwner.PaymentHistoryAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DriverTransactionFragment extends BaseFragment implements VehicleOwnerItemClick, OnItemClickListener {

    @BindView(R.id.recycleItems)
    RecyclerView recycleItems;
    private PaymentHistoryAdapter adapter;
    private View view;
    private List<PaymentHistoryResponseModel> modelList;
    NetworkListener listenerTransaction = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<PaymentHistoryResponseModel> list = (List<PaymentHistoryResponseModel>) responseBody.getResutData();
                modelList.addAll(list);
                adapter.refreshData(modelList);
            } else
                showMsg(view, responseBody.getMessage());
        }

        @Override
        public void onError(String msg) {
            showMsg(view, msg);
        }

        @Override
        public void onComplete() {
            if(getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                }
            });
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };

    public DriverTransactionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        init(view);
        initComponents();
        return view;
    }

    public void initComponents() {
        recycleItems.setLayoutManager(new LinearLayoutManager(getContext()));
        modelList = new ArrayList<>();
        adapter = new PaymentHistoryAdapter(modelList, appComponent, this);
        recycleItems.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callApi();
    }

    public void callApi() {
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getDriverTransaction(), listenerTransaction);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onCLick(int position) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
