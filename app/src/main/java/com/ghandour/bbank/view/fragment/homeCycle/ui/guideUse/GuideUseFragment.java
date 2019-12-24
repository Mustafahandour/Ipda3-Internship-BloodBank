package com.ghandour.bbank.view.fragment.homeCycle.ui.guideUse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ghandour.bbank.R;
import com.ghandour.bbank.view.fragment.BaseFragment;

public class GuideUseFragment extends BaseFragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_guide_use, container, false);
        return root;
    }
}