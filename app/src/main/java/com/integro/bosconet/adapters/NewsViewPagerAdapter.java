package com.integro.bosconet.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.integro.bosconet.NewsActivity;
import com.integro.bosconet.R;
import com.integro.bosconet.model.News;

import java.util.ArrayList;

public class NewsViewPagerAdapter extends PagerAdapter {

    Context context;
    private ArrayList<News> newsArrayList;
    private LayoutInflater inflater;

    public NewsViewPagerAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((LinearLayout) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_news2, container, false);
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);
        TextView tvTitle=view.findViewById(R.id.tvTitle);
        TextView tvDescription=view.findViewById(R.id.tvDescription);
        LinearLayout llNews=view.findViewById(R.id.llNews);

        Glide.with(context)
                .load(newsArrayList.get(position).getL_img())
                .into(ivImage);
        ((ViewPager) container).addView(view);

        tvTitle.setText("NEWS \u0026 EVENTS");
        tvDescription.setText(newsArrayList.get(position).getTitle());

        llNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
