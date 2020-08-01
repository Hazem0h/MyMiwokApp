package com.example.mymiwokapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageFragmentAdapter extends FragmentPagerAdapter {

    public PageFragmentAdapter(FragmentManager mgr){
        super(mgr);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new CategoryFragment(position);
    }

    @Override
    public int getCount() {
        return MainActivity.NUM_CATEGORIES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Numbers";
            case 1:
                return "Family";
            case 2:
                return "Colors";
            case 3:
                return "Phrases";
        }
        return super.getPageTitle(position);
    }
}
