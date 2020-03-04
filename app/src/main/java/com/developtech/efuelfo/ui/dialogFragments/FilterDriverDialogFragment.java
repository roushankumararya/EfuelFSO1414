package com.developtech.efuelfo.ui.dialogFragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.listeners.FilterUpdateListener;
import com.developtech.efuelfo.model.responseModel.DriverResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.SelectDriverAdapter;

import java.text.DateFormat;
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

/**
 * Created by developtech on 2/1/18.
 */

public class FilterDriverDialogFragment extends DialogFragment {

    @BindView(R.id.tvTitle)
    CustomTextView tvTitle;

    @BindView(R.id.tvSelectName)
    CustomTextView tvSelectName;

    @BindView(R.id.tvSelectDate)
    CustomTextView tvSelectDate;

    @BindView(R.id.rvDialog)
    RecyclerView rvDialog;

    @BindView(R.id.lytDate)
    LinearLayout lytDate;

    @BindView(R.id.lytFrom)
    LinearLayout lytForm;

    @BindView(R.id.lytTo)
    LinearLayout lytTo;

    @BindView(R.id.tvFromDate)
    CustomTextView tvFromDate;

    @BindView(R.id.tvToDate)
    CustomTextView tvToDate;

    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;

    View view;
    private AlertDialog alertDialog;

    List<PaymentHistoryResponseModel> historyModelList;
    SelectDriverAdapter selectDriverAdapter;
    List<DriverResponseModel> driversList;

    Context context;
    int FROM_DATE = 0, TO_DATE = 1;

    FilterUpdateListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_filter, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();

        init();

        return alertDialog;
    }

    public void setData(Context context, List<PaymentHistoryResponseModel> historyModelList, FilterUpdateListener listener)
    {
        this.historyModelList = historyModelList;
        this.context = context;
        this.listener = listener;
    }

    void init()
    {
        if (getContext()!=null)
        {
            tvTitle.setText(getContext().getResources().getString(R.string.filterbydriver));
            tvSelectName.setText(getContext().getResources().getString(R.string.selectdriver));
            tvSelectDate.setText(getContext().getResources().getString(R.string.select_date));
        }

        driversList = new ArrayList<>();

        for(PaymentHistoryResponseModel model : historyModelList)
        {
            driversList.add(model.getDriver());
        }

        selectDriverAdapter = new SelectDriverAdapter(driversList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvDialog.setLayoutManager(layoutManager);
        rvDialog.setAdapter(selectDriverAdapter);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String date = day + "/" + (month+1) + "/" +year;

        tvFromDate.setText(date);
        tvToDate.setText(date);
    }

    @OnClick({R.id.ivBack, R.id.tvSelectName, R.id.tvSelectDate, R.id.lytFrom, R.id.lytTo, R.id.tvApplyFilter, R.id.tvClearFilter})
    void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ivBack:{
                dismiss();
                break;
            }
            case R.id.tvSelectName : {
                tvSelectName.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tvSelectName.setTextColor(Color.WHITE);
                tvSelectDate.setTextColor(Color.BLACK);
                tvSelectDate.setBackgroundColor(Color.WHITE);
                rvDialog.setVisibility(View.VISIBLE);
                lytDate.setVisibility(View.GONE);
                break;
            }
            case R.id.tvSelectDate : {
                tvSelectName.setBackgroundColor(Color.WHITE);
                tvSelectDate.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tvSelectName.setTextColor(Color.BLACK);
                tvSelectDate.setTextColor(Color.WHITE);
                lytDate.setVisibility(View.VISIBLE);
                rvDialog.setVisibility(View.GONE);
                break;
            }
            case R.id.lytFrom : {
                showDatePicker(FROM_DATE);
                break;
            }
            case R.id.lytTo: {
                showDatePicker(TO_DATE);
                break;
            }
            case R.id.tvApplyFilter : {
                filter();
                break;
            }
            case R.id.tvClearFilter : {
                listener.clearFilter();
                dismiss();
                break;
            }
        }
    }


    List<PaymentHistoryResponseModel> filteredList = new ArrayList<>();

    void filter()
    {
        Date fromDate = giveDate(tvFromDate.getText().toString());
        Date toDate = giveDate(tvToDate.getText().toString());

        if(fromDate.after(toDate))
        {
            showMsg(rootLayout, context.getResources().getString(R.string.from_date_sholud_less_than_to_date));
            return;
        }

        List<DriverResponseModel> addedList = selectDriverAdapter.getSelectedList();

        if(addedList.size()==0)
        {
            showMsg(rootLayout, context.getResources().getString(R.string.please_select_driver));
            return;
        }


        for (PaymentHistoryResponseModel model : historyModelList)
        {
            Date created = giveDate2(model.getCreatedAt());
            if((created.after(fromDate) || created.equals(fromDate)) && (created.before(toDate) || created.equals(toDate)))
            {
                for (DriverResponseModel driverModel : addedList)
                {
                    if(model.getDriver().getId().equals(driverModel.getId()))
                    {
                        filteredList.add(model);
                    }
                }
            }
        }

        listener.updateList(filteredList);

        dismiss();

        selectDriverAdapter.resetSelections();
    }


    void showDatePicker(final int check)
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                if(check == FROM_DATE)
                {
                    tvFromDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
                else if(check == TO_DATE)
                {
                    tvToDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }

            }
        }, year, month, day);
        datePickerDialog.show();
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    Date giveDate(String str)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date giveDate2(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public void showMsg(View v, String msg) {

        Snackbar s = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
        View view = s.getView();
        view.setBackgroundColor(getResources().getColor(R.color.bg_color));
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/" + getResources().getString(R.string.font_light)));
        tv.setTextColor(Color.WHITE);
        s.show();

    }

}
