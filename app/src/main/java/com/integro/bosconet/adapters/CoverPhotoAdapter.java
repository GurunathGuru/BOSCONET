package com.integro.bosconet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.integro.bosconet.R;
import com.integro.bosconet.model.CoverPhoto;

import java.util.ArrayList;

public class CoverPhotoAdapter extends PagerAdapter {
    Context context;
    private ArrayList<CoverPhoto> coverPhotoArrayList;

    public CoverPhotoAdapter(Context context, ArrayList<CoverPhoto> coverPhotoArrayList) {
        this.context = context;
        this.coverPhotoArrayList = coverPhotoArrayList;
    }

    @Override
    public int getCount() {
        return coverPhotoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((LinearLayout) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivFullImage;
        View view = LayoutInflater.from(context).inflate(R.layout.card_coverphots, container, false);
        ivFullImage = view.findViewById(R.id.ivImage);
        Glide.with(context)
                .load(coverPhotoArrayList.get(position).getImage())
                .into(ivFullImage);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
