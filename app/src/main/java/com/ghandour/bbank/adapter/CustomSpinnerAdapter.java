package com.ghandour.bbank.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.model.generalResponse.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {

    public Context context;
    private List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private LayoutInflater inflater;
    public int selectedId = 0;

    public CustomSpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflater = (LayoutInflater.from(applicationContext));
    }
    public void setData( List<GeneralResponseData> generalResponseDataList, String hint) {

        this.generalResponseDataList = new ArrayList<>();
        this.generalResponseDataList.add(new GeneralResponseData(0,hint));
        this.generalResponseDataList.addAll(generalResponseDataList);
    }
    @Override
    public int getCount() {
        return generalResponseDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_custom_spinner, null);

        TextView names = (TextView) view.findViewById(R.id.custom_tv_spinner_adapter);

        names.setText(generalResponseDataList.get(i).getName());
        selectedId = generalResponseDataList.get(i).getId();

        return view;
    }
}
