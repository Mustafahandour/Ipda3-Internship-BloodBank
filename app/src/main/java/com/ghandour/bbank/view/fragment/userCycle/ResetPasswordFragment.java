package com.ghandour.bbank.view.fragment.userCycle;

import android.content.Intent;
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
import static com.ghandour.bbank.helper.HelperMethod.showProgressDialog;


public class ResetPasswordFragment extends BaseFragment {

    @BindView(R.id.reset_Password_tv_fragment)
    TextView resetPasswordTvFragment;
    @BindView(R.id.reset_phone_code_et_fragment)
    EditText resetPhoneCodeEtFragment;
    @BindView(R.id.password_et_register_fragment)
    EditText passwordEtRegisterFragment;
    @BindView(R.id.password_confirm_et_register_fragment)
    EditText passwordConfirmEtRegisterFragment;
    @BindView(R.id.register_bt_login_fragment)
    Button registerBtLoginFragment;
    private Unbinder unbinder;
    private ApiServices apiServices;
    public String phone;

    public ResetPasswordFragment() {
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
        View view = inflater.inflate(R.layout.fragment_password_reset, container, false);
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

        String pin_code = resetPhoneCodeEtFragment.getText().toString();
        String password = passwordEtRegisterFragment.getText().toString();
        String confirmPassword = passwordConfirmEtRegisterFragment.getText().toString();
        getNewPassword(phone, pin_code, password, confirmPassword);

    }

    private void getNewPassword(String phone, String pin_code, String password, String confirmPassword) {
        apiServices.newPassword(phone, pin_code, password, confirmPassword).enqueue(new Callback<RestPassword>() {
            @Override
            public void onResponse(Call<RestPassword> call, Response<RestPassword> response) {


                if (response.body().getStatus() == 1) {
                    showProgressDialog(getActivity(), "Please Wait");
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    response.body().getData();
                    Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
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

