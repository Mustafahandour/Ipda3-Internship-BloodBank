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
import com.ghandour.bbank.data.model.notification.NotificationData;
import com.ghandour.bbank.helper.HelperMethod;
import com.ghandour.bbank.view.activity.BaseActivity;
import com.ghandour.bbank.view.fragment.homeCycle.DonationDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ghandour.bbank.helper.HelperMethod.replace;

public class

NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private BaseActivity activity;
    private List<NotificationData> notificationDataList = new ArrayList<>();

    public NotificationAdapter(Activity activity, List<NotificationData> notificationDataList) {
        this.activity = (BaseActivity) activity;
        this.notificationDataList = notificationDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_notification,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        if (notificationDataList.get(position).getPivot().getIsRead().equals("0")) {
            holder.itemNotificationIvIcon.setImageResource(R.drawable.ic_notification);
        } else {
            holder.itemNotificationIvIcon.setImageResource(R.drawable.ic_notification_none);
        }
        holder.itemNotificationIvTitle.setText(notificationDataList.get(position).getTitle());


    }

    private void setAction(ViewHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationDetailsFragment donationDetailsFragment = new DonationDetailsFragment();

                replace(donationDetailsFragment, activity.getSupportFragmentManager(), R.id.frame, null, null);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_notification_iv_icon)
        ImageView itemNotificationIvIcon;
        @BindView(R.id.item_notification_iv_title)
        TextView itemNotificationIvTitle;
        @BindView(R.id.item_notification_iv_time)
        TextView itemNotificationIvTime;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
