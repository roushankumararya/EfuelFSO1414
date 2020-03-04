package com.developtech.efuelfo.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends BaseFragment {

    @BindView(R.id.rvOffers)
    RecyclerView rvOffers;

    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }
}
