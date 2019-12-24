package com.ghandour.bbank.view.fragment.homeCycle.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.adapter.homeAdapter.FavoriteRecyclerViwAdapter;
import com.ghandour.bbank.adapter.homeAdapter.PostRecyclerViwAdapter;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.posts.PostData;
import com.ghandour.bbank.data.model.posts.favorite.Favorite;
import com.ghandour.bbank.data.model.posts.favorite.FavoriteData;
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


public class FavoriteFragment extends BaseFragment {


    @BindView(R.id.favourite_fragment_rv_data_list)
    RecyclerView favouriteFragmentRvDataList;
    private Unbinder unbinder;
    private ApiServices apiServices;
    private LinearLayoutManager linearLayoutManager;
    private List<FavoriteData> favouriteFragmentDataList = new ArrayList<>();
    private FavoriteRecyclerViwAdapter favoriteRecyclerViwAdapter;
    private OnEndLess onEndLess;
    private int maxPage = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        getData();
        return view;
    }

    private void getData() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        favouriteFragmentRvDataList.setLayoutManager(linearLayoutManager);
        onEndLess = new OnEndLess(linearLayoutManager,1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page<= maxPage) {
                  /*
                this condition if no data to view it set page at last page
                 */
                if (maxPage != 0 && current_page != 1) {
                    onEndLess.previous_page = current_page;
                    getFavoritePosts(current_page);
                }else {
                    onEndLess.current_page = onEndLess.previous_page;
                }

            }
            }
        };
        favouriteFragmentRvDataList.addOnScrollListener(onEndLess);
        favoriteRecyclerViwAdapter = new FavoriteRecyclerViwAdapter(getActivity(),favouriteFragmentDataList);
        favouriteFragmentRvDataList.setAdapter(favoriteRecyclerViwAdapter);
        getFavoritePosts(1);

    }

    private void getFavoritePosts(int page) {

        apiServices.getFavorite("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {

                try {

                    if (response.body().getStatus() == 1) {

                        maxPage = response.body().getData().getLastPage();
                        favouriteFragmentDataList.addAll(response.body().getData().getData());
                        favoriteRecyclerViwAdapter.notifyDataSetChanged();

                    }else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    unbinder.unbind();
    }
}