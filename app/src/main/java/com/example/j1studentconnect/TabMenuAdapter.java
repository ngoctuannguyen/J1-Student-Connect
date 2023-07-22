package com.example.j1studentconnect;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabMenuAdapter extends FragmentStatePagerAdapter {
    public TabMenuAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TabHome();
            case 1: return new TabSearch();
            case 2: return new TabProfile();
            default: return new TabHome();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}