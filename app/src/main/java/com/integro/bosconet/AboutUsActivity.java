package com.integro.bosconet;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.integro.bosconet.customTextView.CustomTextView;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        CustomTextView tvWhoWeAre = findViewById(R.id.tvWhoWeAre);
        CustomTextView tvOurMission = findViewById(R.id.tvOurMission);
        CustomTextView tvOurVision = findViewById(R.id.tvOurVision);

        ExpandableRelativeLayout elWhoWeAre = findViewById(R.id.elWhoWeAre);
        ExpandableRelativeLayout elOurMission = findViewById(R.id.elOurMission);
        ExpandableRelativeLayout elOurVision = findViewById(R.id.elOurVision);

        tvWhoWeAre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elWhoWeAre.toggle();
                if (elWhoWeAre.isExpanded()) {
                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_down);
                    tvWhoWeAre.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                } else {
                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_up);
                    tvWhoWeAre.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                }
            }
        });

        tvOurMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elOurMission.toggle();
                if (elOurMission.isExpanded()) {
                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_down);
                    tvOurMission.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                } else {
                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_up);
                    tvOurMission.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                }
            }
        });
        tvOurVision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elOurVision.toggle();
                if (elOurVision.isExpanded()) {
                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_down);
                    tvOurVision.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                } else {
                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_up);
                    tvOurVision.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                }
            }
        });
    }
}
