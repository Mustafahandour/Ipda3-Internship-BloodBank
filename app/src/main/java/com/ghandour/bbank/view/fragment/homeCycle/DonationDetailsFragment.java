package com.ghandour.bbank.view.fragment.homeCycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.donation.DonationData;
import com.ghandour.bbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ghandour.bbank.data.api.ApiClient.getClient;


public class DonationDetailsFragment extends BaseFragment {

    @BindView(R.id.donation_details_fragment_tv_name)
    TextView donationDetailsFragmentTvName;
    @BindView(R.id.donation_details_fragment_tv_age)
    TextView donationDetailsFragmentTvAge;
    @BindView(R.id.donation_details_fragment_tv_blood_type)
    TextView donationDetailsFragmentTvBloodType;
    @BindView(R.id.donation_details_fragment_tv_blood_bag_number)
    TextView donationDetailsFragmentTvBloodBagNumber;
    @BindView(R.id.donation_details_fragment_tv_hospital_name)
    TextView donationDetailsFragmentTvHospitalName;
    @BindView(R.id.donation_details_fragment_tv_hospital_address)
    TextView donationDetailsFragmentTvHospitalAddress;
    @BindView(R.id.donation_details_fragment_tv_phone)
    TextView donationDetailsFragmentTvPhone;
    @BindView(R.id.donation_details_fragment_tv_details)
    TextView donationDetailsFragmentTvDetails;
    @BindView(R.id.donation_details_fragment_bt_call)
    Button donationDetailsFragmentBtCall;
    private Unbinder unbinder;
    private ApiServices apiServices;
    public DonationData donationData;

    public DonationDetailsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_details_donation, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        getDonationDetails(1);
        return view;
    }

    private void getDonationDetails(int donationId) {
        donationDetailsFragmentTvName.setText(getString(R.string.name_patient)+donationData.getPatientName());
        donationDetailsFragmentTvAge.setText(getString(R.string.age_patient)+donationData.getPatientAge());
        donationDetailsFragmentTvBloodType.setText(getString(R.string.blood_type_patient)+donationData.getBloodType().getName());
        donationDetailsFragmentTvBloodBagNumber.setText(getString(R.string.number_of_bag)+donationData.getBagsNum());
        donationDetailsFragmentTvHospitalName.setText(getString(R.string.hospital_name)+donationData.getHospitalName());
        donationDetailsFragmentTvHospitalAddress.setText(getString(R.string.hospital_adress)+donationData.getHospitalAddress());
        donationDetailsFragmentTvPhone.setText(getString(R.string.phone_number)+donationData.getPhone());
        donationDetailsFragmentTvDetails.setText(getString(R.string.notes)+donationData.getNotes());



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



    @OnClick(R.id.donation_details_fragment_bt_call)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+donationData.getPhone()));
        startActivity(intent);
    }
}

