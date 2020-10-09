package com.integro.bosconet.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.bosconet.R;
import com.integro.bosconet.adapters.NotificationAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.model.Notification;
import com.integro.bosconet.model.NotificationList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationFragment extends Fragment {

    private ApiServices apiServices;
    private RecyclerView rvNotifications;
    private LinearLayoutManager manager;
    private ArrayList<Notification> notificationArrayList;
    private NotificationAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvNotifications = view.findViewById(R.id.rvNotifications);
        manager = new LinearLayoutManager(getContext());
        notificationArrayList = new ArrayList<>();
        getNotificationList();
        return view;
    }

    private void getNotificationList() {
        String date = "2019-01-10 22:48:29";
        Call<NotificationList> notificationListCall = apiServices.getNotificationList(date);
        notificationListCall.enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body().getNotificationArrayList() == null) {
                    Toast.makeText(getContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size = response.body().getNotificationArrayList().size();
                if (size != 0) {
                    Log.d("RESPONSE", "news Size " + size);
                    for (int i = 0; i < size; i++) {
                        notificationArrayList.add(response.body().getNotificationArrayList().get(i));
                    }
                    adapter = new NotificationAdapter(getContext(), notificationArrayList);
                    rvNotifications.setAdapter(adapter);
                    rvNotifications.setLayoutManager(manager);
                }
            }

            @Override
            public void onFailure(Call<NotificationList> call, Throwable t) {

            }
        });
    }

}
