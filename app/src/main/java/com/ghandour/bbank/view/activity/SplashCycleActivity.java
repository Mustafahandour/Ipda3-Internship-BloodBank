package com.ghandour.bbank.view.activity;


import android.os.Bundle;

import com.ghandour.bbank.R;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.fragment.splashCycle.SplashFragment;

public class SplashCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_user_cycle);
        SplashFragment splashFragment = new SplashFragment();
        HelperMethod.replace(splashFragment , getSupportFragmentManager() , R.id.splash_user_cycle_fl_fragment_container ,null ,null);
    }
}
