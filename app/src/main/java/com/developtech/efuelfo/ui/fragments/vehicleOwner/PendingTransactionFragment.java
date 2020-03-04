package com.developtech.efuelfo.ui.fragments.vehicleOwner;

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
import com.developtech.efuelfo.ui.activities.stationOwner.RequestPendingActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.PaymentHistoryAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingTransactionFragment extends BaseFragment implements OnItemClickListener {

    private View view;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private PaymentHistoryAdapter adapter;
    private List<PaymentHistoryResponseModel> modelList;

    public PendingTransactionFragment() {

    }

    public static PendingTransactionFragment newInstance(String param1, String param2) {
        PendingTransactionFragment fragment = new PendingTransactionFragment();
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
        bundle.putBoolean("isApproved", false);
        bundle.putString("page", "paymentHistory");
        bundle.putSerializable("model",modelList.get(position));
        Intent intent = new Intent(getContext(), RequestPendingActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
