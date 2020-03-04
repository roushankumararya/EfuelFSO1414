package com.developtech.efuelfo.ui.activities.stationOwner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.adapters.stationOwner.ViewSchedulesAdapter;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewScheduleActivity extends MyActionBar implements OnItemClickListener{

    @BindView(R.id.rvSchedules)
    RecyclerView rvSchedules;

    @BindView(R.id.btnCreateSchdule)
    Button btnCreateSchdule;

    ViewSchedulesAdapter adapter;

    List<SchedulesResponseModel> schedulesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedules);
        ButterKnife.bind(this);
        init();
        initComponents();
        setHomeTitle(getString(R.string.fuel_prices_caps));
        setMenuItem();
        setHomeImage(true);
        showNotification(false);
    }

    @Override
    public void initComponents() {
        AddFuelStationRequestModel requestModel = new AddFuelStationRequestModel();
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().findSchedules(requestModel), getSchedulesListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSchedules.setLayoutManager(layoutManager);
    }

    @Override
    public void retry() {

    }

    @OnClick(R.id.btnCreateSchdule)
    public void onCLick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateSchdule: {
                newIntent(FuelPriceActivity.class, null, false);
                break;
            }
        }
    }

    NetworkListener getSchedulesListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                schedulesList = (List<SchedulesResponseModel>) responseBody.getResutData();
                adapter = new ViewSchedulesAdapter(schedulesList, appComponent, ViewScheduleActivity.this);
                rvSchedules.setAdapter(adapter);
                if (schedulesList.size()>0) {
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


    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", schedulesList.get(position));
        newIntent(FuelPriceActivity.class, bundle, false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter!=null)
        {
            AddFuelStationRequestModel requestModel = new AddFuelStationRequestModel();
            requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
            appComponent.getServiceCaller().callService(appComponent.getAllApis().findSchedules(requestModel), getSchedulesListener);
        }
    }
}

