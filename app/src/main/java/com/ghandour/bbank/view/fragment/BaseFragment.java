package com.ghandour.bbank.view.fragment;

import android.os.Bundle;
import android.util.Base64DataException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ghandour.bbank.R;
import com.ghandour.bbank.view.activity.BaseActivity;
import com.ghandour.bbank.view.activity.HomeActivity;


public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;
    public HomeActivity homeActivity;

    public void initFragment() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;

        try {
            homeActivity = (HomeActivity) getActivity();
        } catch (Exception e) {

        }
    }

    public void onBack() {
        baseActivity.superBackPressed();


    }
}

