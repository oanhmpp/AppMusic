package com.example.appmusic.Adapter;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrFragment = new ArrayList<>();
    private ArrayList<String> arrTitle = new ArrayList<>();

    @Deprecated
    public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

//behavior xác định trạng thái tiếp tục

    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
//hàm này không được dùng nữa
//    public MainViewPagerAdapter(FragmentManager supportFragmentManager) {
//        super(supportFragmentManager);
//    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrFragment.size();
    }

    public void addFragment (Fragment fragment, String title ){
        arrFragment.add(fragment);
        arrTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrTitle.get(position);
    }
}
