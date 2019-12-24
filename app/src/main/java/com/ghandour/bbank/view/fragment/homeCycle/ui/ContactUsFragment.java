package com.ghandour.bbank.view.fragment.homeCycle.ui;

import android.content.Intent;
import android.content.UriPermission;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.contactUs.ContactUs;
import com.ghandour.bbank.data.model.contactUs.setting.Settings;
import com.ghandour.bbank.view.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;


public class ContactUsFragment extends BaseFragment {


    @BindView(R.id.contact_us_fragment_tv_phone)
    TextView contactUsFragmentTvPhone;
    @BindView(R.id.contact_us_fragment_tv_email)
    TextView contactUsFragmentTvEmail;
    @BindView(R.id.contact_us_fragment_bt_facebook)
    Button contactUsFragmentBtFacebook;
    @BindView(R.id.contact_us_fragment_bt_twitter)
    Button contactUsFragmentBtTwitter;
    @BindView(R.id.contact_us_fragment_bt_youtube)
    Button contactUsFragmentBtYoutube;
    @BindView(R.id.contact_us_fragment_bt_instgram)
    Button contactUsFragmentBtInstgram;
    @BindView(R.id.contact_us_fragment_bt_whatsApp)
    Button contactUsFragmentBtWhatsApp;
    @BindView(R.id.contact_us_fragment_et_name)
    EditText contactUsFragmentEtName;
    @BindView(R.id.contact_us_fragment_et_ph)
    EditText contactUsFragmentEtPh;
    @BindView(R.id.contact_us_fragment_et_message)
    EditText contactUsFragmentEtMessage;
    @BindView(R.id.contact_us_fragment_bt_send)
    Button contactUsFragmentBtSend;
    private Unbinder unbinder;
    private ApiServices apiServices;
    private String phone;
    private String email;
    private String facbookUrl;
    private String twitterUrl;
    private String instagramUrl;
    private String whatsapp;
    private String youtube;
    private List<ResolveInfo> activities;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        contactUs();

        return view;
    }

    private void contactUs() {
        apiServices.appSetting("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.body().getStatus() == 1) {
                    contactUsFragmentTvPhone.setText(response.body().getData().getPhone());
                    contactUsFragmentTvEmail.setText(response.body().getData().getEmail());
                    contactUsFragmentBtFacebook.setText(response.body().getData().getFacebookUrl());
                    contactUsFragmentBtYoutube.setText(response.body().getData().getYoutubeUrl());
                    contactUsFragmentBtTwitter.setText(response.body().getData().getTwitterUrl());
                    contactUsFragmentBtInstgram.setText(response.body().getData().getInstagramUrl());
                    contactUsFragmentBtWhatsApp.setText(response.body().getData().getWhatsapp());
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {

            }


        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.contact_us_fragment_tv_phone, R.id.contact_us_fragment_tv_email, R.id.contact_us_fragment_bt_facebook, R.id.contact_us_fragment_bt_twitter, R.id.contact_us_fragment_bt_youtube, R.id.contact_us_fragment_bt_instgram, R.id.contact_us_fragment_bt_whatsApp, R.id.contact_us_fragment_bt_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contact_us_fragment_tv_phone:
                contactUs();
                Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactUsFragmentTvPhone.getText().toString()));
                startActivity(intentPhone);

                break;
            case R.id.contact_us_fragment_tv_email:
                break;
            case R.id.contact_us_fragment_bt_facebook:
                Intent intentFacebook = new Intent(Intent.ACTION_VIEW);
                PackageManager facebookPackageManager = getActivity().getPackageManager();
                activities = facebookPackageManager.queryIntentActivities(intentFacebook,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentFacebookSafe = activities.size() > 0;
                if (isIntentFacebookSafe) {
                    startActivity(intentFacebook);
                }
                break;
            case R.id.contact_us_fragment_bt_twitter:
                Intent intentTwitter = new Intent(Intent.ACTION_VIEW);
                PackageManager twitterPackageManager = getActivity().getPackageManager();
                activities = twitterPackageManager.queryIntentActivities(intentTwitter,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentTwitterSafe = activities.size() > 0;
                if (isIntentTwitterSafe) {
                    startActivity(intentTwitter);
                }
                break;
            case R.id.contact_us_fragment_bt_youtube:
                Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
                PackageManager youtubePackageManager = getActivity().getPackageManager();
                activities = youtubePackageManager.queryIntentActivities(intentYoutube,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentYoutubeSafe = activities.size() > 0;
                if (isIntentYoutubeSafe) {
                    startActivity(intentYoutube);
                }
                break;
            case R.id.contact_us_fragment_bt_instgram:
                Intent intentInstgram = new Intent(Intent.ACTION_VIEW);
                PackageManager instgramPackageManager = getActivity().getPackageManager();
                activities = instgramPackageManager.queryIntentActivities(intentInstgram,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentInstgramSafe = activities.size() > 0;
                if (isIntentInstgramSafe) {
                    startActivity(intentInstgram);
                }
                break;
            case R.id.contact_us_fragment_bt_whatsApp:
                Intent intentWhatsApp = new Intent(Intent.ACTION_VIEW);
                PackageManager whatsAppPackageManager = getActivity().getPackageManager();
                activities = whatsAppPackageManager.queryIntentActivities(intentWhatsApp,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentWhatsAppSafe = activities.size() > 0;
                if (isIntentWhatsAppSafe) {
                    startActivity(intentWhatsApp);
                }
                break;
            case R.id.contact_us_fragment_bt_send:
                String name = contactUsFragmentEtName.getText().toString();
                String phone = contactUsFragmentEtPh.getText().toString();
                String message = contactUsFragmentEtMessage.getText().toString();
                sendMessage(name, phone, message);


                break;
        }
    }

    private void sendMessage(String name, String phone, String message) {
        apiServices.sendMessage("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", "test contact", "help me").enqueue(new Callback<ContactUs>() {
            @Override
            public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                if (response.body().getStatus() == 1) {
                    response.body().getData().getTitle();
                    response.body().getData().getMessage();
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ContactUs> call, Throwable t) {

            }
        });
    }


}