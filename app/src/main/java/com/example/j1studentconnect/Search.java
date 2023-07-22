package com.example.j1studentconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    private ImageButton btnSearchHome, btnSearchProfile;

    private List<StudentForSearch> std_search;
    private RecyclerView recyclerViewSearch;
    private SearchAdapter searchAdapter;
    private EditText searchView, searchStudentName, searchClassID, searchStudentID;
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

        //SearchViewEditText
        searchView = findViewById(R.id.viewToSearch);
        searchStudentName = findViewById(R.id.student_name_search_bar);
        searchStudentID = findViewById(R.id.student_id_search_bar);
        searchClassID = findViewById(R.id.class_id_search_bar);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        searchStudentName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchClassID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchStudentID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

    private void filter(String text) {
        List<StudentForSearch> filterList = new ArrayList<>();

        for (StudentForSearch item : std_search){
            if (item.getStudentInfoSearch().toLowerCase().contains(text.toLowerCase()))
                filterList.add(item);

            if (item.getClassIdSearch().toLowerCase().contains(text.toLowerCase()))
                filterList.add(item);

            if (item.getClassNameSearch().toLowerCase().contains(text.toLowerCase()))
                filterList.add(item);

            if (item.getClassNameSearch().toLowerCase().contains(text.toLowerCase()))
                filterList.add(item);

        }
        searchAdapter.filter_List(filterList);

    }

    private List<StudentForSearch> getListSearch() {
        std_search = new ArrayList<>();
        std_search.clear();
        std_search.add(new StudentForSearch("Nguyễn Tuấn Ngọc", "INT3120 50", "Tiếng Nhật trong Công nghệ thông tin", "3"));
        std_search.add(new StudentForSearch(    "Lương Thị Thu Hương", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Hoàng Phi Hùng", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Bùi Thị Ngọc", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Nguyễn Hữu Cứ", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        return std_search;
    }

}