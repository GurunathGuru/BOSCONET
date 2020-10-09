package com.integro.bosconet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.integro.bosconet.adapters.PhotosGalleryAdapter2;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.model.PhotosGallery2;
import com.integro.bosconet.model.PhotosGallery2List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL;

public class PhotosGalleryActivity2 extends AppCompatActivity {

    private ApiServices apiServices;
    private RecyclerView rvPhotos;
    private StaggeredGridLayoutManager manager;
    private ArrayList<PhotosGallery2> imagesArrayList;
    private PhotosGalleryAdapter2 adapter;

    private String galleryItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvPhotos = findViewById(R.id.rvPhotos);
        manager = new StaggeredGridLayoutManager(2, VERTICAL);
        imagesArrayList = new ArrayList<>();

        galleryItemId = (String) getIntent().getSerializableExtra("itemId");

        getPhotosGalleryList();
    }

    public void getPhotosGalleryList() {
        String ItemId = "" + galleryItemId;
        Call<PhotosGallery2List> imagesListCall = apiServices.getGalleryImagesList(ItemId);
        imagesListCall.enqueue(new Callback<PhotosGallery2List>() {
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
                    Log.d("RESPONSE", "PhotosGallery2 Size " + size);
                    for (int i = 0; i < size; i++) {
                        imagesArrayList.add(response.body().getPhotosGallery2ArrayList().get(i));
                    }
                    adapter = new PhotosGalleryAdapter2(getApplicationContext(), imagesArrayList, galleryItemId);
                    rvPhotos.setLayoutManager(manager);
                    rvPhotos.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<PhotosGallery2List> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Response "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
