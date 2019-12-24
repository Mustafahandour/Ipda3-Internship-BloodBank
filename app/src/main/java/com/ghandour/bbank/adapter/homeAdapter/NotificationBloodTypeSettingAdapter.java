package com.ghandour.bbank.adapter.homeAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.api.ApiServices;
import com.ghandour.bbank.data.model.generalResponse.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ghandour.bbank.data.api.ApiClient.getClient;

public class

NotificationBloodTypeSettingAdapter extends RecyclerView.Adapter<NotificationBloodTypeSettingAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private List<String> oldIds = new ArrayList<>();
    public List<Integer> newIds = new ArrayList<>();



    public NotificationBloodTypeSettingAdapter(Context context, Activity activity, List<GeneralResponseData> generalResponseDataList, List<String> oldIds) {
        this.context = context;
        this.activity = activity;
        this.generalResponseDataList = generalResponseDataList;
        this.oldIds = oldIds;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification_setting,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.itemNotificationSettingCheckbox.setText(generalResponseDataList.get(position).getName());

        if (oldIds.contains(String.valueOf(generalResponseDataList.get(position).getId()))) {

            holder.itemNotificationSettingCheckbox.setChecked(true);
            newIds.add(generalResponseDataList.get(position).getId());
        } else {
            holder.itemNotificationSettingCheckbox.setChecked(false);
        }


    }


    @Override
    public int getItemCount() {
        return generalResponseDataList.size();
    }

 
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_notification_setting_checkbox)
        AppCompatCheckBox itemNotificationSettingCheckbox;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
        @OnClick(R.id.item_notification_setting_checkbox)
        public void onViewClicked() {
            if (!itemNotificationSettingCheckbox.isChecked()) {
                for (int i = 0; i < newIds.size(); i++) {
                    if (newIds.get(i).equals(generalResponseDataList.get(getAdapterPosition()).getId())) {
                        newIds.remove(i);
                        break;
                    }
                }

            } else {
                newIds.add(generalResponseDataList.get(getAdapterPosition()).getId());
            }
        }

    }
}
