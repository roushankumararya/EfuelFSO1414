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
import retrofit2.http.Body;

public class PendingFragment extends BaseFragment implements OnItemClickListener {

    private View view;

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.lytInvoiceRecieve)
    LinearLayout lytInvoiceRecieve;

    private CreditAgreementAdapter adapter;
    private List<CreditAgreementResponseModel> modelList;
    LinearLayoutManager layoutManager;
    int page = 0, count = 10;

    List<CreditAgreementResponseModel> pendingModelList = new ArrayList<>();


    public PendingFragment() {

    }

    public static PendingFragment newInstance(String param1, String param2) {
        PendingFragment fragment = new PendingFragment();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callApi();

        layoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManager);
        modelList = new ArrayList<>();
        adapter = new CreditAgreementAdapter(this, getContext(), modelList, appComponent, false);
        recycleView.setAdapter(adapter);
        recycleView.addOnScrollListener(scrollListener);
    }

    void callApi()
    {
        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();

        if (appComponent.getSpUtils().getFuelStationModel()!=null && appComponent.getSpUtils().getFuelStationModel().getId()!=null)
        {
            requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        }

        requestModel.setLimit(count);
        requestModel.setPage(page);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().vcoPendingAgreement(requestModel), pendingAgreementListener);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isApproved", false);
        bundle.putString("page", "creditAgreement");

        bundle.putSerializable("model", pendingModelList.get(position));
        Intent intent = new Intent(getContext(), RequestPendingActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 100);

    }


    NetworkListener pendingAgreementListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<CreditAgreementResponseModel> responseModels = (List<CreditAgreementResponseModel>) responseBody.getResutData();
                if (responseModels.size()>0) {
                    pendingModelList.addAll(responseModels);
                    adapter.refreshData(pendingModelList);
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
            else
            {
                adapter.addProgress();
            }
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode== 200)
        {
            page=0;
            pendingModelList.clear();
            callApi();
            CreditAgreementFragment caFragment = (CreditAgreementFragment) getParentFragment();
            if (caFragment!=null)
                caFragment.refreshHistoryFrag();
        }
    }
}
