package com.developtech.efuelfo.ui.dialogFragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.fragments.fuelOwner.OnlineTransactionFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.ViewCashTransactionFragment;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dt on 3/17/18.
 */

public class FilterTransDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener
{


    @BindView(R.id.spinnerFuelType)
    Spinner spinnerFuelType;

    @BindView(R.id.switchDate)
    Switch switchDate;

    @BindView(R.id.tvStartDate)
    TextView tvStartDate;

    @BindView(R.id.tvEndDate)
    TextView tvEndDate;

    @BindView(R.id.lytDate)
    LinearLayout lytDate;

    @BindView(R.id.tvStartDateLabel)
    TextView tvStartDateLabel;

    @BindView(R.id.tvEndDateLabel)
    TextView tvEndDateLabel;

    int START_DATE = 0, END_DATE = 1;

    View view;
    private AlertDialog alertDialog;

    AppComponent appComponent;

    Context context;
    ArrayList<String> spFuelTypeList = new ArrayList<>();
    ArrayAdapter<String> spFuelTypeAdapter;
    NetworkListener networkListener;
    FilterListner filterListner;
    Fragment fragment;

    public void setFilterListner(FilterListner filterListner){
        this.filterListner =filterListner;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        textView.setTextColor(Color.BLACK);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface FilterListner{
        public void applyFilter(SearchScheduleRequestModel searchScheduleRequestModel);
        public void clearFilter();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_transaction_filter, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();

        init();

        return alertDialog;
    }

    public void setData(AppComponent appComponent, Fragment fragment)
    {
        this.appComponent = appComponent;
        this.fragment = fragment;
    }

    void init()
    {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String date = day + "/" + (month+1) + "/" +year;
        startDate = "" + year + "-" + (month + 1) + "-" + day + "T00:00:00.000Z";
        endDate = "" + year + "-" + (month + 1) + "-" + day + "T00:00:00.000Z";

        tvStartDate.setText(date);
        tvEndDate.setText(date);

        spFuelTypeList.add("All");
        if (appComponent!=null && appComponent.getSpUtils()!=null) {
            spFuelTypeList.addAll(appComponent.getSpUtils().getFuelStationModel().getFuelCompany().getFuelType());
            spFuelTypeAdapter = new ArrayAdapter<String>(appComponent.getContext(), R.layout.item_simple_spinner, spFuelTypeList);
            spinnerFuelType.setAdapter(spFuelTypeAdapter);
        }

        spinnerFuelType.setOnItemSelectedListener(this);

        if (fragment instanceof OnlineTransactionFragment)
        {
            spinnerFuelType.setSelection(((OnlineTransactionFragment) fragment).fuelType);
            switchDate.setChecked(((OnlineTransactionFragment) fragment).date);
            if (((OnlineTransactionFragment) fragment).date)
            {
                startDate = ((OnlineTransactionFragment) fragment).startDate;
                endDate = ((OnlineTransactionFragment) fragment).endDate;
                tvStartDate.setText(getParsedDate(startDate));
                tvEndDate.setText(getParsedDate(endDate));
            }
        }
        else if (fragment instanceof ViewCashTransactionFragment)
        {
            spinnerFuelType.setSelection(((ViewCashTransactionFragment) fragment).fuelType);
            switchDate.setChecked(((ViewCashTransactionFragment) fragment).date);
            if (((ViewCashTransactionFragment) fragment).date)
            {
                startDate = ((ViewCashTransactionFragment) fragment).startDate;
                endDate = ((ViewCashTransactionFragment) fragment).endDate;
                tvStartDate.setText(getParsedDate(startDate));
                tvEndDate.setText(getParsedDate(endDate));
            }
        }

        switchDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    lytDate.setBackgroundColor(getResources().getColor(R.color.selectDateBgOn));

                    tvStartDateLabel.setTextColor(getResources().getColor(R.color.filterTextEnabled));
                    tvEndDateLabel.setTextColor(getResources().getColor(R.color.filterTextEnabled));
                    tvStartDate.setTextColor(getResources().getColor(R.color.filterDateEnabled));
                    tvEndDate.setTextColor(getResources().getColor(R.color.filterDateEnabled));

                    tvStartDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showDatePicker(START_DATE);
                        }
                    });

                    tvEndDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showDatePicker(END_DATE);
                        }
                    });
                }
                else
                {
                    lytDate.setBackgroundColor(getResources().getColor(R.color.selectDateBgOff));

                    tvStartDateLabel.setTextColor(getResources().getColor(R.color.filterTextDisabled));
                    tvEndDateLabel.setTextColor(getResources().getColor(R.color.filterTextDisabled));
                    tvStartDate.setTextColor(getResources().getColor(R.color.filterDateDisabled));
                    tvEndDate.setTextColor(getResources().getColor(R.color.filterDateDisabled));

                    tvStartDate.setOnClickListener(null);
                    tvEndDate.setOnClickListener(null);
                }
            }
        });

    }


    @OnClick({R.id.btnAccept, R.id.btnDecline})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnAccept: {

                if (switchDate.isChecked() && (getDate(startDate).after(getDate(endDate)) || getDate(startDate).equals(getDate(endDate))))
                {
                    Toast.makeText(fragment.getContext(), "End date should be greater than start date", Toast.LENGTH_SHORT).show();
                    return;
                }


                SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
                requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                if(switchDate.isChecked()){
                    requestModel.setStartDate(startDate);
                    requestModel.setEndDate(endDate);
                }
                String type = (String) spinnerFuelType.getSelectedItem();
                if(!type.equalsIgnoreCase("All")){
                    requestModel.setFuelType(type);
                }

                filterListner.applyFilter(requestModel);
                if (fragment instanceof OnlineTransactionFragment)
                {
                    ((OnlineTransactionFragment) fragment).fuelType = spinnerFuelType.getSelectedItemPosition();
                    ((OnlineTransactionFragment) fragment).date = switchDate.isChecked();
                    ((OnlineTransactionFragment) fragment).startDate = startDate;
                    ((OnlineTransactionFragment) fragment).endDate = endDate;
                }
                else if (fragment instanceof ViewCashTransactionFragment)
                {
                    ((ViewCashTransactionFragment) fragment).fuelType = spinnerFuelType.getSelectedItemPosition();
                    ((ViewCashTransactionFragment) fragment).date = switchDate.isChecked();
                    ((ViewCashTransactionFragment) fragment).startDate = startDate;
                    ((ViewCashTransactionFragment) fragment).endDate = endDate;
                }
                dismiss();
                break;
            }
            case R.id.btnDecline: {
                if (fragment instanceof  OnlineTransactionFragment)
                {
                    ((OnlineTransactionFragment) fragment).date = false;
                    ((OnlineTransactionFragment) fragment).fuelType = 0;
                    ((OnlineTransactionFragment) fragment).page = 0;
                    ((OnlineTransactionFragment) fragment).callApi();

                }
                else if (fragment instanceof  ViewCashTransactionFragment)
                {
                    ((ViewCashTransactionFragment) fragment).date = false;
                    ((ViewCashTransactionFragment) fragment).fuelType = 0;
                    ((ViewCashTransactionFragment) fragment).page = 0;
                    ((ViewCashTransactionFragment) fragment).callApi();
                }
                dismiss();
                filterListner.clearFilter();
                break;
            }
        }
    }


    String startDate,endDate;
    void showDatePicker(final int check)
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                if(check == START_DATE)
                {
                    tvStartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    startDate = ""+ year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "T00:00:00.000Z";
                }
                else if(check == END_DATE)
                {
                    tvEndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    endDate = ""+ year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "T00:00:00.000Z";
                }

            }
        }, year, month, day);
        datePickerDialog.show();
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }


    public String getParsedDate(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(date);
    }

    public Date getDate(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
