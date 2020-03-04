package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.responseModel.SaleFuelTypeResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.stationOwner.TotalSaleFuelTypeAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalSaleFuelTypeFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;

    @BindView(R.id.rvTotalSale)
    RecyclerView rvTotalSale;

    TotalSaleFuelTypeAdapter fuelTypeAdapter;

    View view;

    List<SaleFuelTypeResponseModel> saleFuelTypeList = new ArrayList<>();

    public TotalSaleFuelTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_total_sale, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
requestModel.setLimit(10);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().findSaleByFuelType(requestModel), saleFuelTypeListener);
        fuelTypeAdapter = new TotalSaleFuelTypeAdapter(saleFuelTypeList,this, appComponent);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTotalSale.setLayoutManager(layoutManager);
        rvTotalSale.setAdapter(fuelTypeAdapter);

    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    NetworkListener saleFuelTypeListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                saleFuelTypeList = (List<SaleFuelTypeResponseModel>) responseBody.getResutData();
                if (saleFuelTypeList.size()>0) {
                    fuelTypeAdapter.updateList(saleFuelTypeList);
                }
                else
                {
                    showNoRecords();
                }
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
}
