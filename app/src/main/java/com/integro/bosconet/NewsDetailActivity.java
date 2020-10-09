package com.integro.bosconet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.demono.AutoScrollViewPager;
import com.integro.bosconet.adapters.NewsImagesAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.model.News;
import com.integro.bosconet.model.NewsImages;
import com.integro.bosconet.model.NewsImagesList;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    ApiServices apiServices;
    ArrayList<NewsImages> newsImagesArrayList;
    Call<NewsImagesList> newsImagesListCall;
    NewsImagesAdapter adapter;
    AutoScrollViewPager autoScrollViewPager;
    News news = new News();
    private ImageView ivImage;
    private String itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        newsImagesArrayList = new ArrayList<>();
        autoScrollViewPager = findViewById(R.id.autoScrollViewPager);

        CustomTextView tvDate = findViewById(R.id.tvDate);
        CustomTextView tvTitle = findViewById(R.id.tvTitle);
        CustomTextView tvDescription = findViewById(R.id.tvDescription);
        CustomTextView tvShare = findViewById(R.id.tvShare);
        ivImage = findViewById(R.id.ivImage);

        news = (News) getIntent().getSerializableExtra("TAG");

        itemId = news.getId();
        tvDate.setText(news.getDate());
        tvTitle.setText(news.getTitle());
        tvDescription.setText(news.getDescription());

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "http://bosco-net.in/newshare.php?id=" + news.getId());
                startActivity(intent);
            }
        });
        getNewsImagesList();
    }

    public void getNewsImagesList() {
        String date = "" + itemId;

        Log.d("ITEMID", "" + date);
        newsImagesListCall = apiServices.getNewsImagesList(itemId);
        newsImagesListCall.enqueue(new Callback<NewsImagesList>() {
            @Override
            public void onResponse(@NonNull Call<NewsImagesList> call, @NonNull Response<NewsImagesList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Objects.requireNonNull(response.body()).getNewsImagesArrayList() == null) {
                    Toast.makeText(getApplicationContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size = response.body().getNewsImagesArrayList().size();
                if (size != 0) {
                    for (int i = 0; i < size; i++) {
                        newsImagesArrayList.add(response.body().getNewsImagesArrayList().get(i));
                        adapter = new NewsImagesAdapter(getApplicationContext(), newsImagesArrayList);
                        autoScrollViewPager.setAdapter(adapter);
                        autoScrollViewPager.startAutoScroll();
                    }
                } else {
                    ivImage.setVisibility(View.VISIBLE);
                    autoScrollViewPager.setVisibility(View.GONE);
                    Glide.with(getApplicationContext())
                            .load(news.getL_img())
                            .into(ivImage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsImagesList> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Response "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

