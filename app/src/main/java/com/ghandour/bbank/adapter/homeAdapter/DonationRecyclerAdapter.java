package com.ghandour.bbank.adapter.homeAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.model.donation.DonationData;
import com.ghandour.bbank.data.model.donation.donationDetails.DonationDetailsData;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.activity.BaseActivity;
import com.ghandour.bbank.view.fragment.homeCycle.DonationDetailsFragment;
import com.ghandour.bbank.view.fragment.homeCycle.DonationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

DonationRecyclerAdapter extends RecyclerView.Adapter<DonationRecyclerAdapter.ViewHolder> {


    private BaseActivity activity;
    private List<DonationData> donationDataList = new ArrayList<>();


    public DonationRecyclerAdapter(Activity activity, List<DonationData> donationDataList) {
        this.activity = (BaseActivity) activity;
        this.donationDataList = donationDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_adapter_donation_recyclerview,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.donationPatientName.setText(donationDataList.get(position).getPatientName());
        holder.donationHospitalName.setText(donationDataList.get(position).getHospitalName());
        holder.donationCityName.setText(donationDataList.get(position).getCity().getName());
        holder.donationTextBloodType.setText(donationDataList.get(position).getBloodType().getName());




    }

    private void setAction(ViewHolder holder, int position) {
        holder.donationRequestBtDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationDetailsFragment donationDetailsFragment = new DonationDetailsFragment();

                donationDetailsFragment.donationData =donationDataList.get(position);

             HelperMethod.replace(donationDetailsFragment,activity.getSupportFragmentManager(),R.id.nav_host_fragment, null,null);








            }
        });

    }

    @Override
    public int getItemCount() {
        return donationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.donation_patient_name)
        TextView donationPatientName;
        @BindView(R.id.donation_hospital_name)
        TextView donationHospitalName;
        @BindView(R.id.donation_city_name)
        TextView donationCityName;
        @BindView(R.id.donation_llayout_details)
        LinearLayout donationLlayoutDetails;
        @BindView(R.id.donation_text_blood_type)
        TextView donationTextBloodType;
        @BindView(R.id.donation_request_bt_details)
        Button donationRequestBtDetails;
        @BindView(R.id.donation_request_bt_call)
        Button donationRequestBtCall;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
