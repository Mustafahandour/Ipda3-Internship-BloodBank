package com.ghandour.bbank.adapter.homeAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.model.posts.PostData;
import com.ghandour.bbank.view.activity.BaseActivity;
import com.ghandour.bbank.view.fragment.homeCycle.PostDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ghandour.bbank.helper.HelperMethod.onLoadImageFromUrl;
import static com.ghandour.bbank.helper.HelperMethod.replace;

public class PostRecyclerViwAdapter extends RecyclerView.Adapter<PostRecyclerViwAdapter.ViewHolder> {


    private BaseActivity activity;
    private List<PostData> postDataList = new ArrayList<>();


    public PostRecyclerViwAdapter(Activity activity, List<PostData> postDataList) {
        this.activity = (BaseActivity) activity;
        this.postDataList = postDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_post_recycler_view,
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
        holder.postTvFragment.setText(postDataList.get(position).getTitle());
        onLoadImageFromUrl(holder.postIvFragment, postDataList.get(position).getThumbnailFullPath(), activity);

    }

    private void setAction(ViewHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
                postDetailsFragment.postData = postDataList.get(position);
                replace(postDetailsFragment, activity.getSupportFragmentManager(), R.id.nav_host_fragment, null, null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postDataList.size();
    }

    @OnClick(R.id.post_favorite)
    public void onViewClicked() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView postDescrition;
        @BindView(R.id.post_tv_fragment)
        TextView postTvFragment;
        @BindView(R.id.post_iv_fragment)
        ImageView postIvFragment;
        @BindView(R.id.post_favorite)
        ImageView postFavorite;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
