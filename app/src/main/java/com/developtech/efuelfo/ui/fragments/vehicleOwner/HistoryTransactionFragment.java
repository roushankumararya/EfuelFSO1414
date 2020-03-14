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

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.ui.activities.common.TransactionDetailsActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.PaymentHistoryAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.developtech.efuelfo.ui.activities.stationOwner.TransactionDetailsActivity;

public class HistoryTransactionFragment extends BaseFragment implements OnItemClickListener {

    private View view;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private String page;
    private PaymentHistoryAdapter adapter;
    private List<PaymentHistoryResponseModel> modelList;
    public int RequestCode = 100;

    public HistoryTransactionFragment() {

    }

    public static HistoryTransactionFragment newInstance(String param1, String param2) {
        HistoryTransactionFragment fragment = new HistoryTransactionFragment();
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
        Bundle bundle = getArguments();
        if (bundle != null) {
            page = bundle.getString("page");
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        modelList = new ArrayList<>();
        adapter = new PaymentHistoryAdapter(modelList, appComponent, this);
        recycleView.setAdapter(adapter);
    }

    public void setData(List<PaymentHistoryResponseModel> modelList) {
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
        bundle.putSerializable("model", modelList.get(position));
        newIntent(TransactionDetailsActivity.class,bundle,false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String id = bundle.getString("id");
                for (PaymentHistoryResponseModel model : modelList) {
                    if (model.getId().equalsIgnoreCase(id)) {
                        model.setStatus("TERMINATED");
                        break;
                    }
                }
                adapter.refreshData(modelList);
            }
        }
    }
}
