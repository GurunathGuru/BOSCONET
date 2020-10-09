package com.integro.bosconet.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.R;
import com.integro.bosconet.model.OurTeam;

import java.util.ArrayList;

public class OurTeamAdapter extends RecyclerView.Adapter<OurTeamAdapter.MyViewHolder> {

    Context context;
    private ArrayList<OurTeam> ourTeamArrayList;

    public OurTeamAdapter(Context context, ArrayList<OurTeam> ourTeamArrayList) {
        this.ourTeamArrayList = ourTeamArrayList;
        this.context = context;
    }

    @Override
    public OurTeamAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_ourteam, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OurTeamAdapter.MyViewHolder myViewHolder, int i) {
        final OurTeam ourTeam = ourTeamArrayList.get(i);

        myViewHolder.tvTitle.setText(ourTeam.getName());
        myViewHolder.tvDesignation.setText(ourTeam.getDesignation());

        Glide.with(context)
                .load(ourTeam.getImage())
                .into(myViewHolder.ivImage);
    }

    @Override
    public int getItemCount() {
        return ourTeamArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CustomTextView tvTitle, tvDesignation;
        ImageView ivImage;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesignation = itemView.findViewById(R.id.tvDesignation);

        }
    }
}
