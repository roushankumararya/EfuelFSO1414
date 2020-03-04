package com.developtech.efuelfo.ui.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.ReportRequestModel;
import com.developtech.efuelfo.model.responseModel.RefuelingModel;
import com.developtech.efuelfo.model.responseModel.RefuelingResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.RefuelingAdapter;
import com.developtech.efuelfo.util.SPUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends BaseFragment {

    @BindView(R.id.tvStartDate)
    TextView tvStartDate;

    @BindView(R.id.tvEndDate)
    TextView tvEndDate;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    @BindView(R.id.tvTotalCost)
    TextView tvTotalCost;

    @BindView(R.id.tvTotalVolume)
    TextView tvTotalVolume;

    @BindView(R.id.rvRefueling)
    RecyclerView rvRefueling;

    @BindView(R.id.tvDays)
    TextView tvDays;

    @BindView(R.id.pieChart)
    PieChart pieChart;


    int FROM_DATE = 0, TO_DATE = 1;
    String startDate, endDate;

    HomeActivity homeActivity;

    RefuelingResponseModel responseModel;

    View view;

    protected Typeface robotoRegular, robotoSemiBold;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        homeActivity = (HomeActivity) activity;
    }


    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        robotoRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/regular.ttf");
        robotoSemiBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/semiBold.ttf");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvRefueling.setLayoutManager(layoutManager);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvStartDate.setText(day + "/" + (month + 1) + "/" + year);
        startDate = getParsedDate(year + "-" + (month + 1) + "-" + day);

        tvEndDate.setText(day + "/" + (month + 1) + "/" + year);
        endDate = getParsedDate(year + "-" + (month + 1) + "-" + day);

        callApi();

    }

    void callApi()
    {
        ReportRequestModel requestModel = new ReportRequestModel();
        requestModel.setStartDate(startDate);
        requestModel.setEndDate(endDate);
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());

        appComponent.getServiceCaller().callService(appComponent.getAllApis().reportRefuel(requestModel), reportRefuelListener);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }


    @OnClick({R.id.tvStartDate, R.id.tvEndDate})
    void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tvStartDate: {
                showDatePicker(FROM_DATE);
                break;
            }
            case R.id.tvEndDate: {
                showDatePicker(TO_DATE);
                break;
            }
        }
    }

    void showDatePicker(final int check) {
        Date sDate = appComponent.getUtilFunctions().toDate(startDate);
        Date eDate = appComponent.getUtilFunctions().toDate(endDate);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (check == FROM_DATE) {
            calendar.setTime(sDate);
        }
        else if (check == TO_DATE) {
            calendar.setTime(eDate);
        }

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                if (check == FROM_DATE) {
                    tvStartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    startDate = getParsedDate(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    validate();
                } else if (check == TO_DATE) {
                    tvEndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    endDate = getParsedDate(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    validate();
                }

            }
        }, year, month, day);
        datePickerDialog.show();
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    void bindData()
    {
        tvDays.setText(this.responseModel.getTotalCount()+" Refueling in "+countDays()+" days");
        tvTotalCost.setText(getResources().getString(R.string.rupee_symbol)+" "+this.responseModel.getTotalAmount());
        tvTotalVolume.setText("Ltr "+this.responseModel.getTotalVol());

        ArrayList<RefuelingModel> refuelingList = removeEmptiness((ArrayList<RefuelingModel>) responseModel.getRefuel());

        RefuelingAdapter adapter = new RefuelingAdapter(refuelingList, getContext());
        rvRefueling.setAdapter(adapter);

        if (responseModel!=null && responseModel.getRefuel()!=null)
        {
            initPieChart(refuelingList);
        }
    }


    void initPieChart(List<RefuelingModel> modelList)
    {
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(Color.WHITE);
        if (robotoRegular!=null) {
            pieChart.setEntryLabelTypeface(robotoRegular);
        }
        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        ArrayList<PieEntry> pieEntryList = new ArrayList<>();

        for (RefuelingModel model : modelList)
        {
            PieEntry entry = new PieEntry(model.getTotalSum(),model.getFuelType()+" - "+model.getVolByFuelType()+" L");
            pieEntryList.add(entry);
        }

        PieDataSet dataSet = new PieDataSet(pieEntryList, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);


        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(13f);
        data.setValueTypeface(robotoSemiBold);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(mTfLight);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutQuad);


        pieChart.invalidate();

        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setWordWrapEnabled(true);
//        l.setXEntrySpace(10f);
//        l.setYEntrySpace(10f);
//        l.setYOffset(10f);
        l.setTextColor(Color.WHITE);


    }

    public class MyValueFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("####.##"); // use decimals
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

            // write your logic here

            return mFormat.format(value); // in case you want to add percent
        }
    }


    ArrayList<RefuelingModel> removeEmptiness(ArrayList<RefuelingModel> refuelingList)
    {
        for (int i = 0; i < refuelingList.size(); i++) {
            if (refuelingList.get(i).getTotalSum()<1)
            {
                refuelingList.remove(i);
                break;
            }
        }

        for (int i = 0; i < refuelingList.size(); i++) {
            if (refuelingList.get(i).getTotalSum()<1)
            {
                removeEmptiness(refuelingList);
            }
        }

        return refuelingList;
    }

    void validate()
    {
        Date sDate = appComponent.getUtilFunctions().toDate(startDate);
        Date eDate = appComponent.getUtilFunctions().toDate(endDate);

        if (eDate.before(sDate))
        {
            showMsg(rootLayout, getResources().getString(R.string.end_date_should_greater_than_start_date));
            return;
        }

        callApi();
    }


    NetworkListener reportRefuelListener = new NetworkListener() {
            @Override
            public void onSuccess(ResultModel<?> responseBody) {
                if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                    responseModel = (RefuelingResponseModel) responseBody.getResutData();
                    bindData();
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


    public long countDays()
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date1 = myFormat.parse(tvStartDate.getText().toString());
            Date date2 = myFormat.parse(tvEndDate.getText().toString());
            if (date1.equals(date2))
            {
                return 1;
            }
            long diff = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getParsedDate(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(date);
    }
}
