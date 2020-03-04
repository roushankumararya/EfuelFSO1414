package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.SaleHistoryPagerAdapter;
import com.developtech.efuelfo.ui.dialogFragments.FilterTransDialogFragment;
import com.developtech.efuelfo.ui.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleHistoryInvoicesFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    SaleHistoryPagerAdapter adapter;

    View view;

    public SaleHistoryInvoicesFragment() {
        // Required empty public constructor
    }

    OnlineTransactionFragment onlineTransFrag = new OnlineTransactionFragment();
    ViewCashTransactionFragment viewCashTransFrag = new ViewCashTransactionFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_sale_history_invoices, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Fragment> fragmentList = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putString("page", "SALE");
        onlineTransFrag.setArguments(bundle);
        viewCashTransFrag.setArguments(bundle);
        fragmentList.add(onlineTransFrag);
        fragmentList.add(viewCashTransFrag);
        adapter = new SaleHistoryPagerAdapter(getChildFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        view_pager.setOffscreenPageLimit(2);
        tab_layout.setupWithViewPager(view_pager);

    }


    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {
       if (view_pager.getCurrentItem()==0)
       {
           onlineTransFrag.onFilterClick();
       }
       else
       {
           viewCashTransFrag.onFilterClick();
       }
    }

    @Override
    public void onResume() {
        super.onResume();

        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.showFilter(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.showFilter(false);
    }
}
