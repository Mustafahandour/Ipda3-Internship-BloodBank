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
import com.ghandour.bbank.data.model.posts.favorite.FavoriteData;
import com.ghandour.bbank.view.activity.BaseActivity;
import com.ghandour.bbank.view.fragment.homeCycle.PostDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ghandour.bbank.helper.HelperMethod.onLoadImageFromUrl;
import static com.ghandour.bbank.helper.HelperMethod.replace;

public class FavoriteRecyclerViwAdapter extends RecyclerView.Adapter<FavoriteRecyclerViwAdapter.ViewHolder> {


    private BaseActivity activity;
    private List<FavoriteData> favoriteDataList = new ArrayList<>();


    public FavoriteRecyclerViwAdapter(Activity activity, List<FavoriteData> favoriteDataList) {
        this.activity = (BaseActivity) activity;
        this.favoriteDataList = favoriteDataList;
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
        holder.postTvFragment.setText(favoriteDataList.get(position).getTitle());
        onLoadImageFromUrl(holder.postIvFragment, favoriteDataList.get(position).getThumbnailFullPath(), activity);

    }

    private void setAction(ViewHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
                postDetailsFragment.favoriteData = favoriteDataList.get(position);

                replace(postDetailsFragment, activity.getSupportFragmentManager(), R.id.frame, null, null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return favoriteDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView postDescrition;
        @BindView(R.id.post_tv_fragment)
        TextView postTvFragment;
        @BindView(R.id.post_iv_fragment)
        ImageView postIvFragment;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
