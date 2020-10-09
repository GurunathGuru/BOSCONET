package com.integro.bosconet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.demono.AutoScrollViewPager;
import com.integro.bosconet.adapters.PhotosGalleryViewPagerAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.model.PhotosGallery2;
import com.integro.bosconet.model.PhotosGallery2List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosGalleryActivity3 extends AppCompatActivity {

    private String galleryItemId;
    private int position;

    private ApiServices apiServices;
    private ArrayList<PhotosGallery2> gallery2ArrayList;
    private AutoScrollViewPager vpGallery;
    private PhotosGalleryViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery3);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        gallery2ArrayList = new ArrayList<>();
        vpGallery = findViewById(R.id.vpGallery);
        vpGallery.startAutoScroll();

        galleryItemId = getIntent().getStringExtra("galleryItemId");
        position = Integer.parseInt(getIntent().getStringExtra("position"));

        getGalleryImagesList();
    }

    private void getGalleryImagesList() {
        String ItemId = "" + galleryItemId;
        Call<PhotosGallery2List> gallery2ListCall = apiServices.getGalleryImagesList(ItemId);
        gallery2ListCall.enqueue(new Callback<PhotosGallery2List>() {
            @Override
            public void onResponse(Call<PhotosGallery2List> call, Response<PhotosGallery2List> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body().getPhotosGallery2ArrayList() == null) {
                    Toast.makeText(getApplicationContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size = response.body().getPhotosGallery2ArrayList().size();
                if (size != 0) {
                    Log.d("RESPONSE", "news Size " + size);
                    for (int i = 0; i < size; i++) {
                        gallery2ArrayList.add(response.body().getPhotosGallery2ArrayList().get(i));
                    }
                    adapter = new PhotosGalleryViewPagerAdapter(getApplicationContext(), gallery2ArrayList);
                    vpGallery.setAdapter(adapter);
                    vpGallery.setCurrentItem(position);
                }
            }

            @Override
            public void onFailure(Call<PhotosGallery2List> call, Throwable t) {

            }
        });
    }
}
