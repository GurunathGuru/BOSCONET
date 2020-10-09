package com.integro.bosconet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.bosconet.R;
import com.integro.bosconet.SuccessDetailActivity;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.model.SuccessStory;

import java.util.ArrayList;


public class SuccessStoryAdapter extends RecyclerView.Adapter<SuccessStoryAdapter.MyViewHolder> {
    Context context;
    private ArrayList<SuccessStory> arrayList;

    public SuccessStoryAdapter(Context context, ArrayList<SuccessStory> successStoryArrayList) {
        this.context = context;
        this.arrayList = successStoryArrayList;
    }

    @NonNull
    @Override
    public SuccessStoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_success_stories, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuccessStoryAdapter.MyViewHolder myViewHolder, int i) {
        SuccessStory successStory = arrayList.get(i);
        myViewHolder.tvTitle.setText(successStory.getTitle());
        myViewHolder.tvDescription.setText(successStory.getDescription());

        Glide.with(context)
                .load(successStory.getImage())
                .into(myViewHolder.ivImage);

        myViewHolder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        myViewHolder.llSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuccessDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("DATA", successStory);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        CustomTextView tvTitle, tvDescription, tvReadMore;
        LinearLayout llSuccess;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvReadMore = itemView.findViewById(R.id.tvReadMore);
            llSuccess = itemView.findViewById(R.id.llSuccess);
        }
    }
}
