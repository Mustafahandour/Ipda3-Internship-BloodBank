package com.ghandour.bbank.view.fragment.homeCycle.ui.aboutApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.contactUs.setting.Settings;
import com.ghandour.bbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;

public class AboutAppFragment extends BaseFragment {


    @BindView(R.id.about_app_fragment_tv)
    TextView aboutAppFragmentTv;
    private Unbinder unbinder;
    private ApiServices apiServices;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);
        unbinder = ButterKnife.bind(this, view);
         apiServices = getClient().create(ApiServices.class);


        aboutApp();

        return view;
    }

    private void aboutApp( ) {
        apiServices.appSetting("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.body().getStatus() == 1) {
                    aboutAppFragmentTv.setText(response.body().getData().getAboutApp());

                }
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {

            }
        });

    }
}