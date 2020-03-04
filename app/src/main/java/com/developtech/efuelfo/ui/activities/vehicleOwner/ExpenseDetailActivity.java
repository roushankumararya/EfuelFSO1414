package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
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
import com.developtech.efuelfo.model.responseModel.GetExpensesResponseModel;
import com.developtech.efuelfo.model.responseModel.GetServiceResponseModel;
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

public class ExpenseDetailActivity extends MyActionBar implements OnItemSelectedListener
{

    @BindView(R.id.tvVehicleLabel)
    TextView tvVehicleLabel;

    @BindView(R.id.spinnerVehicle)
    Spinner spinnerVehicle;

    @BindView(R.id.tvDateLabel)
    TextView tvDateLabel;

    @BindView(R.id.tvTimeLabel)
    TextView tvTimeLabel;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvTime)
    TextView tvTime;

    @BindView(R.id.tvOdometerLabel)
    TextView tvOdometerLabel;

    @BindView(R.id.etOdometer)
    EditText etOdometer;

    @BindView(R.id.tvTypeOfExpenseLabel)
    TextView tvTypeOfExpenseLabel;

    @BindView(R.id.viewline1)
    View viewLine1;

    @BindView(R.id.viewLine2)
    View viewLine2;

    @BindView(R.id.tvTotalLabel)
    TextView tvTotalLabel;

    @BindView(R.id.tvExpenseTotal)
    TextView tvExpenseTotal;

    @BindView(R.id.tvAddExpenseType)
    TextView tvAddExpenseType;

    @BindView(R.id.tvReasonLabel)
    TextView tvReasonLabel;

    @BindView(R.id.etReason)
    TextView etReason;

    @BindView(R.id.tvNotesLabel)
    TextView tvNotesLabel;

    @BindView(R.id.etNotes)
    TextView etNotes;

    @BindView(R.id.btnEdit)
    Button btnEdit;

    @BindView(R.id.lytExpenseList)
    LinearLayout lytExpenseLit;

    @BindView(R.id.lytTypeOfExpense)
    LinearLayout lytTypeOfExpense;

    @BindView(R.id.lytExpenseTypes)
    LinearLayout lytExpenseTypes;

    @BindView(R.id.viewlayer)
    View viewlayer;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    boolean isEdit;

    ArrayList<String> spVehicleList = new ArrayList<>();
    List<AllVehicleResponseModel> vehicleResponseModels;
    SpinnerAdapter spVehicleAdapter;
    List<ExpenseTypeModel> expenseModelList;
    GetExpensesResponseModel expenseModel;
    GetServiceResponseModel serviceModel;

    public ADD_DETAIL_TYPE addDetailType = ADD_DETAIL_TYPE.EXPENSE;

    public enum ADD_DETAIL_TYPE{
        EXPENSE, SERVICE;

        ADD_DETAIL_TYPE getValue(String value)
        {
            return ADD_DETAIL_TYPE.valueOf(value);
        }
    }

    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);
        ButterKnife.bind(this);

        init();

        setHomeImage(true);
        setMenuItem();
        if(getIntent().getExtras()!=null)
        {
            expenseModel = (GetExpensesResponseModel) getIntent().getExtras().getSerializable("expense");
            serviceModel = (GetServiceResponseModel) getIntent().getExtras().getSerializable("service");
            addDetailType = ADD_DETAIL_TYPE.valueOf(getIntent().getExtras().getString("type"));
            bindData();
        }

        initComponents();
    }

    @Override
    public void initComponents() {

        if(addDetailType== ADD_DETAIL_TYPE.EXPENSE)
        {
            tvAddExpenseType.setText(getResources().getText(R.string.add_expense_type));
            tvTypeOfExpenseLabel.setText(getResources().getText(R.string.expense_edit));
        }
        else if(addDetailType== ADD_DETAIL_TYPE.SERVICE)
        {
            tvAddExpenseType.setText(getResources().getText(R.string.add_service_type));
            tvTypeOfExpenseLabel.setText(getResources().getText(R.string.service_edit));
            etReason.setVisibility(View.GONE);
            tvReasonLabel.setVisibility(View.GONE);
        }

        spVehicleAdapter = new SpinnerAdapter(appComponent.getContext(), android.R.layout.simple_spinner_dropdown_item, spVehicleList);
        spinnerVehicle.setAdapter(spVehicleAdapter);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getVehicleList(), getAllVehicleListener);

        spinnerVehicle.setOnItemSelectedListener(this);
        switchView();
    }

    @Override
    public void retry() {

    }

    void bindData()
    {
        if(addDetailType==ADD_DETAIL_TYPE.EXPENSE) {
            tvDate.setText(getParsedDate(expenseModel.getDate()));
            tvTime.setText(getParsedTime(expenseModel.getDate()));
            etOdometer.setText(expenseModel.getOdoMeter());
            expenseModelList = expenseModel.getTypeOfExpense();
            initLayout();
            etReason.setText(expenseModel.getReason());
            etNotes.setText(expenseModel.getNotes());
        }
        else if(addDetailType==ADD_DETAIL_TYPE.SERVICE)
        {
            tvDate.setText(getParsedDate(serviceModel.getDate()));
            tvTime.setText(getParsedTime(serviceModel.getDate()));
            etOdometer.setText(serviceModel.getOdoMeter());
            expenseModelList = serviceModel.getTypeOfService();
            initLayout();
            etNotes.setText(serviceModel.getNotes());
        }

    }

    @OnClick({R.id.btnEdit, R.id.tvAddExpenseType, R.id.tvDate, R.id.tvTime, R.id.btnDelete})
    void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnEdit: {

                if(isEdit)
                {
                    validate();
                }
                else
                {
                    isEdit = true;

                    switchView();
                }

                break;
            }
            case R.id.tvAddExpenseType :
            {
                Bundle bundle = new Bundle();

                if(addDetailType== ADD_DETAIL_TYPE.EXPENSE)
                {
                    bundle.putString("type", ADD_DETAIL_TYPE.EXPENSE.toString());
                }
                else if(addDetailType== addDetailType.SERVICE)
                {
                    bundle.putString("type", ADD_DETAIL_TYPE.SERVICE.toString());
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
            case R.id.tvDate:
            {
                showDatePicker();
                break;
            }
            case R.id.tvTime:
            {
                showTimePicker();
                break;
            }
            case R.id.btnDelete: {


                AddExpenseRequestModel requestModel = new AddExpenseRequestModel();


                if(addDetailType==ADD_DETAIL_TYPE.EXPENSE)
                {
                    requestModel.setRequestId(expenseModel.getId());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().deleteExpense(requestModel), deleteListener);
                }
                else if(addDetailType== ADD_DETAIL_TYPE.SERVICE)
                {
                    requestModel.setRequestId(serviceModel.getId());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().deleteService(requestModel), deleteListener);
                }
            }
        }
    }

    void validate()
    {
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

        if(etOdometer.getText().toString().isEmpty())
        {
            showMsg(rootLayout, getResources().getString(R.string.enter_odometer));
            etOdometer.requestFocus();
            return;
        }

        if(expenseModelList==null || expenseModelList.size()<1)
        {
            showMsg(rootLayout, getResources().getString(R.string.add_expense_type));
            lytExpenseLit.requestFocus();
            return;
        }

        if(etNotes.getText().toString().isEmpty())
        {
            showMsg(rootLayout, getResources().getString(R.string.enter_notes));
            etNotes.requestFocus();
            return;
        }

        if(addDetailType==ADD_DETAIL_TYPE.EXPENSE)
        {
            if(etReason.getText().toString().isEmpty())
            {
                showMsg(rootLayout, getResources().getString(R.string.enter_reason));
                etReason.requestFocus();
                return;
            }
        }


        if(addDetailType == ADD_DETAIL_TYPE.EXPENSE)
        {
            AddExpenseRequestModel model = new AddExpenseRequestModel();
            model.setRequestId(expenseModel.getId());
            model.setVehicle(vehicleResponseModels.get(spinnerVehicle.getSelectedItemPosition()).getId());
            model.setDate(getFormatedDate(tvDate.getText().toString()+" "+tvTime.getText().toString()));
            model.setTypeOfExpense(expenseModelList);
            model.setNotes(etNotes.getText().toString());
            model.setReason(etReason.getText().toString());
            model.setOdoMeter(etOdometer.getText().toString());

            hideKB();

            appComponent.getServiceCaller().callService(appComponent.getAllApis().updateExpense(model), updateListener);
        }
        else if(addDetailType == ADD_DETAIL_TYPE.SERVICE)
        {
            AddServiceRequestModel model = new AddServiceRequestModel();
            model.setRequestId(serviceModel.getId());
            model.setVehicle(vehicleResponseModels.get(spinnerVehicle.getSelectedItemPosition()).getId());
            model.setDate(getFormatedDate(tvDate.getText().toString()+" "+tvTime.getText().toString()));
            model.setTypeOfService(expenseModelList);
            model.setNotes(etNotes.getText().toString());
            model.setOdoMeter(etOdometer.getText().toString());

            hideKB();

            appComponent.getServiceCaller().callService(appComponent.getAllApis().updateService(model), updateListener);
        }




    }

    void initLayout()
    {
        if(expenseModelList!=null && expenseModelList.size()>0)
        {
            lytExpenseTypes.removeAllViews();

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
                            lytExpenseLit.setVisibility(View.GONE);
                            expenseModelList.clear();
                            return;

                        }
                        tvExpenseTotal.setText(price+"");


                    }
                });

            }

            tvExpenseTotal.setText(price+"");

            lytExpenseLit.setVisibility(View.VISIBLE);
        }
        else
        {
            lytExpenseTypes.removeAllViews();
            lytExpenseLit.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2323 && resultCode==RESULT_OK)
        {
            expenseModelList = (List<ExpenseTypeModel>) data.getExtras().getSerializable("added");

            initLayout();
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

    void switchView()
    {
        if(isEdit)
        {

            if(addDetailType== ADD_DETAIL_TYPE.EXPENSE)
            {
                setHomeTitle(getString(R.string.expense_edit));
            }
            else if(addDetailType== ADD_DETAIL_TYPE.SERVICE)
            {
                setHomeTitle(getString(R.string.service_edit));
            }

            tvVehicleLabel.setTextColor(Color.WHITE);
            tvDateLabel.setTextColor(Color.WHITE);
            tvTimeLabel.setTextColor(Color.WHITE);
            tvOdometerLabel.setTextColor(Color.WHITE);
            tvReasonLabel.setTextColor(Color.WHITE);
            tvNotesLabel.setTextColor(Color.WHITE);
            tvTypeOfExpenseLabel.setTextColor(Color.WHITE);
            spinnerVehicle.setBackground(getResources().getDrawable(R.drawable.corner_background));
            tvDate.setBackground(getResources().getDrawable(R.drawable.corner_background));
            tvTime.setBackground(getResources().getDrawable(R.drawable.corner_background));
            etReason.setBackground(getResources().getDrawable(R.drawable.corner_background));
            etNotes.setBackground(getResources().getDrawable(R.drawable.corner_background));
            etOdometer.setBackground(getResources().getDrawable(R.drawable.corner_background));
            lytTypeOfExpense.setBackground(getResources().getDrawable(R.drawable.corner_background));
            tvTime.setTextColor(getResources().getColor(R.color.blackLessText));
            etOdometer.setTextColor(getResources().getColor(R.color.blackLessText));
            tvTotalLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvExpenseTotal.setTextColor(getResources().getColor(R.color.blackLessText));
            etReason.setTextColor(getResources().getColor(R.color.blackLessText));
            etNotes.setTextColor(getResources().getColor(R.color.blackLessText));
            tvDate.setTextColor(getResources().getColor(R.color.blackLessText));
            spVehicleAdapter.setItemTextColor(getResources().getColor(R.color.blackLessText));
            viewLine1.setBackgroundColor(Color.BLACK);
            viewLine2.setBackgroundColor(Color.BLACK);
            btnEdit.setText(getResources().getString(R.string.save));
            viewlayer.setVisibility(View.GONE);

            for (int i = 0; i < lytExpenseTypes.getChildCount() ; i++) {
                View view = lytExpenseTypes.getChildAt(i);

                TextView tvExpenseName = view.findViewById(R.id.tvExpenseName);
                TextView tvExpensePrice = view.findViewById(R.id.tvExpensePrice);
                tvExpenseName.setTextColor(getResources().getColor(R.color.blackLessText));
                tvExpensePrice.setTextColor(getResources().getColor(R.color.blackLessText));

            }

        }
        else
        {

            if(addDetailType== ADD_DETAIL_TYPE.EXPENSE)
            {
                setHomeTitle(getString(R.string.expense_detail));
            }
            else if(addDetailType== ADD_DETAIL_TYPE.SERVICE)
            {
                setHomeTitle(getString(R.string.service_detail));
            }

            tvVehicleLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvDateLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvTimeLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvOdometerLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvReasonLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvNotesLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            tvTypeOfExpenseLabel.setTextColor(getResources().getColor(R.color.blackLessText));
            spinnerVehicle.setBackground(getResources().getDrawable(R.drawable.corner_background_black_less_radius));
            tvDate.setBackground(getResources().getDrawable(R.drawable.corner_background_black_less_radius));
            tvTime.setBackground(getResources().getDrawable(R.drawable.corner_background_black_less_radius));
            etNotes.setBackground(getResources().getDrawable(R.drawable.corner_background_black_less_radius));
            etReason.setBackground(getResources().getDrawable(R.drawable.corner_background_black_less_radius));
            etOdometer.setBackground(getResources().getDrawable(R.drawable.corner_background_black_less_radius));
            lytTypeOfExpense.setBackground(getResources().getDrawable(R.drawable.corner_background_black_less_radius));
            tvDate.setTextColor(Color.WHITE);
            tvTime.setTextColor(Color.WHITE);
            etOdometer.setTextColor(Color.WHITE);
            tvTotalLabel.setTextColor(Color.WHITE);
            tvExpenseTotal.setTextColor(Color.WHITE);
            etReason.setTextColor(Color.WHITE);
            etNotes.setTextColor(Color.WHITE);
            tvDate.setTextColor(Color.WHITE);
            viewLine1.setBackgroundColor(Color.WHITE);
            viewLine2.setBackgroundColor(Color.WHITE);
            spVehicleAdapter.setItemTextColor(Color.WHITE);
            btnEdit.setText(getResources().getString(R.string.edit));
            viewlayer.setVisibility(View.VISIBLE);

            for (int i = 0; i < lytExpenseTypes.getChildCount() ; i++) {
                View view = lytExpenseTypes.getChildAt(i);

                TextView tvExpenseName = view.findViewById(R.id.tvExpenseName);
                TextView tvExpensePrice = view.findViewById(R.id.tvExpensePrice);
                tvExpenseName.setTextColor(Color.WHITE);
                tvExpensePrice.setTextColor(Color.WHITE);

            }
        }
    }

    NetworkListener updateListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                isEdit = false;
                switchView();
                if(addDetailType== ADD_DETAIL_TYPE.EXPENSE)
                {
                    showMsg(rootLayout, getResources().getString(R.string.expense_update_msg));
                }
                else if (addDetailType== ADD_DETAIL_TYPE.SERVICE)
                {
                    showMsg(rootLayout, getResources().getString(R.string.service_updated_msg));
                }

            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                }
            });

        }

        @Override
        public void onComplete() {
            runOnUiThread(new Runnable() {
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


                if(expenseModel!=null)
                {
                    for (int i = 0; i < spVehicleList.size(); i++) {
                        if(expenseModel.getVehicle().getVehicleNumber().equals(spVehicleList.get(i)))
                        {
                            spinnerVehicle.setSelection(i);
                            break;
                        }
                    }
                }

            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
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

    NetworkListener deleteListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {

                if(addDetailType== ADD_DETAIL_TYPE.EXPENSE)
                {
                    showMsg(getResources().getString(R.string.expense_deleted_msg));
                }
                else if (addDetailType== ADD_DETAIL_TYPE.SERVICE)
                {
                    showMsg(rootLayout, getResources().getString(R.string.service_deleted_msg));
                }
                finish();
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) view;
        if(isEdit)
        {
            textView.setTextColor(Color.BLACK);
        }
        else
        {
            textView.setTextColor(Color.WHITE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
