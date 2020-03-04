package com.developtech.efuelfo.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by dt on 12/26/17.
 */

public class StationOwnerRegistrationAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList;

    public StationOwnerRegistrationAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return fragmentList.get(0);
            }
            case 1: {
                return fragmentList.get(1);
            }
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0: {
                title = "PROFILE";
                break;
            }
            case 1: {
                title = "FUEL STATION";
                break;
            }
        }
        return title;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
