package com.developtech.efuelfo.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.ui.adapters.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends BaseFragment {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ViewPagerAdapter viewPagerAdapter;

    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void retry() {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> titleList = new ArrayList<>();
        titleList.add(getResources().getString(R.string.offers));
        titleList.add(getResources().getString(R.string.notification));

        OffersFragment offersFragment = new OffersFragment();
        OtherNotificationFragment otherFragment = new OtherNotificationFragment();

        List<Fragment> fragList = new ArrayList<>();
        fragList.add(offersFragment);
        fragList.add(otherFragment);

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragList, titleList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFilterClick() {

    }
}
