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
import com.developtech.efuelfo.model.requestModel.PaginationModel;
import com.developtech.efuelfo.model.requestModel.ParkTransRequestModel;
import com.developtech.efuelfo.model.responseModel.ParkTransactionResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.ParkTransactionAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.HomeFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParkTransactionFragment extends BaseFragment implements OnItemClickListener {


    @BindView(R.id.rvParkTrans)
    RecyclerView rvParkTransl;

    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;

    private ParkTransactionAdapter adapter;

    View view;

    List<ParkTransactionResponseModel> parkTransactionsList = new ArrayList<>();

    LinearLayoutManager layoutManager;
    int page = 0, count = 10;

    public ParkTransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_park_transaction, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new ParkTransactionAdapter(parkTransactionsList, appComponent, this);
        rvParkTransl.setLayoutManager(layoutManager);
        rvParkTransl.setAdapter(adapter);
        rvParkTransl.addOnScrollListener(scrollListener);

        callApi();

    }

    void callApi()
    {
        ParkTransRequestModel requestModel = new ParkTransRequestModel();
        if (appComponent.getSpUtils().getAccountType()== SPUtils.ACCOUNT_TYPES.FSO)
        {
            requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        }
        requestModel.setLimit(count);
        requestModel.setPage(page);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().findParkTransactions(requestModel), parkTransListener);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

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

    NetworkListener parkTransListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<ParkTransactionResponseModel> responseModels = (List<ParkTransactionResponseModel>) responseBody.getResutData();
                if (responseModels.size()>0) {
                    parkTransactionsList.addAll(responseModels);
                    adapter.updateList(parkTransactionsList);
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
            adapter.removeProgress();
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
    public void onItemClick(View view, int position) {
        SaleInitiationFragment fragment = new SaleInitiationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", parkTransactionsList.get(position));
        fragment.setArguments(bundle);
        HomeActivity homeActivity = (HomeActivity)getActivity();
        homeActivity.pushFragment(fragment);
        homeActivity.setHomeTitle(getResources().getString(R.string.sale_initiation_caps));
        homeActivity.navigationView.setCheckedItem(R.id.nav_station_ow_sale_init);
    }
}
