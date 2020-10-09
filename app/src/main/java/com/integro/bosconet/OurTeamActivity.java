package com.integro.bosconet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.bosconet.adapters.OurTeamAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.model.OurTeam;
import com.integro.bosconet.model.OurTeamList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurTeamActivity extends AppCompatActivity {

    private ApiServices apiServices;
    private RecyclerView rvOurTeam;
    private LinearLayoutManager manager;
    private ArrayList<OurTeam> ourTeamArrayList;
    private OurTeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_team);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvOurTeam = findViewById(R.id.rvOurTeam);
        manager = new LinearLayoutManager(this);
        ourTeamArrayList = new ArrayList<>();
        getOurTeamList();
    }

    public void getOurTeamList() {
        String date = "2019-01-10 22:48:29";
        Call<OurTeamList> ourTeamListCall = apiServices.getOurTeamList(date);
        ourTeamListCall.enqueue(new Callback<OurTeamList>() {
            @Override
            public void onResponse(@NonNull Call<OurTeamList> call, @NonNull Response<OurTeamList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().getOurTeamArrayList() == null) {
                    Toast.makeText(getApplicationContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size = response.body().getOurTeamArrayList().size();
                if (size != 0) {
                    Log.d("RESPONSE", "Size " + size);
                    for (int i = 0; i < size; i++) {
                        ourTeamArrayList.add(response.body().getOurTeamArrayList().get(i));
                    }
                    adapter = new OurTeamAdapter(getApplicationContext(), ourTeamArrayList);
                    rvOurTeam.setAdapter(adapter);
                    rvOurTeam.setLayoutManager(manager);
                }
            }

            @Override
            public void onFailure(Call<OurTeamList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Response "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
