package com.integro.bosconet;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.integro.bosconet.adapters.SuccessStoryAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.model.SuccessStory;
import com.integro.bosconet.model.SuccessStoryList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessStoryActivity extends AppCompatActivity {

    private ApiServices apiServices;
    private RecyclerView rvSuccessStories;
    private ArrayList<SuccessStory> successStoryArrayList;
    private SuccessStoryAdapter successStoryAdapter;
    private StaggeredGridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_story);

        apiServices= ApiClients.getClients().create(ApiServices.class);
        rvSuccessStories=findViewById(R.id.rvSuccessStory);
        successStoryArrayList=new ArrayList<>();
        manager=new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
        getSuccessStories();
    }

    public void getSuccessStories(){
        String date="2019-10-26 22:00:51";
        Call<SuccessStoryList> successStoryListCall = apiServices.getSuccessStoryList(date);
        successStoryListCall.enqueue(new Callback<SuccessStoryList>() {
            @Override
            public void onResponse(Call<SuccessStoryList> call, Response<SuccessStoryList> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body()==null){
                    Toast.makeText(getApplicationContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size=response.body().getSuccessStoryArrayList().size();
                for (int i =0;i<size;i++){
                    successStoryArrayList.add(response.body().getSuccessStoryArrayList().get(i));
                }
                rvSuccessStories.setLayoutManager(manager);
                successStoryAdapter=new SuccessStoryAdapter(getApplicationContext(),successStoryArrayList);
                rvSuccessStories.setAdapter(successStoryAdapter);

            }

            @Override
            public void onFailure(Call<SuccessStoryList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Response "+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
