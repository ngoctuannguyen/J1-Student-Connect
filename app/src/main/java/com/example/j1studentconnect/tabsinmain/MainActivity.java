package com.example.j1studentconnect.tabsinmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.tabsinmain.TabMenuAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private TextView state;

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

        Intent intentBefore = getIntent();
        String temp_signal = "0";
        if(intentBefore.getStringExtra("signal") != null) {
            temp_signal = intentBefore.getStringExtra("signal").toString();
        }
        final String signal = temp_signal;

        state = findViewById(R.id.state_i);
        state.setText("5");
        state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (signal.equals("0")) {
                    bottomNavigationView.getMenu().findItem(R.id.tab_home).setChecked(true);
                    viewPager.setCurrentItem(0);
                } else if (signal.equals("1")) {
                    bottomNavigationView.getMenu().findItem(R.id.tab_search).setChecked(true);
                    viewPager.setCurrentItem(1);
                } else if (signal.equals("2")) {
                    bottomNavigationView.getMenu().findItem(R.id.tab_profile).setChecked(true);
                    viewPager.setCurrentItem(2);
                }
            }
        });

        state.setText(signal);

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