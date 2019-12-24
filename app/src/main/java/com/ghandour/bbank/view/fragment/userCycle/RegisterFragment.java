package com.ghandour.bbank.view.fragment.userCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.CustomSpinnerAdapter;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.local.SharedPreferencesManger;
import com.ghandour.bbank.data.model.DateTxt;
import com.ghandour.bbank.data.model.generalResponse.GeneralResponse;
import com.ghandour.bbank.data.model.login.Login;
import com.ghandour.bbank.helper.GeneralRequest;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.activity.HomeActivity;
import com.ghandour.bbank.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;
import static com.ghandour.bbank.helper.GeneralRequest.getSpinnerData;
import static com.ghandour.bbank.helper.HelperMethod.dismissProgressDialog;
import static com.ghandour.bbank.helper.HelperMethod.showCalender;
import static com.ghandour.bbank.helper.HelperMethod.showProgressDialog;


public class RegisterFragment extends BaseFragment {


    @BindView(R.id.register_create_account_tv_fragment)
    TextView registerCreateAccountTvFragment;
    @BindView(R.id.register_name_account_et_fragment)
    EditText registerNameAccountEtFragment;
    @BindView(R.id.register_email_account_et_fragment)
    EditText registerEmailAccountEtFragment;
    @BindView(R.id.register_birthday_et_fragment)
    TextView registerBirthdayEtFragment;
    @BindView(R.id.register_blood_type_sp_fragment)
    Spinner registerBloodTypeSpFragment;
    @BindView(R.id.register_last_donate_date_et_fragment)
    TextView registerLastDonateDateEtFragment;
    @BindView(R.id.register_governorate_sp_fragment)
    Spinner registerGovernorateSpFragment;
    @BindView(R.id.register_cities_sp_fragment)
    Spinner registerCitiesSpFragment;
    @BindView(R.id.register_phone_et_fragment)
    EditText registerPhoneEtFragment;
    @BindView(R.id.password_et_register_fragment)
    EditText passwordEtRegisterFragment;
    @BindView(R.id.password_confirm_et_register_fragment)
    EditText passwordConfirmEtRegisterFragment;
    @BindView(R.id.register_bt_login_fragment)
    Button registerBtLoginFragment;
    private DateTxt DateTxt;
    private ApiServices apiServices;
    private Unbinder unbinder;


    private CustomSpinnerAdapter bloodTybeAdapter, governmentsAdapter, citiesAdapter;


    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);

        bloodTybeAdapter = new CustomSpinnerAdapter(getContext());
        getSpinnerData(registerBloodTypeSpFragment, bloodTybeAdapter, getString(R.string.select_blood_type), apiServices.getBloodType());

        governmentsAdapter = new CustomSpinnerAdapter(getContext());
        citiesAdapter = new CustomSpinnerAdapter(getContext());

      getSpinnerData(registerGovernorateSpFragment, governmentsAdapter, getString(R.string.select_government), apiServices.getGovernrates()
                , registerCitiesSpFragment, citiesAdapter, getString(R.string.select_city), apiServices.getCity(governmentsAdapter.selectedId));
        return view;
    }


    @OnClick({R.id.register_birthday_et_fragment, R.id.register_last_donate_date_et_fragment, R.id.register_bt_login_fragment})
    public void onViewClicked(View view) {
        Calendar calendar = Calendar.getInstance();
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        DateTxt dateTxt = new DateTxt(day, month, year, day + "-" + month + "-" + year);


        switch (view.getId()) {

            case R.id.register_birthday_et_fragment:


                showCalender(getActivity(), null, registerBirthdayEtFragment, dateTxt);
                break;
            case R.id.register_last_donate_date_et_fragment:


                showCalender(getActivity(), null, registerLastDonateDateEtFragment, dateTxt);
                break;


        }
    }

    @OnClick(R.id.register_bt_login_fragment)
    public void onViewClicked() {
        String name = registerNameAccountEtFragment.getText().toString();
        String mail = registerEmailAccountEtFragment.getText().toString();
        String birthday = registerBirthdayEtFragment.getText().toString();
        String city = String.valueOf(apiServices.getCity(governmentsAdapter.selectedId));
        String phone = registerPhoneEtFragment.getText().toString();
        String lastDonate = registerLastDonateDateEtFragment.getText().toString();
        String password = passwordEtRegisterFragment.getText().toString();
        String confirmPassword = passwordConfirmEtRegisterFragment.getText().toString();
        String bloodType = String.valueOf(apiServices.getBloodType());
        getRegister(name, mail, birthday, city, phone, lastDonate, password, confirmPassword, bloodType);

    }

    private void getRegister(String name, String mail, String birthday, String city, String phone, String lastDonate, String password, String confirmPassword, String bloodType) {
        showProgressDialog(getActivity(), getString(R.string.please_wait));
        apiServices.getRegister(name, mail, birthday, city, phone, lastDonate, password, confirmPassword, bloodType).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                SharedPreferencesManger.saveUserData(getActivity(), response.body().getData());
                dismissProgressDialog();

                if (response.body().getStatus() == 1) {
                    showProgressDialog(getActivity(), "Please Wait");
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                dismissProgressDialog();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}



