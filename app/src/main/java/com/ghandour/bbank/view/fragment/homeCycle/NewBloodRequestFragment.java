package com.ghandour.bbank.view.fragment.homeCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.CustomSpinnerAdapter;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.local.SharedPreferencesManger;
import com.ghandour.bbank.data.model.donation.donationRequest.CreateDonationRequest;
import com.ghandour.bbank.view.activity.MapsActivity;
import com.ghandour.bbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.LoadData;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.SaveData;
import static com.ghandour.bbank.helper.GeneralRequest.getSpinnerData;
import static com.ghandour.bbank.helper.HelperMethod.dismissProgressDialog;
import static com.ghandour.bbank.helper.HelperMethod.replace;
import static com.ghandour.bbank.helper.HelperMethod.showProgressDialog;


public class NewBloodRequestFragment extends BaseFragment {



    @BindView(R.id.donate_request_fragment_et_name)
    EditText donateRequestFragmentEtName;
    @BindView(R.id.donate_request_fragment_et_age)
    EditText donateRequestFragmentEtAge;
    @BindView(R.id.donate_request_fragment_sp_blood_type)
    Spinner donateRequestFragmentSpBloodType;
    @BindView(R.id.donate_request_fragment_et_bag_number)
    EditText donateRequestFragmentEtBagNumber;
    @BindView(R.id.donate_request_fragment_et_hospital_name)
    EditText donateRequestFragmentEtHospitalName;
    @BindView(R.id.donate_request_fragment_tv_hospital_address)
    TextView donateRequestFragmentTvHospitalAddress;
    @BindView(R.id.donate_request_fragment_ib_hospital_map_location)
    ImageButton donateRequestFragmentIbHospitalMapLocation;
    @BindView(R.id.donate_request_fragment_sp_government)
    Spinner donateRequestFragmentSpGovernment;
    @BindView(R.id.donate_request_fragment_sp_city)
    Spinner donateRequestFragmentSpCity;
    @BindView(R.id.donate_request_fragment_et_phone)
    EditText donateRequestFragmentEtPhone;
    @BindView(R.id.donate_request_fragment_et_notes)
    EditText donateRequestFragmentEtNotes;
    @BindView(R.id.donate_request_fragment_bt_send_request)
    Button donateRequestFragmentBtSendRequest;
    private Unbinder unbinder;
    private ApiServices apiServices;

    private CustomSpinnerAdapter bloodTybeAdapter, governmentsAdapter, citiesAdapter, bloodBagsAdapter;
    private String hospital_address;


    public NewBloodRequestFragment() {
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
        View view = inflater.inflate(R.layout.fragment_new_donation_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);

        bloodTybeAdapter = new CustomSpinnerAdapter(getContext());
        getSpinnerData(donateRequestFragmentSpBloodType, bloodTybeAdapter, getString(R.string.select_blood_type), apiServices.getBloodType());


        governmentsAdapter = new CustomSpinnerAdapter(getContext());
        citiesAdapter = new CustomSpinnerAdapter(getContext());

        getSpinnerData(donateRequestFragmentSpGovernment, governmentsAdapter, getString(R.string.select_government), apiServices.getGovernrates()
                , donateRequestFragmentSpCity, citiesAdapter, getString(R.string.select_city), apiServices.getCity(governmentsAdapter.selectedId));


        return view;
    }

    private void createDonationRequest(String patientName, String patientAge, String blood_type_id,
                                       String bags_num, String hospital_name, String hospital_address,
                                       String city_id, String phone, String notes,String latitude,String longitude) {
        showProgressDialog(getActivity(), getString(R.string.please_wait));

        apiServices.createDonationRequest("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", patientName, patientAge,
                blood_type_id, bags_num, hospital_name, hospital_address, city_id, phone, notes, latitude, longitude).enqueue(new Callback<CreateDonationRequest>() {
            @Override
            public void onResponse(Call<CreateDonationRequest> call, Response<CreateDonationRequest> response) {
                dismissProgressDialog();

                try {
                    if (response.body().getStatus() == 1) {

                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<CreateDonationRequest> call, Throwable t) {

            }
        });

    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.donate_request_fragment_ib_hospital_map_location, R.id.donate_request_fragment_bt_send_request})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.donate_request_fragment_ib_hospital_map_location:
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);

                break;
            case R.id.donate_request_fragment_bt_send_request:
                String patientName = donateRequestFragmentEtName.getText().toString();
                String patientAge = donateRequestFragmentEtAge.getText().toString();
                String blood_type_id =  String.valueOf(donateRequestFragmentSpBloodType.getSelectedItemPosition());
                String bags_num = donateRequestFragmentEtBagNumber.getText().toString();
                String hospital_name = donateRequestFragmentEtHospitalName.getText().toString();
                String city_id = String.valueOf(donateRequestFragmentSpCity.getSelectedItemPosition());
                String phone = donateRequestFragmentEtPhone.getText().toString();
                String notes = donateRequestFragmentEtNotes.getText().toString();

                String latitude = LoadData(getActivity(),"LATITUDE");
                String longitude = LoadData(getActivity(),"LONGITUDE");

                createDonationRequest(patientName, patientAge,
                        blood_type_id, bags_num, hospital_name, hospital_address, city_id, phone, notes,latitude,longitude);
                SaveData(getActivity(),"LOCATION",null);
                SaveData(getActivity(),"LATITUDE",null);
                SaveData(getActivity(),"LONGITUDE",null);
DonationFragment donationFragment = new DonationFragment();

                 replace(donationFragment,getActivity().getSupportFragmentManager(),R.id.nav_host_fragment,null,null);
                break;
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        hospital_address = LoadData(getActivity(),"LOCATION");
        donateRequestFragmentTvHospitalAddress.setText(hospital_address);
    }
}

