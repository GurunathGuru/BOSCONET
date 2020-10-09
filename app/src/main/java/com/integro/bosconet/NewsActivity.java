package com.integro.bosconet;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.integro.bosconet.adapters.NewsAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.model.News;
import com.integro.bosconet.model.NewsList;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvNews;
    StaggeredGridLayoutManager manager;
    ArrayList<News> newsArrayList;
    Call<NewsList> newsListCall;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvNews = findViewById(R.id.rvNews);
        manager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        newsArrayList = new ArrayList<>();
        getNewsList();
    }

    public void getNewsList() {
        String date = "2019-01-10 22:48:29";
        newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Objects.requireNonNull(response.body()).getNewsListArrayList() == null) {
                    Toast.makeText(getApplicationContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size = response.body().getNewsListArrayList().size();
                for (int i = 0; i < size; i++) {
                    newsArrayList.add(response.body().getNewsListArrayList().get(i));
                }
                adapter = new NewsAdapter(getApplicationContext(), newsArrayList);
                rvNews.setAdapter(adapter);
                rvNews.setLayoutManager(manager);
            }

            @Override
            public void onFailure(@NonNull Call<NewsList> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Response "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
