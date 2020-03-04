package com.developtech.efuelfo.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dt on 3/19/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList;
    ArrayList<String> titleList;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, ArrayList<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
