package com.ghandour.bbank.view.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.login.restPassword.RestPassword;
import com.ghandour.bbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;
import static com.ghandour.bbank.helper.HelperMethod.replace;


public class ForgetPasswordFragment extends BaseFragment {

    @BindView(R.id.login_forgetPass_tv_fragment)
    TextView loginForgetPassTvFragment;
    @BindView(R.id.phone_et_login_fragment)
    EditText phoneEtLoginFragment;
    @BindView(R.id.register_bt_login_fragment)
    Button registerBtLoginFragment;
    private Unbinder unbinder;
    private ApiServices apiServices;
    public static String phone;

    public ForgetPasswordFragment() {
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
        View view = inflater.inflate(R.layout.fragment_password_forget, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        return view;
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

    @OnClick(R.id.register_bt_login_fragment)
    public void onViewClicked() {
        phone = phoneEtLoginFragment.getText().toString();
        getResetPassword(phone);

    }

    private void getResetPassword(String phone) {
        apiServices.resetPassword(phone).enqueue(new Callback<RestPassword>() {
            @Override
            public void onResponse(Call<RestPassword> call, Response<RestPassword> response) {
                if (response.body().getStatus() == 1) {
                    Toast.makeText(baseActivity, response.body().getData().getPinCodeForTest() + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    ResetPasswordFragment resetPasswordFragment = new ResetPasswordFragment();
                    resetPasswordFragment.phone = phone;
                    replace(resetPasswordFragment, getActivity().getSupportFragmentManager(), R.id.splash_user_cycle_fl_fragment_container, null, null);
                } else {
                    Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<RestPassword> call, Throwable t) {

            }
        });
    }

}


