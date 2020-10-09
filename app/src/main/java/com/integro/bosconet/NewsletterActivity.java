package com.integro.bosconet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.bosconet.adapters.NewsletterAdapter;
import com.integro.bosconet.apis.ApiClients;
import com.integro.bosconet.apis.ApiServices;
import com.integro.bosconet.model.Newsletter;
import com.integro.bosconet.model.NewsletterList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsletterActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvNewsletter;
    LinearLayoutManager manager;
    ArrayList<Newsletter> newsletterArrayList;
    Call<NewsletterList> newsletterListCall;
    NewsletterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvNewsletter = findViewById(R.id.rvNewsLetter);
        manager = new LinearLayoutManager(this);
        newsletterArrayList = new ArrayList<>();
        getNewsletterList();
    }

    public void getNewsletterList() {
        String date = "2019-01-10 22:48:29";
        newsletterListCall = apiServices.getNewsletterList(date);
        newsletterListCall.enqueue(new Callback<NewsletterList>() {
            @Override
            public void onResponse(Call<NewsletterList> call, Response<NewsletterList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body().getArrNewsletterList() == null) {
                    Toast.makeText(getApplicationContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size = response.body().getArrNewsletterList().size();
                if (size != 0) {
                    Log.d("RESPONSE", "Size " + size);
                    for (int i = 0; i < size; i++) {
                        newsletterArrayList.add(response.body().getArrNewsletterList().get(i));
                    }
                    adapter = new NewsletterAdapter(getApplicationContext(), newsletterArrayList);
                    rvNewsletter.setAdapter(adapter);
                    rvNewsletter.setLayoutManager(manager);
                }
            }

            @Override
            public void onFailure(Call<NewsletterList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
