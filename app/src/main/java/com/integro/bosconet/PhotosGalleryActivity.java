package com.integro.bosconet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.integro.bosconet.adapters.PhotosGalleryAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.model.PhotosGallery1;
import com.integro.bosconet.model.PhotosGallery1List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL;

public class PhotosGalleryActivity extends AppCompatActivity {

    private ApiServices apiServices;
    private RecyclerView rvPhotosGallery;
    private StaggeredGridLayoutManager manager;
    private ArrayList<PhotosGallery1> photosGallery1ArrayList;
    private PhotosGalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_gallery);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvPhotosGallery = findViewById(R.id.rvPhotosGallery);
        manager = new StaggeredGridLayoutManager(2, VERTICAL);
        photosGallery1ArrayList = new ArrayList<>();
        getPhotosGalleryList();
    }

    public void getPhotosGalleryList() {
        String date = "2019-01-10 22:48:29";
        Call<PhotosGallery1List> photosGalleryListCall = apiServices.getPhotosGalleryList(date);
        photosGalleryListCall.enqueue(new Callback<PhotosGallery1List>() {
            @Override
            public void onResponse(Call<PhotosGallery1List> call, Response<PhotosGallery1List> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body().getPhotosGallery1ArrayList() == null) {
                    Toast.makeText(getApplicationContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size = response.body().getPhotosGallery1ArrayList().size();
                if (size != 0) {
                    Log.d("RESPONSE", "news Size " + size);
                    for (int i = 0; i < size; i++) {
                        photosGallery1ArrayList.add(response.body().getPhotosGallery1ArrayList().get(i));
                    }
                    adapter = new PhotosGalleryAdapter(getApplicationContext(), photosGallery1ArrayList);
                    rvPhotosGallery.setAdapter(adapter);
                    rvPhotosGallery.setLayoutManager(manager);
                }
            }

            @Override
            public void onFailure(Call<PhotosGallery1List> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Response "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
