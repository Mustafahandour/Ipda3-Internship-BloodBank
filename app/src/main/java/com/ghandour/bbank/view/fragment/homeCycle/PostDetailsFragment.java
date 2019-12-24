package com.ghandour.bbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ghandour.bbank.R;
import com.ghandour.bbank.data.model.posts.PostData;
import com.ghandour.bbank.data.model.posts.favorite.FavoriteData;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.activity.BaseActivity;
import com.ghandour.bbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ghandour.bbank.helper.HelperMethod.onLoadImageFromUrl;


public class PostDetailsFragment extends BaseFragment {

    @BindView(R.id.post_details_fragment_iv_)
    ImageView postDetailsFragmentIv;
    @BindView(R.id.post_details_fragment_tv_title)
    TextView postDetailsFragmentTvTitle;
    @BindView(R.id.post_details_fragment_tv_desc)
    TextView postDetailsFragmentTvDesc;
    public PostData postData;
    public FavoriteData favoriteData;
    private Unbinder unbinder;

    public PostDetailsFragment() {
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
       View view = inflater.inflate(R.layout.fragment_details_post, container, false);
unbinder = ButterKnife.bind(this, view);
        getPostDetails();

        return view;
    }

    private void getPostDetails() {

        onLoadImageFromUrl(postDetailsFragmentIv,postData.getThumbnailFullPath(),getActivity());
        postDetailsFragmentTvTitle.setText(postData.getTitle());
        postDetailsFragmentTvDesc.setText(postData.getContent());

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


