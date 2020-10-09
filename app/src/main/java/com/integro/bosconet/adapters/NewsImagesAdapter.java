package com.integro.bosconet.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.integro.bosconet.model.NewsImages;
import com.integro.bosconet.R;

import java.util.ArrayList;

public class NewsImagesAdapter extends PagerAdapter {
    Context context;
    private ArrayList<NewsImages> newsImagesListArrayList;

    public NewsImagesAdapter(Context context, ArrayList<NewsImages> newsImagesArrayList) {
        this.context = context;
        this.newsImagesListArrayList = newsImagesArrayList;
    }

    @Override
    public int getCount() {
        return newsImagesListArrayList.size();
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
        ivFullImage = (ImageView) view.findViewById(R.id.ivImage);

        Glide.with(context)
                .load(newsImagesListArrayList.get(position).getImage())
                .into(ivFullImage);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
