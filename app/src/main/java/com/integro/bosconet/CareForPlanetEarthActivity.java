package com.integro.bosconet;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.integro.bosconet.customTextView.CustomTextView;

public class CareForPlanetEarthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_for_planet_earth);

        CustomTextView donate = findViewById(R.id.tvDonate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://donations.bosconet.in/";
                intent4.putExtra("TAG", url);
                startActivity(intent4);
            }
        });
    }
}
