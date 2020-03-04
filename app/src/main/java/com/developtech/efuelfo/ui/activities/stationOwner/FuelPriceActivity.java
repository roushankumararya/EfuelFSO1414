package com.developtech.efuelfo.ui.activities.stationOwner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;
import com.developtech.efuelfo.model.requestModel.CreateScheduleRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.adapters.stationOwner.FuelPricesAdapter;
import com.developtech.efuelfo.ui.adapters.stationOwner.ViewSchedulesAdapter;
import com.developtech.efuelfo.util.SPUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FuelPriceActivity extends MyActionBar {

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

    @BindView(R.id.rvFuelPrices)
    RecyclerView rvFuelPrices;

    @BindView(R.id.layButton)
    LinearLayout layButton;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    @BindView(R.id.ivArrowDate)
    ImageView ivArrowDate;

    @BindView(R.id.ivArrowTime)
    ImageView ivArrowTime;

    @BindView(R.id.btnOk)
    Button btnOk;

    @BindView(R.id.lytDate)
    LinearLayout lytDate;

    @BindView(R.id.lytTime)
    LinearLayout lytTime;

    @BindView(R.id.btnCreateSchdule)
    Button btnCreateSchedule;

    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    @BindView(R.id.btnCancel)
    Button btnCancel;

    @BindView(R.id.fabAddSchdule)
    FloatingActionButton fabAddSchedule;

    private FuelPricesAdapter adapter;
    private Bundle bundle;

    private List<FuelDetailModel> fuelDetailList = new ArrayList<>();
    private List<SchedulesResponseModel> schedulesList;

    private SchedulesResponseModel scheduleModel;

    private boolean isDisabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fuel_price);
        ButterKnife.bind(this);
        init();
        initComponents();
        setHomeTitle(getString(R.string.fuel_prices_caps));
        setMenuItem();
        setHomeImage(true);
        showNotification(false);
        btnCreateSchedule.setVisibility(View.GONE);
    }

    @Override
    public void initComponents() {

        AddFuelStationRequestModel requestModel = new AddFuelStationRequestModel();
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().findSchedules(requestModel), getSchedulesListener);

        bundle = getIntent().getExtras();

        if (bundle!=null && bundle.getSerializable("model") != null) {

            layButton.setVisibility(View.VISIBLE);
            ivArrowDate.setVisibility(View.GONE);
            ivArrowTime.setVisibility(View.GONE);
            btnOk.setVisibility(View.GONE);


            scheduleModel =(SchedulesResponseModel) bundle.getSerializable("model");

            Date date = new Date();

            Date modelDate = appComponent.getUtilFunctions().getLocalDateFromUtc(scheduleModel.getTime());

            if (modelDate.before(date) || modelDate.equals(date))
            {
                lytDate.setClickable(false);
                lytDate.setEnabled(false);
                lytTime.setClickable(false);
                lytTime.setEnabled(false);
            }

            Date scheduleDate = appComponent.getUtilFunctions().getDate(scheduleModel.getTime());


            if (scheduleDate.before(date) || scheduleDate.equals(date))
            {
                btnUpdate.setVisibility(View.GONE);
                fabAddSchedule.setVisibility(View.GONE);
                isDisabled = true;
            }

            fuelDetailList = scheduleModel.getFuelDetail();
            tvDate.setText(appComponent.getUtilFunctions().getLocalDate(scheduleModel.getTime()));
            tvTime.setText(appComponent.getUtilFunctions().getLocalTime(scheduleModel.getTime()));

        } else {
            layButton.setVisibility(View.GONE);

            Calendar calendar = Calendar.getInstance();
            date = calendar.getTime();
            tvDate.setText(calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));
            tvTime.setText(get12HourTime(date.getHours()+":"+date.getMinutes()));
        }

        frameRefresh.setVisibility(View.GONE);

        rvFuelPrices.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FuelPricesAdapter(fuelDetailList, this, appComponent, isDisabled);
        rvFuelPrices.setAdapter(adapter);
    }

    Date date;

    @Override
    public void retry() {

    }

    @OnClick({R.id.btnOk, R.id.lytDate, R.id.lytTime, R.id.fabAddSchdule, R.id.btnUpdate, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk: {

                validate();

                break;
            }
            case R.id.lytDate : {
                showDatePicker();
                break;
            }
            case R.id.lytTime : {
                showTimePicker();
                break;
            }
            case R.id.fabAddSchdule : {
                addListItem();
                break;
            }
            case R.id.btnUpdate: {
                validate();
                break;
            }
            case R.id.btnCancel: {
                finish();
                break;
            }
        }
    }

    private void validate()
    {
        List<FuelDetailModel> fuelDetailList = adapter.getList();

        if (scheduleModel==null) {
            if (fuelDetailList.size() == 0) {
                showMsg(rootLayout, getResources().getString(R.string.please_add_fuelprice));
                return;
            }
        }

        boolean repeated = false;
        for (int i = 0; i < (fuelDetailList.size()-1) ; i++) {
            for (int j = i; j < (fuelDetailList.size()-1); j++) {
                if(fuelDetailList.get(j).getFuelType().equals(fuelDetailList.get(j+1).getFuelType()))
                {
                    showMsg(rootLayout, getResources().getString(R.string.fuel_type_occur_more_than_once));
                    repeated = true;
                    break;
                }
            }
            if (repeated)
            {
                break;
            }
        }

        if (repeated)
        {
            return;
        }

        if (fuelDetailList.size()>0) {
            for (FuelDetailModel model : fuelDetailList) {
                if (model.getPrice() == null || model.getPrice().isEmpty()) {
                    showMsg(rootLayout, getResources().getString(R.string.add_price));
                    return;
                }
            }
        }

        if(scheduleModel==null) {
            String str = tvDate.getText().toString() + " " + get24HourTime(tvTime.getText().toString());
            Date selectedDate = getDate(str);
            final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

            Calendar date = Calendar.getInstance();
            long t= date.getTimeInMillis();
            Date currentDate = new Date(t + (5 * ONE_MINUTE_IN_MILLIS));

            if (selectedDate.before(currentDate) || selectedDate.equals(currentDate)) {
                showMsg(rootLayout, getResources().getString(R.string.schedule_date_should_greater_current_date));
                return;
            }

            boolean isExists = false;
            for (SchedulesResponseModel model : schedulesList)
            {
                Date scheduleDate = appComponent.getUtilFunctions().getLocalDateFromUtc(model.getTime());

                if (selectedDate.equals(scheduleDate))
                {
                    isExists = true;
                    break;
                }
            }

            if (isExists)
            {
                showMsg(rootLayout, getResources().getString(R.string.schedule_exist_of_date_time));
                return;
            }

            CreateScheduleRequestModel requestModel = new CreateScheduleRequestModel();
            requestModel.setFuelDetail(fuelDetailList);
            requestModel.setFuelStation(appComponent.getSpUtils().getFuelStationModel().getId());
            String strDate = toUTC(selectedDate);
            requestModel.setTime(getFormatedDate(strDate));

            appComponent.getServiceCaller().callService(appComponent.getAllApis().createSchedule(requestModel), createScheduelListener);
        }
        else
        {

            Calendar date = Calendar.getInstance();
            Date cDate = new Date();
            Date modelDate = appComponent.getUtilFunctions().getLocalDateFromUtc(scheduleModel.getTime());

            String str = tvDate.getText().toString() + " " + get24HourTime(tvTime.getText().toString());
            Date selectedDate = getDate(str);

            if (modelDate.after(cDate))
            {
                final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

                long t= date.getTimeInMillis();
                Date currentDate = new Date(t + (5 * ONE_MINUTE_IN_MILLIS));

                if (selectedDate.before(currentDate) || selectedDate.equals(currentDate)) {
                    showMsg(rootLayout, getResources().getString(R.string.schedule_date_should_greater_current_date));
                    return;
                }

                boolean isExists = false;

                for (SchedulesResponseModel model : schedulesList)
                {
                    if (scheduleModel.getId().equals(model.getId()))
                    {
                        schedulesList.remove(model);
                        break;
                    }
                }

                for (SchedulesResponseModel model : schedulesList)
                {
                    Date scheduleDate = appComponent.getUtilFunctions().getLocalDateFromUtc(model.getTime());

                    if (selectedDate.equals(scheduleDate))
                    {
                        isExists = true;
                        break;
                    }
                }

                if (isExists)
                {
                    showMsg(rootLayout, getResources().getString(R.string.schedule_exist_of_date_time));
                    return;
                }
            }

            CreateScheduleRequestModel requestModel = new CreateScheduleRequestModel();
            if (fuelDetailList==null)
                fuelDetailList = new ArrayList<>();
            requestModel.setFuelDetail(fuelDetailList);
            requestModel.setRequestId(scheduleModel.getId());
            String strDate = tvDate.getText().toString() + " " + get24HourTime(tvTime.getText().toString());

            if (modelDate.after(cDate)) {
                String dkfdslkfj = toUTC(selectedDate);
                requestModel.setTime(getFormatedDate(dkfdslkfj));
            }
            else
            {
                requestModel.setTime(appComponent.getUtilFunctions().toUtc(strDate));
            }
            appComponent.getServiceCaller().callService(appComponent.getAllApis().updateSchedule(requestModel), updateListener);
        }
    }

    private void addListItem()
    {
        GetFuelStationResponseModel fuelStationData = appComponent.getSpUtils().getFuelStationModel();
        if(fuelDetailList.size()<fuelStationData.getFuelCompany().getFuelType().size()) {
            fuelDetailList.add(new FuelDetailModel());
            adapter.updateList(fuelDetailList);
        }
    }

    private void showDatePicker()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

            }
        }, year, month, day);
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000);
    }

    String filterTime;
    private void showTimePicker()
    {

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        filterTime = hourOfDay + ":" + minute;
                        setTime();
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void setTime()
    {
        tvTime.setText(get12HourTime(filterTime));
    }

    private Date getDate(String strDate)
    {
        String convertedTime="";
        Date date = null;
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            date = sdf.parse(strDate);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return date;
    }



    private NetworkListener createScheduelListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(getResources().getString(R.string.fuel_prices_schedule_created));
                finish();
            }
            else
            {
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

    private NetworkListener updateListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(rootLayout, getResources().getString(R.string.fuel_prices_updated));
                finish();
            }
            else
            {
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

    private NetworkListener getSchedulesListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                schedulesList = (List<SchedulesResponseModel>) responseBody.getResutData();
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
}
