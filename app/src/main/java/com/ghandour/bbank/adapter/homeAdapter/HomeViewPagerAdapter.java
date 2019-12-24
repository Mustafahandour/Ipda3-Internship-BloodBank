package com.ghandour.bbank.adapter.homeAdapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ghandour.bbank.R;
import com.ghandour.bbank.view.fragment.BaseFragment;
import com.ghandour.bbank.view.fragment.homeCycle.DonationFragment;
import com.ghandour.bbank.view.fragment.homeCycle.PostFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    Context mContext;
    List<Fragment> fragments;
    List<String> fragmentTitle;

    public HomeViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragments = new ArrayList<>();
        this.fragmentTitle = new ArrayList<>();
    }

    public void addPager(Fragment fragment, String title) {
        this.fragments.add(fragment);
        this.fragmentTitle.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return fragmentTitle.get(position);
    }
}
