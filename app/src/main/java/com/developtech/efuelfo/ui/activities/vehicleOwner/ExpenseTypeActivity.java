package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomButton;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.responseModel.ExpenseTypeModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.ExpenseTypeAdapter;
import com.developtech.efuelfo.util.SPUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpenseTypeActivity extends MyActionBar {

    @BindView(R.id.rvExpenseType)
    RecyclerView rvExpenseType;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    ExpenseTypeAdapter expenseTypeAdapter;

    List<ExpenseTypeModel> expenseTypeList = new ArrayList<>();

    List<ExpenseTypeModel> addedExpensesList;

    public TYPE_ACTIVITY typeActivity = TYPE_ACTIVITY.EXPENSE;

    public enum TYPE_ACTIVITY{
        EXPENSE, SERVICE;

        TYPE_ACTIVITY getValue(String value)
        {
            return TYPE_ACTIVITY.valueOf(value);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_type);
        ButterKnife.bind(this);

        if(getIntent().getExtras()!=null)
        {
            typeActivity = TYPE_ACTIVITY.valueOf(getIntent().getExtras().getString("type"));
        }

        init();
        initComponents();

        if(typeActivity==TYPE_ACTIVITY.EXPENSE)
        {
            setHomeTitle(getString(R.string.expense_type));
        }
        else if(typeActivity==TYPE_ACTIVITY.SERVICE)
        {
            setHomeTitle(getString(R.string.service_type));
        }


        setHomeImage(true);
        setMenuItem();

        if(getIntent().getExtras()!=null)
        {
            Bundle bundle = getIntent().getExtras();
            addedExpensesList = (List<ExpenseTypeModel>) bundle.getSerializable("added");
        }

    }

    @Override
    public void initComponents() {
        expenseTypeAdapter = new ExpenseTypeAdapter(expenseTypeList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvExpenseType.setLayoutManager(layoutManager);
        rvExpenseType.setAdapter(expenseTypeAdapter);

        if(typeActivity==TYPE_ACTIVITY.EXPENSE)
        {
            appComponent.getServiceCaller().callService(appComponent.getAllApis().getAvailableExpenseType(), getExpensesListener);
        }
        else if(typeActivity==TYPE_ACTIVITY.SERVICE)
        {
            appComponent.getServiceCaller().callService(appComponent.getAllApis().getAvailableServiceType(), getExpensesListener);
        }

    }

    @Override
    public void retry() {

    }

    @OnClick({R.id.fabAddExpenseType, R.id.btnSave})
    void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fabAddExpenseType: {
                showAddExpenseDialog();
                break;
            }
            case R.id.btnSave : {
                Bundle bundle = new Bundle();
                List<ExpenseTypeModel> addedList = new ArrayList<>();

                for(ExpenseTypeModel model : expenseTypeAdapter.getList())
                {
                    if(model.isAdded())
                    {
                        addedList.add(model);
                        Log.d("expenseIssue", "price: "+model.getPrice()+", isChecked: "+model.isAdded());
                    }
                }

                for(ExpenseTypeModel model : addedList)
                {
                    if(model.getPrice()==null || model.getPrice().equals(""))
                    {
                        showMsg(rootLayout, getResources().getString(R.string.enter_price));
                        return;
                    }
                }

                bundle.putSerializable("added", (Serializable) addedList);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    ExpenseTypeModel expenseTypeModel;

    void showAddExpenseDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_type_of_expense);
        final CustomEditText etExpenseName = dialog.findViewById(R.id.etExpenseName);
        CustomButton btnAdd = dialog.findViewById(R.id.btnAdd);
        TextView tvAddType = dialog.findViewById(R.id.tvAddType);


        if(typeActivity==TYPE_ACTIVITY.EXPENSE)
        {
            tvAddType.setText(getResources().getString(R.string.add_expense_type_dialog));
            etExpenseName.setHint(getResources().getString(R.string.expense_name));
        }
        else if(typeActivity==TYPE_ACTIVITY.SERVICE)
        {
            tvAddType.setText(getResources().getString(R.string.add_service_type_dialog));
            etExpenseName.setHint(getResources().getString(R.string.service_name));
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!etExpenseName.getText().toString().isEmpty())
                {
                    expenseTypeModel = new ExpenseTypeModel();
                    expenseTypeModel.setName(etExpenseName.getText().toString());

                    if(typeActivity==TYPE_ACTIVITY.EXPENSE)
                    {
                        appComponent.getServiceCaller().callService(appComponent.getAllApis().createExpenseType(expenseTypeModel), createListener);
                    }
                    else if(typeActivity==TYPE_ACTIVITY.SERVICE)
                    {
                        appComponent.getServiceCaller().callService(appComponent.getAllApis().createServiceType(expenseTypeModel), createListener);
                    }

                    dialog.dismiss();
                }
            }
        });

        dialog.show();

    }

    NetworkListener getExpensesListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                expenseTypeList = (List<ExpenseTypeModel>) responseBody.getResutData();

                if(addedExpensesList!=null && addedExpensesList.size()>0)
                {
                    for(ExpenseTypeModel added : addedExpensesList)
                    {
                        for(ExpenseTypeModel model : expenseTypeList)
                        {
                            if(added.getId().equals(model.getId()))
                            {
                                model.setAdded(added.isAdded());
                                model.setPrice(added.getPrice());
                            }
                        }
                    }
                }

                expenseTypeAdapter.updateList(expenseTypeList);
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

    NetworkListener createListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                ExpenseTypeModel expenseType = (ExpenseTypeModel) responseBody.getResutData();
                if(expenseTypeModel!=null)
                {
                    expenseTypeModel.setId(expenseType.getId());

                    expenseTypeList.add(expenseTypeModel);

                    expenseTypeAdapter.updateList(expenseTypeList);
                }

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
