package com.ghandour.bbank.view.fragment.homeCycle.ui.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.homeAdapter.NotificationAdapter;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.notification.Notification;
import com.ghandour.bbank.data.model.notification.NotificationData;
import com.ghandour.bbank.helper.OnEndLess;
import com.ghandour.bbank.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;


public class NotificationFragment extends BaseFragment {

    @BindView(R.id.notification_fragment_rv)
    RecyclerView notificationFragmentRv;
    private Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private NotificationAdapter notificationAdapter;
    private ApiServices apiServices;
    List< NotificationData > notificationDataList = new ArrayList<>();
    private int maxPage;
    private OnEndLess onEndLess;

    public NotificationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        init();


        return view;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        notificationFragmentRv.setLayoutManager(linearLayoutManager);
        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page<= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getNotifications(current_page);
                    }else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }

                }

            }
        };
        notificationAdapter = new NotificationAdapter(getActivity(),notificationDataList );
        notificationFragmentRv.setAdapter(notificationAdapter);

        getNotifications(1);

    }

    private void getNotifications(int page) {
        apiServices.getNotification("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", page).enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {

                try {
                    if (response.body().getStatus()== 1) {
                        maxPage = response.body().getData().getLastPage();
                        notificationDataList.addAll(response.body().getData().getData());
                        notificationAdapter.notifyDataSetChanged();

                    }
                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}

