package com.ghandour.bbank.view.activity;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ghandour.bbank.R;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.fragment.BaseFragment;
import com.ghandour.bbank.view.fragment.userCycle.LoginFragment;


public class UserActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_user_cycle);
        LoginFragment loginFragment = new LoginFragment();
        HelperMethod.replace(loginFragment, getSupportFragmentManager(), R.id.splash_user_cycle_fl_fragment_container, null, null);
    }

}
