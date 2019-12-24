package com.ghandour.bbank.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.local.SharedPreferencesManger;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.activity.HomeActivity;
import com.ghandour.bbank.view.fragment.BaseFragment;

import static com.ghandour.bbank.data.local.SharedPreferencesManger.LoadBoolean;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.REMEMBER;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.USER_DATA;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.loadUserData;
import static com.ghandour.bbank.helper.HelperMethod.replace;


public class SplashFragment extends BaseFragment {

    private long SPLASH_DISPLAY_LENGTH = 2000;

    public SplashFragment() {
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
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if(LoadBoolean(getActivity(),REMEMBER)&&loadUserData(getActivity()) !=null){

                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    SliderFragment sliderFragment = new SliderFragment();
                    replace(sliderFragment , getActivity().getSupportFragmentManager() , R.id.splash_user_cycle_fl_fragment_container ,null ,null);
                }



            }
        }, SPLASH_DISPLAY_LENGTH);

        return  view;
    }

}

