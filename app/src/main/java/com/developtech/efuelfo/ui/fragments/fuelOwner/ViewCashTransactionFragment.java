package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.responseModel.ViewCashTransactionResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.TransactionDetailsActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.ViewCashTransactionsAdapter;
import com.developtech.efuelfo.ui.dialogFragments.FilterTransDialogFragment;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.developtech.efuelfo.ui.activities.stationOwner.TransactionDetailsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCashTransactionFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.rvViewCashTrans)
    RecyclerView rvViewCash;

    @BindView(R.id.lytInvoiceRecieve)
    LinearLayout lytInvoiceRecieve;

    public int page=0, count =10;
    LinearLayoutManager layoutManager;

    public int fuelType;
    public boolean date;
    public String startDate,endDate;

    public ViewCashTransactionFragment() {
        // Required empty public constructor
    }

    View view;

    List<ViewCashTransactionResponseModel> transactionsList = new ArrayList<>();
    ViewCashTransactionsAdapter saleHistoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_cash_transaction, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvViewCash.setLayoutManager(layoutManager);
        saleHistoryAdapter = new ViewCashTransactionsAdapter(transactionsList, appComponent, this);
        rvViewCash.setAdapter(saleHistoryAdapter);
        rvViewCash.addOnScrollListener(scrollListener);
        callApi();
    }

    @Override
    public void retry() {

    }


    public void callApi()
    {
        fromFilter = false;
       // PaginationModel paginationModel = new PaginationModel(count, page);
        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        requestModel.setPage(page);
        requestModel.setLimit(count);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().findCashTransactions(requestModel), findCashTransactionListener);
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

    boolean fromFilter;

    @Override
    public void onFilterClick() {
        FilterTransDialogFragment dialogFragment = new FilterTransDialogFragment();
        dialogFragment.setFilterListner(new FilterTransDialogFragment.FilterListner() {
            @Override
            public void applyFilter(SearchScheduleRequestModel searchScheduleRequestModel) {
                fromFilter = true;
                appComponent.getServiceCaller().callService(appComponent.getAllApis().findCashTransactions(searchScheduleRequestModel), findCashTransactionListener);

            }

            @Override
            public void clearFilter() {
                transactionsList.clear();
                saleHistoryAdapter.updateList(transactionsList, true);
            }
        });
        dialogFragment.setData(appComponent, this);
        dialogFragment.show(getFragmentManager(), "");
    }

    NetworkListener findCashTransactionListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<ViewCashTransactionResponseModel> responseModels  = (List<ViewCashTransactionResponseModel>) responseBody.getResutData();
                if (fromFilter)
                {
                    transactionsList = responseModels;
                    saleHistoryAdapter.updateList(transactionsList, false);
                }
                else
                {
                    transactionsList.addAll(responseModels);
                    saleHistoryAdapter.updateList(transactionsList,false);
                }

                if (transactionsList.size() > 1) {
                    hideNoRecords();
                } else {
                    showNoRecords();
                }
                fromFilter = false;
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            saleHistoryAdapter.removeProgress();
            if (getActivity()==null)
            {
                return;
            }

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

                saleHistoryAdapter.addProgress();
            }
        }
    };

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("cash", transactionsList.get(position));
        newIntent(TransactionDetailsActivity.class, bundle, false);
    }
}
