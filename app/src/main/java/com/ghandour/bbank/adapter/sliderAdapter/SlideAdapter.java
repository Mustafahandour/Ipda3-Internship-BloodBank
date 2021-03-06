package com.ghandour.bbank.adapter.sliderAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.ghandour.bbank.R;


public class SlideAdapter extends PagerAdapter {
    Context context;
    private int[] GalImages = new int[]{R.drawable.ic_slider1, R.drawable.ic_slider2};

    LayoutInflater mLayoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return GalImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {

            View itemView = mLayoutInflater.inflate(R.layout.slider_item_adapter, container, false);

           ImageView imageView = itemView.findViewById(R.id.slider_iv_fragment);
            imageView.setImageResource(GalImages[position]);

            container.addView(itemView);

            return itemView;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
