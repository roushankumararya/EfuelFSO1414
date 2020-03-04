package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.stationOwner.FuelPriceActivity;
import com.developtech.efuelfo.ui.activities.stationOwner.ViewScheduleActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.FuelPricesAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuelPricesFragment extends BaseFragment {

    View view;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvTime)
    TextView tvTime;

    @BindView(R.id.frameRefresh)
    FrameLayout frameRefresh;

    @BindView(R.id.tvFuelType)
    TextView tvFuelType;

    @BindView(R.id.tvFuelPrice)
    TextView tvFuelPrice;

    @BindView(R.id.fabAddSchdule)
    FloatingActionButton fabAddSchdule;

    @BindView(R.id.rvFuelPrices)
    RecyclerView rvFuelPrices;

    @BindView(R.id.layButton)
    LinearLayout layButton;

    @BindView(R.id.btnOk)
    Button btnViewSchedule;

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    FuelPricesAdapter adapter;
    private Bundle bundle;

    List<FuelDetailModel> fuelDetailList = new ArrayList<>();

    public FuelPricesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fuel_price, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();

        rvFuelPrices.setLayoutManager(new LinearLayoutManager(getContext()));

        rvFuelPrices.setAdapter(adapter);

        appBarLayout.setVisibility(View.GONE);
        btnViewSchedule.setVisibility(View.VISIBLE);
        layButton.setVisibility(View.GONE);
        fabAddSchdule.setVisibility(View.GONE);
        btnViewSchedule.setText(getResources().getString(R.string.view_schedule));

        Calendar calendar = Calendar.getInstance();
        tvDate.setText(calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));

        tvTime.setText(get12HourTime(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)));

        rvFuelPrices.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FuelPricesAdapter(fuelDetailList, getContext(), appComponent, true);
        rvFuelPrices.setAdapter(adapter);

        String strDate = tvDate.getText().toString() + " " + get24HourTime(tvTime.getText().toString());
        requestModel.setDateTime(getFormatedDateUTC(strDate));
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().searchSchedule(requestModel), scheduleListener);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }


    @OnClick({R.id.btnOk, R.id.tvDate, R.id.tvTime, R.id.frameRefresh, R.id.ivArrowDate, R.id.ivArrowTime, R.id.btnCreateSchdule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk: {
                newIntent(ViewScheduleActivity.class, null, false);
                break;
            }
            case R.id.frameRefresh: {
                refresh();
                break;
            }
            case R.id.tvDate : {
                showDatePicker();
                break;
            }
            case R.id.tvTime : {
                showTimePicker();
                break;
            }
            case R.id.ivArrowDate : {
                showDatePicker();
                break;
            }
            case R.id.ivArrowTime: {
                showTimePicker();
                break;
            }
            case R.id.btnCreateSchdule: {
                newIntent(FuelPriceActivity.class, null, false);
                break;
            }

        }
    }

    void refresh()
    {
        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();

        String strDate = tvDate.getText().toString() + " " + get24HourTime(tvTime.getText().toString());
        requestModel.setDateTime(getFormatedDateUTC(strDate));
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());

        appComponent.getServiceCaller().callService(appComponent.getAllApis().searchSchedule(requestModel), scheduleListener);
    }

    NetworkListener scheduleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<SchedulesResponseModel> schedulesList = (List<SchedulesResponseModel>) responseBody.getResutData();
                fuelDetailList.clear();

                if (schedulesList.size()>0) {
                    for (SchedulesResponseModel model : schedulesList) {
                        fuelDetailList.addAll(model.getFuelDetail());
                    }
                    if (fuelDetailList.size()>0) {
                        adapter.updateList(fuelDetailList);
                    }
                    else
                    {
                        showNoRecords();
                        rvFuelPrices.setVisibility(View.GONE);
                    }
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
            if (getActivity()==null)
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

    void showDatePicker()
    {
        if (getContext()==null)
            return;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

            }
        }, year, month, day);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000);
        datePickerDialog.show();

    }

    String filterTime;
    void showTimePicker()
    {
        if (getContext()==null)
            return;

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        Log.d("TimeIssue", "onTimeSet: "+hourOfDay+" : "+minute);
                        filterTime = hourOfDay + ":" + minute;
                        setTime();
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    void setTime()
    {
        tvTime.setText(get12HourTime(filterTime));
    }
}
