package com.ghandour.bbank.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghandour.bbank.R;


public class EmptyFragment extends BaseFragment {

    public EmptyFragment() {
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
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}

