package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.requestModel.TankTypeModel;
import com.developtech.efuelfo.model.requestModel.TankTypeUpdateRequest;
import com.developtech.efuelfo.model.responseModel.TankResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.stationOwner.TankTypeAdapter;
import com.developtech.efuelfo.ui.dialogFragments.AddTankDialog;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TankTypeFragment extends BaseFragment implements OnItemClickListener{


    @BindView(R.id.recycleTankType)
    RecyclerView recycleTankType;

    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    @BindView(R.id.fabAddTankType)
    FloatingActionButton fabAddTankType;

    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;

    TankTypeAdapter adapter;

    View view;

    List<TankResponseModel> tankList = new ArrayList<>();

    public TankTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tank_type, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().findTank(requestModel), findTankListener);

        recycleTankType.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TankTypeAdapter(tankList, this);
        recycleTankType.setAdapter(adapter);
    }

    @OnClick({R.id.fabAddTankType, R.id.btnUpdate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabAddTankType: {
                AddTankDialog tankDialog = new AddTankDialog();
                tankDialog.setData(appComponent, createTankListener, updateTankListener,this, null, deleteListener);
                tankDialog.show(getChildFragmentManager(), AddTankDialog.class.getName());
                break;
            }
            case R.id.btnUpdate: {
                hideKB();
                List<TankResponseModel> tankList = adapter.getTankTypeList();

                for (TankResponseModel tankModel : tankList)
                {
                    if (tankModel.getOFQty().trim().isEmpty() || tankModel.getCFQty().trim().isEmpty())
                    {
                        showMsg(rootLayout, getResources().getString(R.string.fill_all_values));
                        return;
                    }
                }

                List<TankTypeModel> requestList = new ArrayList<>();

                for (TankResponseModel tankModel : tankList)
                {
                    TankTypeModel model = new TankTypeModel();
                    model.setId(tankModel.getId());
                    model.setCFQty(Float.parseFloat(tankModel.getCFQty()));
                    model.setOFQty(Float.parseFloat(tankModel.getOFQty()));
                    requestList.add(model);
                }

                TankTypeUpdateRequest request = new TankTypeUpdateRequest(requestList);
                appComponent.getServiceCaller().callService(appComponent.getAllApis().updateTankType(request), updateListener);
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

    NetworkListener findTankListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                tankList = (List<TankResponseModel>) responseBody.getResutData();
                adapter.updateList(tankList);
                if (tankList.size()>0) {
                    hideNoRecords();
                }
                else
                {
                    showNoRecords();
                }
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if(getActivity()==null)
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

    NetworkListener createTankListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {

                SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().findTank(requestModel), findTankListener);
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if(getActivity()==null)
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
            hideKB();
            showProgress();
        }
    };

    NetworkListener updateTankListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {

                SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().findTank(requestModel), findTankListener);
                showMsg(rootLayout, getResources().getString(R.string.tank_type_updated));
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if(getActivity()==null)
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
            hideKB();
            showProgress();
        }
    };


    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId())
        {
            case R.id.ivEditTankType: {

                AddTankDialog tankDialog = new AddTankDialog();
                tankDialog.setData(appComponent, createTankListener,updateTankListener, this, tankList.get(position), deleteListener);
                tankDialog.show(getChildFragmentManager(), AddTankDialog.class.getName());

                break;
            }
        }
    }

    NetworkListener deleteListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().findTank(requestModel), findTankListener);
                showMsg(rootLayout, getResources().getString(R.string.fuel_tank_deleted));
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if(getActivity()==null)
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

    NetworkListener updateListener = new NetworkListener() {
            @Override
            public void onSuccess(ResultModel<?> responseBody) {
                if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                    showMsg(rootLayout, getResources().getString(R.string.tank_type_updated));
                }
            }

            @Override
            public void onError(String msg) {

            }

            @Override
            public void onComplete() {
                if (getActivity()!=null)
                {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgress();
                        }
                    });
                }
            }

            @Override
            public void onStart() {
                showProgress();
            }
        };
}
