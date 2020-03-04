package com.developtech.efuelfo.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.developtech.efuelfo.ui.fragments.TutorialFragment;

public class TutorialSliderAdapter extends FragmentStatePagerAdapter {

    public TutorialSliderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TutorialFragment().setPos(position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
