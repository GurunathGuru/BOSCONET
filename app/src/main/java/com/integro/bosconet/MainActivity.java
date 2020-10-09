package com.integro.bosconet;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.integro.bosconet.adapters.SlidePageAdapter;
import com.integro.bosconet.customTextView.CustomTextView;
import com.integro.bosconet.helpers.CustomAlertDailog;

import static com.integro.bosconet.constants.GeneralConstants.APP_UPDATE_CODE;
import static com.integro.bosconet.firebase.MyFirebaseMessagingService.NEWS_KEY;
import static com.integro.bosconet.firebase.MyFirebaseMessagingService.NOTIFICATION_KEY;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "MyFirebaseMsgService";
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentManager fragmentManager;

    //navigation menu
    CustomTextView tvAboutUs, tvNewsLetter, tvPhotoGallery, tvVideoGallery, tvContactUs, tvSuccessStories;
    ImageView ivFacebook, ivTwitter, ivYoutube, ivInstagram;
    private DialogInterface.OnClickListener postiveListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent(getApplicationContext(), WebActivity.class);
            String url = "https://play.google.com/store/apps/developer?id=Integro+Infotech&hl=en";
            intent.putExtra("TAG", url);
            startActivity(intent);
        }
    };
    private DialogInterface.OnClickListener negativeListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        AppUpdateManager updateManager = AppUpdateManagerFactory.create(getApplicationContext());

        Task<AppUpdateInfo> appUpdateInfoTask = updateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(result -> {
            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                try {
                    updateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, MainActivity.this, APP_UPDATE_CODE);
                } catch (IntentSender.SendIntentException e) {
                    new CustomAlertDailog(MainActivity.this, "Update App", postiveListener, negativeListener, "Yes", "No");
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        tvAboutUs = findViewById(R.id.tvAboutUs);
        tvNewsLetter = findViewById(R.id.tvNewsLetter);
        tvContactUs = findViewById(R.id.tvContactUs);
        tvSuccessStories = findViewById(R.id.tvSuccessStories);
        tvPhotoGallery = findViewById(R.id.tvPhotoGallery);
        tvVideoGallery = findViewById(R.id.tvVideoGallery);
        ivFacebook = findViewById(R.id.ivFb);
        ivTwitter = findViewById(R.id.ivTwitter);
        ivYoutube = findViewById(R.id.ivYoutube);
        ivInstagram = findViewById(R.id.ivInsta);

        fragmentManager = getSupportFragmentManager();
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.vpSlide);
        viewPager.setAdapter(new SlidePageAdapter(fragmentManager));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.map2);
        tabLayout.getTabAt(2).setIcon(R.drawable.notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.web);

        FirebaseMessaging.getInstance().subscribeToTopic("donbosco");

        boolean isFCMIntent = getIntent().getBooleanExtra(TAG, false);
        if (isFCMIntent) {
            String type = getIntent().getExtras().getString("type");
            switch (type) {
                case NEWS_KEY:
                    viewPager.setCurrentItem(1);
                    break;
                case NOTIFICATION_KEY:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
        final int color = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);
        final int color3 = ContextCompat.getColor(getApplicationContext(), R.color.colorYellow);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(color3, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });

        tvNewsLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewsletterActivity.class);
                startActivity(intent);
            }
        });

        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address();
            }
        });


        tvSuccessStories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuccessStoryActivity.class);
                startActivity(intent);
            }
        });

        tvPhotoGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhotosGalleryActivity.class);
                startActivity(intent);
            }
        });
        tvVideoGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getApplicationContext(), VideosGalleryActivity.class);
                startActivity(intent);*/
                Intent intent4 = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://www.youtube.com/channel/UCmcBGN0XfnAxHA2rUdBNFLg/";
                intent4.putExtra("TAG", url);
                startActivity(intent4);
            }
        });
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://www.facebook.com/BoscoNetIndia";
                intent4.putExtra("TAG", url);
                startActivity(intent4);
            }
        });
        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://twitter.com/donbosconetwork";
                intent4.putExtra("TAG", url);
                startActivity(intent4);
            }
        });

        ivYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://www.youtube.com/channel/UClzNcO0A3WmPmvaWpQyfQcg";
                intent4.putExtra("TAG", url);
                startActivity(intent4);
            }
        });
        ivInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://www.instagram.com/donbosconetwork/";
                intent4.putExtra("TAG", url);
                startActivity(intent4);
            }
        });
    }

    public void address() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        alertDialogBuilder.setView(view);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        CustomTextView tvPhone = view.findViewById(R.id.tvPhone);
        CustomTextView tvEmail = view.findViewById(R.id.tvEmail);
        CustomTextView tvMap = view.findViewById(R.id.tvMap);
        CustomTextView tvWebsite = view.findViewById(R.id.tvWebsite);

        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2 = "tel:+91 11 25390585";
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse(url2));
                startActivity(intent2);
            }
        });

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
                alertDialog.dismiss();
            }
        });

        tvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "COMING SOON", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        tvWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url3 = "https://www.bosconet.in/";
                final Intent intent3 = new Intent(MainActivity.this, WebActivity.class);
                intent3.putExtra("TAG", url3);
                startActivity(intent3);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @SuppressLint({"LongLogTag", "IntentReset"})
    public void sendEmail() {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + " " + "&body=" + " " + "&to=" + "info@bosconet.in");
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));

    }

    @Override
    public void onBackPressed() {

        new CustomAlertDailog(MainActivity.this, "Update App!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }, "Yes", "No");


        /*AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_UPDATE_CODE && requestCode != RESULT_OK) {
            new CustomAlertDailog(MainActivity.this, "Update App!", postiveListener, negativeListener, "Yes", "No");
        }
    }

    public void openPlayStore() {

    }
}
