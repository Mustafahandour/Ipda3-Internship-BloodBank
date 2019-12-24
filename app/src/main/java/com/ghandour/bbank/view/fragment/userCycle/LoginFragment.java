package com.ghandour.bbank.view.fragment.userCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.local.SharedPreferencesManger;
import com.ghandour.bbank.data.model.login.Login;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.activity.HomeActivity;
import com.ghandour.bbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.REMEMBER;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.SaveData;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.saveUserData;
import static com.ghandour.bbank.helper.HelperMethod.disappearKeypad;
import static com.ghandour.bbank.helper.HelperMethod.dismissProgressDialog;
import static com.ghandour.bbank.helper.HelperMethod.replace;
import static com.ghandour.bbank.helper.HelperMethod.showProgressDialog;


public class LoginFragment extends BaseFragment {

    @BindView(R.id.phone_et_login_fragment)
    EditText phoneEtLoginFragment;
    @BindView(R.id.password_et_login_fragment)
    EditText passwordEtLoginFragment;
    @BindView(R.id.login_bt_login_fragment)
    Button loginEtLoginFragment;
    @BindView(R.id.register_bt_login_fragment)
    Button registerBtLoginFragment;
    @BindView(R.id.login_forgetPass_tv_fragment)
    TextView loginForgetPassTvFragment;
    @BindView(R.id.login_chBox_fragment)
    CheckBox loginChBoxFragment;
    @BindView(R.id.linear_login_view)
    LinearLayout linearLoginView;
    private Unbinder unbinder;
    private ApiServices apiServices;
    private String apiToken;

    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        disappearKeypad(getActivity(), view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.login_forgetPass_tv_fragment, R.id.login_bt_login_fragment, R.id.register_bt_login_fragment})
    public void onViewClicked(View view) {
        disappearKeypad(getActivity(),view);
        switch (view.getId()) {
            case R.id.linear_login_view:
                break;
            case R.id.login_forgetPass_tv_fragment:
                ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
                replace(forgetPasswordFragment, getActivity().getSupportFragmentManager(), R.id.splash_user_cycle_fl_fragment_container, null, null);
                break;
            case R.id.login_bt_login_fragment:

                String phone = phoneEtLoginFragment.getText().toString();
                String password = passwordEtLoginFragment.getText().toString();
                getLogin(phone, password);


                break;
            case R.id.register_bt_login_fragment:
                RegisterFragment registerFragment = new RegisterFragment();
                replace(registerFragment, getActivity().getSupportFragmentManager(), R.id.splash_user_cycle_fl_fragment_container, null, null);
                break;
        }
    }

    private void getLogin(String phone, String password) {
        showProgressDialog(getActivity(), getString(R.string.please_wait));

        apiServices.getClient(phone, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                dismissProgressDialog();
                if (response.body().getStatus() == 1) {
                    saveUserData(getActivity(), response.body().getData());
                    if (loginChBoxFragment.isChecked()) {
                        apiToken = response.body().getData().getApiToken();
                     SaveData(getActivity(),REMEMBER , true);
                    }
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
    public void onBack() {


    }


}


