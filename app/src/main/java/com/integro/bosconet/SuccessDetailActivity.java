package com.integro.bosconet;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.model.SuccessStory;

public class SuccessDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_detail);

        SuccessStory successStory= (SuccessStory) getIntent().getSerializableExtra("DATA");

        ImageView ivImage = findViewById(R.id.ivImage);
        CustomTextView tvTitle = findViewById(R.id.tvTitle);
        CustomTextView tvDescription = findViewById(R.id.tvDescription);
        CustomTextView tvShare = findViewById(R.id.tvShare);

        tvTitle.setText(successStory.getTitle());
        tvDescription.setText(successStory.getDescription());

        Glide.with(this)
                .load(successStory.getImage())
                .into(ivImage);

        tvShare.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View v) {
                String url="www.example.com";
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "http://bosco-net.in/sstory_share.php?id="+successStory.getId());
                startActivity(intent);
            }
        });
    }
}
