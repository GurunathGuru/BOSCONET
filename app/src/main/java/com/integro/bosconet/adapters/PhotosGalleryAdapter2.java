package com.integro.bosconet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.bosconet.PhotosGalleryActivity3;
import com.integro.bosconet.R;
import com.integro.bosconet.model.PhotosGallery2;

import java.util.ArrayList;

public class PhotosGalleryAdapter2 extends RecyclerView.Adapter<PhotosGalleryAdapter2.MyViewHolder> {
    Context context;
    private ArrayList<PhotosGallery2> imagesArrayList;
    private String galleryItemId;

    public PhotosGalleryAdapter2(Context context, ArrayList<PhotosGallery2> imagesArrayList, String galleryItemId) {
        this.context = context;
        this.imagesArrayList = imagesArrayList;
        this.galleryItemId = galleryItemId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_gallery_images, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final PhotosGallery2 photosGallery2 = imagesArrayList.get(i);

        Glide.with(context)
                .load(photosGallery2.getImage())
                .into(myViewHolder.ivPhoto);

        myViewHolder.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosGalleryActivity3.class);
                intent.putExtra("itemId", photosGallery2.getId());
                intent.putExtra("galleryItemId", galleryItemId);
                intent.putExtra("position", "" + i);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        myViewHolder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        ImageView ivShare;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.ivImage);
            ivShare = itemView.findViewById(R.id.ivShare);

        }
    }
}
