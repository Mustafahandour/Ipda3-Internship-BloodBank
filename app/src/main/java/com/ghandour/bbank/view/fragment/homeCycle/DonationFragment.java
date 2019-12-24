package com.ghandour.bbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.CustomSpinnerAdapter;
import com.ghandour.bbank.adapter.homeAdapter.DonationRecyclerAdapter;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.donation.Donation;
import com.ghandour.bbank.data.model.donation.DonationData;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.helper.OnEndLess;
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
import static com.ghandour.bbank.helper.GeneralRequest.getSpinnerData;


public class DonationFragment extends BaseFragment {


    @BindView(R.id.fragment_donation_sp_blood_type)
    Spinner fragmentDonationSpBloodType;
    @BindView(R.id.fragment_donation_sp_city)
    Spinner fragmentDonationSpCity;
    @BindView(R.id.search_bt)
    Button searchBt;
    @BindView(R.id.donation_fragment_rv_request)
    RecyclerView donationFragmentRvRequest;
    @BindView(R.id.donation_fragment_iv_new_request)
    ImageView donationFragmentIvNewRequest;
    private Unbinder unbinder;
    private ApiServices apiServices;
    private LinearLayoutManager linearLayoutManager;
    private DonationRecyclerAdapter donationRecyclerAdapter;
    private List<DonationData> donationFragmentDataList = new ArrayList<>();
    private OnEndLess onEndLess;
    private int maxPage = 0;
    private CustomSpinnerAdapter bloodTybeAdapter;
    private CustomSpinnerAdapter cityAdapter;
    private CustomSpinnerAdapter governmentAdapter;

    public DonationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_donation, container, false);
        apiServices = getClient().create(ApiServices.class);
        unbinder = ButterKnife.bind(this, view);

        bloodTybeAdapter = new CustomSpinnerAdapter(getContext());
        getSpinnerData(fragmentDonationSpBloodType, bloodTybeAdapter, "select blood type", apiServices.getBloodType());

        cityAdapter = new CustomSpinnerAdapter(getContext());
        governmentAdapter = new CustomSpinnerAdapter(getContext());
        getSpinnerData(fragmentDonationSpCity, cityAdapter, "select city", apiServices.getCity(governmentAdapter.selectedId));

        getData();
        return view;
    }

    private void getData() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        donationFragmentRvRequest.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                   /*
                this condition if no data to view it set page at last page
                 */
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getRequest(current_page);

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }

            }
        };
        donationFragmentRvRequest.addOnScrollListener(onEndLess);
        donationRecyclerAdapter = new DonationRecyclerAdapter(getActivity(), donationFragmentDataList);
        donationFragmentRvRequest.setAdapter(donationRecyclerAdapter);
        getRequest(1);


    }

    private void getRequest(int page) {
        apiServices.getDonationRequest("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", page).enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        donationFragmentDataList.addAll(response.body().getData().getData());
                        donationRecyclerAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {

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




    @OnClick(R.id.donation_fragment_iv_new_request)
    public void onViewClicked() {
        NewBloodRequestFragment newBloodRequestFragment = new NewBloodRequestFragment();
        HelperMethod.replace(newBloodRequestFragment, getActivity().getSupportFragmentManager(), R.id.nav_host_fragment, null, null);

    }
}

