package com.integro.bosconet.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.R;
import com.integro.bosconet.model.Notification;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private ArrayList<Notification> notificationArrayList;
    Context context;

    public NotificationAdapter(Context context, ArrayList<Notification> notificationArrayList) {
        this.context = context;
        this.notificationArrayList = notificationArrayList;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_notification, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder myViewHolder, int i) {
        final Notification notification = notificationArrayList.get(i);

        myViewHolder.tvDate.setText(notification.getDate());
        myViewHolder.tvTitle.setText(notification.getTitle());
        myViewHolder.tvDescription.setText(notification.getDescription());
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView tvDate;
        CustomTextView tvTitle;
        CustomTextView tvDescription;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
