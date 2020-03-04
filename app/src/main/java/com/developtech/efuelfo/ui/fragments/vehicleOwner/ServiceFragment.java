package com.developtech.efuelfo.ui.fragments.vehicleOwner;

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
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.responseModel.GetServiceResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.vehicleOwner.AddExpenseActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.ExpenseDetailActivity;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.ServicesAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceFragment extends BaseFragment implements VehicleOwnerItemClick {

    @BindView(R.id.rvExpense)
    RecyclerView rvExpense;
    private ServicesAdapter adapter;
    private List<GetServiceResponseModel> servicesList = new ArrayList<>();
    private View view;

    public ServiceFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expense, container, false);
        ButterKnife.bind(this, view);
        init(view);
        initComponents();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ServicesAdapter(getContext(), this, servicesList);
        rvExpense.setLayoutManager(new LinearLayoutManager(getContext()));
        rvExpense.setAdapter(adapter);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getServices(), getSerivesListener);
    }

    public void initComponents() {

    }

    @OnClick(R.id.fabAddExpense)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabAddExpense: {
                Bundle bundle = new Bundle();
                bundle.putString("type", AddExpenseActivity.ADD_TYPE_ACTIVITY.SERVICE.toString());
                newIntent(AddExpenseActivity.class, bundle, false);
            }
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onCLick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("type", ExpenseDetailActivity.ADD_DETAIL_TYPE.SERVICE.toString());
        bundle.putSerializable("service", servicesList.get(position));
        newIntent(ExpenseDetailActivity.class, bundle, false);
    }

    NetworkListener getSerivesListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                servicesList = (List<GetServiceResponseModel>) responseBody.getResutData();
                adapter.refreshData(servicesList);
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(view, msg);
        }

        @Override
        public void onComplete() {
            if (getActivity() == null)
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

    @Override
    public void onResume() {
        super.onResume();

        if(adapter!=null)
        {
            appComponent.getServiceCaller().callService(appComponent.getAllApis().getServices(), getSerivesListener);
        }
    }
}
