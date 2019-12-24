package com.ghandour.bbank.view.fragment.homeCycle.ui.myProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.CustomSpinnerAdapter;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.DateTxt;
import com.ghandour.bbank.data.model.login.profile.Profile;
import com.ghandour.bbank.helper.GeneralRequest;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.fragment.BaseFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;
import static com.ghandour.bbank.helper.HelperMethod.showCalender;


public class MyProfileFragment extends BaseFragment {
    @BindView(R.id.profile_name_account_et_fragment)
    EditText profileNameAccountEtFragment;
    @BindView(R.id.profile_email_account_et_fragment)
    EditText profileEmailAccountEtFragment;
    @BindView(R.id.profile_birthday_et_fragment)
    TextView profileBirthdayEtFragment;
    @BindView(R.id.profile_blood_type_sp_fragment)
    Spinner profileBloodTypeSpFragment;
    @BindView(R.id.profile_last_donate_date_et_fragment)
    TextView profileLastDonateDateEtFragment;
    @BindView(R.id.profile_governorate_sp_fragment)
    Spinner profileGovernorateSpFragment;
    @BindView(R.id.profile_cities_sp_fragment)
    Spinner profileCitiesSpFragment;
    @BindView(R.id.profile_phone_et_fragment)
    EditText profilePhoneEtFragment;
    @BindView(R.id.password_et_profile_fragment)
    EditText passwordEtProfileFragment;
    @BindView(R.id.password_confirm_et_profile_fragment)
    EditText passwordConfirmEtProfileFragment;
    @BindView(R.id.profile_bt_edit_fragment)
    Button profileBtEditFragment;
    private Unbinder unbinder;
    private ApiServices apiServices;
    private CustomSpinnerAdapter governmentsAdapter;
    private CustomSpinnerAdapter citiesAdapter;
    private String name;
    private String mail;
    private String birthday;
    private String lastDonate;
    private String phone;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        getProfileInfo();

        return view;
    }

    private void getProfileInfo() {
        apiServices.getProfile("JwaMPH0lyUU7EKmTdXFeT0ovsHGJuvU2EtiJpNtR4XbMkYRCtsy4Q9DOpaae").enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.body().getStatus() == 1) {
                    profileNameAccountEtFragment.setText(response.body().getData().getClient().getName());
                    profileEmailAccountEtFragment.setText(response.body().getData().getClient().getEmail());
                    profileBirthdayEtFragment.setText(response.body().getData().getClient().getBirthDate());

                    governmentsAdapter = new CustomSpinnerAdapter(getContext());
                    citiesAdapter = new CustomSpinnerAdapter(getContext());
                    profileBloodTypeSpFragment.getSelectedItemPosition();
                    profileGovernorateSpFragment.getSelectedItemPosition();
                    profileCitiesSpFragment.getSelectedItemPosition();
                     profileNameAccountEtFragment.setText(response.body().getData().getClient().getDonationLastDate());
                    profileNameAccountEtFragment.setText(response.body().getData().getClient().getPhone());
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.profile_bt_edit_fragment)
    public void onViewClicked() {
        name = profilePhoneEtFragment.getText().toString();
        mail = profileEmailAccountEtFragment.getText().toString();
        birthday = profileBirthdayEtFragment.getText().toString();
        lastDonate = profileLastDonateDateEtFragment.getText().toString();
        phone = profilePhoneEtFragment.getText().toString();

    }

    @OnClick({R.id.profile_birthday_et_fragment, R.id.profile_last_donate_date_et_fragment})
    public void onViewClicked(View view) {
        Calendar calendar = Calendar.getInstance();
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        DateTxt dateTxt = new DateTxt(day, month, year, day + "-" + month + "-" + year);

        switch (view.getId()) {

            case R.id.profile_birthday_et_fragment:
                showCalender(getActivity(), null, profileBirthdayEtFragment, dateTxt);

                break;
            case R.id.profile_last_donate_date_et_fragment:
                showCalender(getActivity(), null, profileLastDonateDateEtFragment, dateTxt);

                break;
        }
    }
}