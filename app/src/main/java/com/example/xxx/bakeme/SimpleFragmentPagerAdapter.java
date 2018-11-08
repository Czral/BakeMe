package com.example.xxx.bakeme;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager manager) {

        super(manager);
    }


    @Override
    public Fragment getItem(int i) {

        if (i == 0) {
            return new StepsFragment();
        }
        else {
            return new VideoFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "STEPS";
        }
        else {
            return"VIDEOS";
        }
    }
}
