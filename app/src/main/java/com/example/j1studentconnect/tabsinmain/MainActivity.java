package com.example.j1studentconnect.tabsinmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.tabsinmain.TabMenuAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    //Intent intentBefore = getIntent();
    //public static String student_id_child = intentBefore.getStringExtra("student_id").toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        getTabMenuAdapter();
    }

    private void getTabMenuAdapter() {
        viewPager = findViewById(R.id.view_paper);
        bottomNavigationView = findViewById(R.id.tab_menu);

        TabMenuAdapter tabMenuAdapter = new TabMenuAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(tabMenuAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.tab_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.tab_search).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.tab_profile).setChecked(true);
                        break;
                    default:
                        bottomNavigationView.getMenu().findItem(R.id.tab_home).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.tab_home) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (item.getItemId() == R.id.tab_search) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (item.getItemId() == R.id.tab_profile) {
                    viewPager.setCurrentItem(2);
                    return true;
                }
                return false;
            }
        });

    }
}