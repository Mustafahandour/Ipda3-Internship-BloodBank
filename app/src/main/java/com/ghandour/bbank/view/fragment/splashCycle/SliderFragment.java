package com.ghandour.bbank.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.viewpager.widget.ViewPager;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.sliderAdapter.SlideAdapter;
import com.ghandour.bbank.view.activity.UserActivity;
import com.ghandour.bbank.view.fragment.BaseFragment;

import butterknife.Unbinder;


public class SliderFragment extends BaseFragment {

    ViewPager sliderVpFragment;
    Button sliderSkipBtFragment;
    private Unbinder unbinder;

    public SliderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        SlideAdapter slideAdapter = new SlideAdapter(getActivity());
        sliderVpFragment = view.findViewById(R.id.slider_vp_fragment);
        sliderVpFragment.setAdapter(slideAdapter);
         sliderSkipBtFragment = view.findViewById(R.id.slider_skip_bt_fragment);

        sliderSkipBtFragment.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), UserActivity.class);
                 startActivity(intent);
             }
         });
        return view;
    }


    @Override
    public void onBack() {
        baseActivity.finish();
    }
}

