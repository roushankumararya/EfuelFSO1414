package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.developtech.efuelfo.model.responseModel.CreditAgreementResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.stationOwner.RequestPendingActivity;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.CreditAgreementAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends BaseFragment implements OnItemClickListener {

    private View view;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.lytInvoiceRecieve)
    LinearLayout lytInvoiceRecieve;

    private List<CreditAgreementResponseModel> modelList;
    private CreditAgreementAdapter adapter;
    public int RequestCode = 100;

    List<CreditAgreementResponseModel> historyModelList = new ArrayList<>();

    int page = 0, count = 10;

    LinearLayoutManager layoutManager;


    public HistoryFragment() {

    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pending, container, false);
        init(view);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lytInvoiceRecieve.setVisibility(View.GONE);

        layoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManager);
        modelList = new ArrayList<>();
        adapter = new CreditAgreementAdapter(this, getContext(), modelList, appComponent, true);
        recycleView.setAdapter(adapter);
        recycleView.addOnScrollListener(scrollListener);

        callApi();
    }

    public void setData(List<CreditAgreementResponseModel> modelList) {
        this.modelList.addAll(modelList);
        adapter.refreshData(modelList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isApproved", true);
        bundle.putString("page", "creditAgreement");
        bundle.putSerializable("model", historyModelList.get(position));
        Intent intent = new Intent(getContext(), RequestPendingActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, RequestCode);
    }


    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int last = layoutManager.findLastCompletelyVisibleItemPosition();
            if (last == (page + count))
            {
                page = page + count;
                callApi();
            }
        }
    };


    void callApi()
    {
        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();

        if (appComponent.getSpUtils().getFuelStationModel()!=null && appComponent.getSpUtils().getFuelStationModel().getId()!=null)
        {
            requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        }

        requestModel.setLimit(count);
        requestModel.setPage(page);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().vcoAgreementHistory(requestModel), agreementHistoryListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RequestCode && resultCode == Activity.RESULT_OK && data != null) {
//            Bundle bundle = data.getExtras();
//            if (bundle != null) {
//                String id = bundle.getString("id");
//                for (CreditAgreementResponseModel model : modelList) {
//                    if (model.getId().equalsIgnoreCase(id)) {
//                        model.setStatus("TERMINATED");
//                        break;
//                    }
//                }
//                adapter.refreshData(modelList);
//            }
//        }
//        else
        if (requestCode==RequestCode && resultCode == 200)
        {
            page=0;
            historyModelList.clear();
            callApi();
        }
    }


    NetworkListener agreementHistoryListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<CreditAgreementResponseModel> responseModels = (List<CreditAgreementResponseModel>) responseBody.getResutData();
                if (responseModels.size()>0) {
                    hideNoRecords();
                    historyModelList.addAll(responseModels);
                    adapter.refreshData(historyModelList);
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
            if(getActivity() == null)
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
    

}
