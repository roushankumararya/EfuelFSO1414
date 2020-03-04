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
import com.developtech.efuelfo.ui.adapters.stationOwner.TotalSaleOperatorAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalSaleOperatorsFragment extends BaseFragment implements OnItemClickListener {


    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;

    @BindView(R.id.rvTotalSale)
    RecyclerView rvTotalSale;

    TotalSaleOperatorAdapter operatorAdapter;

    View view;

    public TotalSaleOperatorsFragment() {
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

        operatorAdapter = new TotalSaleOperatorAdapter(this, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTotalSale.setLayoutManager(layoutManager);
        rvTotalSale.setAdapter(operatorAdapter);
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
}
