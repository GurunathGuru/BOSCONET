package com.integro.bosconet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.bosconet.NewsDetailActivity;
import com.integro.bosconet.R;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    ArrayList<News> newsArrayList;
    Context context;

    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_news3, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final News news = newsArrayList.get(i);

        myViewHolder.tvDate.setText(news.getDate());
        myViewHolder.tvTitle.setText(news.getTitle());
        myViewHolder.tvDescription.setText(news.getDescription());
        Glide.with(context)
                .load(news.getL_img())
                .into(myViewHolder.ivImage);

        myViewHolder.llNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("TAG", news);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView tvDate;
        CustomTextView tvTitle, tvDescription;
        LinearLayout llNews;
        ImageView ivImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            llNews = itemView.findViewById(R.id.llNews);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
