package com.integro.bosconet.adapters;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.integro.bosconet.R;
import com.integro.bosconet.fragments.HomeFragment;
import com.integro.bosconet.fragments.NewsFragment;
import com.integro.bosconet.fragments.NotificationFragment;
import com.integro.bosconet.fragments.WebFragment;

public class SlidePageAdapter extends FragmentPagerAdapter {

    public SlidePageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        if (i == 0) {
            fragment = new HomeFragment();
        }
        if (i == 1) {
            fragment = new NewsFragment();
        }
        if (i == 2) {
            fragment = new NotificationFragment();
        }
        if (i == 3) {
            fragment = new WebFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
