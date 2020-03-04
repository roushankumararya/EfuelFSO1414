package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.developtech.efuelfo.R;

import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddExpenseRequestModel;
import com.developtech.efuelfo.model.requestModel.AddServiceRequestModel;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.model.responseModel.ExpenseTypeModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter;
import com.developtech.efuelfo.util.SPUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpenseActivity extends MyActionBar implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.lytExpenseList)
    LinearLayout lytExpenseList;

    @BindView(R.id.lytExpenseTypes)
    LinearLayout lytExpenseTypes;

    @BindView(R.id.tvExpenseTotal)
    TextView tvExpenseTotal;

    @BindView(R.id.spinnerVehicle)
    Spinner spinnerVehicle;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvTime)
    TextView tvTime;

    @BindView(R.id.etOdometer)
    EditText etOdometer;

    @BindView(R.id.etNotes)
    EditText etNotes;

    @BindView(R.id.etReason)
    EditText etReason;

    @BindView(R.id.tvAddExpenseType)
    TextView tvAddExpenseType;

    @BindView(R.id.tvTypeOfExpenseLabel)
    TextView tvTypeOfExpenseLabel;

    @BindView(R.id.tvReasonLabel)
    TextView tvReasonLabel;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    ArrayList<String> spVehicleList = new ArrayList<>();
    List<AllVehicleResponseModel> vehicleResponseModels;
    SpinnerAdapter spVehicleAdapter;

    public ADD_TYPE_ACTIVITY addActivityType = ADD_TYPE_ACTIVITY.EXPENSE;



    public enum ADD_TYPE_ACTIVITY {
        EXPENSE, SERVICE;

        ADD_TYPE_ACTIVITY getValue(String value)
        {
            return ADD_TYPE_ACTIVITY.valueOf(value);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        ButterKnife.bind(this);

        if(getIntent().getExtras()!=null)
        {
            addActivityType = ADD_TYPE_ACTIVITY.valueOf(getIntent().getExtras().getString("type"));
        }

        init();
        initComponents();

        if(addActivityType== ADD_TYPE_ACTIVITY.EXPENSE)
        {
            setHomeTitle(getString(R.string.add_expense));
        }
        else if(addActivityType== ADD_TYPE_ACTIVITY.SERVICE)
        {
            setHomeTitle(getString(R.string.add_service));
        }

        setHomeImage(true);
        ButterKnife.bind(this);
        setMenuItem();
    }

    @Override
    public void initComponents() {
        spVehicleAdapter = new SpinnerAdapter(appComponent.getContext(), android.R.layout.simple_spinner_dropdown_item, spVehicleList);
        spinnerVehicle.setAdapter(spVehicleAdapter);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getVehicleList(), getAllVehicleListener);

        spinnerVehicle.setOnItemSelectedListener(this);
        
        if(addActivityType== ADD_TYPE_ACTIVITY.EXPENSE)
        {
            tvAddExpenseType.setText(getResources().getText(R.string.add_expense_type));
            tvTypeOfExpenseLabel.setText(getResources().getText(R.string.typeofexpense));
        }
        else if(addActivityType== ADD_TYPE_ACTIVITY.SERVICE)
        {
            tvAddExpenseType.setText(getResources().getText(R.string.add_service_type));
            tvTypeOfExpenseLabel.setText(getResources().getText(R.string.typeofservice));
            tvReasonLabel.setVisibility(View.GONE);
            etReason.setVisibility(View.GONE);
        }
    }

    @Override
    public void retry() {

    }

    @OnClick({R.id.tvAddExpenseType, R.id.btnSave, R.id.tvDate, R.id.tvTime})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAddExpenseType: {

                Bundle bundle = new Bundle();

                if(addActivityType== ADD_TYPE_ACTIVITY.EXPENSE)
                {
                    bundle.putString("type", ExpenseTypeActivity.TYPE_ACTIVITY.EXPENSE.toString());
                }
                else if(addActivityType== ADD_TYPE_ACTIVITY.SERVICE)
                {
                    bundle.putString("type", ExpenseTypeActivity.TYPE_ACTIVITY.SERVICE.toString());
                }

                Intent intent = new Intent(this, ExpenseTypeActivity.class);
                if(expenseModelList!=null && expenseModelList.size()>0)
                {
                    bundle.putSerializable("added", (Serializable) expenseModelList);
                }
                intent.putExtras(bundle);
                startActivityForResult(intent, 2323);
                break;
            }
            case R.id.btnSave : {
                validate();
                break;
            }
            case R.id.tvDate : {
                showDatePicker();
                break;
            }
            case R.id.tvTime : {
                showTimePicker();
            }
        }
    }

    void validate()
    {
        hideKB();
        if (vehicleResponseModels.size()<1)
        {
            showMsg(rootLayout, getResources().getString(R.string.vehicle_not_available));
            spinnerVehicle.requestFocus();
            return;
        }

        if(tvDate.getText().toString().isEmpty())
        {
            showMsg(rootLayout, getResources().getString(R.string.enter_date));
            tvDate.requestFocus();
            return;
        }

        if(tvTime.getText().toString().isEmpty())
        {
            showMsg(rootLayout, getResources().getString(R.string.enter_time));
            tvTime.requestFocus();
            return;
        }

//        if(etOdometer.getText().toString().isEmpty())
//        {
//            showMsg(rootLayout, getResources().getString(R.string.enter_odometer));
//            etOdometer.requestFocus();
//            return;
//        }

        if(expenseModelList==null || expenseModelList.size()<1)
        {
            showMsg(rootLayout, getResources().getString(R.string.add_expense_type));
            lytExpenseList.requestFocus();
            return;
        }

//        if(etNotes.getText().toString().isEmpty())
//        {
//            showMsg(rootLayout, getResources().getString(R.string.enter_notes));
//            etNotes.requestFocus();
//            return;
//        }
//
//        if(addActivityType== ADD_TYPE_ACTIVITY.EXPENSE)
//        {
//            if(etReason.getText().toString().isEmpty())
//            {
//                showMsg(rootLayout, getResources().getString(R.string.enter_reason));
//                etReason.requestFocus();
//                return;
//            }
//        }


        if(addActivityType== ADD_TYPE_ACTIVITY.EXPENSE)
        {
            AddExpenseRequestModel model = new AddExpenseRequestModel();
            model.setVehicle(vehicleResponseModels.get(spinnerVehicle.getSelectedItemPosition()).getId());
            model.setDate(formattedToUTC(getFormatedDate(tvDate.getText().toString()+" "+tvTime.getText().toString())));
            model.setTypeOfExpense(expenseModelList);
            model.setNotes(etNotes.getText().toString());
            model.setReason(etReason.getText().toString());
            model.setOdoMeter(etOdometer.getText().toString());

            hideKB();

            appComponent.getServiceCaller().callService(appComponent.getAllApis().createExpense(model), createExpenseListener);
        }
        else if(addActivityType== ADD_TYPE_ACTIVITY.SERVICE)
        {
            AddServiceRequestModel model = new AddServiceRequestModel();
            model.setVehicle(vehicleResponseModels.get(spinnerVehicle.getSelectedItemPosition()).getId());
            model.setDate(getFormatedDate(tvDate.getText().toString()+" "+tvTime.getText().toString()));
            model.setTypeOfService(expenseModelList);
            model.setNotes(etNotes.getText().toString());
            model.setOdoMeter(etOdometer.getText().toString());

            hideKB();

            appComponent.getServiceCaller().callService(appComponent.getAllApis().createService(model), createExpenseListener);
        }



    }

    void showDatePicker()
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
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    void showTimePicker()
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

                        tvTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    List<ExpenseTypeModel> expenseModelList;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2323 && resultCode==RESULT_OK)
        {
            expenseModelList = (List<ExpenseTypeModel>) data.getExtras().getSerializable("added");

            initLayout();
        }
    }

    int price;

    void initLayout()
    {
        if(expenseModelList!=null && expenseModelList.size()>0)
        {
            lytExpenseTypes.removeAllViews();
            price = 0;
            for (int i=0 ; i<expenseModelList.size(); i++)
            {
                ExpenseTypeModel model = expenseModelList.get(i);

                View itemView = LayoutInflater.from(this).inflate(R.layout.item_expense_list, null);

                TextView tvExpenseName = itemView.findViewById(R.id.tvExpenseName);
                TextView tvExpensePrice = itemView.findViewById(R.id.tvExpensePrice);
                ImageView ivDelete = itemView.findViewById(R.id.ivDelete);

                tvExpenseName.setText(model.getName());
                tvExpensePrice.setText(model.getPrice());

                lytExpenseTypes.addView(itemView);

                price += Integer.parseInt(model.getPrice());

                final int tempIndex = i;

                ivDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(lytExpenseTypes.getChildCount()>1)
                        {
                            lytExpenseTypes.removeViewAt(tempIndex);

                            price = price - Integer.parseInt(expenseModelList.get(tempIndex).getPrice());
                            expenseModelList.remove(tempIndex);

                        }
                        else
                        {
                            lytExpenseTypes.removeAllViews();
                            price = 0;
                            lytExpenseList.setVisibility(View.GONE);
                            expenseModelList.clear();
                            return;

                        }
                        tvExpenseTotal.setText(price+"");


                    }
                });

            }

            tvExpenseTotal.setText(price+"");

            lytExpenseList.setVisibility(View.VISIBLE);
        }
        else
        {
            lytExpenseTypes.removeAllViews();
            lytExpenseList.setVisibility(View.GONE);
        }
    }


    void clearFields()
    {
        tvDate.setText("");
        tvTime.setText("");
        etOdometer.setText("");
        expenseModelList.clear();
        initLayout();
        etReason.setText("");
        etNotes.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) view;
        textView.setTextColor(Color.BLACK);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    NetworkListener getAllVehicleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                vehicleResponseModels = (List<AllVehicleResponseModel>) responseBody.getResutData();

                spVehicleList.clear();
                for (AllVehicleResponseModel model : vehicleResponseModels) {
                    spVehicleList.add(model.getVehicleNumber());
                }

                spVehicleAdapter.notifyDataSetChanged();

            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            hideProgress();
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

    NetworkListener createExpenseListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {


                if(addActivityType== ADD_TYPE_ACTIVITY.EXPENSE)
                {
                    showMsg(getResources().getString(R.string.expense_added_msg));
                    clearFields();
                }
                else if(addActivityType== ADD_TYPE_ACTIVITY.SERVICE)
                {
                    showMsg(getResources().getString(R.string.service_added_msg));
                    clearFields();
                }

                finish();
            }
        }

        @Override
        public void onError(String msg) {
            hideProgress();
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
