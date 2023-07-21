package com.example.j1studentconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    private ImageButton btnSearchHome, btnSearchProfile;
    private RecyclerView recyclerViewSearch;
    private SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in4_search);

        //RecycleView
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSearch.setLayoutManager(linearLayoutManager);

        //SearchAdapter
        searchAdapter = new SearchAdapter(getListSearch());
        recyclerViewSearch.setAdapter(searchAdapter);

        //Button
        btnSearchHome = findViewById(R.id.greyHomeSearch);
        btnSearchProfile = findViewById(R.id.greyProfileSearch);
        btnSearchHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Search.this, MainActivity.class));
            }
        });

        btnSearchProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Search.this, Profile.class));
            }
        });
    }

    private List<StudentForSearch> getListSearch() {
        List<StudentForSearch> std_search = new ArrayList<>();
        std_search.add(new StudentForSearch("Nguyễn Tuấn Ngọc", "INT3120 50", "Tiếng Nhật trong Công nghệ thông tin", "3"));
        std_search.add(new StudentForSearch("Lương Thị Thu Hương", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Hoàng Phi Hùng", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Bùi Thị Ngọc", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Nguyễn Hữu Cứ", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        return std_search;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        
        return true;
    }
}