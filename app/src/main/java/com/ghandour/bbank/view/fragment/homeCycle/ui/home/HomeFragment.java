package com.ghandour.bbank.view.fragment.homeCycle.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.homeAdapter.HomeViewPagerAdapter;
import com.ghandour.bbank.view.fragment.BaseFragment;
import com.ghandour.bbank.view.fragment.homeCycle.DonationFragment;
import com.ghandour.bbank.view.fragment.homeCycle.PostFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initFragment();

        ViewPager homeViewPager = view.findViewById(R.id.home_view_pager);
        TabLayout homeTab = view.findViewById(R.id.home_tab);
        homeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(homeTab));
        DonationFragment donationFragment = new DonationFragment();
        PostFragment postFragment = new PostFragment();
        HomeViewPagerAdapter postViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        postViewPagerAdapter.addPager(donationFragment, getString(R.string.donate));
        postViewPagerAdapter.addPager(postFragment, getString(R.string.posts));

        homeViewPager.setAdapter(postViewPagerAdapter);
        homeTab.setupWithViewPager(homeViewPager);


        return view;
    }


}