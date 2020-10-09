package com.integro.bosconet.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.demono.AutoScrollViewPager;
import com.google.android.material.tabs.TabLayout;
import com.integro.bosconet.CareForPlanetEarthActivity;
import com.integro.bosconet.GiftEducationActivity;
import com.integro.bosconet.R;
import com.integro.bosconet.SkillForYouthActivity;
import com.integro.bosconet.WebActivity;
import com.integro.bosconet.adapters.CoverPhotoAdapter;
import com.integro.bosconet.adapters.NewsViewPagerAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.model.CoverPhoto;
import com.integro.bosconet.model.CoverPhotoList;
import com.integro.bosconet.model.News;
import com.integro.bosconet.model.NewsList;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ApiServices apiServices;
    private ArrayList<CoverPhoto> coverPhotoArrayList;
    private AutoScrollViewPager vpCoverPhotos, vpNews;
    private CoverPhotoAdapter adapter;

    private ArrayList<News> newsArrayList;
    private NewsViewPagerAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        vpCoverPhotos = view.findViewById(R.id.vpCoverPhotos);
        vpNews = view.findViewById(R.id.vpNews);


        apiServices = ApiClients.getClients().create(ApiServices.class);
        coverPhotoArrayList = new ArrayList<>();
        newsArrayList = new ArrayList<>();

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(vpNews, true);

        getCoverPhotosList();
        getNewsList();

        CustomTextView giftEducation = view.findViewById(R.id.giftEducation);
        CustomTextView careForEarth = view.findViewById(R.id.careForEarth);
        CustomTextView skill = view.findViewById(R.id.skill);
        ImageView IvDonate = view.findViewById(R.id.IvDonate);

        giftEducation.setOnClickListener(this);
        careForEarth.setOnClickListener(this);
        skill.setOnClickListener(this);
        IvDonate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.giftEducation:
                Intent intent = new Intent(getContext(), GiftEducationActivity.class);
                startActivity(intent);
                break;

            case R.id.careForEarth:
                Intent intent1 = new Intent(getContext(), CareForPlanetEarthActivity.class);
                startActivity(intent1);
                break;

            case R.id.skill:
                Intent intent2 = new Intent(getContext(), SkillForYouthActivity.class);
                startActivity(intent2);
                break;

            case R.id.IvDonate:
                Intent intent4 = new Intent(getContext(), WebActivity.class);
                String url="https://donations.bosconet.in/campaign/covid19";
                intent4.putExtra("TAG", url);
                startActivity(intent4);
                break;
        }
    }

    private void getCoverPhotosList() {
        String date = "2019-01-10 22:48:29";
        Call<CoverPhotoList> coverPhotoListCall = apiServices.getCoverPhotoList(date);
        coverPhotoListCall.enqueue(new Callback<CoverPhotoList>() {
            @Override
            public void onResponse(@NonNull Call<CoverPhotoList> call, @NonNull Response<CoverPhotoList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (Objects.requireNonNull(response.body()).getCoverPhotoArrayList() == null) {
                    return;
                }
                int size = response.body().getCoverPhotoArrayList().size();
                if (size != 0) {
                    Log.d("RESPONSE", "news Size " + size);
                    for (int i = 0; i < size; i++) {
                        coverPhotoArrayList.add(response.body().getCoverPhotoArrayList().get(i));
                    }
                    adapter = new CoverPhotoAdapter(getContext(), coverPhotoArrayList);
                    vpCoverPhotos.setAdapter(adapter);
                    vpCoverPhotos.startAutoScroll();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CoverPhotoList> call, @NonNull Throwable t) {

            }
        });
    }

    private void getNewsList() {
        String date = "2019-01-10 22:48:29";
        Call<NewsList> newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (Objects.requireNonNull(response.body()).getNewsListArrayList() == null) {
                    return;
                }
                int size = response.body().getNewsListArrayList().size();
                if (size != 0) {
                    Log.d("RESPONSE", "news Size " + size);
                    for (int i = 0; i < size; i++) {
                        newsArrayList.add(response.body().getNewsListArrayList().get(i));
                    }
                    newsAdapter = new NewsViewPagerAdapter(getContext(), newsArrayList);
                    vpNews.setAdapter(newsAdapter);
                    vpNews.startAutoScroll();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsList> call, @NonNull Throwable t) {

            }
        });
    }
}
