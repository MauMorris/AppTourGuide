package com.example.mauriciogodinez.tourguideapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TourAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public TourAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        TourPageFragment fragI = new TourPageFragment();

        switch (position) {
            case 0:
                bundle.putInt("image_message", R.drawable.cdmx_1);
                bundle.putInt("title_message", R.string.title_page_1);
                bundle.putInt("subtitle_message", R.string.subtitle_page_1);
                bundle.putInt("color_message", R.color.color_page_1);
                fragI.setArguments(bundle);
                return fragI;
            case 1:
                bundle.putInt("image_message", R.drawable.cdmx_2);
                bundle.putInt("title_message", R.string.title_page_2);
                bundle.putInt("subtitle_message", R.string.subtitle_page_2);
                bundle.putInt("color_message", R.color.color_page_2);
                fragI.setArguments(bundle);
                return fragI;
            case 2:
            bundle.putInt("image_message", R.drawable.cdmx_3);
            bundle.putInt("title_message", R.string.title_page_3);
            bundle.putInt("subtitle_message", R.string.subtitle_page_3);
            bundle.putInt("color_message", R.color.color_page_3);
            fragI.setArguments(bundle);
            return fragI;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.page_1);
            case 1:
                return mContext.getString(R.string.page_2);
            case 2:
                return mContext.getString(R.string.page_3);
        }
        return null;
    }
}
