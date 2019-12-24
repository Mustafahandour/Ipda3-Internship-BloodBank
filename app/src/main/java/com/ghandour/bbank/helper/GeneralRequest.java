package com.ghandour.bbank.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.CustomSpinnerAdapter;
import com.ghandour.bbank.data.model.generalResponse.GeneralResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.helper.HelperMethod.dismissProgressDialog;
import static com.ghandour.bbank.helper.HelperMethod.showProgressDialog;


public class GeneralRequest {

    public static void getSpinnerData( final Spinner spinner, final CustomSpinnerAdapter adapter, final String hint, Call<GeneralResponse> method) {
        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                if (response.body().getStatus() == 1) {
                    adapter.setData(response.body().getData(), hint);
                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {


            }
        });
    }

    public static void getSpinnerData(final Spinner spinner, final CustomSpinnerAdapter adapter,
                                      final String hint, Call<GeneralResponse> method, final Spinner spinner2, final CustomSpinnerAdapter adapter2,
                                      final String hint2, Call<GeneralResponse> method2) {
        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {


                    if (response.body().getStatus() == 1) {
                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    spinner2.setVisibility(view.VISIBLE);
                                    getSpinnerData(spinner2, adapter2, hint2, method2);

                                }else {
                                    spinner2.setVisibility(view.GONE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }
}



