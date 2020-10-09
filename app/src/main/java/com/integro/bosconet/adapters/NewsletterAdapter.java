package com.integro.bosconet.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.R;
import com.integro.bosconet.model.Newsletter;

import java.util.ArrayList;

public class NewsletterAdapter extends RecyclerView.Adapter<NewsletterAdapter.MyViewHolder> {

    Context context;
    private ArrayList<Newsletter> newsletterArrayList;

    public NewsletterAdapter(Context context, ArrayList<Newsletter> newsletterArrayList) {

        this.context = context;
        this.newsletterArrayList = newsletterArrayList;

    }

    @NonNull
    @Override
    public NewsletterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_newsletter, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsletterAdapter.MyViewHolder myViewHolder, int i) {
        final Newsletter newsletter = newsletterArrayList.get(i);

        Glide.with(context)
                .load(newsletter.getImage())
                .into(myViewHolder.ivImage);

        myViewHolder.tvDate.setText(newsletter.getDate());
        myViewHolder.tvTitle.setText(newsletter.getTitle());
        myViewHolder.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("" + newsletter.getPdf()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsletterArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView tvDate, tvTitle, tvDownload;
        ImageView ivImage;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDownload = itemView.findViewById(R.id.tvDownload);
        }
    }
}
