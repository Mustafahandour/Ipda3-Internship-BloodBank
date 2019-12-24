package com.ghandour.bbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.CustomSpinnerAdapter;
import com.ghandour.bbank.adapter.homeAdapter.PostRecyclerViwAdapter;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.posts.Post;
import com.ghandour.bbank.data.model.posts.PostData;
import com.ghandour.bbank.helper.GeneralRequest;
import com.ghandour.bbank.helper.OnEndLess;
import com.ghandour.bbank.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ghandour.bbank.data.api.ApiClient.getClient;
import static com.ghandour.bbank.helper.GeneralRequest.getSpinnerData;


public class PostFragment extends BaseFragment {

    @BindView(R.id.post_sv_fragment)
    SearchView postSvFragment;
    @BindView(R.id.post_search_sp_fragment)
    Spinner postSearchSpFragment;
    @BindView(R.id.post_search_layout)
    LinearLayout postSearchLayout;
    @BindView(R.id.post_fragment_rv_data_list)
    RecyclerView postFragmentRvDataList;

    private Unbinder unbinder;
    private ApiServices apiServices;
    private LinearLayoutManager linearLayoutManager;
    private PostRecyclerViwAdapter postRecyclerViwAdapter;
    private List<PostData> postFragmentDataList = new ArrayList<>();
    private OnEndLess onEndLess;
    private int maxPage = 0;
    private CustomSpinnerAdapter postCategoryAdapter;


    public PostFragment() {
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
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
       postCategoryAdapter = new CustomSpinnerAdapter(getActivity());
        getSpinnerData(postSearchSpFragment,postCategoryAdapter,"choose post", apiServices.getCategories());
        getData();
        return view;
    }

    private void getData() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        postFragmentRvDataList.setLayoutManager(linearLayoutManager);
        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                /*
                this condition if no data to view it set page at last page
                 */
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getPost(current_page);

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }

            }
        };
        postFragmentRvDataList.addOnScrollListener(onEndLess);

        postRecyclerViwAdapter = new PostRecyclerViwAdapter(getActivity(), postFragmentDataList);
        postFragmentRvDataList.setAdapter(postRecyclerViwAdapter);
        getPost(1);


    }

    private void getPost(int page) {
        apiServices.getPosts("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", page).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                try {


                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        postFragmentDataList.addAll(response.body().getData().getData());
                        postRecyclerViwAdapter.notifyDataSetChanged();
                    } else {
                        response.body().getMsg();
                    }


                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

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

