package com.ghandour.bbank.view.fragment.homeCycle.ui.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.homeAdapter.NotificationBloodTypeSettingAdapter;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.generalResponse.GeneralResponse;
import com.ghandour.bbank.data.model.generalResponse.GeneralResponseData;
import com.ghandour.bbank.data.model.notification.notificationSetting.NotificationSetting;
import com.ghandour.bbank.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;


public class NotificationSettingFragment extends BaseFragment {


    @BindView(R.id.notification_fragment_setting_tv_title)
    TextView notificationFragmentSettingTvTitle;
    @BindView(R.id.notification_fragment_setting_rv_bloodType)
    RecyclerView notificationFragmentSettingRvBloodType;
    @BindView(R.id.notification_fragment_setting_rel_bloods_gone)
    RelativeLayout notificationFragmentSettingRelBloodsGone;
    @BindView(R.id.notification_fragment_setting_iv_bloodType)
    ImageView notificationFragmentSettingIvBloodType;
    @BindView(R.id.notification_fragment_setting_tv_bloodType)
    TextView notificationFragmentSettingTvBloodType;
    @BindView(R.id.notification_fragment_setting_rel_bloodType_title)
    RelativeLayout notificationFragmentSettingRelBloodTypeTitle;
    @BindView(R.id.notification_fragment_setting_rel_bloods)
    RelativeLayout notificationFragmentSettingRelBloods;
    @BindView(R.id.notification_fragment_setting_rv_government)
    RecyclerView notificationFragmentSettingRvGovernment;
    @BindView(R.id.notification_fragment_setting_rel_government_gone)
    RelativeLayout notificationFragmentSettingRelGovernmentGone;
    @BindView(R.id.notification_fragment_setting_iv_government)
    ImageView notificationFragmentSettingIvGovernment;
    @BindView(R.id.notification_fragment_setting_tv_government)
    TextView notificationFragmentSettingTvGovernment;
    @BindView(R.id.notification_fragment_setting_rel_government_title)
    RelativeLayout notificationFragmentSettingRelGovernmentTitle;
    @BindView(R.id.notification_fragment_setting_rel_government)
    RelativeLayout notificationFragmentSettingRelGovernment;
    @BindView(R.id.notification_fragment_setting_bt_save)
    Button notificationFragmentSettingBtSave;
    @BindView(R.id.notification_fragment_setting_rel_sub_view)
    RelativeLayout notificationFragmentSettingRelSubView;
    private NotificationBloodTypeSettingAdapter BloodTypeSettingAdapter, GovernmentSettingAdapter;
    private List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private Unbinder unbinder;
    private ApiServices apiServices;
    private List<String> bloodType = new ArrayList<>(), government = new ArrayList<>();

    public NotificationSettingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        inti();
        getNotificationSetting();
        return view;
    }

    private void inti() {
        notificationFragmentSettingRvBloodType.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        notificationFragmentSettingRvGovernment.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }


    private void getNotificationSetting() {

        apiServices.getNotificationsSettings("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<NotificationSetting>() {
            @Override
            public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        bloodType = response.body().getData().getBloodTypes();
                        government = response.body().getData().getGovernorates();
                        getBloodType();
                        getGovernment();
                    }

                } catch (Exception e) {

                }

            }


            @Override
            public void onFailure(Call<NotificationSetting> call, Throwable t) {
                try {

                } catch (Exception e) {

                }

            }
        });
    }


    private void getBloodType() {
        apiServices.getBloodType().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {

                    BloodTypeSettingAdapter = new NotificationBloodTypeSettingAdapter(getActivity(),getActivity(), response.body().getData(), bloodType);
                    notificationFragmentSettingRvBloodType.setAdapter(BloodTypeSettingAdapter);

                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });

    }

    private void getGovernment() {
        apiServices.getGovernrates().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    GovernmentSettingAdapter = new NotificationBloodTypeSettingAdapter(getActivity(),getActivity(), response.body().getData(), government);
                    notificationFragmentSettingRvGovernment.setAdapter(GovernmentSettingAdapter);


                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.notification_fragment_setting_iv_bloodType, R.id.notification_fragment_setting_iv_government, R.id.notification_fragment_setting_bt_save, R.id.notification_fragment_setting_rel_sub_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notification_fragment_setting_iv_bloodType:
                visible(notificationFragmentSettingRelBloodsGone,notificationFragmentSettingIvBloodType);
                break;
            case R.id.notification_fragment_setting_iv_government:
                visible(notificationFragmentSettingRelGovernmentGone, notificationFragmentSettingIvGovernment);
                break;
            case R.id.notification_fragment_setting_bt_save:
                onCall(false);
                break;
            case R.id.notification_fragment_setting_rel_sub_view:
                break;
        }
    }


    private void visible(View view ,  ImageView imageView) {
        if (view.getVisibility()== View.GONE) {
            view.setVisibility(View.VISIBLE);

            imageView.setImageResource(R.drawable.ic_minus_solid);
        }else {
            imageView.setImageResource(R.drawable.ic_plus_solid);
            view.setVisibility(View.GONE);
        }

    }

    private void onCall(final boolean state) {
        apiServices.setNotificationsSettings("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl"
                ,GovernmentSettingAdapter.newIds,BloodTypeSettingAdapter.newIds).enqueue(new Callback<NotificationSetting>() {
            @Override
            public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<NotificationSetting> call, Throwable t) {

            }
        });

    }
}