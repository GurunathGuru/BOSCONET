package com.integro.bosconet.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.model.PhotosGallery1;
import com.integro.bosconet.PhotosGalleryActivity2;
import com.integro.bosconet.R;

import java.util.ArrayList;

public class PhotosGalleryAdapter extends RecyclerView.Adapter<PhotosGalleryAdapter.MyViewHolder> {

    Context context;
    private ArrayList<PhotosGallery1> photosGallery1ArrayList;

    public PhotosGalleryAdapter(Context context, ArrayList<PhotosGallery1> photosGallery1ArrayList) {
        this.context = context;
        this.photosGallery1ArrayList = photosGallery1ArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_gallery_folders, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final PhotosGallery1 gallery = photosGallery1ArrayList.get(i);

        myViewHolder.tvTitle.setText(gallery.getTitle());
        Glide.with(context)
                .load(gallery.getImage())
                .into(myViewHolder.ivGallery);

        myViewHolder.llPhotoGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosGalleryActivity2.class);
                intent.putExtra("itemId",gallery.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosGallery1ArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView tvTitle;
        ImageView ivGallery;
        LinearLayout llPhotoGallery;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivGallery = itemView.findViewById(R.id.ivGallery);
            llPhotoGallery = itemView.findViewById(R.id.llPhotoGallery);
        }
    }
}
