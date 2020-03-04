package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.DispenseTransRequestModel;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.VerifyTransactionRequestModel;
import com.developtech.efuelfo.model.responseModel.VerifyTransResponseModel;
import com.developtech.efuelfo.model.responseModel.VerifyVehicleModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.stationOwner.VerifyTransactionDetailActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.QRScanActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.VerifyTransactionAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyTransactionFragment extends BaseFragment implements VehicleOwnerItemClick {

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    @BindView(R.id.etVerifyTransSearch)
    EditText etVerifyTransSearch;

    @BindView(R.id.rvVerifyTrans)
    RecyclerView rvVerifyTrans;

    LinearLayoutManager layoutManager;
    int page = 0, count = 10;

    private int scanRequestCode = 100;

    View view;

    public VerifyTransactionFragment() {
        // Required empty public constructor
    }

    List<VerifyVehicleModel> verifyModelList = new ArrayList<>();

    VerifyTransactionAdapter adapter;

    String transId, fuelStationId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_verify_transaction, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle!=null && bundle.getString("action_noti")!=null)
        {
            String transId = bundle.getString("id");
            String fuelStationId = bundle.getString("fuelStationId");

            DeleteDriverRequestModel requestModel = new DeleteDriverRequestModel(transId);
            requestModel.setFuelStationId(fuelStationId);
            appComponent.getServiceCaller().callService(appComponent.getAllApis().verifyTransByQrCode(requestModel), verifyTransactionListener);
        }

        etVerifyTransSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s=charSequence.toString();
                if(!s.equals(s.toUpperCase()))
                {
                    s=s.toUpperCase();
                    etVerifyTransSearch.setText(s);
                    etVerifyTransSearch.setSelection(etVerifyTransSearch.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvVerifyTrans.setLayoutManager(layoutManager);
        adapter = new VerifyTransactionAdapter(this, verifyModelList, appComponent);
        rvVerifyTrans.setAdapter(adapter);
        rvVerifyTrans.addOnScrollListener(scrollListener);

    }

    @OnClick({R.id.ivSearch, R.id.ivQrCode})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ivSearch: {
                hideKB();
                if (etVerifyTransSearch.getText().toString().trim().isEmpty()){
                    showMsg(rootLayout, getResources().getString(R.string.enter_vehical_number));
                    return;
                }

                verifyModelList.clear();
                page = 0;

                callApi();

                break;
            }
            case R.id.ivQrCode : {

                verifyModelList.clear();
                page = 0;

                Intent intent = new Intent(getContext(), QRScanActivity.class);
                startActivityForResult(intent, scanRequestCode);
                break;
            }

        }
    }

    void callApi()
    {
        VerifyTransactionRequestModel requestModel = new VerifyTransactionRequestModel(etVerifyTransSearch.getText().toString(), appComponent.getSpUtils().getFuelStationModel().getId());
        requestModel.setLimit(count);
        requestModel.setPage(page);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().verifyTransaction(requestModel), verifyTransactionListener);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int last = layoutManager.findLastCompletelyVisibleItemPosition()+1;
            if (last == (page + count))
            {
                if (page>0)
                {
                    if (last== (count*page)) {
                        page++;
                        callApi();
                    }
                }
                else
                {
                    if (last==count)
                    {
                        page++;
                        callApi();
                    }
                }
            }
        }
    };


    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onCLick(int position) {
        Bundle bundle = new Bundle();
        VerifyVehicleModel verifyVehicleModel = verifyModelList.get(position);
        verifyVehicleModel.setVehicleNumber(etVerifyTransSearch.getText().toString().trim());
        bundle.putSerializable("model",verifyVehicleModel);
        Intent intent = new Intent(getContext(), VerifyTransactionDetailActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 345);
    }


    NetworkListener verifyTransactionListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<VerifyVehicleModel> responseModels = (List<VerifyVehicleModel>) responseBody.getResutData();
                if (responseModels.size()>0) {
                    verifyModelList.addAll(responseModels);
                    adapter.updateList(verifyModelList);
                }
                if (verifyModelList.size()>0)
                {
                    hideNoRecords();
                }
                else
                {
                    showNoRecords();
                }
            }
            else
            {
                showNoRecords();
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {

            if (getActivity()==null)
                return;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                    adapter.removeProgress();
                }
            });
        }

        @Override
        public void onStart() {
            if (page == 0)
            {
                showProgress();
            }
            else {
                adapter.addProgress();
            }
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == scanRequestCode && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String id = bundle.getString("string");
                    if (id != null) {
                        DeleteDriverRequestModel requestModel = new DeleteDriverRequestModel(id);
                        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                        appComponent.getServiceCaller().callService(appComponent.getAllApis().verifyTransByQrCode(requestModel), verifyTransactionListener);
                    }
                }
            }
        }
        else if (requestCode==345 && resultCode == Activity.RESULT_OK)
        {
            adapter.clearList();

            if (!etVerifyTransSearch.getText().toString().trim().isEmpty()){
                verifyModelList.clear();
                page = 0;

                callApi();
            }



        }
    }
}
